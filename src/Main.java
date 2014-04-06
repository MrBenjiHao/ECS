import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main extends Canvas{
	public static void main(String[] args){
		World gameWorld = new World();

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

	public Main(){
		setPreferredSize(new Dimension(500, 500));
		setIgnoreRepaint(true);
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
		double maxTimeDiff = 0.5;
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
					Thread.sleep((long)(1000 * (prevTime - currTime)));
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

	public void FPS(double timer){}

	public void process(){
	}

	public void render(){
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 500, 500);

		//Render here
		g.setColor(Color.BLUE);
		g.fillRect(50, 50, 10, 10);

		g.dispose();
		bufferStrategy.show();
	}
	/*
	Problem to resolve
		- Implement clean way of adding entities to systems
		- Implement the refreshing of systems in case of an entity having it's components altered
	*/
}