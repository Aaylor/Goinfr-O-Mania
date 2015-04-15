package engine;

import graphics.MBoard;

/**
 * Created by tratost on 13/04/15.
 */
public class MLevel {

    private MScore actualScore;
    private MGoal goal;
    private MBoard board;

    public MLevel(MScore actualScore, MGoal goal, MBoard board) {
        this.actualScore = actualScore;
        this.goal = goal;
        this.board = board;
    }

    public MLevel() {
        this.actualScore = new MScore();
        this.goal = new MGoal();
        this.board = new MBoard();
    }
}
