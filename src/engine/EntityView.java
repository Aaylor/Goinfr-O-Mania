package engine;

import java.util.Observable;
import java.util.Observer;

public class EntityView implements Observer {

    private Skin skin;
    private Entity entity;

    /* Constructors */

    public EntityView(Skin skin) {
        this.skin = skin;
    }


    /* Setters */

    public void setEntity(Entity entity) {
        this.entity = entity;
        entity.addObserver(this);
    }


    /* Getters */

    public Skin getSkin() {
        return skin;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
