package Model;

/**
 * Created by tratost on 13/04/15.
 */
public class MBackground {

    private String path;
    private int weight;
    private int height;


    //CONSTRUCTORS

    public MBackground(String path, int weight, int height) {
        this.path = path;
        this.weight = weight;
        this.height = height;
    }

    public MBackground() {
        this.path = "to complete !!!";
        this.weight = 0;
        this.height = 0;
    }

    //SETTERS

    public void setPath(String path) {
        this.path = path;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    //GETTERS

    public String getPath() {
        return path;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }
}
