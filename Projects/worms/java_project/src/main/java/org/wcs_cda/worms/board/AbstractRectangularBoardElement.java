package org.wcs_cda.worms.board;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public abstract class AbstractRectangularBoardElement extends AbstractMovable{
	private Rectangle2D innerRect;
	private Rectangle2D outerRect;
	
	public AbstractRectangularBoardElement(
			int x, 
			int y, 
			int rectwidth, 
			int rectheight,
			int outerMargin) {
		innerRect = new Rectangle2D.Double(x, y, rectwidth, rectheight);
		outerRect = new Rectangle2D.Double(
				x - outerMargin, 
				y - outerMargin, 
				rectwidth + 2 * outerMargin, 
				rectheight + 2 * outerMargin
		);
	}

	public double getX() {
		return getInnerRect().getCenterX();
	}

	public double getY() {
		// TODO Auto-generated method stub
		return getInnerRect().getCenterY();
	}
	
	public Rectangle2D getInnerRect() {
		return innerRect;
	}
	
	public Rectangle2D getOuterRect() {
		return outerRect;
	}

	public Shape getShape() {
		return innerRect;
	}
}
