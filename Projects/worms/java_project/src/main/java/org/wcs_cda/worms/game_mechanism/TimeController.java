package org.wcs_cda.worms.game_mechanism;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.Timer;

import org.wcs_cda.worms.Player;
import org.wcs_cda.worms.Config;

public class TimeController implements ActionListener{	
	private PhysicalController board;
	private Timer timer;
	private ArrayList<Player> players = new ArrayList<Player>();
	
	public TimeController() {
		initGame();
		
		board.addKeyListener(new KeyboardController());
		
		timer = new Timer(Config.getClockDelay(), this);
        timer.start();
	}
	
	private void initGame() {
		board = new PhysicalController();
		// Lucky luke because for the moment he is a poor lonesome
		// player
		Player luckyLuke = createPlayer("Lucky Luke", Color.RED);
		board.createWorm(luckyLuke);
	}

	private Player createPlayer(String name, Color color) {
		Player player = new Player(name, color);
		players.add(player);
		
		return player;
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

	public static Object getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
}
