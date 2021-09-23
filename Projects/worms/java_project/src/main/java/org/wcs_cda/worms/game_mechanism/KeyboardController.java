package org.wcs_cda.worms.game_mechanism;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardController extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
    	// TimeController.getInstance().getCurrentPhase().forwardKeyPressed(key);
        /* int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT) {
            .getCurrentMovable().move(Direction.LEFT);
        }

        if (key == KeyEvent.VK_RIGHT) {
        	board.getCurrentMovable().move(Direction.RIGHT);
        }*/
    }
}