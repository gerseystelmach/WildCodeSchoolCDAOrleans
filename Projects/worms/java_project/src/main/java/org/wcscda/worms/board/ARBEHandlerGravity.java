package org.wcscda.worms.board;

import org.wcscda.worms.board.weapons.GrenadeAmmo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;

public class ARBEHandlerGravity extends ARBEWithGravity{

    private final IMovableHandler movableHandler;
    private static final int STANDING_RECTANGLE_HEIGHT = 5;

    private Rectangle2D supportRect;

    public ARBEHandlerGravity(
            double x, double y, int rectWidth, int rectHeight, IMovableHandler movableHandler) {
        super(x, y, rectWidth, rectHeight);
        this.movableHandler = movableHandler;
        supportRect =
                new Rectangle2D.Double(
                        x, y + rectHeight + STANDING_RECTANGLE_HEIGHT, rectWidth, STANDING_RECTANGLE_HEIGHT);
    }

    public Rectangle2D getOuterRect() {
        return supportRect;
    }

    public boolean isStandingOn(Shape s) {
        return s.intersects(getOuterRect());
    }

    @Override
    protected void drawMain(Graphics2D g, ImageObserver io) {
        movableHandler.drawMain(g, io);
    }

    @Override
    public void collideWith(AbstractBoardElement movable, Point2D prevPosition) {
        movableHandler.colideWith(movable, prevPosition);
    }

    @Override
    public boolean isCollidingWith(Shape s) {
        Boolean fromAdditionnal = movableHandler.isColidingWithAdditionnal(s);
        if (fromAdditionnal != null) {
            return fromAdditionnal;
        }
        return super.isCollidingWith(s);
    }

    public boolean isSubjectToGravity() {
        return true;
    }
}
