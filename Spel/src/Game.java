import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//allt i game-klassen ska köras i en egen thread.
public class Game implements Runnable {

	private Display display;
	private int width, height;
	private boolean running = false;
	private Thread thread;
	public String title;

	private BufferStrategy bs;
	private Graphics g;

	// State
	private State gameState;
	private State menuState;

	// Input
	private KeyManager keyManager;
	private MouseManager mouseManager;

	// Camera
	private GameCamera gameCamera;

	//
	private Handler handler;

	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}

	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();

		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(menuState);

	}

	private void tick() {
		keyManager.tick();
		if (State.getState() != null) {
			State.getState().tick();
		}
	}

	// Vår grafik!
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// clear screen
		g.clearRect(0, 0, width, height);

		// draw here!

		if (State.getState() != null) {
			State.getState().render(g);
		}

		// end drawing
		bs.show();
		g.dispose();

	}

	public void run() {
		init();

		int fps = 60;
		double timePerTick = Math.pow(10, 9) / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		// game-loop EDITA DENNA SLUKAR JÄTTE MKT CPU
		try {
			while (running) {
				now = System.nanoTime();
				delta += (now - lastTime) / timePerTick;
				timer += now - lastTime;
				lastTime = now;
				Thread.sleep(14); // 14 ger mig ca 69 "FPS" i spelet som körs och uppdateras
				if (delta >= 1) {
					render();
					tick();
					ticks++;
					delta--;
				}

				// if (timer >= Math.pow(10, 9)) {
				// System.out.println("Ticks and Frames " + ticks);
				// ticks =0;
				// timer =0;
				// }

			}
			} catch (InterruptedException ex) {
				}
		// while loop slut
	
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public void sleep(long x) {

	}

	public void wait(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public GameCamera getGameCamera() {
		return gameCamera;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	
	public void setToGameState() {
		State.setState(gameState);

	}
	
	public void setToMenuState() {
		State.setState(menuState);

	}
	
	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running) {
			return;
		}

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
