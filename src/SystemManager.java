/**
 * Created by Ben on 2/17/14.
 */
import java.util.HashMap;
import java.util.ArrayList;

public final class SystemManager {
	private ArrayList<ProcessSystem> systems = new ArrayList<ProcessSystem>();
	private HashMap<String, ProcessSystem> systemMap = new HashMap<String, ProcessSystem>();
	private World world;

	public SystemManager(World world){
		this.world = world;
	}

	public void process(){
		for(ProcessSystem s : systems){
			s.process();
		}
	}

	//Placing entity into appropriate systems
	public void insertEntity(Entity e){
		for(ProcessSystem s : systems){
			if(s.checkEntity(e)){
				s.addEntity(e);
			}
		}
	}

	public void registerSystem(ProcessSystem system){
		boolean systemExists = systemMap.containsKey(system.getUniqueID());
		if(!systemExists){
			systems.add(system);
			systemMap.put(system.getUniqueID(), system);
		}
		else System.err.println(system.getUniqueID() + " could not be registered");
	}

	public void removeSystem(String UID){
		boolean systemExists = systemMap.containsKey(UID);
		if(systemExists){
			ProcessSystem temp = systemMap.get(UID);
			systems.remove(temp);
			systemMap.remove(UID);
		}
		else System.out.println(UID + " could not be removed");
	}

	public ProcessSystem getSystem(String UID){
		boolean systemExists = systemMap.containsKey(UID);
		if(systemExists){
			return systemMap.get(UID);
		}
		else System.out.println(UID + " could not be retrieved");
		return null;
	}

	public World getWorld(){
		return world;
	}
}
