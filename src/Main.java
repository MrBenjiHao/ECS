import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.event.*;

public class Main extends Canvas{
	private static JFrame window = new JFrame("ECS");

	public static void main(String[] args){
		Main main = new Main();

		window.add(main);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setVisible(true);
		window.pack();
		window.setLocationRelativeTo(null);
		window.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e){
				if(test_e != null){
					//gameWorld.getEntityManager().removeEntity(test_e);
				}
			}

			public void keyReleased(KeyEvent e){

			}

			public void keyTyped(KeyEvent e){

			}
		});

		main.start();
	}

	private BufferStrategy bufferStrategy;
	private static Graphics2D g;
	private static World gameWorld = new World();
	private static Entity test_e;

	public Main(){
		setPreferredSize(new Dimension(500, 500));
		setIgnoreRepaint(true);

		// TEST RUN
		SystemManager systemManager = gameWorld.getSystemManager();
		EntityManager entityManager = gameWorld.getEntityManager();

		systemManager.registerSystem(new MovementSystem());
		systemManager.registerSystem(new RenderSystem());

		Entity e = entityManager.createEntity();

		e.addComponent(new Velocity(0, 0, 1, 1));
		e.addComponent(new RenderComponent(50, 500, Color.BLACK));
		entityManager.addEntity(e);

		e = entityManager.createEntity();
		e.addComponent(new Velocity(0, 0, 2, 2));
		e.addComponent(new RenderComponent(50, 500, Color.BLUE));
		entityManager.addEntity(e);

		e = entityManager.createEntity();
		e.addComponent(new Velocity(0, 0, 3, 3));
		e.addComponent(new RenderComponent(50, 500, Color.WHITE));
		entityManager.addEntity(e);
		entityManager.assignToUnique("TEST_UNIQUE", e);

		test_e = entityManager.getUniqueEntity("TEST_UNIQUE");

		e = entityManager.createEntity();
		e.addComponent(new Velocity(0, 0, 4, 4));
		e.addComponent(new RenderComponent(50, 500, Color.RED));
		entityManager.addEntity(e);

		e = entityManager.createEntity();
		e.addComponent(new Velocity(0, 0, 5, 5));
		e.addComponent(new RenderComponent(50, 500, Color.MAGENTA));
		entityManager.addEntity(e);

		e = entityManager.createEntity();
		e.addComponent(new Velocity(0, 0, 6, 6));
		e.addComponent(new RenderComponent(50, 500, Color.ORANGE));
		entityManager.addEntity(e);
	}

	public void start(){
		createBufferStrategy(2);
		bufferStrategy = getBufferStrategy();
		//System.setProperty("sun.java2d.opengl", "True");
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
				System.out.println("Process: " + (1000.0 / processes) + " FPS: " + (1000.0 / frames));
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