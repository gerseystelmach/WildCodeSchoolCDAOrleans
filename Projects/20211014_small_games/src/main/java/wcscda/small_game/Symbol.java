package wcscda.small_game;

import javax.swing.*;
import java.awt.*;

public class Symbol {

    private static Image symbolX = null;
    private static Image symbolCircle = null;
    private static final String symbolXResource = "src/main/java/wcscda/small_game/resources/xsymbol.png";
    private static final String symbolCircleResource = "src/main/java/wcscda/small_game/resources/circle.png";
    private final Player player;
    private String chosenSymbol;
    private Boolean isSymbolXBeenUsed;
    private Boolean isSymbolCircleBeenUsed;

    protected Symbol(Player player) {
        this.player = player;
    }

    private static void initImages() {


        symbolX =  new ImageIcon(symbolXResource).getImage().getScaledInstance(60, 60, 0);

        symbolCircle =  new ImageIcon(symbolCircleResource).getImage().getScaledInstance(60, 60, 0);
    }

    public Player getPlayer() {
        return player;
    }

    public Boolean getSymbolXBeenUsed() {
        return isSymbolXBeenUsed;
    }

    public void setSymbolXBeenUsed(Boolean symbolXBeenUsed) {
        isSymbolXBeenUsed = symbolXBeenUsed;
    }

    public Boolean getSymbolCircleBeenUsed() {
        return isSymbolCircleBeenUsed;
    }

    public void setSymbolCircleBeenUsed(Boolean symbolCircleBeenUsed) {
        isSymbolCircleBeenUsed = symbolCircleBeenUsed;
    }

    public String getChosenSymbol() {
        return chosenSymbol;
    }

    public void setChosenSymbol(String chosenSymbol) {
        this.chosenSymbol = chosenSymbol;
    }
}
