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

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -3545061167023626203L;

	// Version
	public final static String VERSION = "v0.01";

	// Debug
	public static final boolean DEBUG = true;
	public static final boolean FPSCOUNTER = true;

	// Settings
	public static boolean ANTIALIASING = true;

	// Thread
	private Thread thread;

	// Running
	private boolean running = false;

	// Handler
	private Handler handler;

	// Menu
	private Menu menu;

	// Camera
	private Camera camera;

	// FPS Counter
	private HUDFPS fpscounter;

	// Notification
	Notification notification;

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

		new Window(WIDTH, HEIGHT, "Graph Coloring Game", this);

		notification = new Notification();

		menu = new Menu(this, handler, notification);

		MouseInput mouse = new MouseInput(this, handler, camera);

		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		this.addMouseListener(menu);
		this.addMouseMotionListener(menu);
		this.addKeyListener(new KeyBoardInput(camera));

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

	public void initilizeGame() {
		// Temporary
		new GameMode(this, handler, notification);

		/*
		 * for(int i = 0; i < 20; i++) { handler.addObject(new TestSprite(20,
		 * 20, ID.TestSprite)); }
		 */
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
				delta--;
			}
			if (running)
				render();
			frames++;

			// Tick and FPS displayer
			if (FPSCOUNTER && System.currentTimeMillis() - timer > 1000) {
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
		}

		handler.tick();
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
		
		if (Game.ANTIALIASING) {
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
		
		// Stuff to Render Start

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		// g.setColor(Color.BLACK);
		// g.drawOval(WIDTH / 2 - ((HEIGHT - 100) / 2), HEIGHT / 2 - ((HEIGHT -
		// 100) / 2), HEIGHT - 100, HEIGHT - 100);

		if (gameState == STATE.Menu) {
			if (menu != null)
				menu.render(g);
		}
		g2d.translate(camera.getX(), camera.getY());
		handler.render(g);
		g2d.translate(-camera.getX(), -camera.getY());

		notification.render(g);

		if (FPSCOUNTER) {
			fpscounter.render(g);
		}

		// Stuff to Render Endz
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new Game();
	}
}
