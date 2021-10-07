package org.wcscda.worms.board.weapons;

import java.awt.*;
import java.awt.geom.Ellipse2D;
// import java.awt.geom.Ellipse2D;
import java.awt.image.ImageObserver;
import org.wcscda.worms.Helper;

import javax.swing.*;

public class Hadoken extends AbstractWeapon {
  private static final int hadokenRadius = 50;
  private static final String imagePath = "src/resources/weapons/hadoken.png";
  private static Image image = null;

  private static void initImages() {
    image = new ImageIcon(imagePath).getImage().getScaledInstance(40, 30, 0);
  }

  public static Image getImage() {
    return image;
  }

  @Override
  public Image getImage2() {
    if (image == null) {
      initImages();
    }
    return getImage();
  }

  @Override
  public void draw(Graphics2D g, ImageObserver io) {
    // Draw an ellipse
    Ellipse2D circle =
        new Ellipse2D.Double(
            Helper.getWormX() - hadokenRadius,
            Helper.getWormY() - hadokenRadius,
            2 * hadokenRadius,
            2 * hadokenRadius);

    g.setColor(Color.BLUE);
    g.setStroke(new BasicStroke(10));
    g.draw(circle);
  }
}