package wcscda.small_game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

/** Hello world! */
public class SmallGameApp extends JFrame {

  private static Player player1;
  private static Player player2;
  public static final Morpion morpionGame = new Morpion();

  public static void main(String[] args) {
    EventQueue.invokeLater(
            () -> {
              SmallGameApp sga = new SmallGameApp();
              sga.setVisible(true);
            });
  }

  private Board board;

  public SmallGameApp() {

    board = new Board(morpionGame);
    morpionGame.createCases();

    player1 = new Player("Gersey");
    player1.setSymbol("x");
    player2 = new Player("Thuy");
    player2.setSymbol("o");



    add(board);

    setResizable(false);
    pack();
    setTitle("WCS-CDA - Small game");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public Board getBoard() {
    return board;
  }
}
