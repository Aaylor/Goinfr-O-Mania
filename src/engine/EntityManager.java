package engine;

import java.util.LinkedList;


/**
 *  Manage every created entities.
 */
public class EntityManager {

    LinkedList<Entity> entities;

    /**
     *  Create an empty manager.
     */
    public EntityManager(){
        entities = new LinkedList<>();
    }

    /**
     *  Create a new manager with a number of default Entities.
     *  @param defaultEntities Default entities to add.
     */
    public EntityManager(LinkedList<Entity> defaultEntities) {
        entities = (LinkedList<Entity>) defaultEntities.clone();
    }

    /**
     *  Entity loop. Does every operations needed by entities.
     *  (Movable, Destruction, ...).
     */
    public void entityLoop() {
        for (Entity e1 : entities) {
            for (Entity e2 : entities) {
                if (e1 == e2) continue;

                if (collision(e1, e2)) {
                    /* Do something here */
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
        entities.addFirst(e);
    }

    /**
     *  Remove entity of the manager.
     *  If the entity doesn't exists, the function returns false.
     *  @param e The entity to remove.
     *  @return True if entity has been removed correctly.
     */
    public boolean removeEntity(Entity e){
        return entities.remove(e);
    }

}
