public class MovementSystem extends ProcessSystem{
	private final String velocityID = new Velocity(0, 0, 0, 0).getUniqueID();

	public MovementSystem(){
		setSystemType(SystemType.PROCESS);
		setUniqueID("MOVEMENT_SYSTEM");

		registerComponent(velocityID);
	}

	public void process(){
		for(Entity e : getEntities()){
			Velocity v = (Velocity) e.getComponent(velocityID);
			if(v.isEnabled()){
				v.x += v.dx;
				v.y += v.dy;
				if(v.x > 450 || v.x < 0) v.dx *= -1;
				if(v.y > 450 || v.y < 0) v.dy *= -1;
			} 
		}
	}
}