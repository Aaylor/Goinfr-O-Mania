package engine;

import java.awt.event.KeyEvent;

public class KeyConfiguration {

    private static final KeyConfiguration defaultConfiguration = new KeyConfiguration();

    private int up;
    private int down;
    private int left;
    private int right;

    private int quit;
    private int save;
    private int load;
    private int pause;

    private int menu;

    public KeyConfiguration() {
        up    = KeyEvent.VK_UP;
        down  = KeyEvent.VK_DOWN;
        left  = KeyEvent.VK_LEFT;
        right = KeyEvent.VK_RIGHT;
        quit  = KeyEvent.VK_Q;
        save  = KeyEvent.VK_S;
        load  = KeyEvent.VK_L;
        pause = KeyEvent.VK_P;
        menu  = KeyEvent.VK_ESCAPE;
    }


    public static KeyConfiguration getDefaultConfiguration() {
        return defaultConfiguration;
    }

    public int getUp() {
        return up;
    }

    public int getDown() {
        return down;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getQuit() {
        return quit;
    }

    public int getSave() {
        return save;
    }

    public int getLoad() {
        return load;
    }

    public int getPause() {
        return pause;
    }
}
