package graphics;

import engine.Entity;
import engine.Level;
import engine.Player;
import sound.MSound;

import java.util.LinkedList;
import java.util.Observable;

public class Board extends Observable {

    private Background background;
    private Player currentPlayer;
    private Level currentLevel;

    public Board(Background background) {
        this.background = background;
    }

    public Board(Player player, Level level) {
        this.background    = null;
        this.currentPlayer = player;
        this.currentLevel  = level;
    }

    public Player getPlayer() {
        return currentPlayer;
    }

    public Level getLevel() {
        return currentLevel;
    }

}
