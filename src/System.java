/**
 * Created by Ben on 2/17/14.
 */
import java.util.ArrayList;

public class System {
	private String UID;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<String> systemComponents = new ArrayList<String>();

	protected SystemManager systemManager;

	protected void setUniqueID(String UID){
		this.UID = UID;
	}

	public System(SystemManager systemManager){
		this.systemManager = systemManager;
	}

	//Must be abstract in production
	public void process(){}

	public boolean checkEntity(Entity e){
		boolean valid = false;
		for(String s : systemComponents){
			valid = e.hasComponent(s);
			if(!valid) break;
		}	
		return valid;
	}

	public void refreshEntityList(){
		for(Entity e : entities){
			if(!checkEntity(e)) entities.remove(e);
		}
	}

	public void setSystemComponents(String UID){
		if(!systemComponents.contains(UID)) systemComponents.add(UID);
	}

	public ArrayList<Entity> getEntities(){return entities;}

	public String getUniqueID(){return UID;}

	public SystemManager getSystemManager(){
		return systemManager;
	}

	public void addEntity(Entity e){
		if(entities.contains(e)) entities.add(e);
	}
}
