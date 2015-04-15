package engine;

/**
 * Created by Aaylor, Tratost, PixelMan on 15/04/15.
 */
public abstract class EntityController {

    /* Attributes */

    /**
     * The entity that must be controlled.
     */
    private Entity entity;



    /* Constructors */

    public EntityController(Entity entity) {
        this.entity = entity;
    }



    /* Getters */

    public Entity getEntity() {
        return entity;
    }



    /* Entity Controller Functionalities */

    /* TODO : Add Abstract Entity Controller functionalities */
}
