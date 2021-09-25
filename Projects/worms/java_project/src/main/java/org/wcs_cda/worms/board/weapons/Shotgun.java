package org.wcs_cda.worms.board.weapons;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Shotgun extends AbstractWeapon {
	private static final String imagePath = "src/resources/weapons/Shotgun_small.png";
	private static Image image = null;
	
	private static void initImages() {
		image = new ImageIcon(imagePath).getImage().getScaledInstance(50, 30, 0);
		
	}
	
	@Override
	public void draw(Graphics g, ImageObserver io, int x, int y) {
		if(image == null) {
			initImages();
		}
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform trans = AffineTransform.getTranslateInstance(x + 100, y);
		trans.scale(-1, 1);
		g2d.drawImage(image, trans, io);
	}

	@Override
	protected AbstractAmmo getAmmo() {
		// TODO Auto-generated method stub
		return null;
	}

}
