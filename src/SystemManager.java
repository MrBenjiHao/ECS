/**
 * Created by Ben on 2/17/14.
 */
import java.util.HashMap;
import java.util.ArrayList;

public final class SystemManager {
	private ArrayList<ProcessSystem> processSystems = new ArrayList<ProcessSystem>();
	private ArrayList<ProcessSystem> renderSystems = new ArrayList<ProcessSystem>();
	private HashMap<String, ProcessSystem> systemMap = new HashMap<String, ProcessSystem>();
	private World world;

	public SystemManager(World world){
		this.world = world;
	}

	public void process(){
		for(ProcessSystem s : processSystems){
			s.process();
		}
	}

	public void render(){
		for(ProcessSystem s : renderSystems){
			s.process();
		}
	}

	//Placing entity into appropriate processSystems
	public void insertEntity(Entity e){
		for(ProcessSystem s : systemMap.values()){
			if(s.checkEntity(e)){
				s.addEntity(e);
			}
		}
	}

	public void registerSystem(ProcessSystem system){
		boolean systemExists = systemMap.containsKey(system.getUniqueID());
		if(!systemExists && !system.getUniqueID().equals("NULL")){
			if(system.getSystemType() == ProcessSystem.SystemType.PROCESS) processSystems.add(system);
			else if(system.getSystemType() == ProcessSystem.SystemType.RENDER) renderSystems.add(system);
			systemMap.put(system.getUniqueID(), system);
			system.setSystemManager(this);
		}
		else System.out.println(system.getUniqueID() + " could not be registered");
	}

	public void removeSystem(String UID){
		boolean systemExists = systemMap.containsKey(UID);
		if(systemExists){
			ProcessSystem temp = systemMap.get(UID);
			if(temp.getSystemType() == ProcessSystem.SystemType.PROCESS) processSystems.remove(temp);
			else if(temp.getSystemType() == ProcessSystem.SystemType.RENDER) renderSystems.remove(temp);
			systemMap.remove(UID);
		}
		else System.out.println(UID + " could not be removed");
	}

	public ProcessSystem getSystem(String UID){
		boolean systemExists = systemMap.containsKey(UID);
		if(systemExists){
			ProcessSystem temp = systemMap.get(UID);
			if(temp.getSystemType() == ProcessSystem.SystemType.PROCESS) return temp;
			else if(temp.getSystemType() == ProcessSystem.SystemType.RENDER) return temp;
		}
		else System.out.println(UID + " could not be retrieved");
		return null;
	}

	public World getWorld(){
		return world;
	}

	public ArrayList<ProcessSystem> getProcessSystems(){
		return processSystems;
	}

	public ArrayList<ProcessSystem> getRenderSystems(){
		return renderSystems;
	}

	public HashMap<String, ProcessSystem> getSystemMap(){
		return systemMap;
	}
}
