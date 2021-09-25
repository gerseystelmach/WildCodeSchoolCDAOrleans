package org.wcs_cda.worms.game_mechanism.phases;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import org.wcs_cda.worms.Config;
import org.wcs_cda.worms.Player;
import org.wcs_cda.worms.board.Worm;

public class WormMovingPhase extends AbstractPhase {
	private static final int WORM_STEP_SPEED = 3;
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
		if(key.equals("Left")) {
			activeWorm.setSpeed(WORM_STEP_SPEED);
		}
		
		if(key.equals("Space")) {
			activeWorm.getPlayer().getCurrentWeapon().fire();
		}
	}
	
	@Override
	protected void drawMain(Graphics2D g, ImageObserver io) {
		if(!activeWorm.isMoving()) {
			int x = (int)activeWorm.getX();
			int y = (int)activeWorm.getY();
			
			activeWorm.getPlayer().getCurrentWeapon().draw(g, io, x, y);
		}
	}

}
