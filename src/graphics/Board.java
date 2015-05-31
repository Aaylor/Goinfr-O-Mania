package graphics;

import engine.*;
import engine.effects.SlowSpeedBuff;
import helpers.Chrono;
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


    public void sendGluttonMovement(Movable.Direction d, boolean b) {
        Glutton glutton = currentLevel.getEntityManager().getGlutton();
        glutton.move(d, b);
        setChanged();
        notifyObservers();
    }

    public void sendGluttonAttack() {
        Glutton glutton = currentLevel.getEntityManager().getGlutton();
        glutton.getManager().attack(glutton);
    }

    public void notification() {
        setChanged();
        notifyObservers();
    }

}
