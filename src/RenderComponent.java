import java.awt.Color;

public class RenderComponent extends Component{
	public int sizeX, sizeY;
	public Color c;

	public RenderComponent(int x, int y, Color c){
		this.sizeX = x;
		this.sizeY = y;
		this.c = c;
		setUniqueID("RENDER_COMPONENT");
	}
}