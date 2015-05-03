package engine;

import java.awt.event.KeyEvent;

public class KeyConfiguration {

    private static final KeyConfiguration defaultConfiguration = new KeyConfiguration();

    private int up;
    private int down;
    private int left;
    private int right;
    private int attack;

    private int quit;
    private int save;
    private int load;
    private int pause;

    private int menu;

    public KeyConfiguration() {
        up     = KeyEvent.VK_UP;
        down   = KeyEvent.VK_DOWN;
        left   = KeyEvent.VK_LEFT;
        right  = KeyEvent.VK_RIGHT;
        attack = KeyEvent.VK_SPACE;
        quit   = KeyEvent.VK_Q;
        save   = KeyEvent.VK_S;
        load   = KeyEvent.VK_L;
        pause  = KeyEvent.VK_P;
        menu   = KeyEvent.VK_ESCAPE;
    }


    public static KeyConfiguration getDefaultConfiguration() {
        return defaultConfiguration;
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

    public int getQuit() {
        return quit;
    }

    public void setQuit(int quit) {
        this.quit = quit;
    }

    public int getSave() {
        return save;
    }

    public void setSave(int save) {
        this.save = save;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

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
