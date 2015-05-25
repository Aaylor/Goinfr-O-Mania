package engine;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Locale;

/**
 * Created by PixelMan on 24/05/15.
 */
public class Settings {

    /* Difficulty */
    public static final int EASY    = 0;
    public static final int NORMAL  = 1;
    public static final int HARD    = 2;

    /* Langues */
    public static final int FRENCH = 0;
    public static final int ENGLISH = 1;

    private double volume;
    private int difficulty;
    private int lang;

    /* Constructors */

    public Settings(int volume, int difficulty) throws InvalidArgumentException {
        setVolume(volume);
        setDifficulty(difficulty);

    }

    public Settings(Settings toCopy){
        this.volume = toCopy.volume;
        this.difficulty = toCopy.difficulty;
        this.lang = toCopy.lang;
    }


    /* Getters */

    public double getVolume() {
        return volume;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getLang() {
        return lang;
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
     * @throws InvalidArgumentException if the new difficulty is not EASY, NORMAL, or HARD.
     */
    public void setDifficulty(int difficulty) throws InvalidArgumentException {
        if(difficulty < EASY || difficulty > HARD)
            throw new InvalidArgumentException(null);
        this.difficulty = difficulty;
    }

    /**
     * Sets the language of the game. The new language muste be FRENCH or ENGLISH.
     * @param lang the new language
     * @throws InvalidArgumentException if the new language is not FRENCH or ENGLISH.
     */
    public void setLang(int lang) throws InvalidArgumentException {
        if(lang < FRENCH || lang > ENGLISH)
            throw new InvalidArgumentException(null);
        this.lang = lang;
    }


    /* Functionalities */

    /**
     * Returns the Local associated with the options language.
     * @return The Local associated with the options language.
     */
    public Locale getLocale(){
        switch (lang){
            case FRENCH :
                return new Locale("fr", "FR");
            case ENGLISH :
                return new Locale("en", "GB");
            default :
                return Locale.getDefault();
        }
    }

}
