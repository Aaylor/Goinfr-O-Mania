package engine.weapons;

import engine.Entity;
import log.IGLog;
import sound.MSound;

import java.util.*;

public class Weapon {

    private static final Map<String, Weapon> map = new HashMap<>();

    public static final int RANGED = 0;
    public static final int MELEE  = 1;

    private String name;

    private Entity owner;

    private int type;

    private List<MSound> sounds;
    private int soundsCpt;

    private double range;

    private int minDamage;

    private int maxDamage;

    private long cooldown;
    private boolean cooldownReady;

    private Weapon(String name, int type, List<MSound> sounds, double range,
                  int minDamage, int maxDamage, long cooldown) {
        this.name = name;
        owner = null;
        this.type = type;
        this.sounds = sounds;
        soundsCpt = 0;
        this.range = range;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.cooldown = cooldown;
        cooldownReady = true;
    }

    private Weapon(Weapon weapon) {
        name = weapon.name;
        owner = null;
        type = weapon.type;
        sounds = new LinkedList<>(weapon.sounds);
        soundsCpt = 0;
        range = weapon.range;
        minDamage = weapon.minDamage;
        maxDamage = weapon.maxDamage;
        cooldown = weapon.cooldown;
        cooldownReady = true;
    }

    public static void register(String name, int type, Map<String, String> sounds, double range,
                                int minDamage, int maxDamage, long cooldown) {
        if (map.containsKey(name)) {
            throw new IllegalArgumentException("Weapon::register -> " +
                    name + " already registered.");
        }

        if (type != RANGED && type != MELEE) {
            throw new IllegalArgumentException("Weapon::register -> " +
                    name + ", illegal type argument."
            );
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

        map.put(name, new Weapon(name, type, createdSounds, range, minDamage,
                maxDamage, cooldown));
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
        Weapon.register("punch", Weapon.MELEE, punchSounds, 10, 1, 2, 1000);

    }

    private void playNextSound() {
        ++soundsCpt;
        if (soundsCpt >= sounds.size())
            soundsCpt = 0;

        if (soundsCpt < sounds.size())
            sounds.get(soundsCpt).play();
    }

    private void launchCooldown() {
        if (cooldownReady) {
            new Thread() {
                @Override
                public void run() {
                    cooldownReady = false;
                    Date wanted = new Date(new Date().getTime() + cooldown);
                    do {
                        try {
                            Thread.sleep(cooldown);
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


    public boolean isRanged() {
        return type == RANGED;
    }

    public boolean isMelee() {
        return type == MELEE;
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

    public void attack(Entity entity) {
        playNextSound();
        launchCooldown();

        if (entity instanceof Attackable) {
            Random random = new Random();
            Attackable a = (Attackable)entity;

            int damage =
                    random.nextInt(
                            (getMaxDamage() - getMinDamage()) + 1
                    ) + getMinDamage();
            a.takeDamage(damage);
        }
    }


}
