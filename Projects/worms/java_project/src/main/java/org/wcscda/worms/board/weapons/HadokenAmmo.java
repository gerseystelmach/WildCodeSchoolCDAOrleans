package org.wcscda.worms.board.weapons;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import org.wcscda.worms.Helper;
import javax.swing.*;

public class HadokenAmmo extends AbstractAmmo {
  private static final int HADOKEN_AMMO_RADIUS = 15;
  private static final int HADOKEN_RECT_SIZE = 10;
  private static final int EXPLOSION_RADIUS = 100;
  private static final int EXPLOSION_DAMAGE = 30;
  private static final int INITIAL_SPEED = 3;
  private static final String imagePath = "src/resources/weapons/hadoken.png";
  private static Image image = null;

  private final double initialX;
  private final double initialY;

  public HadokenAmmo(Double angle) {
    super(EXPLOSION_RADIUS, EXPLOSION_DAMAGE);
    createMovableRect(HADOKEN_RECT_SIZE, HADOKEN_RECT_SIZE);
    getMovable().setDirection(angle);
    getMovable().setSpeed(INITIAL_SPEED);
    setInitialPosition();


    initialX = Helper.getWormX();
    initialY = Helper.getWormY();

    setInitialPosition();
  }

  private static void initImages() {
    image = new ImageIcon(imagePath).getImage().getScaledInstance(60, 60, 0);
  }


  @Override
  public void drawMain(Graphics2D g, ImageObserver io) {

    if (image == null) {
      initImages();
    }
    g.setColor(Color.cyan);

    g.setStroke(new BasicStroke(10));
    g.drawLine(
            (int) initialX,
            (int) initialY,
            (int) getMovable().getCenterX(),
            (int) getMovable().getCenterY());

    if (Helper.getActiveWorm().getDirection() < Math.PI / 2) {
     /* System.out.println("right");*/
      AffineTransform trans =

              AffineTransform.getTranslateInstance(getMovable().getCenterX() - 8, getMovable().getCenterY() - 33);
      trans.scale(1, 1);

      g.drawImage(image, trans, io);
    } else {
      /*System.out.println("left");*/
      AffineTransform trans =
              AffineTransform.getTranslateInstance(getMovable().getCenterX() + 25, getMovable().getCenterY() - 35);
      trans.scale(-1, 1);

      g.drawImage(image, trans, io);
    }



  }
}
