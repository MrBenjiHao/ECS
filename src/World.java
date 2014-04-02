/**
 * Created by Ben on 2/7/14.
 */
public class World {
    private SystemManager systemManager;
    private EntityManager entityManager;

    public World(){
    }

    public enum SystemType{
        PROCESS, RENDER
    }

    public SystemManager getSystemManager(){
        return systemManager;
    }

    public EntityManager getEntityManager(){
        return entityManager;
    }
}
