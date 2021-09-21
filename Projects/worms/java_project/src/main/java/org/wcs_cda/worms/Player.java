package org.wcs_cda.worms;

import java.awt.Color;
import java.util.ArrayList;

import org.wcs_cda.worms.board.Worm;

public class Player {
	private final String name;
	private final Color color;
	private ArrayList<Worm> worms = new ArrayList<Worm>();
	
	
	
	public Player(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}
	
	public void addWorm(Worm worm) {
		worms.add(worm);
	}

	public Color getColor() {
		return color;
	}
}
