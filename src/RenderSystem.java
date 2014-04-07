import java.awt.*;

public class RenderSystem extends ProcessSystem{
	public RenderSystem(){
		setUniqueID("RENDER_SYSTEM");
		setSystemType(SystemType.RENDER);

		registerComponent(RenderComponent.getUniqueID());
		//System.out.println(systemComponents.size());
	}

	public void process(){
		Graphics2D g = Main.obtainGraphics();

		for(Entity e : getEntities()){
			((RenderComponent) e.getComponent(RenderComponent.getUniqueID())).process(e, g);
			System.out.println("processing render comp");
		}
	}
}