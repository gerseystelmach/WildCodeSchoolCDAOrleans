package org.wcscda.worms.board.weapons;

import org.wcscda.worms.Helper;
import org.wcscda.worms.Player;
import org.wcscda.worms.Worm;
import org.wcscda.worms.board.AbstractDrawableElement;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Inventory extends AbstractDrawableElement {


    private ArrayList<AbstractWeapon> weapons = new ArrayList<>();
    private Map<Worm, ArrayList<AbstractWeapon>> wormWeaponInventory = new HashMap<>();

   public void createInventory() {

       weapons.add(new Shotgun());
       weapons.add(new Hadoken());
       weapons.add(new Grenade());
       weapons.add(new SuperGrenade());
       weapons.add(new GrenadeTimer());


       for (Player player : Helper.getTC().getPlayers()) {
           for (Worm worm : player.getWorms()) {
               wormWeaponInventory.put(worm, weapons);
           }
       }

       for (Map.Entry<Worm, ArrayList<AbstractWeapon>> entry : wormWeaponInventory.entrySet()) {
           Worm worm = entry.getKey();
           ArrayList<AbstractWeapon> abstractWeapon = entry.getValue();
           System.out.println(worm.getName() + ": " + abstractWeapon.size());
       }


   }





    @Override
    protected void drawMain(Graphics2D g, ImageObserver io) {

    }

}
