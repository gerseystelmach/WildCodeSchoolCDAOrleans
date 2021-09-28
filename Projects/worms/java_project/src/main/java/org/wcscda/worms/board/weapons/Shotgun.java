package org.wcscda.worms.board.weapons;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import org.wcscda.worms.Player;
import org.wcscda.worms.Worm;
import org.wcscda.worms.gamemechanism.phases.AbstractPhase;
import org.wcscda.worms.gamemechanism.phases.FirstShootMovingPhase;
import org.wcscda.worms.gamemechanism.phases.MovingPhase;

public class Shotgun extends AbstractWeapon {
  private static final String imagePath = "src/resources/weapons/Shotgun_small.png";
  private static Image image = null;
  private int nbFiredShoots = 0;

  private static void initImages() {
    image = new ImageIcon(imagePath).getImage().getScaledInstance(50, 30, 0);
  }

  @Override
  public void draw(Graphics2D g, ImageObserver io, double x, double y) {
    if (image == null) {
      initImages();
    }

    if (getAngle() > Math.PI / 2) {
      AffineTransform trans = AffineTransform.getTranslateInstance(x + 100, y);
      trans.scale(-1, 1);

      g.drawImage(image, trans, io);
    } else {
      g.drawImage(image, (int) x, (int) y, io);
    }
  }

  @Override
  public void fire(Worm firingWorm, double x, double y) {
    new ShotgunAmmo(firingWorm, x, y, getAngle());
  }

  public AbstractPhase getNextPhase(Player player) {
    if (nbFiredShoots == 0) {
      nbFiredShoots++;
      return new FirstShootMovingPhase(player);
    } else {
      return new MovingPhase(player);
    }
  }
}
