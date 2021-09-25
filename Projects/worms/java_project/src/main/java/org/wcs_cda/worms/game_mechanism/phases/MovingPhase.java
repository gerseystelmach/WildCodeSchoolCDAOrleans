package org.wcs_cda.worms.game_mechanism.phases;

import org.wcs_cda.worms.Player;


public class MovingPhase extends AbstractPhase {

	protected MovingPhase(Player activePlayer) {
		super(activePlayer);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int getMaxDurationSeconds() {
		return 15;
	}

	@Override
	public void forwardKeyPressed(String key) {}
}
