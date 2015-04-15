package engine;

/**
 * Created by tratost on 13/04/15.
 */
public class Goal {

    private int typeOfGoal;

    //CONSTRUCTORS

    public Goal(int typeOfGoal) {
        this.typeOfGoal = typeOfGoal;
    }

    public Goal() {
        this.typeOfGoal = 1;
    }

    //SETTERS

    public void setTypeOfGoal(int typeOfGoal) {
        this.typeOfGoal = typeOfGoal;
    }

    //GETTERS

    public int getTypeOfGoal() {
        return typeOfGoal;
    }

}
