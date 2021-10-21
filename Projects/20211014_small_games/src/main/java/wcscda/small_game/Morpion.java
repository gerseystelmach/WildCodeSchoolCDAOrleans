package wcscda.small_game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Morpion extends Drawable implements SmallGameInterface {

    private static ArrayList<Case> gameCases = new ArrayList<>();

    public void createCases() {
        int valueXIncrease = 0;
        int valueXIncrease1 = 0;
        int valueXIncrease2 = 0;

        for (int i = 0; i < 9; i++) {

            if (i <= 2) {
                gameCases.add(new Case(400 + valueXIncrease, 160));
                valueXIncrease += 200;
            }
            valueXIncrease1 = 0;
            if (i > 2 && i <= 5) {
                gameCases.add(new Case(400 + valueXIncrease1, 350));
                valueXIncrease1 += 200;
            }
            valueXIncrease2 = 0;
            if (i > 5) {
                gameCases.add(new Case(400 + valueXIncrease2, 530));
                valueXIncrease2 += 200;
            }
        }
    }

    public static ArrayList<Case> getGameCases() {
        return gameCases;
    }

    public void setGameCases(ArrayList<Case> gameCases) {
        this.gameCases = gameCases;
    }

    @Override
    public void draw(Graphics2D g, ImageObserver io) {
        g.setColor(Color.pink);
        g.setFont(new Font("Roboto", Font.BOLD, 50));
        drawGrid(g, io);
        g.drawString("Morpion", 150, 50);

<<<<<<< HEAD
=======
        g.setFont(new Font("Roboto", Font.BOLD, 50));
        g.drawString("TicTacToe", 50, 50);
>>>>>>> 00bc508c52041d940e63bc4703a0c56884a2caa8
    }

    private void drawGrid(Graphics2D g, ImageObserver io) {

        int x1Vertical = 500;
        int y1Vertical = 100;
        int x2Vertical = 500;
        int y2Vertical = 600;

        int x1Horizontal = 300;
        int y1Horizontal = 250;
        int x2Horizontal = 900;
        int y2Horizontal = 250;

        int valueLine = 0;
        /* Drawing vertical lines */
        for (int i = 0; i < 2; i++) {

            g.drawLine(x1Vertical + valueLine, y1Vertical, x2Vertical + valueLine, y2Vertical);
            valueLine += 200;
        }
        valueLine = 0;
        /* Drawing horizontal lines */
        for (int i = 0; i < 2; i++) {
            g.drawLine(x1Horizontal, y1Horizontal + valueLine, x2Horizontal, y2Horizontal + valueLine);
            valueLine += 200;
        }
    }
}
