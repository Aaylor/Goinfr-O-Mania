package engine;

import java.awt.image.BufferedImage;
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

    public Entity getEntity() {
        return entity;
    }

    public BufferedImage getCurrentDrawing() {
        return skin.test();
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Entity view: update()");
    }
}
