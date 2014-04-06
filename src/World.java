/**
 * Created by Ben on 2/7/14.
 */
public class World {
    private SystemManager systemManager;
    private EntityManager entityManager;

    public World(){
        systemManager = new SystemManager(this);
        entityManager = new EntityManager(this);
    }

    public SystemManager getSystemManager(){
        return systemManager;
    }

    public EntityManager getEntityManager(){
        return entityManager;
    }
}
