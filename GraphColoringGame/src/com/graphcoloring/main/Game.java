package com.graphcoloring.main;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;

import com.graphcoloring.hud.ColorPickerHUD;
import com.graphcoloring.hud.HUDFPS;
import com.graphcoloring.hud.HintHUD;
import com.graphcoloring.hud.Notification;
import com.graphcoloring.hud.TimerHUD;
import com.graphcoloring.input.KeyBoardInput;
import com.graphcoloring.input.MouseInput;
import com.graphcoloring.menu.LoadingMenu;
import com.graphcoloring.menu.Menu;
import com.graphcoloring.menu.PauseMenu;
import com.graphcoloring.menu.ScoreMenu;

// TODO: Auto-generated Javadoc
/**
 * The Class Game.
 */
public class Game extends Canvas implements Runnable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3545061167023626203L;

	/** The Constant VERSION. */
	// Version
	public final static String VERSION = "v0.01";

	/** The Constant backgroundColor. */
	// Game Colors
	public static final Color backgroundColor = new Color(20, 21, 23);
	
	/** The Constant textColor. */
	public static final Color textColor = new Color(255, 255, 255);
	
	/** The Constant transparentColor. */
	public static final Color transparentColor = new Color(1, 1, 1, 0.4f);
	
	/** The Constant dimWhiteColor. */
	public static final Color dimWhiteColor = new Color(236, 240, 241);
	
	/** The Constant silverColor. */
	public static final Color silverColor = new Color(189, 195, 199);
	
	/** The Constant stroke1. */
	//Strokes
	public static final BasicStroke stroke1 = new BasicStroke(1);
	
	/** The Constant stroke2. */
	public static final BasicStroke stroke2 = new BasicStroke(1);
	
	/** The Constant stroke3. */
	public static final BasicStroke stroke3 = new BasicStroke(3);
	
	/** The Constant stroke20. */
	public static final BasicStroke stroke20 = new BasicStroke(20);

	/** The font regular. */
	// Fonts
	public static Font fontRegular;
	
	/** The font bold. */
	public static Font fontBold;
	
	/** The volume. */
	// Volume
	public static float VOLUME = 100.0f;
	
	/** The musicvolume. */
	public static float MUSICVOLUME = 100.0f;

	/** The Constant FPSCOUNTER. */
	// FPS Counter
	public static final boolean FPSCOUNTER = true;
	
	/** The Constant SOLVEGRAPH. */
	// Solve Graph after generating?
	public static final boolean SOLVEGRAPH = false;

	/** The fixedrenderrate. */
	// Settings
	public static boolean FIXEDRENDERRATE = true;
	
	/** The antialiasing. */
	public static boolean ANTIALIASING = true;
	
	/** The dithering. */
	public static boolean DITHERING = true;
	
	/** The smallnodes. */
	public static boolean SMALLNODES = false;

	/** The thread. */
	// Thread
	private Thread thread;

	/** The running. */
	// Running
	private boolean running = false;

	/** The handler. */
	// Handler
	private Handler handler;

	/** The menu. */
	// Menu
	private Menu menu;

	/** The pause menu. */
	// Pause Menu
	private PauseMenu pauseMenu;

	/** The score menu. */
	// Score Menu
	private ScoreMenu scoreMenu;

	/** The camera. */
	// Camera
	private Camera camera;

	/** The fpscounter. */
	// FPS Counter
	private HUDFPS fpscounter;

	/** The notification. */
	// Notification
	Notification notification;

	/** The gamemode. */
	// Gamemodes
	private GameMode gamemode;

	/** The hint HUD. */
	// HintHUD
	private HintHUD hintHUD;
	
	/** The timer HUD. */
	// TimerHUD
	private TimerHUD timerHUD;

	/** The color picker HUD. */
	// Color Picker HUD
	private ColorPickerHUD colorPickerHUD;

	/** The sound player. */
	// SoundPlayer
	public static SoundPlayer soundPlayer;
	
	/** The timer game. */
	// Timer
	public static TimerGame timerGame;
	
	/** The chromatic number. */
	// Chromatic Number
	public static int chromaticNumber;
	
	/** The loading menu. */
	// Loading Menu
	private LoadingMenu loadingMenu;

	/**
	 * The Enum STATE.
	 */
	// States
	public enum STATE {
		
		/** The Menu. */
		Menu, 
 /** The Game. */
 Game, 
 /** The Pause. */
 Pause, 
 /** The Score. */
 Score, 
 /** The Loading. */
 Loading;
	};
	
	
	/**
	 * The Enum GAMEMODE.
	 */
	//Gamemodes
	public enum GAMEMODE {
		
		/** The Bitter end. */
		BitterEnd, 
 /** The Best upper bound. */
 BestUpperBound, 
 /** The Random order. */
 RandomOrder;
	};

	/** The game state. */
	public STATE gameState = STATE.Menu;
	
	/** The gamemode state. */
	public GAMEMODE gamemodeState;

	/** The height. */
	// Windows Size
	public static int WIDTH = 1000, HEIGHT = WIDTH / 12 * 9;

	/**
	 * Instantiates a new game.
	 */
	public Game() {

		registerFonts();

		handler = new Handler();

		soundPlayer = new SoundPlayer();

		camera = new Camera(0, 0);

		if (FPSCOUNTER) {
			fpscounter = new HUDFPS();
		}

		notification = new Notification();
		timerGame = new TimerGame();
		
		pauseMenu = new PauseMenu(this, handler, menu);
		scoreMenu = new ScoreMenu(this, handler, menu);
		loadingMenu = new LoadingMenu();

		hintHUD = new HintHUD(this, notification, 5);
		timerHUD = new TimerHUD(this, scoreMenu);

		menu = new Menu(this, handler, notification, soundPlayer);

		colorPickerHUD = new ColorPickerHUD(this, notification);

		new Window(WIDTH, HEIGHT, "VerteX", this, menu, colorPickerHUD);

		MouseInput mouse = new MouseInput(this, handler, camera, colorPickerHUD, scoreMenu, timerHUD);

		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		this.addMouseWheelListener(mouse);
		this.addMouseListener(menu);
		this.addMouseMotionListener(menu);
		this.addMouseListener(pauseMenu);
		this.addMouseListener(colorPickerHUD);
		this.addMouseListener(hintHUD);
		this.addMouseListener(scoreMenu);
		this.addKeyListener(new KeyBoardInput(this, camera, timerHUD, timerGame, colorPickerHUD));

	}

	/**
	 * Start.
	 */
	public synchronized void start() {
		if(running) {
			return;
		}
		
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	/**
	 * Stop.
	 */
	public synchronized void stop() {
		if(!running) {
			return;
		}
		
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Register fonts.
	 */
	public void registerFonts() {
		try {
			fontRegular = Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.load("fonts/Oswald-ExtraLight.ttf"));
			fontBold = Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.load("fonts/Oswald-ExtraLight.ttf"));

			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.load("fonts/Oswald-ExtraLight.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.load("fonts/Oswald-DemiBold.ttf")));
		} catch (IOException | FontFormatException e) {
			System.out.println("Error when reading fonts");
		}
	}

	/**
	 * Gets the font.
	 *
	 * @param font the font
	 * @return the font
	 */
	public static Font getFont(int font) {
		if (font == 1) {
			return fontRegular;
		} else {
			return fontBold;
		}
	}

	/**
	 * Initilize game.
	 *
	 * @param nodes the nodes
	 * @param edges the edges
	 * @param time the time
	 * @param gamemodeState the gamemode state
	 */
	public void initilizeGame(int nodes, int edges, int time, GAMEMODE gamemodeState) {
		
		// Reset the Game variables
		reset();
		
		this.gamemodeState = gamemodeState;
		
		if(gamemodeState == GAMEMODE.BitterEnd) {
			gamemode = new GameMode(nodes, edges, this, handler, notification, colorPickerHUD, menu, false);
			timerGame.startTimer();
		} else if(gamemodeState == GAMEMODE.BestUpperBound) {
			gamemode = new GameMode(nodes, edges, this, handler, notification, colorPickerHUD, menu, timerHUD, time);
		} else if(gamemodeState == GAMEMODE.RandomOrder) {
			gamemode = new GameMode(nodes, edges, this, handler, notification, colorPickerHUD, menu, true);
			timerGame.startTimer();
		}

		// for(int i = 0; i < 20; i++) { handler.addObject(new TestSprite(20, 20, ID.TestSprite)); }

	}

	/**
	 * Reset.
	 */
	public void reset() {
		handler.removeAllObjects();
		colorPickerHUD.removeAllObjects();
		colorPickerHUD.setSelector(false);
		scoreMenu.setWin(true);
		scoreMenu.setScore(0);
		scoreMenu.setTime(0);
		scoreMenu.setTimeLeft(false);
		timerGame.setFinishTime(0);
		hintHUD.setHintCount(5);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
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

	/**
	 * Tick.
	 */
	private void tick() {

		if(gameState != STATE.Loading) {
			handler.tick();
		}
		
		if (gameState == STATE.Menu) {
			menu.tick();
		} else if (gameState == STATE.Game) {
			colorPickerHUD.tick();
			hintHUD.tick();
			if(gamemodeState == GAMEMODE.BestUpperBound) {
				timerHUD.tick();
			}
		} else if (gameState == STATE.Pause) {
			pauseMenu.tick();
		} else if (gameState == STATE.Score) {
			scoreMenu.tick();
		} else if(gameState == STATE.Loading) {
			loadingMenu.tick();
		}

		notification.tick();
	}

	/**
	 * Render.
	 */
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

		g.setColor(backgroundColor);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		// g.setColor(Color.BLACK);
		// g.drawOval(WIDTH / 2 - ((HEIGHT - 100) / 2), HEIGHT / 2 - ((HEIGHT -
		// 100) / 2), HEIGHT - 100, HEIGHT - 100);

		if(gameState != STATE.Loading) {
			g2d.translate(camera.getX(), camera.getY());
			handler.render(g);
			g2d.translate(-camera.getX(), -camera.getY());
		}

		if (gameState == STATE.Menu) {
			if (menu != null)
				menu.render(g);
		} else if (gameState == STATE.Game) {
			colorPickerHUD.render(g);
			hintHUD.render(g);
			if(gamemodeState == GAMEMODE.BestUpperBound) {
				timerHUD.render(g);
			}
		} else if (gameState == STATE.Pause) {
			pauseMenu.render(g);
		} else if (gameState == STATE.Score) {
			scoreMenu.render(g);
		} else if(gameState == STATE.Loading) {
			loadingMenu.render(g);
		}

		notification.render(g);

		if (!FIXEDRENDERRATE && FPSCOUNTER) {
			fpscounter.render(g);
		}

		// Stuff to Render End
		g.dispose();
		bs.show();
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		new Game();
	}
}
