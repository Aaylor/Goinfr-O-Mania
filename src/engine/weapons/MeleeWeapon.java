package engine.weapons;

import sound.MSound;

import java.util.List;

public class MeleeWeapon extends AbstractWeapon {

    private double range;

    public MeleeWeapon(double damage, List<MSound> sounds, int range) {
        super(damage, sounds);
        this.range = range;
    }

    @Override
    public void attack() {
        playSound();
    }

    @Override
    public void playSound() {
        MSound s = super.nextSound();

        if (s == null) return;

        if (!MSound.soundCurrentlyPlayed(getSounds())) {
            s.play();
        }
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }
}
