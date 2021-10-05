package org.wcscda.worms.board.weapons;

import org.wcscda.worms.Config;
import org.wcscda.worms.Helper;
import org.wcscda.worms.board.AbstractBoardElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;
import java.util.TimerTask;
import javax.swing.Timer;

public class GrenadeTimerAmmo extends AbstractAmmo implements ActionListener {
    private static final int EXPLOSION_RADIUS = 150;
    private static final int EXPLOSION_DAMAGE = 18;
    /*It changes the size of the bullet */
    private static final int GRENADE_RECT_SIZE = 10;
    private static final int INITIAL_SPEED = 1;
    private static final String imagePath = "src/resources/weapons/grenadetimer.png";
    private static Image image = null;



    private static void initImages() {
        image = new ImageIcon(imagePath).getImage().getScaledInstance(50, 30, 0);
    }

    public GrenadeTimerAmmo(Double angle) {
        super(EXPLOSION_RADIUS, EXPLOSION_DAMAGE);
        createMovableRect(GRENADE_RECT_SIZE, GRENADE_RECT_SIZE);
        getMovable().setDirection(angle);
        getMovable().setSpeed(INITIAL_SPEED);

    }


      @Override
        public void colideWith(AbstractBoardElement movable, Point2D prevPosition) {

          new java.util.Timer().schedule(
                  new java.util.TimerTask() {
                      @Override
                      public void run() {
                          explode();
                          Helper.getCurrentWeapon().triggerAmmoExplosion();
                      }
                  },
                  1000
          );
          System.out.println();
    }

    @Override
    public void drawMain(Graphics2D g, ImageObserver io) {
        if (image == null) {
            initImages();
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
