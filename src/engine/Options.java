package engine;

import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * Created by PixelMan on 24/05/15.
 */
public class Options {

    double volume;

    /* Constructors */

    public Options(int volume) {
        this.volume = volume;
    }

    public Options(Options toCopy){
        this.volume = toCopy.volume;
    }


    /* Getters */

    public double getVolume() {
        return volume;
    }


    /* Setters */

    public void setVolume(double volume) throws InvalidArgumentException {
        if(volume < 0. || volume > 1.){
            String[] strings = new String[1];
            strings[0] = "volume must be a value between 0.0 and 1.0";
            throw new InvalidArgumentException(strings);
        }
        this.volume = volume;
    }
}
