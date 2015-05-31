package engine;

import engine.cake.AbstractCake;
import engine.cake.InvulnerabilityCake;
import engine.cake.LifeCake;
import engine.cake.SpeedCake;
import engine.effects.InverseKeyBuff;
import engine.nutritionists.CakeChaserNutritionist;
import engine.nutritionists.GluttonChaserNutritionist;
import engine.nutritionists.TrapShooterNutritionist;
import engine.traps.InverseKeyTrap;
import engine.traps.LifeTrap;
import engine.traps.SlowTrap;
import sound.MSound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class EntityAssociation {

    /* Gluttons */
    public final static String DEFAULT_GLUTTON       = "default_glutton";

    /* Nutritionists */
    public final RandomCreation randomNutritionist = new RandomCreation();
    public final static String DEFAULT_CAKECHASER    = "default_cakechaser";
    public final static String DEFAULT_GLUTTONCHASER = "default_gluttonchaser";
    public final static String TRAP_SHOOTER          = "trap_shooter";

    /* Cakes */
    public final RandomCreation randomCakes   = new RandomCreation();
    public final static String DEFAULT_LIFECAKE      = "default_lifecake";
    public final static String SUPERLIFECAKE         = "superlifecake";
    public final static String HYPERLIFECAKE         = "hyperlifecake";
    public final static String SPEEDCAKE             = "speedcake";
    public final static String INVULCAKE             = "invulcake";

    /* Traps */
    public final RandomCreation randomTraps = new RandomCreation();
    public final static String LIFETRAP    = "lifetrap";
    public final static String BIGLIFETRAP = "biglifetrap";
    public final static String SLOWTRAP    = "slowtrap";
    public final static String INVKEYTRAP  = "invkeytrap";

    private final static String FOLDER_GLUTTON = "pictures/Characters/goinfre";
    private final static String FOLDER_CAKE_CHASER = "pictures/Characters/Nutritioniste1";
    private final static String FOLDER_GLUTTON_CHASER = "pictures/Characters/Nutritioniste2";

    private final static String FILE_CAKE = "pictures/cake/cake_cream.png";
    private final static String FILE_SUPER_CAKE = "pictures/cake/cake_citron.png";
    private final static String FILE__HYPER_CAKE = "pictures/cake/cake_choco.png";
    private final static String FILE_SPEED_CAKE ="pictures/cake/cake_fraise.png";

    private final static String FOLDER_LIFE_TRAP = "pictures/trap/mw2";
    private final static String FOLDER_BIG_LIFE_TRAP = "pictures/trap/katon";
    private final static String FOLDER_SLOW_TRAP = "pictures/trap/mw1";

    private static Map<String, Entity> entityMap   = new HashMap<>();
    private static Map<String, EntityView> viewMap = new HashMap<>();

    public final static void register(String name, Entity e, EntityView ev) {
        if (entityMap.containsKey(name)) {
            throw new IllegalArgumentException(name + " already exists.");
        }

        entityMap.put(name, e);
        viewMap.put(name, ev);
    }


    public final static EntityAssociation getInstance(Difficulties difficulty) {
        EntityAssociation ea = new EntityAssociation();

        final int cakeChaser;
        final int gluttonChaser;
        final int trapShooter;

        final int lifeCake;
        final int superLifeCake;
        final int hyperLifeCake;
        final int invulCake;
        final int speedCake;

        final int lifeTrap;
        final int bigLifeTrap;
        final int slowTrap;
        final int invKeyTrap;

        /* TODO FIXME XXX TODO FIXME XXX */
        switch (difficulty) {
            default:
            case EASY:
                cakeChaser = 20;
                gluttonChaser = 60;
                trapShooter = 200;
                lifeCake = 50;
                superLifeCake = 30;
                hyperLifeCake = 5;
                invulCake = 200;
                speedCake = 20;
                lifeTrap = 20;
                bigLifeTrap = 15;
                slowTrap = 90;
                invKeyTrap = 300;
                break;
            case MEDIUM:
                cakeChaser = 20;
                gluttonChaser = 60;
                trapShooter = 300;
                lifeCake = 50;
                superLifeCake = 30;
                hyperLifeCake = 5;
                invulCake = 200;
                speedCake = 20;
                lifeTrap = 20;
                bigLifeTrap = 15;
                slowTrap = 90;
                invKeyTrap = 300;
                break;
            case HARD:
                cakeChaser = 20;
                gluttonChaser = 60;
                trapShooter = 200;
                lifeCake = 50;
                superLifeCake = 30;
                hyperLifeCake = 5;
                invulCake = 200;
                speedCake = 20;
                lifeTrap = 20;
                bigLifeTrap = 15;
                slowTrap = 90;
                invKeyTrap = 300;
                break;
        }

        ea.randomNutritionist.add(DEFAULT_CAKECHASER, cakeChaser);
        ea.randomNutritionist.add(DEFAULT_GLUTTONCHASER, gluttonChaser);
        ea.randomNutritionist.add(TRAP_SHOOTER, trapShooter);

        ea.randomCakes.add(DEFAULT_LIFECAKE, lifeCake);
        ea.randomCakes.add(SUPERLIFECAKE, superLifeCake);
        ea.randomCakes.add(HYPERLIFECAKE, hyperLifeCake);
        ea.randomCakes.add(SPEEDCAKE, speedCake);
        ea.randomCakes.add(INVULCAKE, invulCake);

        ea.randomTraps.add(LIFETRAP, lifeTrap);
        ea.randomTraps.add(BIGLIFETRAP, bigLifeTrap);
        ea.randomTraps.add(SLOWTRAP, slowTrap);
        ea.randomTraps.add(INVKEYTRAP, invKeyTrap);

        return ea;
    }

    public final Entity getEntity(String name) {
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

    public final EntityView getEntityView(String name) {
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

    /**
     * Create a character animation from file but, the name of the files must be "an" follow by the number,
     * of the animation. The first image is "an1". Image must be on the same size for a same animation.
     * @param folder the folder in which we can find the images
     * @param numberimg The number of image in folder
     * @param extension the type of extension
     * @return the tab of buffered image creates
     */
    public static BufferedImage[] createCharacterFromFile(String folder, int numberimg, String extension) throws java.io.IOException{
        String imgname = folder+"/an";
        String tmp = "";
        BufferedImage[] tab = new BufferedImage[numberimg];
        for(int i = 0; i < numberimg; i++){
            tmp = imgname+(i+1)+extension;
            File img = new File(tmp);
            try {
                BufferedImage in = ImageIO.read(img);
                tab[i] = in;
            } catch (java.io.IOException e) {
                System.err.println("Error with image :" + tmp);
                throw new java.io.IOException();
            }
        }
        return tab;
    }

    public final static void defaults() {
        /* Default Glutton */

        Skin skin = new Skin(30, 30);
        Glutton glutton = new Glutton(null, new Dimension(30, 30), 3f, 0f, 6);
        try {
            BufferedImage[] p = createCharacterFromFile(FOLDER_GLUTTON, 16, ".png");
            skin = new Skin(p, 3);
            glutton = new Glutton(null, new Dimension(p[0].getWidth(), p[0].getHeight()), 3f, 0f, 6);
        }
        catch (Exception e){}

        EntityView gluttonView = new EntityView(skin);
        register(DEFAULT_GLUTTON, glutton, gluttonView);



        /* NUTRITIONIST */
        /* CakeChaser */
        Skin skin2 = new Skin(30, 30);
        CakeChaserNutritionist cakeChaser = new CakeChaserNutritionist(null, new Dimension(30, 30), 2f, 0f, 2);
        try {
            BufferedImage[] p2 = createCharacterFromFile(FOLDER_CAKE_CHASER, 16, ".png");
            skin2 = new Skin(p2, 3);
            cakeChaser = new CakeChaserNutritionist(null, new Dimension(p2[0].getWidth(), p2[0].getHeight()), 2f, 0f, 2);
        }
        catch (Exception e){}
        EntityView cakeChaserView = new EntityView(skin2);
        register(DEFAULT_CAKECHASER, cakeChaser, cakeChaserView);

        /* GluttonChaser */
        Skin skin3 = new Skin(30, 30);
        GluttonChaserNutritionist gluttonChaser =
                new GluttonChaserNutritionist(null, new Dimension(30, 30), 2f, 0f, 3);
        try {
            BufferedImage[] p3 = createCharacterFromFile(FOLDER_GLUTTON_CHASER, 16, ".png");
            skin3 = new Skin(p3, 3);
            gluttonChaser =
                    new GluttonChaserNutritionist(null, new Dimension(p3[0].getWidth(), p3[0].getHeight()), 2f, 0f, 3);
        }
        catch (Exception e){}
        EntityView gluttonChaserView = new EntityView(skin3);
        register(DEFAULT_GLUTTONCHASER, gluttonChaser, gluttonChaserView);

        /* Trap Shooter */
        Skin skinShooter = new Skin(30, 30);
        TrapShooterNutritionist tsn = new TrapShooterNutritionist(null, new Dimension(30, 30), 2f, 0f, 3);
        EntityView tsnView = new EntityView(skinShooter);
        register(TRAP_SHOOTER, tsn, tsnView);


        /* CAKES */
        /* DefaultCake */
        Skin skin4 = new Skin(20, 20);
        LifeCake cake = new LifeCake(null, new Dimension(15, 15),
                new MSound(DEFAULT_LIFECAKE, "music/pickupitem00.wav"), 1);
        try {
            File img = new File(FILE_CAKE);
            BufferedImage in = ImageIO.read(img);
            BufferedImage[] p4 = { in };
            skin4 = new Skin(p4, 0);
            cake =  new LifeCake(null, new Dimension(p4[0].getWidth(), p4[0].getHeight()),
                    new MSound(DEFAULT_LIFECAKE, "music/pickupitem00.wav"), 1);

        }
        catch (Exception e){
            System.err.println("Error with image :" + FILE_CAKE);
        }

        EntityView cakeView = new EntityView(skin4);
        register(DEFAULT_LIFECAKE, cake, cakeView);

        /* Super life cake */
        Skin skin5 = new Skin(20, 20);
        LifeCake superLifeCake = new LifeCake(null, new Dimension(15, 15),
                new MSound(SUPERLIFECAKE, "music/pickupitem00.wav"), 2);
        try {
            File img = new File(FILE_SUPER_CAKE);
            BufferedImage in = ImageIO.read(img);
            BufferedImage[] p5 = { in };
            skin5 = new Skin(p5, 0);
            superLifeCake = new LifeCake(null, new Dimension(p5[0].getWidth(), p5[0].getHeight()),
                    new MSound(SUPERLIFECAKE, "music/pickupitem00.wav"), 2);
        }
        catch (Exception e){
            System.err.println("Error with image :" + FILE_SUPER_CAKE);
        }
        EntityView superLifeCakeView = new EntityView(skin5);
        register(SUPERLIFECAKE, superLifeCake, superLifeCakeView);

        /* Hyper life cake */
        Skin skin6 = new Skin(20, 20);
        LifeCake hyperLifeCake = new LifeCake(null, new Dimension(15, 15),
                new MSound(HYPERLIFECAKE, "music/pickupitem00.wav"), 4);
        try {
            File img = new File(FILE_SUPER_CAKE);
            BufferedImage in = ImageIO.read(img);
            BufferedImage[] p6 = { in };
            skin6 = new Skin(p6, 0);
            hyperLifeCake = new LifeCake(null, new Dimension(p6[0].getWidth(), p6[0].getHeight()),
                    new MSound(HYPERLIFECAKE, "music/pickupitem00.wav"), 4);
        }
        catch (Exception e){
            System.err.println("Error with image :" + FILE__HYPER_CAKE);
        }
        EntityView hyperLifeCakeView = new EntityView(skin6);
        register(HYPERLIFECAKE, hyperLifeCake, hyperLifeCakeView);

        /* speed cake */
        Skin skinSC = new Skin(20, 20);
        SpeedCake speedCake = new SpeedCake(null, new Dimension(15, 15),
                new MSound(SPEEDCAKE, "music/pickspeeditem00.wav"));
        try {
            File img = new File(FILE_SPEED_CAKE);
            BufferedImage in = ImageIO.read(img);
            BufferedImage[] pSC = { in };
            skinSC = new Skin(pSC, 0);
            speedCake = new SpeedCake(null, new Dimension(pSC[0].getWidth(), pSC[0].getHeight()),
                    new MSound(SPEEDCAKE, "music/pickspeeditem00.wav"));
        }
        catch (Exception e) {
            System.err.println("Error with image :" + FILE_SPEED_CAKE);
        }
        EntityView speedCakeView = new EntityView(skinSC);
        register(SPEEDCAKE, speedCake, speedCakeView);

         /* invul cake */
        Skin invulSkin = new Skin(20, 20);
        InvulnerabilityCake invulCake= new InvulnerabilityCake(null, new Dimension(20, 20),
                new MSound(INVULCAKE, "music/pickspeeditem00.wav"));
        /*try {
            File img = new File(FILE_SPEED_CAKE);
            BufferedImage in = ImageIO.read(img);
            BufferedImage[] pSC = { in };
            skinSC = new Skin(pSC, 0);
            speedCake = new SpeedCake(null, new Dimension(pSC[0].getWidth(), pSC[0].getHeight()),
                    new MSound(SPEEDCAKE, "music/pickspeeditem00.wav"));
        }
        catch (Exception e) {
            System.err.println("Error with image :" + FILE_SPEED_CAKE);
        }*/
        EntityView invulCakeView = new EntityView(invulSkin);
        register(INVULCAKE, invulCake, invulCakeView);


        /* TRAPS */
        /* Life trap */
        Skin skin7 = new Skin(23, 23);
        LifeTrap lifeTrap = new LifeTrap(null, new Dimension(23, 23), true,
                null, 1000, 5000, 1);
        try {
            BufferedImage[] p7 = createCharacterFromFile(FOLDER_LIFE_TRAP, 4, ".png");
            skin7 = new Skin(p7, 6);
            lifeTrap = new LifeTrap(null, new Dimension(p7[0].getWidth(), p7[0].getHeight()), true,
                    null, 1000, 5000, 1);
        }
        catch (Exception e){}
        EntityView lifeTrapView = new EntityView(skin7);
        register(LIFETRAP, lifeTrap, lifeTrapView);

        /* Big Life trap */
        Skin skin8 = new Skin(40, 40);
        LifeTrap bigLifeTrap = new LifeTrap(null, new Dimension(40, 40), true,
                null, 1000, 4000, 2);
        try {
            BufferedImage[] p8 = createCharacterFromFile(FOLDER_BIG_LIFE_TRAP, 3, ".png");
            skin8 = new Skin(p8, 6);
            bigLifeTrap = new LifeTrap(null, new Dimension(p8[0].getWidth(), p8[0].getHeight()), true,
                    null, 1000, 4000, 2);
        }
        catch (Exception e){}
        EntityView bigLifeTrapView = new EntityView(skin8);
        register(BIGLIFETRAP, bigLifeTrap, bigLifeTrapView);

        /*Slow Trap */
        Skin skin9 = new Skin(7, 7);
        SlowTrap slowTrap = new SlowTrap(null, new Dimension(7, 7), true, null, 2000, 7000);
        try {
            BufferedImage[] p9 = createCharacterFromFile(FOLDER_SLOW_TRAP, 4, ".png");
            skin9 = new Skin(p9, 6);
            slowTrap = new SlowTrap(null, new Dimension(7, 7), true, null, 2000, 7000);
        }
        catch (Exception e){}
        EntityView slowTrapView = new EntityView(skin9);
        register(SLOWTRAP, slowTrap, slowTrapView);

        /* Inv key trap */
        Skin skinInvKeyTrap = new Skin(15, 15);
        InverseKeyTrap invKeyTrap =
                new InverseKeyTrap(null, new Dimension(15, 15), true, null, 2000, 10000);
        /*try {
            BufferedImage[] p9 = createCharacterFromFile(FOLDER_SLOW_TRAP, 4, ".png");
            skin9 = new Skin(p9, 6);
            slowTrap = new SlowTrap(null, new Dimension(7, 7), true, null, 2000, 7000);
        }
        catch (Exception e){}*/
        EntityView invKeyTrapView = new EntityView(skinInvKeyTrap);
        register(INVKEYTRAP, invKeyTrap, invKeyTrapView);


    }

}
