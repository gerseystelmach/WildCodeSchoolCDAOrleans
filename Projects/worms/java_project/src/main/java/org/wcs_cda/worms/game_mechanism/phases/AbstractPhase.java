package org.wcs_cda.worms.game_mechanism.phases;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import org.wcs_cda.worms.Player;
import org.wcs_cda.worms.board.AbstractDrawable;

public abstract class AbstractPhase extends AbstractDrawable {
	private Instant phaseStart;
	private Player activePlayer;
	
	protected abstract int getMaxDurationSeconds();
	
	protected AbstractPhase(Player activePlayer) {
		phaseStart = Instant.now();
	}
	
	public abstract void forwardKeyPressed(String key);
	
	public boolean isFinished() {
		Duration timeElapsed = Duration.between(Instant.now(), phaseStart);
		return timeElapsed.toSeconds() > getMaxDurationSeconds();
	}

	@Override
	protected void drawMain(Graphics g, ImageObserver io) {
		// By default do nothing, let's the other do something if needed
	}
}
