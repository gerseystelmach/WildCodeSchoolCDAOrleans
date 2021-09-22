package org.wcs_cda.worms.board.weapons;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

public abstract class AbstractWeapon {
	private double angle;
	
	public AbstractWeapon() {
		this.angle = 0;
	}
	
	public abstract void draw(Graphics g, ImageObserver io, int x, int y);
	
	public double getAngle() {
		return this.angle;
	}
}
