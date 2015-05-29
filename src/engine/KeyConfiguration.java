package engine;

import java.awt.event.KeyEvent;
import java.util.prefs.Preferences;

public class KeyConfiguration {

    private int up;
    private int down;
    private int left;
    private int right;
    private int attack;

    private Preferences preferences;

    //private int quit;
    //private int save;
    //private int load;
    private int pause;

    private int menu;

    public KeyConfiguration() {
        preferences = Preferences.userRoot().node(getClass().getName());

        up     = preferences.getInt("up", KeyEvent.VK_UP);
        down   = preferences.getInt("down", KeyEvent.VK_DOWN);
        left   = preferences.getInt("left", KeyEvent.VK_LEFT);
        right  = preferences.getInt("right", KeyEvent.VK_RIGHT);
        attack = preferences.getInt("attack", KeyEvent.VK_SPACE);
        //quit   = preferences.getInt("quit", KeyEvent.VK_Q);
        //save   = preferences.getInt("save", KeyEvent.VK_S);
        //load   = preferences.getInt("load", KeyEvent.VK_L);
        pause  = preferences.getInt("pause", KeyEvent.VK_P);
        menu   = preferences.getInt("menu", KeyEvent.VK_ESCAPE);
    }

    public KeyConfiguration(KeyConfiguration toCopy) {
        preferences = toCopy.preferences;
        up = toCopy.getUp();
        down = toCopy.getDown();
        left = toCopy.getLeft();
        right = toCopy.getRight();
        attack = toCopy.getAttack();
        pause = toCopy.getPause();
        menu = toCopy.getMenu();

    }

    public void defaultConfiguration() {
        up     = KeyEvent.VK_UP;
        down   = KeyEvent.VK_DOWN;
        left   = KeyEvent.VK_LEFT;
        right  = KeyEvent.VK_RIGHT;
        attack = KeyEvent.VK_SPACE;
        //quit   = KeyEvent.VK_Q;
        //preferences.putInt("quit", KeyEvent.VK_Q);
        //save   = KeyEvent.VK_S;
        //preferences.putInt("save", KeyEvent.VK_S);
        //load   = KeyEvent.VK_L;
        //preferences.putInt("load", KeyEvent.VeK_L);
        pause  = KeyEvent.VK_P;
        menu   = KeyEvent.VK_ESCAPE;
    }

    public void savePreferences() {
        preferences.putInt("up", up);
        preferences.putInt("down", down);
        preferences.putInt("left", left);
        preferences.putInt("right", right);
        preferences.putInt("attack", attack);
        preferences.putInt("pause", pause);
        preferences.putInt("menu", menu);
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    /*public int getQuit() {
        return quit;
    }

    public void setQuit(int quit) {
        preferences.putInt("quit", quit);
        this.quit = quit;
    }

    public int getSave() {
        return save;
    }

    public void setSave(int save) {
        preferences.putInt("save", save);
        this.save = save;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        preferences.putInt("load", load);
        this.load = load;
    }*/

    public int getPause() {
        return pause;
    }

    public void setPause(int pause) {
        this.pause = pause;
    }

    public int getMenu() {
        return menu;
    }

    public void setMenu(int menu) {
        this.menu = menu;
    }

}
