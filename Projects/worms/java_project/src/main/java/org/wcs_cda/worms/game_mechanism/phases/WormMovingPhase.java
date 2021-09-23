package org.wcs_cda.worms.game_mechanism.phases;

import org.wcs_cda.worms.Config;
import org.wcs_cda.worms.Player;

public class WormMovingPhase extends AbstractPhase {

	protected WormMovingPhase(Player activePlayer) {
		super(activePlayer);
	}

	@Override
	protected int getMaxDurationSeconds() {
		return Config.getMaxWormTurnDuration();
	}

}
