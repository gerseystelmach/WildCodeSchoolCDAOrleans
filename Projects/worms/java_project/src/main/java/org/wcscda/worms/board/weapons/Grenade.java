package org.wcscda.worms.board.weapons;

import org.wcscda.worms.Helper;
import org.wcscda.worms.gamemechanism.phases.WormMovingPhase;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.ImageObserver;


public class Grenade extends AbstractWeapon {

    private static final String imagePath = "src/resources/weapons/grenade.png";
     private static Image image = null;
    private static final int grenadeRadius = 50;


    private static void initImages() {
        image = new ImageIcon(imagePath).getImage().getScaledInstance(50, 30, 0);
    }

    @Override
    public void draw(Graphics2D g, ImageObserver io) {
        if (image == null) {
            initImages();
        }

        if (getAngle() < Math.PI / 2) {
            AffineTransform trans =
                    AffineTransform.getTranslateInstance(Helper.getWormX() + 50, Helper.getWormY());
            trans.scale(-1, 1);

            g.drawImage(image, trans, io);
        } else {
            AffineTransform trans =
                    AffineTransform.getTranslateInstance(Helper.getWormX() - 50, Helper.getWormY());
            trans.scale(1, 1);

            g.drawImage(image, trans, io);
        }

        g.setStroke(new BasicStroke(10));

    }

}
