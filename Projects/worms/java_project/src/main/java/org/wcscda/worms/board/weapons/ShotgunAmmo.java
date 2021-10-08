package org.wcscda.worms.board.weapons;

import org.wcscda.worms.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

public class ShotgunAmmo extends AbstractAmmo {
    private static final int EXPLOSION_RADIUS = 30;
    private static final int EXPLOSION_DAMAGE = 15;
    private static final int HADOKEN_RECT_SIZE = 1;
    private static final String imagePath = "src/resources/weapons/bulletshotgun.png";
    private static Image image = null;

    public ShotgunAmmo(Double angle) {
        super(EXPLOSION_RADIUS, EXPLOSION_DAMAGE);
        createMovableRect(HADOKEN_RECT_SIZE, HADOKEN_RECT_SIZE);
        getMovable().setDirection(angle);
        getMovable().setSpeed(5);
        setInitialPosition();
    }

    private static void initImages() {
        image = new ImageIcon(imagePath).getImage().getScaledInstance(30, 20, 0);
    }

    @Override
    public void drawMain(Graphics2D g, ImageObserver io) {

        if (image == null) {
            initImages();
        }

        if (Helper.getActiveWorm().getDirection() < Math.PI / 2) {
            AffineTransform trans =
                    AffineTransform.getTranslateInstance(getMovable().getCenterX(), getMovable().getCenterY());
            trans.scale(1, 1);

            g.drawImage(image, trans, io);
        } else {
            AffineTransform trans =
                    AffineTransform.getTranslateInstance(getMovable().getCenterX(), getMovable().getCenterY() - 14);
            trans.scale(-1, 1);

            g.drawImage(image, trans, io);
        }

    }
}
