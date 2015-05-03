package engine;


public abstract class AbstractWeapon {

    private Entity owner;

    private double damage;

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

}
