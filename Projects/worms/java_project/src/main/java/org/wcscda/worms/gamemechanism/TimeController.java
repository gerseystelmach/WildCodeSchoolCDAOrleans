package org.wcscda.worms.gamemechanism;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.Timer;

import org.wcscda.worms.Config;
import org.wcscda.worms.Player;
import org.wcscda.worms.Worm;
import org.wcscda.worms.gamemechanism.phases.AbstractPhase;
import org.wcscda.worms.gamemechanism.phases.WormMovingPhase;
import org.wcscda.worms.gamemechanism.playerrecorder.KeyboardControllerPlayer;
import org.wcscda.worms.gamemechanism.playerrecorder.KeyboardControllerRecorder;

public class TimeController implements ActionListener {
  private static TimeController instance;

  public KeyboardController getKeyboardController() {
    return keyboardController;
  }

  private final KeyboardController keyboardController;
  private PhysicalController board;
  private Timer timer;
  private ArrayList<Player> players = new ArrayList<Player>();
  private int activePlayerIndex = 0;
  private AbstractPhase currentPhase;
  private int phaseCount = 0;
  private int playerQuantity = 0;
  private int wormQuantity = 0;
  private String playerWinner;
  private boolean delayedSetNextWorm;
  private ScriptPlayer scriptPlayer;

  public ScriptPlayer getScriptPlayer() {
    return scriptPlayer;
  }


  public TimeController() {

    instance = this;
    initGame(3, 1);
    keyboardController = createController();
    board.addKeyListener(keyboardController);
    timer = new Timer(Config.getClockDelay(), this);
    timer.start();

  }

  private KeyboardController createController() {
    if (Config.getRecordGame()) {
      return new KeyboardControllerRecorder(this.board);
    } else if (Config.getPlayRecord()) {
      return new KeyboardControllerPlayer();
    } else {
      return new KeyboardController();
    }
  }

  private void initGame(int playerQuantity, int wormQuantity) {

    board = new PhysicalController();
    setPlayerQuantity(playerQuantity);
    setWormQuantity(wormQuantity);

    /* Array with colors for each player */
    Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.GRAY, Color.ORANGE, Color.PINK};
    // Requirement 1: Create number of players
    for (int i = 0; i < playerQuantity; i++) {
      String playerName = "Player " + i;
      Player luckyLuke = createPlayer(playerName, colors[i]);
      for (int j = 0; j < wormQuantity; j++) {
        String wormPlayer = "Worm " + i;
        Worm worm = luckyLuke.createWorm(wormPlayer);
        worm.addWeapons();
        board.wormInitialPlacement(worm);
      }
    }

    /*if(Config.getScriptFilename() != null) {
      scriptPlayer = new ScriptPlayer(Config.getScriptFilename());
    }*/
    doSetNextWorm();

   ArrayList<Player> groupPlayers = getPlayers();
    Random randomGenerator = new Random();

    if (groupPlayers.size() > 0) {
      int playerIndex = randomGenerator.nextInt(groupPlayers.size());
      groupPlayers.get(playerIndex).setBeginnerLevel(true);
      System.out.println("begginer" + playerIndex);
    }
}

  public String getWinner() {
    List<Player> losers = new ArrayList<>();
    // NRO 2021-10-05 NOT-NICE : winner is confusing here,
    //  stillPlaying would be more accurate (for example)
    List<Player> winner = new ArrayList<>();

    for(Player player: getPlayers()) {
      if ( player.getWorms().size() == 0) {
        losers.add(player);
      } else {
        winner.add(player);
      }
    }
    if (winner.size() == 1) {
      setPlayerWinner(winner.get(0).getName());
    }
    return getPlayerWinner();

  }

  public void setWormQuantity(int wormQuantity) {
    this.wormQuantity = wormQuantity;
  }

  public void setNextWorm() {
    delayedSetNextWorm = true;
  }

  protected void delayedActions() {
    if (delayedSetNextWorm) {
      delayedSetNextWorm = false;
      doSetNextWorm();
    }
  }

  protected void doSetNextWorm() {
    for (int i = 0; i < players.size(); ++i) {
      activePlayerIndex += 1;
      activePlayerIndex %= players.size();
      if (getActivePlayer().hasWorms()) break;
    }

    // No player have any worm, it is sad ...
    if (!getActivePlayer().hasWorms()) {
      return;
    }

    getActivePlayer().setNextWorm();
    getActivePlayer().initWeapon();

    AbstractPhase phase = new WormMovingPhase();
    this.setCurrentPhase(phase);

    getWinner();

  }

  public void setPlayerQuantity(int playerQuantity) {
    this.playerQuantity = playerQuantity;

  }

  private Player createPlayer(String name, Color color) {
    Player player = new Player(name, color);
    players.add(player);

    return player;
  }

  public Component getBoard() {
    return board;
  }

  /* Loop for all phases of the game */
  @Override
  public void actionPerformed(ActionEvent e) {
    phaseCount++;
    board.actionPerformed(e);
  }

  public static TimeController getInstance() {
    if (instance == null) {
      instance = new TimeController();
    }
    return instance;
  }

  public AbstractPhase getCurrentPhase() {
    return currentPhase;
  }

  public void setCurrentPhase(AbstractPhase currentPhase) {
    if ((this.currentPhase != null) && this.currentPhase != currentPhase) {
      this.currentPhase.removeSelf();
    }
    this.currentPhase = currentPhase;
  }

  public ArrayList<Player> getPlayers() {
    return players;
  }

  public int getPhaseCount() {
    return phaseCount;
  }

  public void setPhaseCount(int phaseCount) {
    this.phaseCount = phaseCount;
  }

  public Player getActivePlayer() {
    return players.get(activePlayerIndex);
  }

  public String getPlayerWinner() {
    return playerWinner;
  }

  public void setPlayerWinner(String playerWinner) {
    this.playerWinner = playerWinner;
  }
}
