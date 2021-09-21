package org.wcs_cda.worms;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardController extends KeyAdapter {
	private Board board;
	
	public KeyboardController(Board board) {
		this.board = board;
	}
	
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT) {
            board.getCurrentWorm().moveLeft();
        }

        if (key == KeyEvent.VK_RIGHT) {
        	board.getCurrentWorm().moveRight();
        }
    }

	public Board getBoard() {
		return board;
	}
}