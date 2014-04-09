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
		systemManager.registerSystem(new RenderSystem());

		Entity e = entityManager.createEntity();
		e.addComponent(new Velocity(30, 0, 3, 3));
		e.addComponent(new RenderComponent(50, 50, Color.WHITE));
		entityManager.addEntity(e);

		e = entityManager.createEntity();
		e.addComponent(new Velocity(10, 0, 1, 1));
		e.addComponent(new RenderComponent(50, 50, Color.BLACK));
		entityManager.addEntity(e);

		e = entityManager.createEntity();
		e.addComponent(new Velocity(20, 0, 2, 2));
		e.addComponent(new RenderComponent(50, 50, Color.BLUE));
		entityManager.addEntity(e);

		e = entityManager.createEntity();
		e.addComponent(new Velocity(40, 0, 4, 4));
		e.addComponent(new RenderComponent(50, 50, Color.RED));
		entityManager.addEntity(e);

		e = entityManager.createEntity();
		e.addComponent(new Velocity(50, 0, 5, 5));
		e.addComponent(new RenderComponent(50, 50, Color.MAGENTA));
		entityManager.addEntity(e);

		e = entityManager.createEntity();
		e.addComponent(new Velocity(60, 0, 6, 6));
		e.addComponent(new RenderComponent(50, 50, Color.ORANGE));
		entityManager.addEntity(e);
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
		double maxTimeDiff = 100.0 / 1000.0;
		double delta = 1.0 / 60.0;
		double processes = 0, frames = 0;

		while(true){
			currTime = System.nanoTime() / NANO_TO_SEC;
			if(currTime - prevTime > maxTimeDiff) prevTime = currTime;
			if(currTime >= prevTime){
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

	public void process(){
		gameWorld.process();
	}

	public void render(){
		g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 500, 500);

		//Render here
		gameWorld.render();

		g.dispose();
		bufferStrategy.show();
	}

	public static Graphics2D obtainGraphics(){return g;}
}