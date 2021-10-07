package org.wcscda.worms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.wcscda.worms.board.Inventory;
import org.wcscda.worms.board.weapons.*;


public class Player {

  private final String name;
  private final Color color;
  private final ArrayList<Worm> worms = new ArrayList<Worm>();
  private AbstractWeapon currentWeapon;
  private int currentWormIndex = 0;
  // Create property beginner level
  private boolean beginnerLevel;


  public Player(String name, Color color) {
    this.name = name;
    this.color = color;
    this.beginnerLevel = false;

  }


    public String getName() {
    return name;
  }

  public Worm createWorm(String nom) {
    Worm worm = new Worm(this, nom);
    worms.add(worm);

    return worm;
  }

  public Color getColor() {
    return color;
  }

  public AbstractWeapon getCurrentWeapon() {
    return currentWeapon;
  }

  public ArrayList<Worm> getWorms() {
    return worms;
  }

  public Worm getActiveWorm() {
    if (getWorms().isEmpty()) {
      return null;
    }
    return getWorms().get(currentWormIndex);
  }

  public void setNextWorm() {
    if (worms.isEmpty()) return;

    currentWormIndex += 1;
    currentWormIndex %= worms.size();
  }

  /* NRO 2021-09-30 : TODO-student make a better version of
   * this, this is just a temporary version :-)
   * This should call the inventory, and handle
   */
  public void changeWeapon() {
    if (currentWeapon.isChangingWeaponDisabled()) {
      return;
    }
    List<AbstractWeapon> weapons = new ArrayList<>();
    weapons.add(new Shotgun());
    weapons.add(new Hadoken());
    weapons.add(new Grenade());
    weapons.add(new SuperGrenade());
    weapons.add(new GrenadeTimer());

    for (int i = 0; i < weapons.size(); i++) {
        if (currentWeapon.getClass() == (weapons.get(i).getClass())) {
            currentWeapon = weapons.get(i + 1);
             break;
     } else if (currentWeapon.getClass() == weapons.get(weapons.size() - 1).getClass()) {
      currentWeapon = weapons.get(0);
      }
    }

    // NRO 2021-10-05 NOT-NICE : This is not very good, obviously
    //  you would prefer to have a Weapon array, and manage
    //  the next with an index. But well, in that case you would
    //  need to have an array of class, which is possible, but leave
    //  it out for now ...

  }

  public boolean isBeginnerLevel() {
    return beginnerLevel;
  }

  public void setBeginnerLevel(boolean beginnerLevel) {
    this.beginnerLevel = beginnerLevel;
  }

  public void initWeapon() {
    currentWeapon = new Shotgun();
  }

  public boolean hasWorms() {
    return !getWorms().isEmpty();
  }
}
