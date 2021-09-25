package org.wcs_cda.worms.board;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public abstract class ARBEWithGravity extends AbstractRectangularBoardElement {
	private static final int STANDING_RECTANGLE_HEIGHT = 5;
	
	private Rectangle2D supportRect;
	
	public ARBEWithGravity(
			int x, 
			int y, 
			int rectWidth, 
			int rectHeight) {
		super(x, y, rectWidth, rectHeight);
		supportRect = new Rectangle2D.Double(
				x, 
				y + rectWidth, 
				rectHeight, 
				STANDING_RECTANGLE_HEIGHT
		);
	}
	
	public Rectangle2D getOuterRect() {
		return supportRect;
	}
	
	public boolean isStandingOn(Shape s) {
		return s.intersects(getOuterRect());
	}
	
	public boolean isSubjectToGravity() {
		return true;
	}
	
	@Override
	public void rawMove(int x, int y) {
		super.rawMove(x, y);
		GeomUtils.moveRect(supportRect, x, y);
	}
	
	// NRO 2021-09-25 : 
	// Question subtile, à quoi sert cette fonction, qui est
	// exactement la même que le accept de AbstractMovable ??
	@Override
	public void accept(IMovableVisitor visitor) {
		visitor.visit(this);
	}
}
