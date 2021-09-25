package org.wcs_cda.worms.board.weapons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.ImageObserver;

public class Hadoken extends AbstractWeapon {
	@Override
	public void draw(Graphics g, ImageObserver io, int x, int y) {
		Ellipse2D circle = new Ellipse2D.Double(x, y, 40, 40);
		
		g.setColor(Color.BLUE);
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
		g.drawOval(x, y, 60, 60);
	}

	@Override
	protected AbstractAmmo getAmmo() {
		// TODO Auto-generated method stub
		return null;
	}

}
