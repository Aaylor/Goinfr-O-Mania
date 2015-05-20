package engine;

import engine.cake.AbstractCake;
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

    private Glutton player;
    private EntityView gluttonView;

    private LinkedList<AbstractNutritionist> nutritionists;
    private Map<AbstractNutritionist, EntityView> nutritionistsView;

    private LinkedList<Entity> others;
    private Map<Entity, EntityView> othersView;

    /**
     *  Create an empty manager.
     */
    public EntityManager(){
        nutritionists = new LinkedList<>();
        others = new LinkedList<>();

        nutritionistsView = new HashMap<>();
        othersView = new HashMap<>();
    }

    private void nutritionistsMove() {
        for (AbstractNutritionist nutritionist : nutritionists) {
            nutritionist.nextStep();
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
                entity.effect(player);
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
            if (e2 == null)
                continue;

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

    public void setRandomPosition(Entity e, int minHeight, int maxHeight,
                                  int minWidth, int maxWidth) {

        while (true) {
            Point2D p = new Point2D.Double(
                    ExtMath.getRandomBewteen(minWidth, maxWidth),
                    ExtMath.getRandomBewteen(minHeight, maxHeight)
            );

            Circle circle = new Circle(p.getX(), p.getY(), e.getSize().getWidth() / 2);

            if (!hasCrossCollision(e, circle)) {
                e.setPoint(p);
                return;
            }

        }
    }

    public void addAtRandomPosition(Entity entity, EntityView view,
                                    int minHeight, int maxHeight,
                                    int minWidth, int maxWidth) {
        setRandomPosition(entity, minHeight, maxHeight, minWidth, maxWidth);

        view.setEntity(entity);

        if (entity instanceof Glutton) {
            changeGlutton((Glutton)entity, view);
        } else if (entity instanceof AbstractNutritionist) {
            addNutritionist((AbstractNutritionist) entity, view);
        } else {
            addOther(entity, view);
        }

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

    private void changeGlutton(Glutton glutton,  EntityView view) {
        player = glutton;
        player.setManager(this);
        gluttonView = view;
        gluttonView.setEntity(glutton);
    }

    /**
     *  Add a new nutritionist into the manager.
     *  @param n The new nutritionist.
     */
    public void addNutritionist(AbstractNutritionist n, EntityView view) {
        synchronized (nutritionists) {
            nutritionists.addFirst(n);
        }
        n.setManager(this);

        synchronized (nutritionistsView) {
            nutritionistsView.put(n, view);
        }

        view.setEntity(n);
    }

    /**
     *  Remove the nutritionist of the manager.
     *  If the nutritionist doesn't exists, the function returns false.
     *  @param n The nutritionist to remove.
     *  @return True if nutritionist has been remove correctly.
     */
    public boolean removeNutritionist(AbstractNutritionist n) {

        synchronized (nutritionists) {
            if (nutritionists.remove(n)) {
                n.setManager(null);
                synchronized (nutritionistsView) {
                    nutritionistsView.remove(n);
                }
                return true;
            }
        }

        return false;
    }

    /**
     *  Add a new other into the manager.
     *  @param n The new other.
     */
    public void addOther(Entity n, EntityView view) {
        others.addFirst(n);
        n.setManager(this);
        othersView.put(n, view);
        view.setEntity(n);
    }

    /**
     *  Remove the others of the manager.
     *  If the others doesn't exists, the function returns false.
     *  @param n The other to remove.
     *  @return True if other has been remove correctly.
     */
    public boolean removeOther(Entity n) {
        if (others.remove(n)) {
            n.setManager(null);
            othersView.remove(n);
            return true;
        }

        return false;
    }

    public List<Entity> getCakes() {
        List<Entity> cakes = new LinkedList<>();

        for (Entity entity : others) {
            if (entity instanceof AbstractCake)
                cakes.add(entity);
        }

        return cakes;
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
