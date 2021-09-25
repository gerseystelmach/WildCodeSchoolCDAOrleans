package org.wcs_cda.worms.game_mechanism;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;
import org.wcs_cda.worms.Player;
import org.wcs_cda.worms.board.*;

public class PhysicalController extends JPanel {

    private final int BOARD_WIDTH = 1200;
    private final int BOARD_HEIGHT = 800;
        
    private WormField wormField;
    
    public PhysicalController() {
        initBoard();
    }
    
    private void initBoard() {
        setBackground(Color.BLACK);
        setFocusable(true);

        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));

        wormField = new WormField(BOARD_WIDTH, BOARD_HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        boolean inGame = true;
        
        if (inGame) {
        	wormField.draw(g, this);
        	for(Worm worm: Worm.getAllWorms()) {
        		doGravity(worm);
        		worm.draw(g, this);
        		TimeController.getInstance().getCurrentPhase().draw(g, this);
        	}
        	
            Toolkit.getDefaultToolkit().sync();

        } else {
            gameOver(g);
        }        
    }

    private void doGravity(Worm worm) {
		while(!wormField.getFrontier().intersects(worm.getOuterRect()))
		{
			worm.setY(worm.getY() + 3);
		}
		
		while(wormField.getFrontier().intersects(worm.getRect())
		)
		{
			worm.setY(worm.getY() - 3);
		}
	}

	private void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (BOARD_WIDTH - metr.stringWidth(msg)) / 2, BOARD_HEIGHT / 2);
    }

    public boolean actionPerformed(ActionEvent e) {
        repaint();
        
        return true;
    }

	public int getB_WIDTH() {
		return BOARD_WIDTH;
	}

	public int getB_HEIGHT() {
		return BOARD_HEIGHT;
	}
}
