package org.wcscda.worms.board.weapons;

import org.wcscda.worms.Config;
import org.wcscda.worms.Helper;
import org.wcscda.worms.board.ARBEHandlerGravity;
import org.wcscda.worms.board.AbstractBoardElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.ImageObserver;
import java.util.TimerTask;
import javax.swing.Timer;

public class GrenadeTimerAmmo extends AbstractAmmo implements ActionListener {

    private static final int EXPLOSION_RADIUS = 50;
    private static final int EXPLOSION_DAMAGE = 20;
    /*It changes the size of the bullet */
    private static final int GRENADE_RECT_SIZE = 10;
    private static final int INITIAL_SPEED = 1;
    private static final String imagePath = "src/resources/weapons/grenadetimer.png";
    private static Image image = null;
    private int initTime = 0;



    private static void initImages() {
        image = new ImageIcon(imagePath).getImage().getScaledInstance(50, 30, 0);
    }

    public GrenadeTimerAmmo(Double angle) {
        super(EXPLOSION_RADIUS, EXPLOSION_DAMAGE);
        createMovableRect(GRENADE_RECT_SIZE, GRENADE_RECT_SIZE);
        getMovable().setDirection(angle);
        getMovable().setSpeed(INITIAL_SPEED);
        setInitialPosition();
    }

    // Set Timer wid the delay time
    public void onIterationBegin() {
        if (initTime + 140 < Helper.getClock()) {
            explode();
            Helper.getCurrentWeapon().triggerAmmoExplosion();
        }

    }

    @Override
    public void colideWith(AbstractBoardElement movable, Point2D prevPosition) {
        getMovable().setPosition(prevPosition);
        onIterationBegin();
    }

    @Override
    protected void createMovableRect(int rectWidth, int rectHeight) {
        setMovable(new ARBEHandlerGravity(
                Helper.getWormX() - rectWidth / 2,
                Helper.getWormY() - rectHeight / 2,
                rectWidth,
                rectHeight,
                this));
        setInitTime(Helper.getClock());

    }

    public void setInitTime(int initTime) {
        this.initTime = initTime;
    }
    @Override
    public void drawMain(Graphics2D g, ImageObserver io) {

        if (image == null) {
            initImages();
        }

        AffineTransform trans =
                AffineTransform.getTranslateInstance(getMovable().getCenterX() + 20, getMovable().getCenterY() - 20);
        trans.scale(-1, 1);

        g.drawImage(image, trans, io);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}