package engine;

/**
 * Created by Aaylor, Tratost, PixelMan on 15/04/15.
 */
public abstract class CEntity {

    /* Attributes */

    /**
     * The entity that must be controlled.
     */
    private CEntity entity;



    /* Constructors */

    public CEntity(CEntity entity) {
        this.entity = entity;
    }



    /* Getters */

    public CEntity getEntity() {
        return entity;
    }



    /* Entity Controller Functionalities */

    /* TODO : Add Abstract Entity Controller functionalities */
}
