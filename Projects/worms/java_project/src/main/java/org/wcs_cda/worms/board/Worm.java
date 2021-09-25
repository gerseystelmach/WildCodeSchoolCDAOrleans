package org.wcs_cda.worms.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import org.wcs_cda.worms.Player;
import org.wcs_cda.worms.RandomGenerator;
import org.wcs_cda.worms.game_mechanism.Board;

public class Worm extends AbstractRectangularBoardElement {
	private static final String leftFacingResource = "src/resources/WormLF.png";
	private static final String rightFacingResource = "src/resources/WormRF.png";
	
	private static final int imageWidth = 70;
	private static final int imageHeigth = 60;
	private static final int rectWidth = imageWidth - 20;
	private static final int rectHeight = imageHeigth - 20;
	
	private static final int outerMargin = 3;
	
	private static Image wormLF = null;
	private static Image wormRF = null;
	
	// The position of the worm
	private final Player player;
	private final String name;
	
	private int life = 100;
	
	private static void initImages() {
		wormLF = new ImageIcon(leftFacingResource).getImage().getScaledInstance(imageWidth, imageHeigth, 0);
		wormRF = new ImageIcon(rightFacingResource).getImage().getScaledInstance(imageWidth, imageHeigth, 0);
	}
	
	public Worm(Player player, String name) {
		this(
			player,
			name,
			getRandomStartingX(),
			getRandomStartingY()
		);
	}
	
	public Worm(Player player, String name, int x, int y) {
		super(x, y, rectWidth, rectHeight, outerMargin);
		
		this.player = player;
		this.name = name;
	}
		
	private static int getRandomStartingX() {
		return RandomGenerator.getInstance().nextInt(
				Board.getB_WIDTH() - imageWidth
		);
	}
	
	private static int getRandomStartingY() {
		return RandomGenerator.getInstance().nextInt(
				Board.getB_HEIGHT() - imageHeigth
		);
	}
	
	@Override
	protected void drawMain(Graphics2D g, ImageObserver io) {
		if( wormLF == null ) initImages();
		Image worm = isRightFacing() ? wormRF : wormLF;

		g.drawImage(
				worm, 
				(int)getInnerRect().getMinX(), 
				(int)getInnerRect().getMinY(), 
				io
		);
		
		// Drawing the life
		g.setColor(player.getColor());
		g.drawString(
				"" + life, 
				(int)getX() + (isRightFacing() ? 30 : 10), 
				(int)getY() - 50
		);
	}
	
	private boolean isRightFacing() {
		return Math.abs(getDirection()) < 1e-6;
	}

	@Override
	protected void drawDebug(Graphics2D g, ImageObserver io) {
		g.setColor(Color.red);
		g.draw(getInnerRect());
	}
		
	public Player getPlayer() {
		return player;
	}
}
