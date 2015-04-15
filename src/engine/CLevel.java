package engine;

import graphics.CBoard;

/**
 * Created by Aaylor, Tratost, PixelMan on 15/04/15.
 */
public class CLevel {

    /* Attributes */

    /**
     * The game level that must be controlled
     */
    MLevel level;

    /**
     * The controller of the board corresponding of the level
     */
    CBoard board_controller;



    /* Constructors */

    public CLevel(MLevel level, CBoard board_controller) {
        this.level = level;
        this.board_controller = board_controller;
    }



    /* Getters */

    public MLevel getLevel() {
        return level;
    }

    public CBoard getBoard_controller() {
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
