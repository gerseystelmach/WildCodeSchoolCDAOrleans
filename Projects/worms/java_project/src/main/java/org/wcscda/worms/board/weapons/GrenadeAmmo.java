package org.wcscda.worms.board.weapons;

import org.wcscda.worms.DrawHelper;
import org.wcscda.worms.Helper;
import org.wcscda.worms.board.AbstractRectangularBoardElement;
import org.wcscda.worms.board.Explosion;
import org.wcscda.worms.board.IMovableHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.ImageObserver;


public class GrenadeAmmo  extends AbstractAmmo implements IMovableHandler {
    private static final int EXPLOSION_RADIUS = 100;
    private static final int EXPLOSION_DAMAGE = 15;
    /*It changes the size of the bullet */
    private static final int GRENADE_RECT_SIZE = 10;
    private static final String imagePath = "src/resources/weapons/grenade.png";
    private static final int INITIAL_SPEED = 1;
    private static Image image = null;
    private AbstractRectangularBoardElement movable;


    private static void initImages() {
        image = new ImageIcon(imagePath).getImage().getScaledInstance(50, 30, 0);
    }


    public GrenadeAmmo(Double angle) {
        super(EXPLOSION_RADIUS, EXPLOSION_DAMAGE);
        createMovableRect(GRENADE_RECT_SIZE, GRENADE_RECT_SIZE);
        getMovable().setDirection(angle);
        getMovable().setSpeed(INITIAL_SPEED);

     }

    @Override
    protected void explode() {
        super.explode();
      }

    @Override
    public void drawMain(Graphics2D g, ImageObserver io) {

        if (image == null) {
            initImages();
        }

        AffineTransform trans =
                AffineTransform.getTranslateInstance(getMovable().getCenterX() + 35, getMovable().getCenterY() - 20);
        trans.scale(-1, 1);

        g.drawImage(image, trans, io);

    }
}


