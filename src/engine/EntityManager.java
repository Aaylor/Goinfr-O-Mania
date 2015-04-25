package engine;

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

        nutritionists = new LinkedList<>();
        others = new LinkedList<>();

        nutritionistsView = new HashMap<>();
        othersView = new HashMap<>();
    }

    /**
     *  Entity loop. Does every operations needed by entities.
     *  (Movable, Destruction, ...).
     */
    public void entityLoop() {
        for (Nutritionist nutritionist : nutritionists) {
            /*nutritionist.move();*/ //TODO: what to do here ?
        }

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

    /**
     *  Check if two entities are in collision.
     *  @param e1 The first entity.
     *  @param e2 The second entity.
     *  @return True if they collided.
     */
    public boolean collision(Entity e1, Entity e2) {
        return e1.getBounds().intersects(e2.getBounds());
    }

    /**
     *  Add a new entity into the manager.
     *  @param e The entity to add.
     */
    public void addEntity(Entity e, EntityView view){
        others.addFirst(e);
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
