package engine;

import graphics.Board;
import sound.MSound;
import sound.SoundManager;

public class Level {

    private Score actualScore;
    private Goal goal;
    private EntityManager em;
    private SoundManager sm;
    private MSound actualMusic;
    private Board board;

    public Level(Score actualScore, Goal goal, EntityManager em, SoundManager sm, MSound music, Board board) {
        this.actualScore = actualScore;
        this.goal = goal;
        this.em = em;
        this.sm = sm;
        this.actualMusic = music;
        this.board = board;
    }
}
