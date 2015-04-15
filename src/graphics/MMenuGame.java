package graphics;

import engine.MDifficulties;
import engine.MLevel;
import engine.MPlayer;

import java.util.Observable;

/**
 * This class represent the modelisation of the menu game.
 */
public class MMenuGame extends Observable {

    private MPlayer actualPlayer;
    /*
    La table des score n'est peut-être pas nécessaire dans le model du menu du jeu,
    Le controleur de jeu permettra d'accéder ?
    Pareil pour les sauvegardes
    */
    private MDifficulties difficulties;
    private MLevel actualLevel;

    //CONSTRUCTORS

    public MMenuGame(MPlayer actualPlayer, MDifficulties difficulties, MLevel actualLevel) {
        this.actualPlayer = actualPlayer;
        this.difficulties = difficulties;
        this.actualLevel = actualLevel;
    }

    public MMenuGame() {
        this.actualPlayer = new MPlayer();
        this.difficulties = new MDifficulties();
        this.actualLevel = new MLevel();
    }

    //SETTERS

    public void setActualPlayer(MPlayer actualPlayer) {
        this.actualPlayer = actualPlayer;
    }

    public void setDifficulties(MDifficulties difficulties) {
        this.difficulties = difficulties;
    }

    public void setActualLevel(MLevel actualLevel) {
        this.actualLevel = actualLevel;
        setChanged();
        notifyObservers();
    }

    //GETTERS

    public MPlayer getActualPlayer() {
        return actualPlayer;
    }

    public MDifficulties getDifficulties() {
        return difficulties;
    }

    public MLevel getActualLevel() {
        return actualLevel;
    }
}
