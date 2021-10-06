package org.wcscda.worms.board;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;

public class ARBEHandlerGravity extends ARBEWithGravity {

    private final IMovableHandler movableHandler;
    private static final int STANDING_RECTANGLE_HEIGHT = 5;

    public ARBEHandlerGravity(
            double x, double y, int rectWidth, int rectHeight, IMovableHandler movableHandler) {
        super(x, y, rectWidth, rectHeight);
        this.movableHandler = movableHandler;
    }

    @Override
    protected void drawMain(Graphics2D g, ImageObserver io) {
        movableHandler.drawMain(g, io);
    }

    @Override
    public void collideWith(AbstractBoardElement movable, Point2D prevPosition) {
        movableHandler.colideWith(movable, prevPosition);
    }

 /*   @Override
    public boolean isCollidingWith(Shape s) {
        Boolean fromAdditionnal = movableHandler.isColidingWithAdditionnal(s);
        if (fromAdditionnal != null) {
            return fromAdditionnal;
        }
        return super.isCollidingWith(s);
    }*/
}