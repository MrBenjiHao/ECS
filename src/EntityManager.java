/**
 * Created by Ben on 2/17/14.
 */
import java.util.HashMap;
import java.util.ArrayList;

public final class EntityManager {
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private HashMap<String, ArrayList<Entity>> groupMap = new HashMap<String, ArrayList<Entity>>();
	private HashMap<String, Entity> uniqueMap = new HashMap<String, Entity>();


	private World world;

	public EntityManager(World world){
		this.world = world;
	}

	public Entity createEntity(){
		Entity e = new Entity();
		entities.add(e);
		return e;
	}

	// Figures out which systems are interested in the entity
	public void integrateEntity(Entity e){
		world.getSystemManager().insertEntity(e);
	}

	public void removeEntity(Entity e){
		/*
		Remove entity from all systems, groups, uniques and entities list
		Entity must have removed set to true
		Once entity is set to be removed, add it to a queue
		Then this method will take care of removing the entity from the world
		*/
		SystemManager manager = world.getSystemManager();
		for(ProcessSystem s : manager.getProcessSystems()){
			s.getEntities().remove(e);
		}
		for(ProcessSystem s : manager.getRenderSystems()){
			s.getEntities().remove(e);
		}
		entities.remove(e);
		if(!e.getGroupID().equals("NULL")) groupMap.get(e.getGroupID()).remove(e);
		if(!e.getUniqueID().equals("NULL")) uniqueMap.remove(e.getUniqueID());
	}

	public ArrayList<Entity> getGroup(String UID){
		boolean groupExists = groupMap.containsKey(UID);
		if(groupExists) return groupMap.get(UID);
		else System.out.println(UID + " could not be retrieved");
		return null;
	}

	public Entity getUniqueEntity(String UID){
		boolean entityExists = uniqueMap.containsKey(UID);
		if(entityExists) return uniqueMap.get(UID);
		else System.out.println(UID + " could not be retrieved");
		return null;
	}

	public void registerGroup(String UID){
		boolean groupExists = groupMap.containsKey(UID);
		if(!groupExists){
			ArrayList<Entity> group = new ArrayList<Entity>();
			groupMap.put(UID, group);
		}
		else System.out.println(UID + " could not be registered");
	}

	public void assignToGroup(String UID, Entity e){
		boolean groupExists = groupMap.containsKey(UID);
		if(groupExists){
			//Check if entity has already been assigned to group
			boolean entityAssigned = groupMap.get(UID).contains(e);
			if(!entityAssigned) {
				e.setGroupID(UID);
				groupMap.get(UID).add(e); 
			}
			else System.out.println("Entity already assigned to " + UID);
		}
		else System.out.println("Could not assign entity to group " + UID);
	}

	public void assignToUnique(String UID, Entity e){
		boolean uniqueAssigned = uniqueMap.containsKey(UID);
		if(!uniqueAssigned){
			e.setUniqueID(UID);
			uniqueMap.put(UID, e);
		}
		else System.out.println("Could not assigned entity to unique " + UID);
	}

	public World getWorld(){return world;}
}
