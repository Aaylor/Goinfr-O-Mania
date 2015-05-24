package engine.weapons;

import engine.Cooldown;
import engine.Entity;
import engine.EntityAssociation;
import engine.Skin;
import log.IGLog;
import sound.MSound;

import java.awt.image.BufferedImage;
import java.util.*;

public class Weapon {

    private static final Map<String, Weapon> map = new HashMap<>();

    private String name;

    private Entity owner;

    private List<MSound> sounds;
    private int soundsCpt;

    private double range;

    private int minDamage;

    private int maxDamage;

    private Cooldown cooldown;

    private Skin weaponSkin;


    private Weapon(String name, List<MSound> sounds, double range,
                  int minDamage, int maxDamage, long cooldown, Skin weaponSkin) {
        this.name = name;
        owner = null;
        this.sounds = sounds;
        soundsCpt = 0;
        this.range = range;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.cooldown = new Cooldown(cooldown);
        this.weaponSkin = weaponSkin;
    }

    private Weapon(Weapon weapon) {
        name       = weapon.name;
        owner      = null;
        sounds     = new LinkedList<>(weapon.sounds);
        soundsCpt  = 0;
        range      = weapon.range;
        minDamage  = weapon.minDamage;
        maxDamage  = weapon.maxDamage;
        cooldown   = new Cooldown(weapon.cooldown.getTime());
        try {
            weaponSkin = (Skin)weapon.weaponSkin.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public static void register(String name, Map<String, String> sounds, double range,
                                int minDamage, int maxDamage, long cooldown, Skin s) {
        if (map.containsKey(name)) {
            throw new IllegalArgumentException("Weapon::register -> " +
                    name + " already registered.");
        }

        if (minDamage > maxDamage) {
            throw new IllegalArgumentException("Weapon::register -> " +
                    name + ", minimal damage < maximal damage."
            );
        }

        LinkedList<MSound> createdSounds = new LinkedList<>();

        for (String key : sounds.keySet()) {
            String path = sounds.get(key);

            try {
                createdSounds.addLast(new MSound(key, path));
            } catch (Exception e) {
                IGLog.error("Unable to load <" + key + "> at \"" + path + "\"");
            }
        }

        map.put(name, new Weapon(name, createdSounds, range, minDamage,
                maxDamage, cooldown, s));
    }

    public static Weapon make(String name) {
        if (!map.containsKey(name)) {
            throw new IllegalArgumentException("Weapon::make -> " +
                    name + " doesn't exists."
            );
        }

        return new Weapon(map.get(name));
    }

    public static void defaults() {

        /* Punch */
        HashMap<String, String> punchSounds = new HashMap<>();
        punchSounds.put("left-punch", "sounds/left-punch.mp3");
        punchSounds.put("right-punch", "sounds/right-punch.mp3");
        Skin skinPunch = new Skin(20,20);
        try {
            BufferedImage[] c = EntityAssociation.createCharacterFromFile("pictures/animation/", 7, ".png");
            skinPunch = new Skin(c, 4);
        } catch (Exception e){}
        Weapon.register("punch", punchSounds, 10, 1, 2, 1000, skinPunch);

    }

    private void playNextSound() {
        ++soundsCpt;
        if (soundsCpt >= sounds.size())
            soundsCpt = 0;

        if (soundsCpt < sounds.size())
            sounds.get(soundsCpt).play();
    }


    public boolean ready() {
        return cooldown.isReady();
    }


    public Entity getOwner() {
        return owner;
    }

    public void setOwner(Entity owner) {
        this.owner = owner;
    }

    public double getRange() {
        return range;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public BufferedImage getNextAnimation() {
        return weaponSkin.move();
    }

    public boolean attack(Entity entity) {
        playNextSound();
        cooldown.start();
        weaponSkin.start(true);

        if (entity instanceof Attackable) {
            Random random = new Random();
            Attackable a = (Attackable)entity;

            int damage =
                    random.nextInt(
                            (getMaxDamage() - getMinDamage()) + 1
                    ) + getMinDamage();
            return a.takeDamage(damage);
        }

        return false;
    }


}
