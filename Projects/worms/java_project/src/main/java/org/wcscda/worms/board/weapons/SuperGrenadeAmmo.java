package org.wcscda.worms.board.weapons;

import org.wcscda.worms.Helper;
import org.wcscda.worms.board.ARBEHandlerGravity;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

public class SuperGrenadeAmmo extends AbstractAmmo {

    private static final int EXPLOSION_RADIUS = 150;
    private static final int EXPLOSION_DAMAGE = 30;
    /*It changes the size of the bullet */
    private static final int GRENADE_RECT_SIZE = 10;
    private static final int INITIAL_SPEED = 3;
    private static final String imagePath = "src/resources/weapons/grenadefruit.png";
    private static Image image = null;

    private static void initImages() {
        image = new ImageIcon(imagePath).getImage().getScaledInstance(50, 50, 0);
    }

    public SuperGrenadeAmmo(Double angle) {
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
                rectWidth,
                rectHeight,
                this));

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
