package org.wcscda.worms.board.weapons;

import org.wcscda.worms.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class GrenadeAmmo  extends AbstractAmmo  {
    private static final int EXPLOSION_RADIUS = 30;
    private static final int EXPLOSION_DAMAGE = 15;
    /*It changes the size of the bullet */
    private static final int GRENADE_RECT_SIZE = 1;
    private static final int GRENADE_AMMO_RADIUS = 15;
    private static final  String imagePath = "src/resources/weapons/grenade.png";
    private static final int INITIAL_SPEED = 3;
    private static Image image = null;
    private final double initialX;
    private final double initialY;

    private static void initImages() {
        image = new ImageIcon(imagePath).getImage().getScaledInstance(20, 30, 0);
    }

    public GrenadeAmmo(Double angle) {
        super(EXPLOSION_RADIUS, EXPLOSION_DAMAGE);
        createMovableRect(GRENADE_RECT_SIZE, GRENADE_RECT_SIZE);
        getMovable().setDirection(angle);
        getMovable().setSpeed(INITIAL_SPEED);

        initialX = Helper.getWormX();
        initialY = Helper.getWormY();
    }

    @Override
    public void drawMain(Graphics2D g, ImageObserver io) {
        if (image == null) {
            initImages();
        }
        // Show the grenade when we shoot
        g.drawImage(image, (int) initialX, (int) initialY, (int) getMovable().getCenterX(), (int) getMovable().getCenterY(), io);
    }
}
