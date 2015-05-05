package engine.weapons;


import engine.Entity;
import engine.Skin;
import sound.MSound;
import sound.Soundable;

import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class AbstractWeapon implements Soundable {

    private Entity owner;

    private double damage;

    private Skin skin;

    private Dimension dimension;

    private List<MSound> sounds;
    private int soundsCpt;

    private int cooldownTime;
    private volatile boolean cooldownReady;

    public AbstractWeapon(double damage, List<MSound> sounds) {
        this.damage = damage;
        this.owner = null;
        this.skin = new Skin(10, 10);
        this.dimension = new Dimension(10, 10);
        this.sounds = sounds;

        cooldownTime  = 1000;
        cooldownReady = true;
    }

    protected List<MSound> getSounds() {
        return sounds;
    }

    protected MSound nextSound() {
        ++soundsCpt;
        if (soundsCpt >= sounds.size())
            soundsCpt = 0;

        if (soundsCpt < sounds.size())
            return sounds.get(soundsCpt);

        return null;
    }

    protected void launchCooldown() {
        if (cooldownReady) {
            new Thread() {
                @Override
                public void run() {
                    cooldownReady = false;
                    Date wanted = new Date(new Date().getTime() + cooldownTime);
                    do {
                        try {
                            Thread.sleep(cooldownTime);
                            break;
                        } catch (InterruptedException e) {
                            if (new Date().after(wanted))
                                break;
                        }
                    } while (true);
                    cooldownReady = true;
                }
            }.start();
        }
    }

    public boolean ready() {
        return cooldownReady;
    }

    public abstract void attack();

    public Entity getOwner() {
        return owner;
    }

    public void setOwner(Entity owner) {
        this.owner = owner;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

}
