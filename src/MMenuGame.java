/**
 * This class represent the modelisation of the menu game.
 */
public class MMenuGame {

    private MPlayer actualPlayer;
    /*
    La table des score n'est peut-être pas nécessaire dans le model du menu du jeu,
    Le controleur de jeu permettra d'accéder ?
    Pareil pour les sauvegardes
    */
    private MDifficulties difficulties;

    //CONSTRUCTORS

    public MMenuGame(MPlayer actualPlayer, MDifficulties difficulties) {
        this.actualPlayer = actualPlayer;
        this.difficulties = difficulties;
    }

    public MMenuGame() {
        this.actualPlayer = new MPlayer();
        this.difficulties = new MDifficulties();
    }

    //SETTERS

    public void setActualPlayer(MPlayer actualPlayer) {
        this.actualPlayer = actualPlayer;
    }

    public void setDifficulties(MDifficulties difficulties) {
        this.difficulties = difficulties;
    }

    //GETTERS

    public MPlayer getActualPlayer() {
        return actualPlayer;
    }

    public MDifficulties getDifficulties() {
        return difficulties;
    }
}
