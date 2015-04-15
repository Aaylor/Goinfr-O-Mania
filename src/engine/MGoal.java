package engine;

/**
 * Created by tratost on 13/04/15.
 */
public class MGoal {

    private int typeOfGoal;

    //CONSTRUCTORS

    public MGoal(int typeOfGoal) {
        this.typeOfGoal = typeOfGoal;
    }

    public MGoal() {
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
