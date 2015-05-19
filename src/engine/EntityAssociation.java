package engine;

import engine.nutritionists.CakeChaserNutritionist;
import engine.nutritionists.GluttonChaserNutritionist;
import sun.util.resources.cldr.ebu.CalendarData_ebu_KE;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public final class EntityAssociation {

    private static Map<String, Entity>     entityMap = new HashMap<>();
    private static Map<String, EntityView> viewMap   = new HashMap<>();

    public final static void register(String name, Entity e, EntityView ev) {
        if (entityMap.containsKey(name)) {
            throw new IllegalArgumentException(name + " already exists.");
        }

        entityMap.put(name, e);
        viewMap.put(name, ev);
    }

    public final static Entity getEntity(String name) {
        if (!entityMap.containsKey(name)) {
            throw new IllegalArgumentException(name + " doesn't exists.");
        }

        try {
            return (Entity) entityMap.get(name).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public final static EntityView getEntityView(String name) {
        if (!viewMap.containsKey(name)) {
            throw new IllegalArgumentException(name + " doesn't exists.");
        }

        try {
            return (EntityView) viewMap.get(name).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public final static void defaults() {
        /* TODO */

        /* Default Glutton */
        Glutton glutton = new Glutton(null, new Dimension(30, 30), 3f, 0f, 10);
        EntityView gluttonView = new EntityView(new Skin(30, 30));
        register("default_glutton", glutton, gluttonView);


        /* CakeChaser */
        CakeChaserNutritionist cakeChaser = new CakeChaserNutritionist(null, new Dimension(30, 30), 2f, 0f, 3);
        EntityView cakeChaserView = new EntityView(new Skin(30, 30));
        register("default_cakechaser", cakeChaser, cakeChaserView);


        /* GluttonChaser */
        GluttonChaserNutritionist gluttonChaser =
                new GluttonChaserNutritionist(null, new Dimension(30, 30), 2f, 0f, 6);
        EntityView gluttonChaserView = new EntityView(new Skin(30, 30));
        register("default_gluttonchaser", gluttonChaser, gluttonChaserView);


        /* DefaultCake */
        Cake cake = new Cake(null, new Dimension(15, 15), null);
        EntityView cakeView = new EntityView(new Skin(30, 30));
        register("default_cake", cake, cakeView);

    }

}
