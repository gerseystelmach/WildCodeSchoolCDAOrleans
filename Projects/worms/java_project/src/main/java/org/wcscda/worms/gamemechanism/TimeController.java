package org.wcscda.worms.gamemechanism;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.util.*;
import java.util.List;
import javax.swing.Timer;

import org.wcscda.worms.Config;
import org.wcscda.worms.Player;
import org.wcscda.worms.Worm;
import org.wcscda.worms.gamemechanism.phases.AbstractPhase;
import org.wcscda.worms.gamemechanism.phases.WormMovingPhase;

public class TimeController implements ActionListener {
  private static TimeController instance;
  private PhysicalController board;
  private Timer timer;
  private ArrayList<Player> players = new ArrayList<Player>();
  private int activePlayerIndex = 0;
  private AbstractPhase currentPhase;
  private int phaseCount = 0;
  private int playerQuantity = 0;
  private int wormQuantity = 0;
  private String playerWinner;

  public TimeController() {
    initGame(3, 1);

    board.addKeyListener(new KeyboardController());
    timer = new Timer(Config.getClockDelay(), this);
    timer.start();

  }

  private void initGame(int playerQuantity, int wormQuantity) {
    board = new PhysicalController();
    if (playerQuantity > 8 || wormQuantity < 1) {
      playerQuantity = 8;
      wormQuantity = 1;
    }
    setPlayerQuantity(playerQuantity);
    setWormQuantity(wormQuantity);

    /* Array with colors for each player */
    Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.GRAY, Color.WHITE, Color.PINK};
    // Requirement 1: Create number of players
    for (int i = 0; i < playerQuantity; i++) {
      String playerName = "Player" + i;
      Player luckyLuke = createPlayer(playerName, colors[i]);
      for (int j = 0; j < wormQuantity; j++) {
        String wormPlayer = "Worm" + i;
        Worm worm = luckyLuke.createWorm(wormPlayer);
        board.wormInitialPlacement(worm);
      }
    }
    setNextWorm();
}

  public int getWormQuantity() {
    return wormQuantity;
  }


  public String getWinner() {
    List<Player> losers = new ArrayList<>();
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
      System.out.println(getPlayerWinner());
    }
    return getPlayerWinner();

  }

/*
  protected void drawMain(Graphics2D g, ImageObserver io) {
    g.drawString("asdasd" ,  60, 60 - 15);
  }*/

  public void setWormQuantity(int wormQuantity) {
    this.wormQuantity = wormQuantity;
  }

  public void setNextWorm() {
    activePlayerIndex += 1;
    activePlayerIndex %= players.size();

    getWinner();

    AbstractPhase phase = new WormMovingPhase(getActivePlayer().getNextWorm());
      this.setCurrentPhase(phase);

  }

  public int getPlayerQuantity() {
    return playerQuantity;
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
    boolean inGame = board.actionPerformed(e);
    if (!inGame) {
      timer.stop();
    }

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
