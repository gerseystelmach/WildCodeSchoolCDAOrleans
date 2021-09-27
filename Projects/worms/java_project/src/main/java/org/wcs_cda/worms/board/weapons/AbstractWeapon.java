package org.wcs_cda.worms.board.weapons;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import org.wcs_cda.worms.board.Worm;

public abstract class AbstractWeapon {
	private double angle;
	
	public AbstractWeapon() {
		this.angle = 0;
	}
	
	public abstract void draw(Graphics2D g, ImageObserver io, double x, double y);
	
	public double getAngle() {
		return this.angle;
	}
	
	public abstract void fire(Worm firingWorm, double x, double y);
	
	public void setAngle(double angle) {
		this.angle = angle;
	} 
}
