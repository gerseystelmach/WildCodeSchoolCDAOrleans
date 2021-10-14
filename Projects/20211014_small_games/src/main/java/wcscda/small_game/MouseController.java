package wcscda.small_game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;

import static wcscda.small_game.SmallGameApp.morpionGame;

public class MouseController extends MouseAdapter {
    private final Board board;
    private Morpion morpion;


    public MouseController(Board board) {
        this.board = board;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        board.redraw(new Drawable(){
            @Override
            public void draw(Graphics2D g, ImageObserver io) {
                int x = mouseEvent.getX();
                int y = mouseEvent.getY();
                System.out.println("Position X " + x);
                System.out.println("Position Y " + y);

                g.setColor(Color.WHITE);

      /* TO DO Create a loop for the players to recover the symbol*/

            for (Case caseOfGame : Morpion.getGameCases()) {
                if(Player)
                if (mouseEvent.getX() ==  caseOfGame.getX() && mouseEvent.getY() == caseOfGame.getY()) {
                    g.drawLine(x - 30, y - 30, x + 30, y +30);
                    g.drawLine(x + 30, y - 30, x - 30, y +30);
                    if (mouseEvent.getY() < caseOfGame.getY()) {

                    }

                }
            }

    }

    /*@Override
    public void mousePressed(MouseEvent mouseEvent) {

    }*/
}
