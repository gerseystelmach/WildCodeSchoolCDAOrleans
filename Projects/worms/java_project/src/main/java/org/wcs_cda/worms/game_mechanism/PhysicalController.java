package org.wcs_cda.worms.game_mechanism;

import java.awt.geom.Point2D;

import org.wcs_cda.worms.board.ARBEWithGravity;
import org.wcs_cda.worms.board.AbstractMovable;
import org.wcs_cda.worms.board.AbstractRectangularBoardElement;
import org.wcs_cda.worms.board.IMovableVisitor;
import org.wcs_cda.worms.board.Worm;

public class PhysicalController extends Board implements IMovableVisitor{
	public void wormInitialPlacement(Worm worm) {
		while(worm.isColidingWith(getWormField().getFrontier()))
		{
			worm.rawMove(0, -3);
		}

		while(!worm.isStandingOn(getWormField().getFrontier()))
		{
			worm.rawMove(0, 3);
		}
	}
	
	private void doGravity(ARBEWithGravity arbe) {
		
	}

	@Override
	public void visit(AbstractMovable ab) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ARBEWithGravity arbewg) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void doMoves() {
		for(AbstractMovable movable: AbstractMovable.getAllMovable()) {
			if(!movable.isMoving()) {
				continue;
			}
			
			Point2D currentPosition = movable.getCurrentPosition();
			movable.move(this);
		}
	}
}
