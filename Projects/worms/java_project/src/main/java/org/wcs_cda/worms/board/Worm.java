package org.wcs_cda.worms.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

import org.wcs_cda.worms.Direction;
import org.wcs_cda.worms.Player;

public class Worm extends AbstractBoardElement implements IMovable {
	private static final String leftFacingResource = "src/resources/WormLF.png";
	private static final String rightFacingResource = "src/resources/WormRF.png";
	
	private static final int imageWidth = 70;
	private static final int imageHeigth = 60;
	
	private static Image wormLF = null;
	private static Image wormRF = null;
	
	private boolean isRightFacing = true;
	
	// The position of the worm
	private int x = 100;
	private int y = 100;
	private Player player;
	private int life = 100;
	private boolean isMoving = false;
	private double weaponAngle = 0.0;
	
	private static void initImages() {
		wormLF = new ImageIcon(leftFacingResource).getImage().getScaledInstance(imageWidth, imageHeigth, 0);
		wormRF = new ImageIcon(rightFacingResource).getImage().getScaledInstance(imageWidth, imageHeigth, 0);
	}
	
	public Worm(Player player) {
		this.player = player;
		player.addWorm(this);
	}
	
	@Override
	protected void drawMain(Graphics g, ImageObserver io) {
		if( wormLF == null ) initImages();
		Image worm = isRightFacing ? wormRF : wormLF;
		
		g.drawImage(worm, x, y, io);
		// Drawing the life
		g.setColor(player.getColor());
		g.drawString("" + life, x + 30, y - 20);
		if(!isMoving) {
			drawWeapon(g, io);
		}
		isMoving = false;
	}
	
	private void drawWeapon(Graphics g, ImageObserver io) {
		player.getCurrentWeapon().draw(g, io, x, y);
	}

	@Override
	protected void drawDebug(Graphics g, ImageObserver io) {
		g.setColor(Color.red);
		g.drawRect(x, y, imageWidth, imageHeigth);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Rectangle2D getRect() {
		Rectangle2D rect = new Rectangle2D.Double();
		rect.setRect(x + imageWidth/4, y + imageHeigth/4, imageWidth/2, imageHeigth/2);
		
		return rect;
	}
	
	@Override
	public void move(Direction direction) {
		if(direction == Direction.LEFT) {
			moveLeft();
		}
		else if (direction == Direction.RIGHT) {
			moveRight();
		}
		isMoving = true;
	}
	
	private void moveLeft() { 
		isRightFacing = false;
		this.x -= 1; 
	}
	
	public void moveRight() { 
		isRightFacing = true; 
		this.x += 1; 
	}

	public Rectangle2D getOuterRect() {
		Rectangle2D rect = new Rectangle2D.Double();
		rect.setRect(x + imageWidth/4 - 5, y + imageHeigth/4 - 5, imageWidth/2 + 10, imageHeigth/2 + 10);
		
		return rect;
	}
}
