public class Main{
	public static void main(String[] args){
		new Component();
		new Entity();
		new EntityManager(new World());
		new System(new SystemManager(new World()));
	}
}