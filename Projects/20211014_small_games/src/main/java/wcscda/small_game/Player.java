package wcscda.small_game;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    private String name;
    private Symbol symbol;
    private ArrayList<Symbol> gameMoves = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

   public void setSymbol(String symbol) {
        this.symbol.setChosenSymbol(symbol);
    }

}
