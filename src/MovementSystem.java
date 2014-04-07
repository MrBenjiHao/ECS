public class MovementSystem extends ProcessSystem{
	public MovementSystem(){
		setSystemType(SystemType.PROCESS);
		setUniqueID("MOVEMENT_SYSTEM");

		// Set system components
		registerComponent(Velocity.getUniqueID());
		//System.out.println(systemComponents.size());
	}

	public void process(){
		for(Entity e : getEntities()){
			Velocity v = (Velocity) e.getComponent(Velocity.getUniqueID());
			if(v.isEnabled()){
				System.out.println("velocity");
				v.x += v.dx;
				if(v.x > 450 || v.x < 0) v.dx *= -1;
			} 
		}
	}
}