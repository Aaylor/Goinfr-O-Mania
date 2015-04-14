package Model;

/**
 * Created by tratost on 13/04/15.
 */
public class MLevel {

    private MScore actualScore;
    private MGoal goal;
    private MPan pan;

    public MLevel(MScore actualScore, MGoal goal, MPan pan) {
        this.actualScore = actualScore;
        this.goal = goal;
        this.pan = pan;
    }

    public MLevel(MScore actualScore) {
        this.actualScore = new MScore();
        this.goal = new MGoal();
        this.pan = new MPan();
    }
}
