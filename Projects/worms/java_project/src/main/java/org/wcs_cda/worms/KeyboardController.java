package org.wcs_cda.worms;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import org.wcs_cda.worms.board.Board;

public class KeyboardController extends KeyAdapter {
	private Board board;
	
	public KeyboardController(Board board) {
		this.board = board;
	}
	
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT) {
            board.getCurrentMovable().move(Direction.LEFT);
        }

        if (key == KeyEvent.VK_RIGHT) {
        	board.getCurrentMovable().move(Direction.RIGHT);
        }
    }

	public Board getBoard() {
		return board;
	}
}