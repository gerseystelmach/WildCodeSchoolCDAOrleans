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
        image = new ImageIcon(imagePath).getImage().getScaledInstance(30, 30, 0);
    }

    public static Image getImage() {
        return image;
    }


    @Override
    public void draw(Graphics2D g, ImageObserver io) {
        if (image == null) {
            initImages();
        }

        if (getAngle() < Math.PI / 2) {
            // NRO 2021-10-05 BAD : DRY ! (Don't Repeat Yourself) 
            //  In that case you can easily put this code and the following one in 
            //  the same method,just passing a value of -1 or 1 to set the scale 
            //  and (-/+) 50
            AffineTransform trans =
                    AffineTransform.getTranslateInstance(Helper.getWormX() + 50, Helper.getWormY());
            trans.scale(-1, 1);

            g.drawImage(image, trans, io);
        } else {
            // NRO 2021-10-05 BAD : DRY ! (Don't Repeat Yourself) 
            AffineTransform trans =
                    AffineTransform.getTranslateInstance(Helper.getWormX() - 50, Helper.getWormY());
            trans.scale(1, 1);

            g.drawImage(image, trans, io);
        }

        g.setStroke(new BasicStroke(10));


    }

}
