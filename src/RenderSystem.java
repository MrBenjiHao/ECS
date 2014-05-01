import java.awt.*;
import java.util.Random;

public class RenderSystem extends ProcessSystem{
	private final String velocityID = new Velocity(0, 0, 0, 0).getUniqueID();
	private final String renderID = new RenderComponent(0, 0, null).getUniqueID();

	public RenderSystem(){
		setSystemType(SystemType.RENDER);
		setUniqueID("RENDER_SYSTEM");

		registerComponent(renderID);
	}

	Random rand = new Random();
	int ticks = 0;

	public void process(){
		Graphics2D g = Main.obtainGraphics();
		ticks++;

		for(Entity e : getEntities()){
			Velocity v = (Velocity) e.getComponent(velocityID);
			RenderComponent r = (RenderComponent) e.getComponent(renderID);

			// if((rand.nextInt() * 100) > 50 && ticks % 30 == 0) r.setEnabled(!r.isEnabled());

			if(r.isEnabled()){
				g.setColor(r.c);
				g.fillRect(v.x, v.y, r.sizeX, r.sizeY);
			}
		}
	}
}