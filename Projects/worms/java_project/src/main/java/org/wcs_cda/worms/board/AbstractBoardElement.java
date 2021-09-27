package org.wcs_cda.worms.board;

import java.awt.Shape;
import java.awt.geom.Point2D;

public abstract class AbstractBoardElement extends AbstractDrawableElement {
	public abstract Shape getShape();
}
