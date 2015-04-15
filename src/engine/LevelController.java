package engine;

import graphics.BoardController;

/**
 * Created by Aaylor, Tratost, PixelMan on 15/04/15.
 */
public class LevelController {

    /* Attributes */

    /**
     * The game level that must be controlled
     */
    private Level level;

    /**
     * The controller of the board corresponding of the level
     */
    private BoardController board_controller;



    /* Constructors */

    public LevelController(Level level, BoardController board_controller) {
        this.level = level;
        this.board_controller = board_controller;
    }



    /* Getters */

    public Level getLevel() {
        return level;
    }

    public BoardController getBoard_controller() {
        return board_controller;
    }




    /* Level Controller Functionalities */

    /**
     * Starts the level.
     */
    public void start(){
        /* TODO : start() */
    }

    /**
     * Detects the victory of the player and updates the level.
     */
    public void hasWon(){
        /* TODO : hasWon() */
    }

    /**
     * Detects the defeat of the player and updates the level.
     */
    public void hasLost(){
        /* TODO : hasLost() */
    }


}
