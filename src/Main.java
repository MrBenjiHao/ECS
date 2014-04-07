import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main extends Canvas{
	public static void main(String[] args){
		Main main = new Main();

		JFrame window = new JFrame("ECS");
		window.add(main);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
		window.pack();
		window.setLocationRelativeTo(null);

		main.start();
	}

	private BufferStrategy bufferStrategy;
	private static Graphics2D g;
	private World gameWorld = new World();


	public Main(){
		setPreferredSize(new Dimension(500, 500));
		setIgnoreRepaint(true);

		// TEST RUN
		SystemManager systemManager = gameWorld.getSystemManager();
		EntityManager entityManager = gameWorld.getEntityManager();

		systemManager.registerSystem(new MovementSystem());

		entityManager.registerGroup("TEST_GROUP");

		Entity e = entityManager.createEntity();
		e.addComponent(new Velocity(0, 0, 2, 2));
		entityManager.integrateEntity(e);

		entityManager.assignToGroup("TEST_GROUP", e);
		entityManager.assignToUnique("TEST_UNIQUE", e);
	}

	public void start(){
		createBufferStrategy(2);
		bufferStrategy = getBufferStrategy();
		System.setProperty("sun.java2d.opengl", "True");
		run();
	}

	private final double NANO_TO_SEC = 1000000000.0;

	public void run(){
		double currTime;
		double prevTime = System.nanoTime() / NANO_TO_SEC;
		double FPSTIMER = System.nanoTime();
		double maxTimeDiff = 500.0 / 1000.0;
		double delta = 1.0 / 60.0;
		double processes = 0, frames = 0;

		while(true){
			currTime = System.nanoTime() / NANO_TO_SEC;
			if(currTime - prevTime > maxTimeDiff) prevTime = currTime;
			if(currTime > prevTime){
				process();
				processes++;
				prevTime += delta;
				if(currTime < prevTime){
					render();
					frames++;
				} 
			}
			else{
				try{
					Thread.sleep((long) (1000 * (prevTime - currTime)));
				}
				catch(Exception e){}
			}

			if(System.nanoTime() - FPSTIMER > 1000000000.0){
				//System.out.println("Process: " + processes + " FPS: " + frames);
				processes = frames = 0;
				FPSTIMER += 1000000000.0;
			}
		}
	}

	int x = 0;

	public void process(){
		gameWorld.process();
		EntityManager m = gameWorld.getEntityManager();
		Entity e = m.getUniqueEntity("TEST_UNIQUE");

		Velocity v = (Velocity) e.getComponent(Velocity.getUniqueID());

		x = v.x;
	}

	public void render(){
		g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 500, 500);

		//Render here
		gameWorld.render();

		g.setColor(Color.WHITE);
		g.fillRect(x, 50, 50, 50);

		g.dispose();
		bufferStrategy.show();
	}

	public static Graphics2D obtainGraphics(){return g;}
	/*
	Problems to resolve
		- Implement clean way of adding entities to systems
		- Implement a way for rendering systems to have continuous access to the graphics
			- render systems will need to use the static graphics context of the main class
	*/
}