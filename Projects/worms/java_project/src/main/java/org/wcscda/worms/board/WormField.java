package org.wcscda.worms.board;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.image.ImageObserver;

import org.wcscda.worms.Helper;
import org.wcscda.worms.RandomGenerator;

import javax.swing.*;

public class WormField extends AbstractBoardElement {
  private Area frontier;
  private static final String imagePath = "src/resources/BGGAME.jpg";

  public WormField() {}

  public WormField(int width, int height) {
    initRandomSpline(width, height);
  }

  private static Image image = null;

  private static void initImages() {
    image = new ImageIcon(imagePath).getImage().getScaledInstance(1400, 1000, 0);
  }

  private void initRandomSpline(int width, int height) {
    int nbSegments = 10;
    int nbSplines = 2 * nbSegments + 1;

    int[] randomSplineHeight = new int[nbSplines];

    for (int i = 0; i < nbSplines; ++i) {
      randomSplineHeight[i] = (int) ((0.25 + 0.5 * RandomGenerator.nextDouble()) * height);
    }

    Path2D p = new Path2D.Double();

    p.moveTo(0, randomSplineHeight[0]);

    for (int i = 0; i < nbSegments; ++i) {

      p.quadTo(
          (double) width * (2 * i + 1) / 20,
          randomSplineHeight[2 * i + 1],
          (double) width * (2 * i + 2) / 20,
          randomSplineHeight[2 * i + 2]);
    }

    p.lineTo(width, height);
    p.lineTo(0, height);
    p.lineTo(0, randomSplineHeight[0]);
    p.closePath();

    frontier = new Area(p);
  }


  @Override
  public void drawMain(Graphics2D g, ImageObserver io) {

    if (image == null) {
      initImages();
    }
    // NRO 2021-10-14 : The PC is the whole screen, so
    // 0, 0 would have been fine
    // But well, great idea :-)
    g.drawImage(image, Helper.getPC().getX(), Helper.getPC().getY(), io);

    g.setColor(Color.darkGray);
    g.fill(frontier);






  }

  public Area getFrontier() {
    return frontier;
  }

  public Shape getShape() {
      return frontier;
  }

  public void doExplosionOnField(Shape explosionShape) {
    frontier.subtract(new Area(explosionShape));
  }

  protected Integer getDepth() {
    return -1;
  }
}
