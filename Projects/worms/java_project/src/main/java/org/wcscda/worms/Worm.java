package org.wcscda.worms;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

import org.wcscda.worms.board.*;
import org.wcscda.worms.board.weapons.*;
import org.wcscda.worms.gamemechanism.Board;

public class Worm extends ARBEWithGravity implements IVisitable {
  private static final String leftFacingResource = "src/resources/WormLF.png";
  private static final String rightFacingResource = "src/resources/WormRF.png";
  private static final String  trophy = "src/resources/weapons/trophy.png";
  private static final int imageHeight = 60;
  private static final int imageWidth = 54;
  private static final int rectPadding = 15;

  private static Image wormLF = null;
  private static Image wormRF = null;
  private static Image trophyWinner = null;

  private int shownLife = 100;
  private int life = 100;
  private final String name;
  private final Player player;
  private boolean isUserMoving;
  private Inventory inventory;
  private final ArrayList<AbstractWeapon> weapons = new ArrayList<>();


  private static void initImages() {
    wormLF = new ImageIcon(leftFacingResource).getImage().getScaledInstance(imageWidth, imageHeight, 0);
    wormRF = new ImageIcon(rightFacingResource).getImage().getScaledInstance(imageWidth, imageHeight, 0);
    trophyWinner = new ImageIcon(trophy).getImage().getScaledInstance(imageWidth, imageHeight, 0);

  }

  // NRO 2021-09-28 : Player is the Worm factory
  protected Worm(Player player, String name) {
    this(player, name, getRandomStartingX(), getRandomStartingY());

  }

  public Inventory getInventory() {
    return inventory;
  }

  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }

  // Idem
  protected Worm(Player player, String name, int x, int y) {
    super(x, y, imageWidth - 2 * rectPadding, imageHeight - 2 * rectPadding);

    this.player = player;
    this.name = name;
    this.inventory = new Inventory(this);


  }

  public void addWeapons() {

    this.weapons.add(new Shotgun());
    this.weapons.add(new Hadoken());
    this.weapons.add(new Grenade());
    this.weapons.add(new SuperGrenade());
    this.weapons.add(new GrenadeTimer());

  }

  public ArrayList<AbstractWeapon> getWeapons() {
    return weapons;
  }

  private static int getRandomStartingX() {
    return RandomGenerator.getInstance().nextInt(Board.getBWIDTH() - imageWidth);
  }

  private static int getRandomStartingY() {
    return RandomGenerator.getInstance().nextInt(Board.getBHEIGHT() - imageHeight);
  }

  // NRO 2021-10-05 BAD : This method is too long ! The fact that methods
  //  must held in one screen (let say about 25 lines long) is a golden rule !
  @Override
  protected void drawMain(Graphics2D g, ImageObserver io) {
    if (wormLF == null) initImages();
    Image worm = isRightFacing() ? wormRF : wormLF;

    g.drawImage(worm, getX() - rectPadding, getY() - rectPadding, io);
    g.setColor(player.getColor());

    // Drawing the Player name
    g.drawString(player.getName(), (int) getX() - 10, (int) getY() - 55);
 // Drawing the worm name
    g.drawString(getName(), (int) getX() - 10, (int) getY() - 35);

    // NRO 2021-10-05 NOT-NICE : in that case, creating a method
    //  drawLife would be better (nicer to read) than adding a comment
    // Drawing the life
    g.drawString("" + life, (int) getX(), (int) getY() - 15);
    g.drawString("" + getShownLife(), (int) getX(), (int) getY() - 15);

/*To show the dammage bonus for begginers */
    for (Player player : Helper.getTC().getPlayers()) {
      if (player.isBeginnerLevel()) {
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("(+25% Damage)", (int) player.getActiveWorm().getCenterX() - 50, (int) player.getActiveWorm().getCenterY() - 90);
      }
    }


/* To display message of the winner */
    if (Helper.getTC().getWinner() != null) {
      g.drawImage(trophyWinner, getX() - 50, getY() - 40, io);
      Font myFont = new Font("Serif", Font.BOLD, 40);
      g.setFont(myFont);
      g.setColor(player.getColor());

      // NRO 2021-10-05 BAD : DRY ! (Don't Repeat Yourself)
      g.drawString( Helper.getTC().getWinner(), 550, 60);
      g.drawString("Congratulations! You are the winner!", 200, 120);

    if (Helper.getClock() % 50 < 20) {
      g.setColor(Color.WHITE);
      // NRO 2021-10-05 BAD : DRY ! (Don't Repeat Yourself)
      g.drawString( Helper.getTC().getWinner(), 550, 60);
      g.drawString("Congratulations! You are the winner!", 200, 120);
      }
    }

  }

  private int getShownLife() {

    if (life < shownLife) {
      shownLife--;
    } else if (life > shownLife) {
      shownLife++;
    }
    return this.shownLife;
  }

  private boolean isRightFacing() {
    return Math.abs(getDirection()) < 1e-6;
  }

  public Player getPlayer() {
    return player;
  }

  public boolean isUserMoving() {
    return isUserMoving;
  }

  public void setUserMoving(boolean isUserMoving) {
    this.isUserMoving = isUserMoving;
  }

  @Override
  public void collideWith(AbstractBoardElement movable, Point2D prevPosition) {
    setPosition(prevPosition);
  }

  @Override
  public String toString() {
    return "Worm " + this.getName() + " / player : " + this.getPlayer();
  }

  public int getLife() {
    return life;
  }

  public String getName() {
    return name;
  }

  @Override
  public void takeDamage(int damage) {
if (Helper.getTC().getActivePlayer().isBeginnerLevel()) {
     damage = (int) (damage * 1.25);
  }

   life -= damage;

    if (life <= 0) {
        life = 0;
      die();
    }
  }

  public void die() {
    removeSelf();
  }

  @Override
  protected void onRemoval() {
    player.getWorms().remove(this);
  }

  @Override
  public void accept(Point2D prevPosition, IMovableVisitor visitor) {
    visitor.visit(this, prevPosition);
  }
}
