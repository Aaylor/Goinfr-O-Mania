package engine;

import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class EntityView implements Cloneable, Observer {

    private Skin skin;
    private Entity entity;

    /* Constructors */

    public EntityView(Skin skin) {
        this.skin = skin;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        EntityView ev = (EntityView) super.clone();

        ev.skin = (Skin) skin.clone();
        ev.entity = null;

        return ev;
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
        return skin.move();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
