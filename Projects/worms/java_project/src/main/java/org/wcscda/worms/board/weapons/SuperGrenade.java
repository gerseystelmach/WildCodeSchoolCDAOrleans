package org.wcscda.worms.board.weapons;

import org.wcscda.worms.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

// NRO 2021-10-05 BAD : Nice ! 
public class SuperGrenade extends AbstractWeapon {

    private static final int superGrenadeRadius = 80;
    private static final String imagePath = "src/resources/weapons/grenadefruit.png";
    private static Image image = null;

    private static void initImages() {
        image = new ImageIcon(imagePath).getImage().getScaledInstance(50, 50, 0);
    }

    public static Image getImage() {
        return image;
    }

    @Override
    public Image getImage2() {
        if (image == null)
        {initImages();}
        return getImage();
    }

    @Override
    public void draw(Graphics2D g, ImageObserver io) {
        if (image == null) {
            initImages();
        }

        // NRO 2021-10-05 BAD : DRY ! (Don't Repeat Yourself) 
        //  Also you have this code in several weapon, can you think of
        //  a way to put it in common
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
