package engine;

import engine.cake.AbstractCake;
import engine.nutritionists.AbstractNutritionist;
import engine.weapons.Weapon;
import graphics.Board;
import graphics.BoardController;
import graphics.Circle;
import helpers.ExtMath;
import log.IGLog;
import sun.awt.image.ImageWatched;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 *  Manage every created entities.
 */
public class EntityManager {

    private Glutton player;
    private EntityView gluttonView;
    private Level level;

    private List<AbstractNutritionist> nutritionists;
    private Map<AbstractNutritionist, EntityView> nutritionistsView;

    private List<Entity> others;
    private Map<Entity, EntityView> othersView;
    private Map<Entity, Long> othersLifeTime;

    private Dimension boardDimension;

    /**
     *  Create an empty manager.
     */
    public EntityManager(Level level){
        nutritionists = Collections.synchronizedList(new LinkedList<>());
        others = Collections.synchronizedList(new LinkedList<>());

        nutritionistsView = new ConcurrentHashMap<>();
        othersView = new ConcurrentHashMap<>();
        othersLifeTime = new ConcurrentHashMap<>();

        this.level = level;
    }

    private void nutritionistsMove() {
        for (AbstractNutritionist nutritionist : nutritionists) {
            nutritionist.nextStep();
        }
    }

    /**
     *  Entity loop. Does every operations needed by entities.
     *  (Movable, Destruction, ...).
     */
    public void entityLoop() {
        nutritionistsMove();

        List<AbstractNutritionist> nToDestroy = new LinkedList<>();
        List<Entity> othersToDestroy = new LinkedList<>();

        for (Entity entity : others) {
            if (othersLifeTime.containsKey(entity)) {
                long end = othersLifeTime.get(entity);

                if (end < level.getChrono().timeEllapsed()) {
                    IGLog.write("EntityManager::entityLoop() -> time to destroy " + entity);
                    othersToDestroy.add(entity);
                    continue;
                }
            }

            if (collision(player, entity)) {
                player.effect(entity);

                if (entity.effect(player)) {
                    othersToDestroy.add(entity);
                }

                /* if could add a score, then do it. */
                if (entity instanceof Valuable) {
                    level.addToScore(((Valuable)entity).scoreValue());
                }

            } else {
                for (AbstractNutritionist nutritionist : nutritionists) {
                    if (collision(nutritionist, entity)) {

                        if (nutritionist.effect(entity)) {
                            nToDestroy.add(nutritionist);
                        }

                        if (entity.effect(nutritionist)) {
                            othersToDestroy.add(entity);
                        }

                        break;
                    }
                }
            }
        }

        removeNutritionist(nToDestroy);
        removeOther(othersToDestroy);
    }

    private boolean checkCrossCollision(List<? extends Entity> entities, Entity e1,
                                        Circle position) {
        if (e1.isCrossable())
            return false;

        for (Entity e2 : entities) {
            if (e2 == null)
                continue;

            if (e1 != e2 && !e2.isCrossable() && collision(position, e2))
                return true;
        }

        return false;
    }

    public boolean hasCrossCollision(Entity e1, Circle position) {
        LinkedList<Entity> lp = new LinkedList<>();
        lp.add(player);
        return checkCrossCollision(lp, e1, position) ||
                checkCrossCollision(nutritionists, e1, position) ||
                checkCrossCollision(others, e1, position);
    }

    public boolean outOfBound(Circle circle) {
        if (getBoardDimension() == null) {
            IGLog.error("No dimension found for the board.");
            return false;
        }

        double x = circle.getCenterX();
        double y = circle.getCenterY();

        double boardWidth  = getBoardDimension().getWidth();
        double boardHeight = getBoardDimension().getHeight();

        double radius = circle.getRadius();

        return  (Math.ceil(x - radius)  < 0) ||
                (Math.floor(x + radius) > boardWidth)  ||
                (Math.ceil(y - radius)  < 0) ||
                (Math.floor(y + radius) > boardHeight);
    }

    public boolean outOfBound(Entity e) {
        return outOfBound(e.getBoundsCircle());
    }

    public void setRandomPosition(Entity e, int minHeight, int maxHeight,
                                  int minWidth, int maxWidth) {

        while (true) {
            Point2D p = new Point2D.Double(
                    ExtMath.getRandomBewteen(minWidth, maxWidth),
                    ExtMath.getRandomBewteen(minHeight, maxHeight)
            );

            Circle circle = new Circle(p.getX(), p.getY(), e.getSize().getWidth() / 2);

            if (!hasCrossCollision(e, circle) && !outOfBound(circle)) {
                e.setPoint(p);
                return;
            }

        }
    }

    public void setRandomPosition(Entity entity) {
        setRandomPosition(entity, 0, (int)boardDimension.getHeight(),
                0, (int)boardDimension.getWidth());
    }

    public void addAtRandomPosition(Entity entity, EntityView view,
                                    int minHeight, int maxHeight,
                                    int minWidth, int maxWidth) {
        setRandomPosition(entity, minHeight, maxHeight, minWidth, maxWidth);

        view.setEntity(entity);

        if (entity instanceof Glutton) {
            changeGlutton((Glutton)entity, view);
        } else if (entity instanceof AbstractNutritionist) {
            addNutritionist((AbstractNutritionist) entity, view);
        } else {
            addOther(entity, view);
        }

    }

    public void addAtRandomPosition(Entity e, EntityView v) {
        addAtRandomPosition(e, v, 0, (int)boardDimension.getHeight(),
                0, (int)boardDimension.getWidth());
    }


    /**
     *  Check if two entities are in collision.
     *  @param e1 The first entity.
     *  @param e2 The second entity.
     *  @return True if they collided.
     */
    public boolean collision(Entity e1, Entity e2) {
        return collision(e1.getBoundsCircle(), e2);
    }

    public boolean collision(Circle circle, Entity entity) {
        return circle.intersects(entity.getBoundsCircle());
    }

    public void attack(AbstractMovableEntity e) {
        Weapon w = e.getWeapon();

        if (w == null)
            return;

        if (!w.ready())
            return;

        LinkedList<Entity> all = new LinkedList<>();

        if (!(e instanceof Glutton))
            all.add(player);

        if (!(e instanceof AbstractNutritionist))
            all.addAll(nutritionists);

        all.addAll(others);


        /* Now check if there is anyone on the range. */
        Point2D center = e.getCenter();

        double weaponRadius = w.getRange() + e.getBoundsCircle().getRadius();
        Circle attackRange = new Circle(
                e.getCenterX() - weaponRadius,
                e.getCenterY() - weaponRadius,
                weaponRadius
        );

        for (Entity attackedEntity : all) {

            if (attackedEntity == e)
                continue;

            double llimit = ExtMath.addToAngle(e.getDirection(), -90);
            double hlimit = ExtMath.addToAngle(e.getDirection(), 90);

            /* Fourth step : calculate the new needed angle to face the player. */
            double dx = attackedEntity.getCenterX() - center.getX();
            double dy = attackedEntity.getCenterY() - center.getY();
            double nextAngle = ExtMath.addToAngle(Math.toDegrees(Math.atan2(dy, dx)), 360);

            if (((llimit < hlimit && nextAngle >= llimit && nextAngle <= hlimit) ||
                (llimit > hlimit && (nextAngle >= llimit || nextAngle <= hlimit))) &&
                attackRange.intersects(attackedEntity.getBoundsCircle())) {
                System.out.println("L'entité (" + attackedEntity + ") a été touché.");

                if (w.attack(attackedEntity)) { /* the attacked entity is dead */
                    if (attackedEntity instanceof AbstractNutritionist) {
                        removeNutritionist((AbstractNutritionist) attackedEntity);
                    } else if (! (attackedEntity instanceof Glutton)) {
                        removeOther(attackedEntity);
                    }

                    if (e instanceof  Glutton && attackedEntity instanceof Valuable) {
                        level.addToScore(((Valuable) attackedEntity).scoreValue());
                    }
                }

            }

        }

    }

    /**
     *  Add a new entity into the manager.
     *  @param e The entity to add.
     */
    public void addEntity(Entity e, EntityView view){
        others.add(e);
        e.setManager(this);
        othersView.put(e, view);
        view.getSkin().start();
        view.setEntity(e);
    }

    /**
     *  Remove entity of the manager.
     *  If the entity doesn't exists, the function returns false.
     *  @param e The entity to remove.
     *  @return True if entity has been removed correctly.
     */
    public boolean removeEntity(Entity e){
        if (others.remove(e)) {
            e.setManager(null);
            othersView.remove(e);
            return true;
        }

        return false;
    }

    private void changeGlutton(Glutton glutton,  EntityView view) {
        player = glutton;
        player.setManager(this);
        gluttonView = view;
        gluttonView.getSkin().start();
        gluttonView.setEntity(glutton);
    }

    /**
     *  Add a new nutritionist into the manager.
     *  @param n The new nutritionist.
     */
    public void addNutritionist(AbstractNutritionist n, EntityView view) {
        nutritionists.add(n);
        n.setManager(this);
        nutritionistsView.put(n, view);
        view.getSkin().start();
        view.setEntity(n);
    }

    /**
     *  Remove the nutritionist of the manager.
     *  If the nutritionist doesn't exists, the function returns false.
     *  @param n The nutritionist to remove.
     *  @return True if nutritionist has been remove correctly.
     */
    public boolean removeNutritionist(AbstractNutritionist n) {
        if (nutritionists.remove(n)) {
            n.setManager(null);
            nutritionistsView.remove(n);
            return true;
        }

        return false;
    }
    
    public boolean removeNutritionist(List<AbstractNutritionist> es) {
        nutritionists.removeAll(es);

        for (Entity e : es) {
            nutritionistsView.remove(e);
        }

        return true;
    }

    /**
     *  Add a new other into the manager.
     *  @param n The new other.
     */
    public void addOther(Entity n, EntityView view) {
        others.add(n);
        n.setManager(this);
        othersView.put(n, view);
        view.getSkin().start();
        view.setEntity(n);
    }

    public void addOther(Entity e, EntityView view, long lifetime) {
        addOther(e, view);
        putLifeTime(e, lifetime);
    }

    public void putLifeTime(Entity e, long lifetime) {
        othersLifeTime.put(e, level.getChrono().timeEllapsed() + lifetime);
    }

    /**
     *  Remove the others of the manager.
     *  If the others doesn't exists, the function returns false.
     *  @param n The other to remove.
     *  @return True if other has been remove correctly.
     */
    public boolean removeOther(Entity n) {
        if (others.remove(n)) {
            n.setManager(null);
            othersView.remove(n);
            othersLifeTime.remove(n);
            return true;
        }

        return false;
    }

    public boolean removeOther(List<Entity> es) {
        others.removeAll(es);

        for (Entity e : es) {
            othersView.remove(e);
            othersLifeTime.remove(e);
        }

        return true;
    }

    public List<Entity> getCakes() {
        List<Entity> cakes = new LinkedList<>();

        for (Entity entity : others) {
            if (entity instanceof AbstractCake)
                cakes.add(entity);
        }

        return cakes;
    }



    public EntityView getGluttonView() {
        return gluttonView;
    }

    public Collection<EntityView> getNutritionistsView() {
        return nutritionistsView.values();
    }

    public Collection<EntityView> getOthersView() {
        return othersView.values();
    }

    public Glutton getGlutton() {
        return player;
    }

    public Dimension getBoardDimension() {
        return boardDimension;
    }

    public void setBoardDimension(Dimension boardDimension) {
        this.boardDimension = boardDimension;
    }
}
