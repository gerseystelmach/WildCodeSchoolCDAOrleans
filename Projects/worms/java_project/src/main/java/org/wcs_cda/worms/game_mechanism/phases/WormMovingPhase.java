package org.wcs_cda.worms.game_mechanism.phases;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import org.wcs_cda.worms.Config;
import org.wcs_cda.worms.Player;
import org.wcs_cda.worms.board.Worm;

public class WormMovingPhase extends AbstractPhase {
	private Worm activeWorm;
	
	public WormMovingPhase(Worm worm) {
		super(worm.getPlayer());
		this.activeWorm = worm;
	}

	@Override
	protected int getMaxDurationSeconds() {
		return Config.getMaxWormTurnDuration();
	}

	public Worm getActiveWorm() {
		return activeWorm;
	}

	@Override
	public void forwardKeyPressed(String key) {
		if(key.equals("Left") || key.equals("Right")) {
			activeWorm.move(key);
		}
	}
	
	@Override
	protected void drawMain(Graphics g, ImageObserver io) {
		if(!activeWorm.isMoving()) {
			int x = activeWorm.getX();
			int y = activeWorm.getY();
			
			activeWorm.getPlayer().getCurrentWeapon().draw(g, io, x, y);
		}
	}

}
