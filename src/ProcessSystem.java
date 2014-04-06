/**
 * Created by Ben on 2/17/14.
 */
import java.util.ArrayList;

public class ProcessSystem {
	private static String UID;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<String> systemComponents = new ArrayList<String>();

	protected SystemManager systemManager;

	public ProcessSystem(SystemManager systemManager){
		this.systemManager = systemManager;
	}

	protected void setUniqueID(String UID){
		this.UID = UID;
	}

	//Must be abstract in production
	public void process(){

	}

	public boolean checkEntity(Entity e){
		for(String s : systemComponents){
			if(!e.hasComponent(s)) return false;
		}	
		return true;
	}

	public void refreshEntityList(){
		for(Entity e : entities){
			if(!checkEntity(e)) entities.remove(e);
		}
	}

	protected void setSystemComponents(String UID){
		if(!systemComponents.contains(UID)) systemComponents.add(UID);
	}

	public ArrayList<Entity> getEntities(){return entities;}

	public static String getUniqueID(){return UID;}

	public SystemManager getSystemManager(){
		return systemManager;
	}

	public void addEntity(Entity e){
		if(!entities.contains(e)) entities.add(e);
	}
}
