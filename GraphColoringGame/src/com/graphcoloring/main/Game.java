package com.graphcoloring.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import com.graphcoloring.hud.HUDFPS;
import com.graphcoloring.hud.Notification;
import com.graphcoloring.input.KeyBoardInput;
import com.graphcoloring.input.MouseInput;
import com.graphcoloring.menu.Menu;
import com.graphcoloring.menu.PauseMenu;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -3545061167023626203L;

	// Version
	public final static String VERSION = "v0.01";

	// Debug
	public static final boolean DEBUG = true;
	public static final boolean FPSCOUNTER = true;

	// Settings
	public static boolean FIXEDRENDERRATE = true;
	public static boolean ANTIALIASING = true;
	public static boolean DITHERING = true;
	public static boolean SMALLNODES = false;

	// Thread
	private Thread thread;

	// Running
	private boolean running = false;

	// Handler
	private Handler handler;

	// Menu
	private Menu menu;

	// Pause Menu
	private PauseMenu pauseMenu;

	// Camera
	private Camera camera;

	// FPS Counter
	private HUDFPS fpscounter;

	// Notification
	Notification notification;

	// Gamemodes
	private GameMode gamemode;

	// States
	public enum STATE {
		Menu, Game, Pause;
	};

	public STATE gameState = STATE.Menu;

	// Windows Size
	public static int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

	public Game() {
		handler = new Handler();

		camera = new Camera(0, 0);

		if (DEBUG)
			fpscounter = new HUDFPS();

		notification = new Notification();

		menu = new Menu(this, handler, notification);

		new Window(WIDTH, HEIGHT, "Graph Coloring Game", this, menu);
		pauseMenu = new PauseMenu(this, handler, menu);

		MouseInput mouse = new MouseInput(this, handler, camera);

		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		this.addMouseListener(menu);
		this.addMouseMotionListener(menu);
		this.addMouseListener(pauseMenu);
		this.addKeyListener(new KeyBoardInput(this, camera));

	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initilizeGame(int nodes, int edges) {
		// Temporary
		gamemode = new GameMode(nodes, edges, this, handler, notification, menu);

		// for(int i = 0; i < 20; i++) { handler.addObject(new TestSprite(20,
		// 20, ID.TestSprite)); }

	}

	public void run() {
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;

		// FPS Display
		int frames = 0;
		long timer = System.currentTimeMillis();

		// Game Loop
		while (running) {
			long now = System.nanoTime();

			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				tick();
				if (FIXEDRENDERRATE) {
					render();
				}
				delta--;
			}
			if (running && !FIXEDRENDERRATE) {
				render();
			}
			frames++;

			// Tick and FPS displayer
			if (!FIXEDRENDERRATE && FPSCOUNTER && System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				fpscounter.setFPS(frames);
				frames = 0;
			}
		}
		// If we exit Game Loop, stop
		stop();
	}

	private void tick() {

		if (gameState == STATE.Menu) {
			menu.tick();
		} else if (gameState == STATE.Game) {
			handler.tick();
		} else if (gameState == STATE.Pause) {
			pauseMenu.tick();
		}

		notification.tick();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		if (ANTIALIASING) {
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
		if (DITHERING) {
			g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		}

		// Stuff to Render Start

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		// g.setColor(Color.BLACK);
		// g.drawOval(WIDTH / 2 - ((HEIGHT - 100) / 2), HEIGHT / 2 - ((HEIGHT -
		// 100) / 2), HEIGHT - 100, HEIGHT - 100);

		if (gameState == STATE.Game || gameState == STATE.Pause) {
			g2d.translate(camera.getX(), camera.getY());
			handler.render(g);
			g2d.translate(-camera.getX(), -camera.getY());
		}

		if (gameState == STATE.Menu) {
			if (menu != null)
				menu.render(g);
		} else if (gameState == STATE.Game) {
			handler.tick();
		} else if (gameState == STATE.Pause) {
			pauseMenu.render(g);
		}

		notification.render(g);

		if (!FIXEDRENDERRATE && FPSCOUNTER) {
			fpscounter.render(g);
		}

		// Stuff to Render End
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new Game();
	}
}
