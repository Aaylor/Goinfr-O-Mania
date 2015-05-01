package engine;

import log.IGLog;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


/**
 *  Manage every created entities.
 */
public class EntityManager {

    private final Glutton player;
    private final EntityView gluttonView;

    private LinkedList<Nutritionist> nutritionists;
    private Map<Nutritionist, EntityView> nutritionistsView;

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

        /* First step : get the player position. */
        Point2D playerPosition = player.getPoint();

        /* Second step : iterate on every nutritionists. */
        for (Nutritionist nutritionist : nutritionists) {

            Point2D nutritionistPosition = nutritionist.getPoint();
            double  nutritionistAngle    = nutritionist.getDirection();

//            nutritionist.move(Movable.Direction.FRONT);

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
                for (Nutritionist nutritionist : nutritionists) {
                    if (collision(nutritionist, entity)) {
                        nutritionist.effect(entity);
                        break;
                    }
                }
            }
        }
    }

    public boolean hasCollision(Entity e1, Rectangle2D position) {
        if (e1 != player && collision(position, player))
            return true;

        for (Entity entity : nutritionists) {
            if (e1 != entity && collision(position, entity))
                return true;
        }

        return false;
    }

    /**
     *  Check if two entities are in collision.
     *  @param e1 The first entity.
     *  @param e2 The second entity.
     *  @return True if they collided.
     */
    public boolean collision(Entity e1, Entity e2) {
        return collision(e1.getBounds(), e2);
    }

    public boolean collision(Rectangle2D r, Entity e) {
        return r.intersects(e.getBounds());
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
    public void addNutritionist(Nutritionist n, EntityView view) {
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
    public boolean removeNutritionist(Nutritionist n) {
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
