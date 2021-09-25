package org.wcs_cda.worms.board;

import java.awt.geom.Point2D;
import java.util.HashSet;

public abstract class AbstractMovable extends AbstractBoardElement {
	// Speed is in pixel by clock iteration
	private double speed = 0.0;
	// In radian
	private double direction = 0.0;

	private static final HashSet<AbstractMovable> allMovables = new HashSet<AbstractMovable>();
	
	public static HashSet<AbstractMovable> getAllMovable() {
		return allMovables;
	}
	
	public AbstractMovable() {
		allMovables.add(this);
	}
	
	public boolean collide(AbstractBoardElement otherElement) {
		return false;
	}

	public double getSpeed() {
		return speed;
	}

	public boolean isMoving() {
		return speed > 1e-6;
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

	public void removeSelf() {
		getAllMovable().remove(this);
	}
	
	protected abstract void rawMove(int x, int y);
	
	public void move(IMovableVisitor visitor) {
		rawMove(
			(int)(speed * Math.cos(direction)),
			(int)(speed * Math.sin(direction))
		);
		accept(visitor);
	}
	
	public Point2D getCurrentPosition() {
		return new Point2D.Double(getX(), getY());
	}
	
	public abstract int getX();
	public abstract int getY();
	public abstract boolean isSubjectToGravity();
	
	public void accept(IMovableVisitor visitor) {
		visitor.visit(this);
	}
}
