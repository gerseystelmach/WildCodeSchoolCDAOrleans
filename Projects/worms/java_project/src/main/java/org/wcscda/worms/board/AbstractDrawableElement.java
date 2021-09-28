package org.wcscda.worms.board;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.HashSet;

import org.wcscda.worms.Config;

// NRO 2021-09-27 : Drawable elements are present visually
//  but might not have physical presence (ie timer)
public abstract class AbstractDrawableElement {
	private static final HashSet<AbstractDrawableElement> allDrawable = new HashSet<>();
	private static final HashSet<AbstractDrawableElement> toBeRemoved = new HashSet<>();
	private static final HashSet<AbstractDrawableElement> toBeAdded = new HashSet<>();
	
	public static HashSet<AbstractDrawableElement> getAllDrawable() {
		return allDrawable;
	}

	public static void processToBeRemovedAndAdded() {
		allDrawable.removeAll(toBeRemoved);
		allDrawable.addAll(toBeAdded);
		toBeRemoved.clear();
		toBeAdded.clear();
	}

	protected AbstractDrawableElement() {
		allDrawable.add(this);
	}
	
	protected AbstractDrawableElement(boolean differedAdding) {
		toBeAdded.add(this);
	}
	
	public final void draw(Graphics2D g, ImageObserver io) {
		drawMain(g, io);
		if (Config.isDebug()) {
			drawDebug(g, io);
		}
	}

	// By default, no debug print
	protected void drawDebug(Graphics2D g, ImageObserver io) {}

	protected abstract void drawMain(Graphics2D g, ImageObserver io);

	public void removeSelf() {
		toBeRemoved.add(this);
	}
}
