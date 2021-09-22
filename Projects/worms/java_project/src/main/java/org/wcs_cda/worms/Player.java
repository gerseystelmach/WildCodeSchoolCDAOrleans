package org.wcs_cda.worms;

import java.awt.Color;
import java.util.ArrayList;

import org.wcs_cda.worms.board.AbstractBoardElement;
import org.wcs_cda.worms.board.Worm;
import org.wcs_cda.worms.board.weapons.AbstractWeapon;
import org.wcs_cda.worms.board.weapons.Shotgun;

public class Player {
	private final String name;
	private final Color color;
	private ArrayList<Worm> worms = new ArrayList<Worm>();
	private AbstractWeapon currentWeapon;
	
	public Player(String name, Color color) {
		this.name = name;
		this.color = color;
		this.currentWeapon = new Shotgun();
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

	public AbstractWeapon getCurrentWeapon() {
		return new Shotgun();
	}
}
