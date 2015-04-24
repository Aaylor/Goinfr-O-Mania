package engine;

import java.awt.*;
import java.util.LinkedList;


/**
 *  Manage every created entities.
 */
public class EntityManager {

    private final Glutton player;
    private LinkedList<Nutritionist> nutritionists;
    private LinkedList<Entity> others;

    /**
     *  Create an empty manager.
     */
    public EntityManager(Glutton player){
        this.player = player;
    }

    /**
     *  Entity loop. Does every operations needed by entities.
     *  (Movable, Destruction, ...).
     */
    public void entityLoop() {
        for (Nutritionist nutritionist : nutritionists) {
            nutritionist.move();
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
    public void addEntity(Entity e){
        others.addFirst(e);
    }

    /**
     *  Remove entity of the manager.
     *  If the entity doesn't exists, the function returns false.
     *  @param e The entity to remove.
     *  @return True if entity has been removed correctly.
     */
    public boolean removeEntity(Entity e){
        return others.remove(e);
    }

    /**
     *  Add a new nutritionist into the manager.
     *  @param n The new nutritionist.
     */
    public void addNutritionist(Nutritionist n) {
        nutritionists.addFirst(n);
    }

    /**
     *  Remove the nutritionist of the manager.
     *  If the nutritionist doesn't exists, the function returns false.
     *  @param n The nutritionist to remove.
     *  @return True if nutritionist has been remove correctly.
     */
    public boolean removeNutritionist(Nutritionist n) {
        return nutritionists.remove(n);
    }

}
