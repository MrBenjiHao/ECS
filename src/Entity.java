/**
 * Created by Ben on 2/17/14.
 */
import java.util.HashMap;

public final class Entity {
	private HashMap<String, Component> componentMap = new HashMap<String, Component>();
	private String groupID = "NULL";
	private String uniqueID = "NULL";
	private boolean removed = false;

	public void setGroupID(String UID){
		groupID = UID;
	}

	public void setUniqueID(String UID){
		uniqueID = UID;
	}

	public String getGroupID(){
		return groupID;
	}

	public String getUniqueID(){
		return uniqueID;
	}

	public void remove(){
		if(!removed){
			removed = true;
		}
	}

	public boolean isRemoved(){return removed;}

	public void addComponent(Component c){
		boolean compExists = componentMap.containsKey(c.getUniqueID());
		if(!compExists) componentMap.put(c.getUniqueID(), c);
		else System.out.println(c.getUniqueID() + " could not be added");
	}

	public void removeComponent(String UID){
		boolean compExists = componentMap.containsKey(UID);
		if(compExists) componentMap.remove(UID);
		else System.out.println(UID + " could not be removed");
	}

	public Component getComponent(String UID){
		boolean compExists = componentMap.containsKey(UID);
		if(compExists) return componentMap.get(UID);
		else{ 
			System.out.println(UID + " could not be retrieved");
			return null;
		}
	}
	
	public boolean hasComponent(String UID){
		return componentMap.containsKey(UID);
	}
}
