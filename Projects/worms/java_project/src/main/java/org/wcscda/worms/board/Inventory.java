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

    private Boolean isInventoryOpen = false;
    private final Worm worm;

    public Inventory(Worm worm) {
        this.worm = worm;
    }


    public Boolean getInventoryOpen() {
        return isInventoryOpen;
    }

    public void setInventoryOpen(Boolean inventoryOpen) {
        isInventoryOpen = inventoryOpen;
    }

    @Override
    protected void drawMain(Graphics2D g, ImageObserver io) {
        if (getInventoryOpen() && Helper.getActiveWorm() == worm) {
            Rectangle2D.Double inventoryRect = new Rectangle2D.Double(Helper.getPC().getX() + 995, Helper.getPC().getY(), 200, 250);
            g.setColor(Color.black);
            g.setStroke(new BasicStroke(5));
            g.draw(inventoryRect);

            g.drawString("Inventory of " + worm.getName(), (float) inventoryRect.getX() + 30, (float) inventoryRect.getY() + 20);
            int pos = 60;
            int posImg = 40;

            ArrayList<AbstractWeapon> wormWeapons = Helper.getActiveWorm().getWeapons();
            for (AbstractWeapon wormWeapon : wormWeapons) {
                g.drawImage(wormWeapon.getImage2(),(int)inventoryRect.getX() + 120, (int)inventoryRect.getY() + posImg, io);
                g.drawString(wormWeapon.getClass().getSimpleName(), (float) inventoryRect.getX() + 30, (float) inventoryRect.getY() + pos);
               pos += 40;
               posImg += 40;
            }


        }

    }
}
