package graphics;

import engine.*;
import helpers.Chrono;
import sound.MSound;

import java.util.LinkedList;
import java.util.Observable;

public class Board extends Observable {

    private Background background;
    private Player currentPlayer;
    private Level currentLevel;

    private Chrono chrono;

    public Board(Background background) {
        this.background = background;
    }

    public Board(Player player, Level level) {
        this.background    = null;
        this.currentPlayer = player;
        this.currentLevel  = level;
        chrono = new Chrono();
    }

    public Player getPlayer() {
        return currentPlayer;
    }

    public Level getLevel() {
        return currentLevel;
    }

    public Chrono getChrono() {
        return chrono;
    }

    public void sendGluttonMovement(Movable.Direction d) {
        Glutton glutton = currentLevel.getEntityManager().getGlutton();
        glutton.move(d);
        setChanged();
        notifyObservers();
    }

    public void sendGluttonAttack() {
        Glutton glutton = currentLevel.getEntityManager().getGlutton();
        glutton.getManager().attack(glutton);
    }

    /* XXX: Remove this. */
    public void notification() {
        setChanged();
        notifyObservers();
    }

}
