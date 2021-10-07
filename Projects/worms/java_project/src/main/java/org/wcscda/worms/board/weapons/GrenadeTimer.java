package org.wcscda.worms.board.weapons;

import org.wcscda.worms.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

// NRO 2021-10-05 NOT-NICE : You could avoid so much copy-paste by inheriting
//  of Grenade and just overriding what you need to 
public class GrenadeTimer extends AbstractWeapon {

    private static final String imagePath = "src/resources/weapons/grenadetimer.png";
    private static Image image = null;
    private static final int grenadeRadius = 50;

    private static void initImages() {
        image = new ImageIcon(imagePath).getImage().getScaledInstance(60, 30, 0);
    }

    public String getWeaponName() {
        return "Grenade with Timer";
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
