/**
 * Created by Ben on 2/7/14.
 */
public final class World {
    private SystemManager systemManager;
    private EntityManager entityManager;

    public World(){
        systemManager = new SystemManager(this);
        entityManager = new EntityManager(this);
    }

    public void process(){
        systemManager.process();
    }

    public void render(){
        systemManager.render();
    }

    public SystemManager getSystemManager(){
        return systemManager;
    }

    public EntityManager getEntityManager(){
        return entityManager;
    }
}
