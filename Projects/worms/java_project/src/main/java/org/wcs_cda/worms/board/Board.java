package org.wcs_cda.worms.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.wcs_cda.worms.Player;

public class Board extends JPanel {

    private final int B_WIDTH = 1200;
    private final int B_HEIGHT = 800;
        
    private ArrayList<Worm> worms;
    private Worm activeWorm;
    
    private WormField wormField;
    
    public Board() {
        initBoard();
    }
    
    private void initBoard() {
        setBackground(Color.BLACK);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        wormField = new WormField(B_WIDTH, B_HEIGHT);
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
        	doGravity();
        	activeWorm.draw(g, this);
        	
            Toolkit.getDefaultToolkit().sync();

        } else {
            gameOver(g);
        }        
    }

    private void doGravity() {
		while(!wormField.getFrontier().intersects(activeWorm.getOuterRect()))
		{
			activeWorm.setY(activeWorm.getY() + 3);
		}
		
		while(wormField.getFrontier().intersects(activeWorm.getRect())
		)
		{
			activeWorm.setY(activeWorm.getY() - 3);
		}
	}

	private void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    public boolean actionPerformed(ActionEvent e) {

        /* if (inGame) {

            checkApple();
            checkCollision();
            move();
        }*/

        repaint();
        
        return true;
    }

	public IMovable getCurrentMovable() {
		return activeWorm;
	}

	public void createWorm(Player luckyLuke) {
		Worm worm = new Worm(luckyLuke);
		activeWorm = worm;
	}
}
