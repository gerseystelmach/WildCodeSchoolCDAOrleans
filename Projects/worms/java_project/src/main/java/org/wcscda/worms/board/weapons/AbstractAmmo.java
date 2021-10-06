package org.wcscda.worms.board.weapons;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import org.wcscda.worms.Helper;
import org.wcscda.worms.board.*;
import org.wcscda.worms.utils.MathHelper;

public abstract class AbstractAmmo implements IMovableHandler {
  private static final double INITIAL_DISTANCE_MARGIN = 0.5;

  private final int firedPhase;
  private final int explosionRadius;
  private final int explosionDamage;
/* Se eu uso a classe abaixo como um atributo, o que me permite de mudar o tipo do atributo.
Eu nao poderia fazer isso se eu herdasse a classe. */
  private AbstractRectangularBoardElement movable;
  private boolean initialPositionSet = false;

  public AbstractAmmo(int explosionRadius, int explosionDamage) {
    firedPhase = Helper.getClock();

    this.explosionDamage = explosionDamage;
    this.explosionRadius = explosionRadius;

  }

  public AbstractRectangularBoardElement getMovable() {
    return movable;
  }

  // Override this method if you want to have another movement
  // behaviour
  // Draw the moving rectangle when shooting
  protected void createMovableRect(int rectWidth, int rectHeight) {

   this.movable = new ARBEWIthHandler(0, 0, rectWidth, rectHeight, this);

  }

  /* Important ! must be called at the end of construction process */
  protected void setInitialPosition() {
    double x = Helper.getWormX();
    double y = Helper.getWormY();

    Rectangle2D wormRect = Helper.getActiveWorm().getInnerRect();

    double distance =
        MathHelper.distance(wormRect.getWidth(), wormRect.getHeight())
            + MathHelper.distance(
                movable.getInnerRect().getWidth(), movable.getInnerRect().getHeight())
            + INITIAL_DISTANCE_MARGIN;

    movable.setPosition(
        new Point2D.Double(
            x + distance * Math.cos(movable.getDirection()) - movable.getInnerRect().getWidth() / 2,
            y
                + distance * Math.sin(movable.getDirection())
                - movable.getInnerRect().getHeight() / 2));

    initialPositionSet = true;
  }

  protected void setMovable(AbstractRectangularBoardElement movable) {
    this.movable = movable;
  }

/*
  @Override
  public Boolean isColidingWithAdditionnal(Shape s) {
    if ((s == Helper.getActiveWorm().getShape())
            && Helper.getClock() <= firedPhase + FIRING_WORM_ANTICOLLISION) {
      return false;
    }
    return null;
  }
*/

  protected int getFiredPhase() {
    return firedPhase;
  }

  @Override
  public void colideWith(AbstractBoardElement movable, Point2D prevPosition) {
    explode();
    Helper.getCurrentWeapon().triggerAmmoExplosion();

  }

  protected void explode() {

    this.movable.removeSelf();
    Helper.getPC()
        .generateExplosion(
                /* GetCenter = bullet position after explosion */
            this.movable.getCenterX(), this.movable.getCenterY(), explosionRadius, explosionDamage);

  }
}
