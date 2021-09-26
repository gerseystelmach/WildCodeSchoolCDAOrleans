package org.wcs_cda.worms.board.weapons;

import java.awt.geom.Point2D;

import org.wcs_cda.worms.board.AbstractMovable;
import org.wcs_cda.worms.board.AbstractRectangularBoardElement;
import org.wcs_cda.worms.game_mechanism.TimeController;

public abstract class AbstractAmmo extends AbstractRectangularBoardElement {
	private final int firedPhase;
	
	public AbstractAmmo(double x, double y, int rectWidth, int rectHeight) {
		super(x, y, rectWidth, rectHeight);
		firedPhase = TimeController.getInstance().getPhaseCount();
	}

	protected int getFiredPhase() {
		return firedPhase;
	}
	
	@Override
	public void colideWith(AbstractMovable movable, Point2D prevPosition) {
		AbstractMovable.getToBeRemoved().add(this);
		explode();
	}

	protected abstract void explode();
}
