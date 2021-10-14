package org.wcscda.worms.board.weapons;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import org.wcscda.worms.*;
import org.wcscda.worms.gamemechanism.phases.*;

public class Shotgun extends AbstractWeapon {
  private static final String imagePath = "src/resources/weapons/Shotgun_small.png";
  private static Image image = null;
  private int nbFiredShoots = 0;

  private static void initImages() {
    image = new ImageIcon(imagePath).getImage().getScaledInstance(50, 30, 0);
  }

  public static Image getImage() {
    return image;
  }

  public int getNbFiredShoots() {
    return nbFiredShoots;
  }

  @Override
  public Image getImage2() {
    return getImage();
  }

  @Override
  public void draw(Graphics2D g, ImageObserver io) {
    if (image == null) {
      initImages();
    }
    g.setStroke(new BasicStroke(10));
    /* Code to choose condition for each side. If > is right, otherwise is left. We can change the image in accordance to the side.  */
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
  }


  public AbstractPhase getNextPhase() {
    nbFiredShoots++;
    return new MovingPhase();
  }


  public void triggerAmmoExplosion() {
    if (nbFiredShoots == 2) {
      super.triggerAmmoExplosion();
    } else {
      Helper.getTC().setCurrentPhase(new WormMovingPhase());
    }
  }

  public boolean isChangingWeaponDisabled() {
    return nbFiredShoots != 0;
  }
}