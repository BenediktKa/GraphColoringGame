package com.graphcoloring.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -3545061167023626203L;

	// Debug
	public static final boolean DEBUG = true;

	// Thread
	private Thread thread;

	// Running
	private boolean running = false;

	// Handler
	private Handler handler;

	// Menu
	private Menu menu;

	// States
	public enum STATE {
		Menu, Settings, Gamemodes, Game;
	};

	public STATE gameState = STATE.Menu;

	// Windows Size
	public static final int WIDTH = 1280, HEIGHT = WIDTH / 12 * 9;

	public Game() {
		handler = new Handler();

		new Window(WIDTH, HEIGHT, "Graph Coloring Game", this);

		menu = new Menu(this, handler);

		this.addMouseListener(new MouseInput(handler));
		this.addMouseListener(menu);
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
		new GameMode(this, handler);
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
			if (DEBUG && System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS " + frames);
				frames = 0;
			}

		}
		// If we exit Game Loop, stop
		stop();
	}

	private void tick() {
		if (gameState == STATE.Game) {
			handler.tick();
		} else if (gameState == STATE.Menu || gameState == STATE.Settings || gameState == STATE.Gamemodes) {
			menu.tick();
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		// Stuff to Render Start

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//g.setColor(Color.BLACK);
		//g.drawOval(WIDTH / 2 - ((HEIGHT - 100) / 2), HEIGHT / 2 - ((HEIGHT - 100) / 2), HEIGHT - 100, HEIGHT - 100);

		if (gameState == STATE.Game) {
			handler.render(g);
		} else if (gameState == STATE.Menu || gameState == STATE.Settings || gameState == STATE.Gamemodes) {
			menu.render(g);
		}

		// Stuff to Render End
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new Game();
	}
}
