import java.awt.*;

public class RenderComponent extends Component{
	public int sizeX, sizeY;

	public RenderComponent(int x, int y){
		this.sizeX = x;
		this.sizeY = y;
		setUniqueID("RENDER_COMPONENT");
	}

	public void process(Entity e, Graphics2D g){
		Velocity v = (Velocity) e.getComponent(Velocity.getUniqueID());

		g.setColor(Color.WHITE);
		g.fillRect(v.x, v.y, sizeX, sizeY);
	}
}