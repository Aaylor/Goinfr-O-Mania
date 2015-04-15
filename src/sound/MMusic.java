package sound;

/**
 * Created by tratost on 13/04/15.
 */
public class MMusic {

    //in second ?
    private int timer;
    private int actualTime;
    private String path;

    //CONSTRUCTORS

    public MMusic(int timer, int actualTime, String path) {
        this.timer = timer;
        this.actualTime = actualTime;
        this.path = path;
    }

    public MMusic() {
        this.timer = 50;
        this.actualTime = 0;
        this.path = "must complete because.... this is not a file";
    }

    //SETTERS

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public void setActualTime(int actualTime) {
        this.actualTime = actualTime;
    }

    public void setPath(String path) {
        this.path = path;
    }

    //GETTERS

    public int getTimer() {
        return timer;
    }

    public int getActualTime() {
        return actualTime;
    }

    public String getPath() {
        return path;
    }
}
