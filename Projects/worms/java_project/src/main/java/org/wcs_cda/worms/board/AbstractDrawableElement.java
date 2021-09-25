package org.wcs_cda.worms.board;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import org.wcs_cda.worms.Config;

public abstract class AbstractDrawableElement {
	public final void draw(Graphics2D g, ImageObserver io) {
		drawMain(g, io);
		if(Config.isDebug()) {
			drawDebug(g, io);
		}
	}

	// By default, no debug print
	protected void drawDebug(Graphics2D g, ImageObserver io) {}

	protected abstract void drawMain(Graphics2D g, ImageObserver io);
}
