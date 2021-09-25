package org.wcs_cda.worms.board;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

public abstract class AbstractMovable extends AbstractBoardElement {
	// Speed is in pixel by clock iteration
	private double speed = 0.0;
	// In radian
	private double direction = 0.0;
		
	public boolean collide(AbstractBoardElement otherElement) {
		return false;
	}

	public double getSpeed() {
		return speed;
	}

	public boolean isMoving() {
		return speed < 1e-6;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}
}
