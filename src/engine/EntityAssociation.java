package engine;

import engine.nutritionists.CakeChaserNutritionist;
import engine.nutritionists.GluttonChaserNutritionist;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public final class EntityAssociation {

    /* Gluttons */
    public final static String DEFAULT_GLUTTON       = "default_glutton";

    /* Nutritionists */
    public final static String DEFAULT_CAKECHASER    = "default_cakechaser";
    public final static String DEFAULT_GLUTTONCHASER = "default_gluttonchaser";

    /* Cakes */
    public final static String DEFAULT_CAKE          = "default_cake";

    private static Map<String, Entity> entityMap   = new HashMap<>();
    private static Map<String, EntityView> viewMap = new HashMap<>();

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
        register(DEFAULT_GLUTTON, glutton, gluttonView);


        /* CakeChaser */
        CakeChaserNutritionist cakeChaser = new CakeChaserNutritionist(null, new Dimension(30, 30), 2f, 0f, 3);
        EntityView cakeChaserView = new EntityView(new Skin(30, 30));
        register(DEFAULT_CAKECHASER, cakeChaser, cakeChaserView);


        /* GluttonChaser */
        GluttonChaserNutritionist gluttonChaser =
                new GluttonChaserNutritionist(null, new Dimension(30, 30), 2f, 0f, 6);
        EntityView gluttonChaserView = new EntityView(new Skin(30, 30));
        register(DEFAULT_GLUTTONCHASER, gluttonChaser, gluttonChaserView);


        /* DefaultCake */
        Cake cake = new Cake(null, new Dimension(15, 15), null);
        EntityView cakeView = new EntityView(new Skin(30, 30));
        register(DEFAULT_CAKE, cake, cakeView);

    }

}
