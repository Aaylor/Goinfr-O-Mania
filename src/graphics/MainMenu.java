package graphics;

import engine.Difficulties;
import engine.Level;
import engine.Player;

import java.util.Observable;

/**
 * This class represent the modelisation of the menu game.
 */
public class MainMenu extends Observable {

    private Player actualPlayer;
    /*
    La table des score n'est peut-être pas nécessaire dans le model du menu du jeu,
    Le controleur de jeu permettra d'accéder ?
    Pareil pour les sauvegardes
    */
    private Difficulties difficulties;
    private Level actualLevel;

    //CONSTRUCTORS

    public MainMenu(Player actualPlayer, Difficulties difficulties, Level actualLevel) {
        this.actualPlayer = actualPlayer;
        this.difficulties = difficulties;
        this.actualLevel = actualLevel;
    }


    //SETTERS

    public void setActualPlayer(Player actualPlayer) {
        this.actualPlayer = actualPlayer;
    }

    public void setDifficulties(Difficulties difficulties) {
        this.difficulties = difficulties;
    }

    public void setActualLevel(Level actualLevel) {
        this.actualLevel = actualLevel;
        setChanged();
        notifyObservers();
    }

    //GETTERS

    public Player getActualPlayer() {
        return actualPlayer;
    }

    public Difficulties getDifficulties() {
        return difficulties;
    }

    public Level getActualLevel() {
        return actualLevel;
    }
}
