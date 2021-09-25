package org.wcs_cda.worms.board;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public abstract class AbstractRectangularBoardElement extends AbstractMovable{
	private Rectangle2D innerRect;
	
	public AbstractRectangularBoardElement(
			int x, 
			int y, 
			int rectWidth, 
			int rectHeight) {
		innerRect = new Rectangle2D.Double(x, y, rectWidth, rectHeight);
	}

	public int getX() {
		return (int)getInnerRect().getX();
	}

	public int getY() {
		return (int)getInnerRect().getY();
	}
	
	public Rectangle2D getInnerRect() {
		return innerRect;
	}

	public Shape getShape() {
		return innerRect;
	}
	
	public boolean isColidingWith(Shape s) {
		return s.intersects(getInnerRect());
	}
	
	@Override
	protected void rawMove(int x, int y) {
		GeomUtils.moveRect(innerRect, x, y);
	}
}
