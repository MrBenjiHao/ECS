/**
 * Created by Ben on 2/17/14.
 */
import java.util.HashMap;
import java.util.ArrayList;

public class EntityManager {
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private HashMap<String, ArrayList<Entity>> groupMap = new HashMap<String, ArrayList<Entity>>();
	private HashMap<String, Entity> uniqueMap = new HashMap<String, Entity>();

	private World world;

	public EntityManager(World world){
		this.world = world;
	}

	public ArrayList<Entity> retrieveGroup(String UID){
		boolean groupExists = groupMap.containsKey(UID);
		if(groupExists) return groupMap.get(UID);
		return null;
	}

	public Entity retrieveUniqueEntity(String UID){
		boolean entityExists = uniqueMap.containsKey(UID);
		if(entityExists) return uniqueMap.get(UID);
		return null;
	}

	public Entity createEntity(){
		Entity e = new Entity();
		entities.add(e);
		return e;
	}

	// Figures out which systems are interested in the entity
	public void integrateEntity(Entity e){
		
	}

	public void registerGroup(String UID){
		boolean groupExists = groupMap.containsKey(UID);
		if(!groupExists){
			ArrayList<Entity> group = new ArrayList<Entity>();
			groupMap.put(UID, group);
		}
	}

	public void assignToGroup(String UID, Entity e){
		boolean groupExists = groupMap.containsKey(UID);
		if(groupExists){
			//Check if entity has already been assigned to group
			boolean entityAssigned = groupMap.get(UID).contains(e);
			if(!entityAssigned) groupMap.get(UID).add(e); 
		}
	}
	public void assignToUnique(String UID, Entity e){
		boolean uniqueAssigned = uniqueMap.containsKey(UID);
		if(!uniqueAssigned){
			uniqueMap.put(UID, e);
		}
	}
	public World getWorld(){return world;}
}
