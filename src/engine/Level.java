package engine;

import graphics.Board;

/**
 * Created by tratost on 13/04/15.
 */
public class Level {

    private Score actualScore;
    private Goal goal;
    private Board board;

    public Level(Score actualScore, Goal goal, Board board) {
        this.actualScore = actualScore;
        this.goal = goal;
        this.board = board;
    }

    public Level() {
        this.actualScore = new Score();
        this.goal = new Goal();
        this.board = new Board();
    }
}
