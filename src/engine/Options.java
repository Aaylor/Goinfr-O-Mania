package engine;

import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * Created by PixelMan on 24/05/15.
 */
public class Options {

    /* Difficulty */
    public static final int EASY    = 0;
    public static final int NORMAL  = 1;
    public static final int HARD    = 2;

    private double volume;
    private int difficulty;

    /* Constructors */

    public Options(int volume, int difficulty) {
        this.volume = volume;
        this.difficulty = difficulty;

    }

    public Options(Options toCopy){
        this.volume = toCopy.volume;
        this.difficulty = toCopy.difficulty;
    }


    /* Getters */

    public double getVolume() {
        return volume;
    }

    public int getDifficulty() {
        return difficulty;
    }

    /* Setters */

    /**
     * Sets the options volume. It must be a value between 0.0 (mute) and 1.0 (maximum volume)
     * @param volume The new volume
     * @throws InvalidArgumentException if volume is not between 0.0 and 1.0.
     */
    public void setVolume(double volume) throws InvalidArgumentException {
        if(volume < 0. || volume > 1.)
            throw new InvalidArgumentException(null);
        this.volume = volume;
    }

    /**
     * Sets the difficulty of the game. The new difficulty must be EASY, NORMAL or HARD.
     * @param difficulty The new difficulty
     * @throws InvalidArgumentException if the new diffuclty is not EASY, NORMAL, or HARD.
     */
    public void setDifficulty(int difficulty) throws InvalidArgumentException {
        if(difficulty < EASY || difficulty > HARD)
            throw new InvalidArgumentException(null);
        this.difficulty = difficulty;
    }
}
