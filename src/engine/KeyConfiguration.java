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
        defaultConfiguration.up    = KeyEvent.VK_UP;
        defaultConfiguration.down  = KeyEvent.VK_DOWN;
        defaultConfiguration.left  = KeyEvent.VK_LEFT;
        defaultConfiguration.right = KeyEvent.VK_RIGHT;
        defaultConfiguration.quit  = KeyEvent.VK_Q;
        defaultConfiguration.save  = KeyEvent.VK_S;
        defaultConfiguration.load  = KeyEvent.VK_L;
        defaultConfiguration.pause = KeyEvent.VK_P;
        defaultConfiguration.menu  = KeyEvent.VK_ESCAPE;
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
