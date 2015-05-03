package engine;


public abstract class AbstractWeapon {

    private Entity owner;

    private double damage;

    private Skin skin;

    public AbstractWeapon(double damage) {
        this.damage = damage;
        this.owner = null;
        this.skin = new Skin(20, 20);
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
}
