package org.wcs_cda.worms;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class MainLoop implements ActionListener{
	private static final int DELAY = 140;
	
	private Board board;
	private Timer timer;
	
	public MainLoop() {
		board = new Board();
		board.addKeyListener(new KeyboardController(board));
		
		timer = new Timer(DELAY, this);
        timer.start();
	}
	
	public Component getBoard() {
		return board;
	}
	
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean inGame = board.actionPerformed(e);
        
        if (!inGame) {
            timer.stop();
        }
    }
}
