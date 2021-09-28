package org.wcscda.worms.gamemechanism.phases;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import org.wcscda.worms.Config;
import org.wcscda.worms.Worm;
import org.wcscda.worms.gamemechanism.PhysicalController;
import org.wcscda.worms.gamemechanism.TimeController;

public class WormMovingPhase extends AbstractPhase {
  private static final double WORM_STEP_SPEED = 3.0;
  private static final double WEAPON_LINE_LENGTH = 30.0;
  private Worm activeWorm;

  public WormMovingPhase(Worm worm) {
    super(worm.getPlayer());
    this.activeWorm = worm;
    getActivePlayer().getCurrentWeapon().setAngle(worm.getDirection());
  }

  @Override
  protected int getMaxDurationSeconds() {
    return Config.getMaxWormTurnDuration();
  }

  public Worm getActiveWorm() {
    return activeWorm;
  }

  @Override
  public void forwardKeyPressed(String key) {
    if (key.equals("Left")) {
      moveWorm(Math.PI);
    }

    if (key.equals("Right")) {
      moveWorm(0);
    }

    if (key.equals("Up")) {
      moveWeapon(-1);
    }

    if (key.equals("Down")) {
      moveWeapon(1);
    }

    if (key.equals("Space")) {
      activeWorm
          .getPlayer()
          .getCurrentWeapon()
          .fire(activeWorm, activeWorm.getCenterX(), activeWorm.getCenterY());

      TimeController.getInstance().setCurrentPhase(new MovingPhase(getActivePlayer()));
    }
  }

  private void moveWeapon(int direction) {
    double angle = getActivePlayer().getCurrentWeapon().getAngle();
    getActivePlayer()
        .getCurrentWeapon()
        .incrementAngle(direction * Math.cos(activeWorm.getDirection()) * Math.PI / 8);
    if (Math.abs(getActivePlayer().getCurrentWeapon().getAngle() - activeWorm.getDirection())
        >= Math.PI / 2 + 1e-3) {
      getActivePlayer().getCurrentWeapon().setAngle(angle);
    }
    System.out.println(
        getActivePlayer().getCurrentWeapon().getAngle() + " / " + activeWorm.getDirection());
  }

  private void moveWorm(double angle) {
    activeWorm.getPlayer().getCurrentWeapon().setAngle(angle);
    activeWorm.setDirection(angle);
    activeWorm.setUserMoving(true);
    activeWorm.singleMove(PhysicalController.getInstance(), Math.cos(angle) * WORM_STEP_SPEED, 0.0);
  }

  @Override
  protected void drawMain(Graphics2D g, ImageObserver io) {
    if (!activeWorm.isUserMoving()) {
      double x = activeWorm.getCenterX();
      double y = activeWorm.getCenterY();

      activeWorm.getPlayer().getCurrentWeapon().draw(g, io, x, y);

      drawWeaponDirectionLine(g, io, x, y);
    }
    activeWorm.setUserMoving(false);
  }

  private void drawWeaponDirectionLine(Graphics2D g, ImageObserver io, double x, double y) {
    double angle = activeWorm.getPlayer().getCurrentWeapon().getAngle();

    g.setColor(activeWorm.getPlayer().getColor());

    g.drawLine(
        (int) x,
        (int) y,
        (int) (x + WEAPON_LINE_LENGTH * Math.cos(angle)),
        (int) (y + WEAPON_LINE_LENGTH * Math.sin(angle)));
  }
}
