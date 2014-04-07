/**
 * Created by Ben on 2/17/14.
 */
import java.util.ArrayList;

public class ProcessSystem {
	private static String UID = "NULL"; // Maybe a default UID?
	private SystemType systemType = SystemType.PROCESS; // PROCESS by default
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<String> systemComponents = new ArrayList<String>();

	protected SystemManager systemManager;

	public enum SystemType{
		PROCESS, RENDER;
	}

	protected void setUniqueID(String UID){
		this.UID = UID;
	}

	protected void setSystemType(SystemType t){
		systemType = t;
	}

	public SystemType getSystemType(){
		return systemType;
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

	protected void setSystemComponents(String UID){
		if(!systemComponents.contains(UID)) systemComponents.add(UID);
	}

	public ArrayList<Entity> getEntities(){return entities;}

	public static String getUniqueID(){return UID;}

	public void setSystemManager(SystemManager s){
		this.systemManager = s;
	}

	public SystemManager getSystemManager(){
		return systemManager;
	}

	public void addEntity(Entity e){
		if(!entities.contains(e)) entities.add(e);
	}
}
