package org.wcscda.worms.board.weapons;


import org.wcscda.worms.Helper;
import org.wcscda.worms.board.AbstractRectangularBoardElement;
import org.wcscda.worms.board.Explosion;
import org.wcscda.worms.board.IMovableHandler;
import org.wcscda.worms.board.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

public class GrenadeAmmo  extends AbstractAmmo {

    // Radius of the explosion which appears in a red big circle
    private static final int EXPLOSION_RADIUS = 50;
    private static final int EXPLOSION_DAMAGE = 20;
    /*It changes the size of the bullet: the one in form of rectangle   */
    private static final int GRENADE_RECT_SIZE =30;
  //  private static final int GRENADE_AMMO_RADIUS = 150;
    private static final  String imagePath = "src/resources/weapons/grenade.png";
    private static final int INITIAL_SPEED = 5;
    private static Image image = null;

    private static void initImages() {
        image = new ImageIcon(imagePath).getImage().getScaledInstance(30, 30, 0);
    }

    public GrenadeAmmo(Double angle) {
        super(EXPLOSION_RADIUS, EXPLOSION_DAMAGE);
        createMovableRect(GRENADE_RECT_SIZE, GRENADE_RECT_SIZE);
        getMovable().setDirection(angle);
        getMovable().setSpeed(INITIAL_SPEED);
        setInitialPosition();
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

    @Override
    public void drawMain(Graphics2D g, ImageObserver io) {
        if (image == null) {
            initImages();
        }
        g.drawImage(image, (int) (getMovable().getCenterX() - 20), (int) (getMovable().getCenterY() - 20), io);
    }

}


