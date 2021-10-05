package org.wcscda.worms.board.weapons;

import org.wcscda.worms.Helper;
import org.wcscda.worms.board.ARBEHandlerGravity;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class SuperGrenadeAmmo extends AbstractAmmo {

    private static final int EXPLOSION_RADIUS = 150;
    private static final int EXPLOSION_DAMAGE = 18;
    /*It changes the size of the bullet */
    private static final int GRENADE_RECT_SIZE = 10;
    private static final int INITIAL_SPEED = 3;
    private static final String imagePath = "src/resources/weapons/grenadefruit.png";
    private static Image image = null;

    private static void initImages() {
        image = new ImageIcon(imagePath).getImage().getScaledInstance(50, 30, 0);
    }

    public SuperGrenadeAmmo(Double angle) {
        super(EXPLOSION_RADIUS, EXPLOSION_DAMAGE);
        createMovableRect(GRENADE_RECT_SIZE, GRENADE_RECT_SIZE);
        getMovable().setDirection(angle);
        getMovable().setSpeed(INITIAL_SPEED);

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
    }
}
