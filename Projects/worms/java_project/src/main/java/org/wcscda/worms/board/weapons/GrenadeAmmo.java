package org.wcscda.worms.board.weapons;

import org.wcscda.worms.DrawHelper;
import org.wcscda.worms.Helper;
import org.wcscda.worms.board.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;

public class GrenadeAmmo  extends AbstractAmmo{

    // Radius of the explosion which appears in a red big circle
    private static final int EXPLOSION_RADIUS = 50;
    private static final int EXPLOSION_DAMAGE = 30;
    /*It changes the size of the bullet: the one in form of rectangle   */
    private static final int GRENADE_RECT_SIZE =30;
  //  private static final int GRENADE_AMMO_RADIUS = 150;
    private static final  String imagePath = "src/resources/weapons/grenade.png";
    private static final int INITIAL_SPEED = 3;
    private static Image image = null;
    private final double initialX;
    private final double initialY;

    private static void initImages(Graphics2D g) {
        image = new ImageIcon(imagePath).getImage().getScaledInstance(20, 30, 0);

    }

    public GrenadeAmmo(Double angle) {
        super(EXPLOSION_RADIUS, EXPLOSION_DAMAGE);
        createMovableRect(GRENADE_RECT_SIZE, GRENADE_RECT_SIZE);
        getMovable().setDirection(angle);
        getMovable().setSpeed(7);
        initialX = Helper.getWormX();
        initialY = Helper.getWormY();

    }

    @Override
    public void drawMain(Graphics2D g, ImageObserver io) {
        // Show the grenade when we shoot
             g.drawImage(image, (int) initialX, (int) initialY, (int) getMovable().getCenterX(), (int) getMovable().getCenterY(), io);
        }

        // Create the movement of the Grenade

    public static void setImage(Image image) {
        GrenadeAmmo.image = image;
    }

    @Override
    protected void createMovableRect(int rectWidth, int rectHeight) {
        setMovable(new ARBEHandlerGravity(
                Helper.getWormX() - rectWidth / 2,
                Helper.getWormY() - rectHeight / 2,
               10,
               10,
                this));
    }

    /*@Override
        protected void createMovableRect(int rectWidth, int rectHeight) {
            AbstractAmmo aa = this;
            this.movable =
                    new ARBEWithGravity(Helper.getWormX()+20,Helper.getWormY()-100,10,10) {
                        @Override
                        public void collideWith(AbstractBoardElement movable, Point2D prevPosition) {
                            aa.colideWith(movable, prevPosition);
                        }

                        @Override
                        protected void drawMain(Graphics2D g, ImageObserver io) {

                        }
                    };

        }*/

}


