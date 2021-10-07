package org.wcscda.worms.board;

import org.wcscda.worms.Helper;
import org.wcscda.worms.Player;
import org.wcscda.worms.Worm;
import org.wcscda.worms.board.weapons.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Inventory extends AbstractDrawableElement {


    private final ArrayList<AbstractWeapon> weapons = new ArrayList<>();
    private final Map<Worm, ArrayList<AbstractWeapon>> wormWeaponInventory = new HashMap<>();
    private Boolean isInventoryOpen = false;
    private Worm worm;

    public Inventory(Worm worm) {
        this.worm = worm;
        weapons.add(new Shotgun());
        weapons.add(new Hadoken());
        weapons.add(new Grenade());
        weapons.add(new SuperGrenade());
        weapons.add(new GrenadeTimer());

        for (Player player : Helper.getTC().getPlayers()) {
            for (Worm iWorm : player.getWorms()) {
                wormWeaponInventory.put(iWorm, weapons);
            }
        }
    }

    public Boolean getInventoryOpen() {
        return isInventoryOpen;
    }

    public Map<Worm, ArrayList<AbstractWeapon>> getWormWeaponInventory() {
        return wormWeaponInventory;
    }

    public void setInventoryOpen(Boolean inventoryOpen) {
        isInventoryOpen = inventoryOpen;
    }

    @Override
    protected void drawMain(Graphics2D g, ImageObserver io) {
        if (getInventoryOpen()) {
            Rectangle2D.Double inventoryRect = new Rectangle2D.Double(Helper.getPC().getX() + 995, Helper.getPC().getY(), 200, 200);
            g.setColor(Color.lightGray);
            g.draw(inventoryRect);

            int pos = 50;
            for (Map.Entry<Worm, ArrayList<AbstractWeapon>> entry : wormWeaponInventory.entrySet()) {
                Worm worm = entry.getKey();
                if (worm.getName().equals(Helper.getActiveWorm().getName())) {
                    ArrayList<AbstractWeapon> abstractWeapon = entry.getValue();
                    g.drawString("Inventory of " + worm.getName(), (float) inventoryRect.getX() + 30, (float) inventoryRect.getY() + 20);
                    for (AbstractWeapon weapon : abstractWeapon) {
                        g.drawString(weapon.getClass().getSimpleName(), (float) inventoryRect.getX() + 30, (float) inventoryRect.getY() + pos);
                        pos += 30;
                    }

                }
            }

        }

    }
}

