package sound;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Hashtable;
import java.util.List;

public class MMusic {

    /**
     *  Save the AudioClip to the given name.
     */
    private static Hashtable<String, MediaPlayer> memory;

    /**
     *  The prefix given to every file.
     */
    private static String prefix = "file://";

    private static double volumeGlobal=1;


    static {
        memory = new Hashtable<>();
    }


    /**
     *  The saved clip.
     */
    private MediaPlayer mp;

    /**
     *  Try to find the sound into the memory.
     *  @param name The sound name.
     *  @throws java.lang.IllegalArgumentException If the name doesn't exists.
     */
    public MMusic(String name) {
        if (!memory.containsKey(name)) {
            throw new IllegalArgumentException("name doesn't exists in memory");
        }

        this.mp = memory.get(name);
    }

    /**
     *  Try to find the sound into the memory, if it doesn't exists, create a new one.
     *  @param name The sound name.
     *  @param path The sound path.
     *  @throws java.lang.IllegalArgumentException If the path is not correct.
     */
    public MMusic(String name, String path) {
        final JFXPanel fxPanel = new JFXPanel();
        if (memory.containsKey(name)) {
            this.mp = memory.get(name);
        } else {
            File file = new File(path);
            if (!file.exists() || file.isDirectory()) {
                throw new IllegalArgumentException("invalid path.");
            }

            if (!file.isAbsolute()) {
                path = file.getAbsolutePath();
            }


            Media m = new Media(addPrefix(path));
            this.mp = new MediaPlayer(m);
            memory.put(name, this.mp);
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

    public static boolean soundCurrentlyPlayed(List<MMusic> musics) {
        for (MMusic m : musics) {
            if (m.isPlaying())
                return true;
        }

        return false;
    }

    /**
     *  Play the sound.
     */
    public void play() {
        mp.setVolume(volumeGlobal);
        mp.play();
    }

    /**
     *  Play the sound with the given volume.
     *  @param volume The sound volume.
     */
    public void play(double volume) {
        volumeGlobal = volume;
        mp.setVolume(volume);
        mp.play();
    }

    public void playTimes(int cycle) {
        mp.setCycleCount(cycle);
        play();
    }

    public void playInfinite() {
        mp.setCycleCount(MediaPlayer.INDEFINITE);
        play();
    }

    /**
     *  Check if the sound is already playing.
     *  @return true if the sound is already playing.
     */
    public boolean isPlaying() {
        return (mp.getStatus() == MediaPlayer.Status.PLAYING);
    }

    public void setVolume(double volume) {
        volumeGlobal = volume;
        mp.setVolume(volume);
    }

    public void pause() {
        mp.pause();
    }

    /**
     *  Stop the sound.
     */
    public void stop() {
        mp.stop();
    }
}
