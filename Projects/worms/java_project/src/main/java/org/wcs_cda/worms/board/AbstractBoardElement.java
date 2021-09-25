package org.wcs_cda.worms.board;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.ImageObserver;

import org.wcs_cda.worms.Config;

// Created by NRO 
// Shape is used to detect collision, outer to detect touching
// Some elements, like ammo only have one, elements that can stand
// on the ground (like worms) have 2 
public abstract class AbstractBoardElement extends AbstractDrawableElement {
	public abstract Shape getShape();
	
	
	public Shape getOuterShape() {
		return getShape();
	}
}
