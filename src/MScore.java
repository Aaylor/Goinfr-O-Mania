/**
 * Created by tratost on 13/04/15.
 */
public class MScore {

    private String name;
    private int value;


    //CONSTRUCTORS

    public MScore(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public MScore() {
        this.name = "Tester";
        this.value = 0;
    }

    //SETTERS

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    //GETTERS

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
