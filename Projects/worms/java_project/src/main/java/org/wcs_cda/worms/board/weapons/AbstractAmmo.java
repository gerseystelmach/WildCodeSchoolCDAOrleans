package org.wcs_cda.worms.board.weapons;

import java.awt.geom.Point2D;

import org.wcs_cda.worms.board.AbstractBoardElement;
import org.wcs_cda.worms.board.AbstractMovable;
import org.wcs_cda.worms.board.AbstractRectangularBoardElement;
import org.wcs_cda.worms.board.Worm;
import org.wcs_cda.worms.game_mechanism.TimeController;

public abstract class AbstractAmmo extends AbstractRectangularBoardElement {
	private final static int FIRING_WORM_ANTICOLLISION = 20;
	
	private final int firedPhase;
	private final Worm firingWorm;
	
	public AbstractAmmo(Worm worm, double x, double y, int rectWidth, int rectHeight) {
		super(x, y, rectWidth, rectHeight);
		firedPhase = TimeController.getInstance().getPhaseCount();
		this.firingWorm = worm;
	}

	protected int getFiredPhase() {
		return firedPhase;
	}
	
	@Override
	public void colideWith(AbstractBoardElement movable, Point2D prevPosition) {
		if((movable == firingWorm) 
		 && TimeController.getInstance().getPhaseCount() <= firedPhase + FIRING_WORM_ANTICOLLISION) {
			return;
		}
		
		// System.out.println("Ammo colided with " + movable);
		
		removeSelf();
		explode();
		
		TimeController.getInstance().setNextWorm();
	}

	protected abstract void explode();
}
