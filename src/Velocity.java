public class Velocity extends Component{
	public int x, y, dx, dy;

	public Velocity(int x, int y, int dx, int dy){
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;

		setUniqueID("VELOCITY_COMPONENT");
	}
}