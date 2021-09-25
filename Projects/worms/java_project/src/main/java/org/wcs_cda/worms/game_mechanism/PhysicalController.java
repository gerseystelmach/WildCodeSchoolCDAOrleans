package org.wcs_cda.worms.game_mechanism;

import org.wcs_cda.worms.board.AbstractRectangularBoardElement;
import org.wcs_cda.worms.board.Worm;

public class PhysicalController extends Board {
	public void wormInitialPlacement(Worm worm) {
		while(worm.isColidingWith(getWormField().getFrontier()))
		{
			worm.move(0, -3);
		}

		while(!worm.isTouching(getWormField().getFrontier()))
		{
			worm.move(0, 3);
		}
	}
	
	private void doGravity(AbstractRectangularBoardElement arbe) {
		
	}
}
