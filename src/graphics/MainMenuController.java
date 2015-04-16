package graphics;

import engine.LevelController;

/**
 * Created by Aaylor, Tratost, PixelMan on 15/04/15.
 */
public class MainMenuController {

    /* Attributes */

    /**
     * The game main menu that must be controlled
     */
    private MainMenu mainMenu;

    /**
     * The controller of the actual level
     */
    private LevelController levelController;

    /* TODO : Add those attributes :
        - Difficulty controller
        - Scores controller
        - Save controller
        - Level edition controller
     */


    /* Constructors */

    public MainMenuController(MainMenu mainMenu, LevelController levelController) {
        this.mainMenu = mainMenu;
        this.levelController = levelController;
    }



    /* Getters */

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public LevelController getLevelController() {
        return levelController;
    }




    /* Main Menu Controller functionalities */

    /**
     * Runs the main menu.
     */
    public void run(){
        /* TODO : run() */
    }



    /* /!\ Not implemented yet /!\ */

    /**
     * Changes the difficulty of the game
     */
    public void changeDifficulty(){
        /* TODO : changeDifficulty() */
    }

    /**
     * Load a game.
     */
    public void loadGame(){
        /* TODO : loadGame() */
    }

    /**
     * Save the current game.
     */
    public void saveGame(){
        /* TODO : saveGame() */
    }

    /**
     * TODO : options() documentation
     */
    public void options(){
        /* TODO : options() */
    }

    /**
     * Shows the scores of the several games.
     */
    public void showScores(){
        /* TODO : showScores () */
    }

     /* /!\ Not implemented yet /!\ */


}