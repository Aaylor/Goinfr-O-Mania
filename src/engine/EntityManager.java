package engine;

import engine.nutritionists.AbstractNutritionist;
import engine.weapons.Weapon;
import graphics.Circle;
import helpers.ExtMath;

import java.awt.geom.Point2D;
import java.util.*;


/**
 *  Manage every created entities.
 */
public class EntityManager {

    private final Glutton player;
    private final EntityView gluttonView;

    private LinkedList<AbstractNutritionist> nutritionists;
    private Map<AbstractNutritionist, EntityView> nutritionistsView;

    private LinkedList<Entity> others;
    private Map<Entity, EntityView> othersView;

    /**
     *  Create an empty manager.
     */
    public EntityManager(Glutton player, EntityView view){
        this.player = player;
        this.gluttonView = view;
        view.setEntity(player);
        player.setManager(this);

        nutritionists = new LinkedList<>();
        others = new LinkedList<>();

        nutritionistsView = new HashMap<>();
        othersView = new HashMap<>();
    }

    private void nutritionistsMove() {
        for (AbstractNutritionist nutritionist : nutritionists) {
            nutritionist.nextStep(this);
        }
    }

    /**
     *  Entity loop. Does every operations needed by entities.
     *  (Movable, Destruction, ...).
     */
    public void entityLoop() {
        nutritionistsMove();

        for (Entity entity : others) {
            if (collision(player, entity)) {
                player.effect(entity);
            } else {
                for (AbstractNutritionist nutritionist : nutritionists) {
                    if (collision(nutritionist, entity)) {
                        nutritionist.effect(entity);
                        break;
                    }
                }
            }
        }
    }

    private boolean checkCrossCollision(List<? extends Entity> entities, Entity e1,
                                        Circle position) {
        if (e1.isCrossable())
            return false;

        for (Entity e2 : entities) {
            if (e1 != e2 && !e2.isCrossable() && collision(position, e2))
                return true;
        }

        return false;
    }

    public boolean hasCrossCollision(Entity e1, Circle position) {
        LinkedList<Entity> lp = new LinkedList<>();
        lp.add(player);
        return checkCrossCollision(lp, e1, position) ||
                checkCrossCollision(nutritionists, e1, position) ||
                checkCrossCollision(others, e1, position);
    }


    /**
     *  Check if two entities are in collision.
     *  @param e1 The first entity.
     *  @param e2 The second entity.
     *  @return True if they collided.
     */
    public boolean collision(Entity e1, Entity e2) {
        return collision(e1.getBoundsCircle(), e2);
    }

    public boolean collision(Circle circle, Entity entity) {
        return circle.intersects(entity.getBoundsCircle());
    }

    public void attack(AbstractMovableEntity e) {
        Weapon w = e.getWeapon();

        if (w == null)
            return;

        if (!w.ready())
            return;

        LinkedList<Entity> all = new LinkedList<>();
        all.add(player);
        all.addAll(nutritionists);
        all.addAll(others);

        if (w.isMelee()) {
            /* Now check if there is anyone on the range. */
            Point2D center = e.getCenter();

            double weaponRadius = w.getRange() + e.getBoundsCircle().getRadius();
            Circle attackRange = new Circle(
                    e.getCenterX() - weaponRadius,
                    e.getCenterY() - weaponRadius,
                    weaponRadius
            );

            for (Entity attackedEntity : all) {

                if (attackedEntity == e)
                    continue;

                double llimit = ExtMath.addToAngle(e.getDirection(), -90);
                double hlimit = ExtMath.addToAngle(e.getDirection(), 90);

                /* Fourth step : calculate the new needed angle to face the player. */
                double dx = attackedEntity.getCenterX() - center.getX();
                double dy = attackedEntity.getCenterY() - center.getY();
                double nextAngle = ExtMath.addToAngle(Math.toDegrees(Math.atan2(dy, dx)), 360);

                if (((llimit < hlimit && nextAngle >= llimit && nextAngle <= hlimit) ||
                    (llimit > hlimit && (nextAngle >= llimit || nextAngle <= hlimit))) &&
                    attackRange.intersects(attackedEntity.getBoundsCircle())) {
                    System.out.println("L'entité (" + attackedEntity + ") a été touché.");
                    w.attack(attackedEntity);
                }

            }

        } else if (w.isRanged()) {


        } else {
            /* Do nothing here */
        }

    }

    /**
     *  Add a new entity into the manager.
     *  @param e The entity to add.
     */
    public void addEntity(Entity e, EntityView view){
        others.addFirst(e);
        e.setManager(this);
        othersView.put(e, view);
        view.setEntity(e);
    }

    /**
     *  Remove entity of the manager.
     *  If the entity doesn't exists, the function returns false.
     *  @param e The entity to remove.
     *  @return True if entity has been removed correctly.
     */
    public boolean removeEntity(Entity e){
        if (others.remove(e)) {
            e.setManager(null);
            othersView.remove(e);
            return true;
        }

        return false;
    }

    /**
     *  Add a new nutritionist into the manager.
     *  @param n The new nutritionist.
     */
    public void addNutritionist(AbstractNutritionist n, EntityView view) {
        nutritionists.addFirst(n);
        n.setManager(this);
        nutritionistsView.put(n, view);
        view.setEntity(n);
    }

    /**
     *  Remove the nutritionist of the manager.
     *  If the nutritionist doesn't exists, the function returns false.
     *  @param n The nutritionist to remove.
     *  @return True if nutritionist has been remove correctly.
     */
    public boolean removeNutritionist(AbstractNutritionist n) {
        if (nutritionists.remove(n)) {
            n.setManager(null);
            nutritionistsView.remove(n);
            return true;
        }

        return false;
    }


    public EntityView getGluttonView() {
        return gluttonView;
    }

    public Collection<EntityView> getNutritionistsView() {
        return nutritionistsView.values();
    }

    public Collection<EntityView> getOthersView() {
        return othersView.values();
    }

    public Glutton getGlutton() {
        return player;
    }
}
