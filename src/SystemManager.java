/**
 * Created by Ben on 2/17/14.
 */
import java.util.HashMap;
import java.util.ArrayList;

public class SystemManager {
	private ArrayList<System> systems = new ArrayList<System>();
	private HashMap<String, System> systemMap = new HashMap<String, System>();
	private World world;

	public SystemManager(World world){
		this.world = world;
	}

	public void process(){
		for(System s : systems){
			s.process();
		}
	}

	//Placing entity into appropriate systems
	public void insertEntity(Entity e){
		for(System s : systems){
			if(s.checkEntity(e)){
				s.addEntity(e);
			}
		}
	}

	public void registerSystem(System system){
		boolean systemExists = systemMap.containsKey(system.getUniqueID());
		if(!systemExists){
			systems.add(system);
			systemMap.put(system.getUniqueID(), system);
		}
	}

	public void removeSystem(String UID){
		boolean systemExists = systemMap.containsKey(UID);
		if(systemExists){
			System temp = systemMap.get(UID);
			systems.remove(temp);
			systemMap.remove(UID);
		}
	}

	public System getSystem(String UID){
		boolean systemExists = systemMap.containsKey(UID);
		if(systemExists){
			return systemMap.get(UID);
		}
		return null;
	}

	public World getWorld(){
		return world;
	}
}
