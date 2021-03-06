package engine;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Locale;
import java.util.prefs.Preferences;

/**
 * Created by PixelMan on 24/05/15.
 */
@SuppressWarnings("DefaultFileTemplate")
public class Settings {

    /* Langues */
    public static final int FRENCH = 0;
    public static final int ENGLISH = 1;

    /* Default values */
    private static final double       DEFAULT_VOLUME      = 1d;
    private static final Difficulties DEFAULT_DIFFICULTY  = Difficulties.MEDIUM;
    private static final int          DEFAULT_LANG        = ENGLISH;

    private Preferences preferences;

    private double volume;
    private double soundEffects;
    private Difficulties difficulty;
    private int lang;
    private KeyConfiguration keyConfiguration;

    /* Constructors */

    public Settings() {
        preferences = Preferences.userRoot().node(getClass().getName());

        volume           = preferences.getDouble("volume", DEFAULT_VOLUME);
        soundEffects     = preferences.getDouble("soundEffects", DEFAULT_VOLUME);
        difficulty       = Difficulties.difficultyOfInt(
                preferences.getInt("difficulty",Difficulties.intOfDifficulty(DEFAULT_DIFFICULTY))
        );
        lang             = preferences.getInt("lang", DEFAULT_LANG);
        keyConfiguration = new KeyConfiguration();
    }

    public Settings(Settings toCopy){
        this.volume = toCopy.volume;
        this.soundEffects = toCopy.soundEffects;
        this.difficulty = toCopy.difficulty;
        this.lang = toCopy.lang;
        this.keyConfiguration = new KeyConfiguration(toCopy.keyConfiguration);
        this.preferences = toCopy.preferences;
    }

    public void reset() {
        try {
            setVolume(DEFAULT_VOLUME);
            setDifficulty(DEFAULT_DIFFICULTY);
            setLang(DEFAULT_LANG);
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
    }

    /* Getters */

    public double getVolume() {
        return volume;
    }

    public double getSoundEffects() {
        return soundEffects;
    }

    public Difficulties getDifficulty() {
        return difficulty;
    }

    public int getLang() {
        return lang;
    }

    public KeyConfiguration getKeyConfiguration(){ return keyConfiguration; }

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

    public void setKeyConfiguration(KeyConfiguration keys){
        keyConfiguration = keys;
    }

    /**
     * Sets the options volume. It must be a value between 0.0 (mute) and 1.0 (maximum volume)
     * @param soundEffects The new volume
     * @throws InvalidArgumentException if volume is not between 0.0 and 1.0.
     */
    public void setSoundEffects(double soundEffects) throws InvalidArgumentException {
        if(volume < 0. || volume > 1.)
            throw new InvalidArgumentException(null);
        this.soundEffects = soundEffects;
    }

    /**
     * Sets the difficulty of the game. The new difficulty must be EASY, NORMAL or HARD.
     * @param difficulty The new difficulty
     * @throws InvalidArgumentException if the new difficulty is not EASY, NORMAL, or HARD.
     */
    public void setDifficulty(int difficulty) throws InvalidArgumentException {
        if (difficulty < 0 || difficulty > 2)
            throw new InvalidArgumentException(null);
        this.difficulty = Difficulties.difficultyOfInt(difficulty);
    }

    public void setDifficulty(Difficulties d) {
        this.difficulty = d;
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

    public void savePreferences(){
        preferences.putDouble("volume", volume);
        preferences.putDouble("soundEffects", soundEffects);
        preferences.putInt("difficulty", Difficulties.intOfDifficulty(difficulty));
        preferences.putInt("lang", lang);
        keyConfiguration.savePreferences();
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
