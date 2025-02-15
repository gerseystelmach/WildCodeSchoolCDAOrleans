package org.wcscda.worms.gamemechanism;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Optional;
import org.wcscda.worms.Worm;
import org.wcscda.worms.board.*;
import org.wcscda.worms.utils.DrawHelper;

/** @author nicolas */
public class PhysicalController extends Board implements IMovableVisitor {
  private static final long serialVersionUID = 1L;
  private static final int MAX_PIXEL_DIFF_SLOPE = 10;
  private static final int SLOPE_STEP = 2;
  private static final double GRAVITY_ACCELERATION = 0.1;

  private static PhysicalController instance;

  public static PhysicalController getInstance() {
    return instance;
  }

  public PhysicalController() {
    instance = this;
  }

  public void wormInitialPlacement(Worm worm) {
    while (getFirstCollidingWith(worm).isPresent()) {
      worm.rawMove(0, -2);
    }

    while (getFirstStandingOn(worm).isEmpty()) {
      worm.rawMove(0, 2);
    }
  }

  // est ce que ça tiens sur qql choses, (vers ou terrain), si
  // NRO 2021-10-05 : Not only, it is the getFirstStandingOn that is
  //  doing that (Your code is a little bit outdeated), this one 
  //  is also making the object accelerate if not standing on anything
  private void doGravity(ARBEWithGravity arbe) {
    if (getFirstStandingOn(arbe).isEmpty()) {
      arbe.setSpeedY(arbe.getSpeedY() + GRAVITY_ACCELERATION);
    } else if (arbe.getSpeedY() > 0){
      // NRO 2021-10-01 : You might have to change that if you
      // want some rebounce effect
      arbe.setSpeedY(0);
    }
  }

  public Optional<AbstractBoardElement> getFirstStandingOn(ARBEWithGravity arbe) {
    Optional<AbstractBoardElement> optAm =
        AbstractMovable.getAllBoardElement()
            .filter(am -> (am != arbe) && arbe.isStandingOn(am.getShape()))
            .findFirst();
    return optAm;
  }

  private Optional<AbstractBoardElement> getFirstCollidingWith(ARBEWithGravity arbe) {
    Optional<AbstractBoardElement> optAm =
        AbstractBoardElement.getAllBoardElement()
            .filter(am -> (am != arbe) && arbe.isCollidingWith(am.getShape()))
            .findFirst();
    return optAm;
  }

  private boolean doGravityUserMove(Worm worm) {
    // NRO 2021-10-01 : isColiding means the worm (or other object)
    // is IN the worm field, so he must be against a slope,
    // so we try to make him climb it
    for (int i = 0; i * SLOPE_STEP < MAX_PIXEL_DIFF_SLOPE; ++i) {
      if (getFirstCollidingWith(worm).isPresent()) {
        worm.rawMove(0, -SLOPE_STEP);
      } else {
        break;
      }
    }

    // Worms is still coliding, he must be standing against a wall
    // just revert its position
    if (getFirstCollidingWith(worm).isPresent()) {
      return false;
    }

    //
    for (int i = 0; i * SLOPE_STEP < MAX_PIXEL_DIFF_SLOPE; ++i) {
      if (getFirstStandingOn(worm).isEmpty()) {
        worm.rawMove(0, SLOPE_STEP);
      }
    }

    return true;
  }

  @Override
  public void visit(AbstractMovable ab, Point2D prevPosition) {
    checkForCollision(ab, prevPosition);
  }

  @Override
  public void visit(ARBEWithGravity arbewg, Point2D prevPosition) {
    // Do gravity first
    doGravity(arbewg);
    checkForCollision(arbewg, prevPosition);
  }

  @Override
  public void visit(Worm worm, Point2D prevPosition) {
    if (worm.isUserMoving()) {
      boolean moveIsPossibleWithGravity = doGravityUserMove(worm);
      if (!moveIsPossibleWithGravity) {
        worm.setPosition(prevPosition);
        return;
      }
    }
    doGravity(worm);
    checkForCollision(worm, prevPosition);
  }

  public void checkForCollision(AbstractMovable ab, Point2D prevPosition) {
    if (ab.isCollidingWith(getWormField())) {
      ab.collideWith(getWormField(), prevPosition);
      return;
    }

    // NRO 2021-09-28 : For information this is a little bit of
    //  algorithmic addict overkill
    // You don't need to understand that for the moment.
    // I am looking for the first object that colide with ab
    Optional<AbstractMovable> oam =
        AbstractMovable.getAllMovable().filter(movable -> ab.isCollidingWith(movable)).findFirst();

    if (oam.isPresent()) {
      ab.collideWith(oam.get(), prevPosition);
    }
  }

  @Override
  protected void doMoves() {
    AbstractMovable.getAllMovable()
        .forEach(
            movable -> {
              if (movable.getSpeed() < 0.05) {
                movable.setSpeed(0.0);
              }

              movable.move(this);
            });
  }

  public void generateExplosion(
      double centerX, double centerY, int explosionRadius, int explosionDamage) {
    Ellipse2D circle = DrawHelper.getCircle(centerX, centerY, explosionRadius);
    getWormField().doExplosionOnField(circle);

    AbstractMovable.getAllMovable()
        .forEach(
            movable -> {
              if (movable.isCollidingWith(circle)) {
                movable.takeDamage(explosionDamage);
              }
            });

    new Explosion(centerX, centerY, explosionRadius);
  }
}
