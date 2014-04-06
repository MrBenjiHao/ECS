/**
 * Created by Ben on 2/17/14.
 */
import java.util.HashMap;

public final class Entity {
	private HashMap<String, Component> componentMap = new HashMap<String, Component>();

	public void addComponent(Component c){
		boolean compExists = componentMap.containsKey(c.getUniqueID());
		if(!compExists) componentMap.put(c.getUniqueID(), c);
		else System.out.println(c.getUniqueID() + " could not be added");
	}

	public void removeComponent(Component c){
		boolean compExists = componentMap.containsKey(c.getUniqueID());
		if(compExists) componentMap.remove(c.getUniqueID());
		else System.out.println(c.getUniqueID() + " could not be removed");
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
