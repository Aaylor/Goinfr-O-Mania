package sound;

import javafx.scene.media.AudioClip;

import java.io.File;
import java.util.Hashtable;
import java.util.List;

/**
 *  Class to handle sound, with memory.
 *  If a sound is created with the same name, it returns the save AudioClip.
 */
public class MSound {

    /**
     *  Save the AudioClip to the given name.
     */
    private static Hashtable<String, AudioClip> memory;

    /**
     *  The prefix given to every file.
     */
    private static String prefix = "file://";


    static {
        memory = new Hashtable<>();
    }


    /**
     *  The saved clip.
     */
    private AudioClip clip;

    /**
     *  Try to find the sound into the memory.
     *  @param name The sound name.
     *  @throws java.lang.IllegalArgumentException If the name doesn't exists.
     */
    public MSound(String name) {
        if (!memory.containsKey(name)) {
            throw new IllegalArgumentException("name doesn't exists in memory");
        }

        this.clip = memory.get(name);
    }

    /**
     *  Try to find the sound into the memory, if it doesn't exists, create a new one.
     *  @param name The sound name.
     *  @param path The sound path.
     *  @throws java.lang.IllegalArgumentException If the path is not correct.
     */
    public MSound(String name, String path) {
        if (memory.containsKey(name)) {
            this.clip = memory.get(name);
        } else {
            File file = new File(path);
            if (!file.exists() || file.isDirectory()) {
                throw new IllegalArgumentException("invalid path.");
            }

            if (!file.isAbsolute()) {
                path = file.getAbsolutePath();
            }

            this.clip = new AudioClip(addPrefix(path));
            memory.put(name, this.clip);
        }
    }

    /**
     * Add the prefix if it doesn't already exists.
     * @param path The path.
     * @return The path with the new prefix.
     */
    private String addPrefix(String path) {
        if (path.startsWith(prefix)) {
            return path;
        } else {
            return prefix + path;
        }
    }

    public static boolean soundCurrentlyPlayed(List<MSound> sounds) {
        for (MSound m : sounds) {
            if (m.isPlaying())
                return true;
        }

        return false;
    }

    /**
     *  Play the sound.
     */
    public void play() {
        clip.play();
    }

    /**
     *  Play the sound with the given volume.
     *  @param volume The sound volume.
     */
    public void play(double volume) {
        clip.play(volume);
    }

    public void playTimes(int cycle) {
        clip.setCycleCount(cycle);
        play();
    }

    public void playInfinite() {
        clip.setCycleCount(AudioClip.INDEFINITE);
        play();
    }

    /**
     *  Check if the sound is already playing.
     *  @return true if the sound is already playing.
     */
    public boolean isPlaying() {
        return clip.isPlaying();
    }

    /**
     *  Stop the sound.
     */
    public void stop() {
        clip.stop();
    }
}
