package com.graphcoloring.menu;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.graphcoloring.hud.Notification;
import com.graphcoloring.hud.Notification.TYPE;
import com.graphcoloring.main.Game;
import com.graphcoloring.main.Handler;
import com.graphcoloring.main.ID;
import com.graphcoloring.main.MenuParticle;
import com.graphcoloring.main.SoundPlayer;

// TODO: Auto-generated Javadoc
/**
 * The Class Menu.
 */
public class Menu extends MouseAdapter {

	/** The game. */
	private Game game;
	
	/** The handler. */
	private Handler handler;
	
	/** The notification. */
	private Notification notification;
	
	/** The sound player. */
	private SoundPlayer soundPlayer;

	/** The border radius. */
	private int borderRadius = 20;

	/** The play button. */
	// Main Menu Elements
	private CustomButton playButton;
	
	/** The settings button. */
	private CustomButton settingsButton;
	
	/** The quit button. */
	private CustomButton quitButton;

	/** The bitter end. */
	// Gamemode Elements
	private CustomButton bitterEnd;
	
	/** The best upper bound. */
	private CustomButton bestUpperBound;
	
	/** The random order. */
	private CustomButton randomOrder;
	
	/** The gamemodes back button. */
	private CustomButton gamemodesBackButton;

	/** The nodes slider. */
	// Gamemode selection Elements
	private CustomSlider nodesSlider;
	
	/** The edges slider. */
	private CustomSlider edgesSlider;

	/** The bitter end start button. */
	// Bitter End Elements
	private CustomButton bitterEndStartButton;

	/** The time slider. */
	// Best UpperBound Elements
	private CustomSlider timeSlider;
	
	/** The best upper bound start button. */
	private CustomButton bestUpperBoundStartButton;

	/** The random order start button. */
	// Random Order
	private CustomButton randomOrderStartButton;

	/** The selection back button. */
	// Gamemode Selection Back Button
	private CustomButton selectionBackButton;

	/** The sound button. */
	// Settings Menu Elements
	private CustomButton soundButton;
	
	/** The fixed refresh rate check box. */
	private CustomCheckBox fixedRefreshRateCheckBox;
	
	/** The antialiasing check box. */
	private CustomCheckBox antialiasingCheckBox;
	
	/** The dithering check box. */
	private CustomCheckBox ditheringCheckBox;
	
	/** The small nodes check box. */
	private CustomCheckBox smallNodesCheckBox;
	
	/** The settings back button. */
	private CustomButton settingsBackButton;

	/** The menu music slider. */
	// Sound Menu
	private CustomSlider menuMusicSlider;
	
	/** The sound FX slider. */
	private CustomSlider soundFXSlider;
	
	/** The sound back button. */
	private CustomButton soundBackButton;

	/** The random. */
	// Random
	private Random random = new Random();

	/**
	 * The Enum MENUSTATE.
	 */
	// States
	public enum MENUSTATE {
		
		/** The Main. */
		Main, 
 /** The Settings. */
 Settings, 
 /** The Sound. */
 Sound, 
 /** The Gamemodes. */
 Gamemodes, 
 /** The Bitter end. */
 BitterEnd, 
 /** The Best upper bound. */
 BestUpperBound, 
 /** The Random order. */
 RandomOrder;
	};

	/** The menu state. */
	public MENUSTATE menuState;

	/**
	 * Instantiates a new menu.
	 *
	 * @param game the game
	 * @param handler the handler
	 * @param notification the notification
	 * @param soundPlayer the sound player
	 */
	public Menu(Game game, Handler handler, Notification notification, SoundPlayer soundPlayer) {
		this.game = game;
		this.handler = handler;
		this.notification = notification;
		this.soundPlayer = soundPlayer;

		menuState = MENUSTATE.Main;

		initialize();
	}

	/**
	 * Initialize.
	 */
	public void initialize() {
		// Main Menu
		playButton = new CustomButton(0, Game.HEIGHT / 4, 200, 50, true, "Play", borderRadius);
		settingsButton = new CustomButton(0, Game.HEIGHT / 4 * 2, 200, 50, true, "Settings", borderRadius);
		quitButton = new CustomButton(0, Game.HEIGHT / 4 * 3, 200, 50, true, "Quit", borderRadius);

		// Gamemodes Menu
		bitterEnd = new CustomButton(0, Game.HEIGHT / 5, 200, 50, true, "Bitter End", borderRadius);
		bestUpperBound = new CustomButton(0, Game.HEIGHT / 5 * 2, 200, 50, true, "Best Upper Bound", borderRadius);
		randomOrder = new CustomButton(0, Game.HEIGHT / 5 * 3, 200, 50, true, "Random Order", borderRadius);
		gamemodesBackButton = new CustomButton(0, Game.HEIGHT / 5 * 4, 200, 50, true, "Back", borderRadius);

		// Gamemode Sliders
		nodesSlider = new CustomSlider(0, Game.HEIGHT / 6, 200, 25, true, 25, 10, "Nodes");
		edgesSlider = new CustomSlider(0, Game.HEIGHT / 6 * 2, 200, 25, true, 75, 15, "Edges");

		// Bitter End
		bitterEndStartButton = new CustomButton(0, Game.HEIGHT / 6 * 3, 200, 50, true, "Start", borderRadius);

		// BestUpperBound
		timeSlider = new CustomSlider(0, Game.HEIGHT / 6 * 3, 200, 25, true, 300, 60, "Seconds");
		bestUpperBoundStartButton = new CustomButton(0, Game.HEIGHT / 6 * 4, 200, 50, true, "Start", borderRadius);

		// RandomOrder
		randomOrderStartButton = new CustomButton(0, Game.HEIGHT / 6 * 3, 200, 50, true, "Start", borderRadius);

		// Gamemode Selection Back Button
		selectionBackButton = new CustomButton(0, Game.HEIGHT / 6 * 5, 200, 50, true, "Back", borderRadius);

		// Settings Menu
		soundButton = new CustomButton(0, Game.HEIGHT / 4, 200, 50, true, "Sounds", borderRadius);
		fixedRefreshRateCheckBox = new CustomCheckBox(Game.WIDTH / 5, Game.HEIGHT / 5 * 1, 50, 25, false, "Fixed Refresh Rate", true);
		antialiasingCheckBox = new CustomCheckBox(Game.WIDTH / 5, Game.HEIGHT / 5 * 2, 50, 25, false, "Antialiasing", true);
		ditheringCheckBox = new CustomCheckBox(Game.WIDTH / 5, Game.HEIGHT / 5 * 3, 50, 25, false, "Dithering", true);
		smallNodesCheckBox = new CustomCheckBox(Game.WIDTH / 5, Game.HEIGHT / 5 * 4, 50, 25, false, "Small Nodes", false);
		settingsBackButton = new CustomButton(0, Game.HEIGHT / 4 * 3, 200, 50, true, "Back", borderRadius);

		// Sound Menu
		menuMusicSlider = new CustomSlider(0, Game.HEIGHT / 4, 200, 25, true, 100, 100, "Menu Music");
		soundFXSlider = new CustomSlider(0, Game.HEIGHT / 4 * 2, 200, 25, true, 100, 100, "SoundFX");
		soundBackButton = new CustomButton(0, Game.HEIGHT / 4 * 3, 200, 50, true, "Back", borderRadius);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e) {
		if (game.gameState != Game.STATE.Menu) {
			return;
		}

		int mx = e.getX();
		int my = e.getY();

		// Main Menu
		if (menuState == MENUSTATE.Main) {
			if (playButton.mouseOver(mx, my)) {
				menuState = MENUSTATE.Gamemodes;
			} else if (settingsButton.mouseOver(mx, my)) {
				menuState = MENUSTATE.Settings;
			} else if (quitButton.mouseOver(mx, my)) {
				System.exit(1);
			}
		}

		// Settings Menu
		else if (menuState == MENUSTATE.Settings) {
			if (soundButton.mouseOver(mx, my)) {
				menuState = MENUSTATE.Sound;
			} else if (fixedRefreshRateCheckBox.mouseOver(mx, my)) {
				fixedRefreshRateCheckBox.setKnobState();
				Game.FIXEDRENDERRATE = Game.FIXEDRENDERRATE ? false : true;
			} else if (antialiasingCheckBox.mouseOver(mx, my)) {
				antialiasingCheckBox.setKnobState();
				Game.ANTIALIASING = Game.ANTIALIASING ? false : true;
			} else if (ditheringCheckBox.mouseOver(mx, my)) {
				ditheringCheckBox.setKnobState();
				Game.DITHERING = Game.DITHERING ? false : true;
			} else if (smallNodesCheckBox.mouseOver(mx, my)) {
				smallNodesCheckBox.setKnobState();
				Game.SMALLNODES = Game.SMALLNODES ? false : true;
			} else if (settingsBackButton.mouseOver(mx, my)) {
				menuState = MENUSTATE.Main;
			}
		}

		// Sound Menu
		else if (menuState == MENUSTATE.Sound) {
			if (soundBackButton.mouseOver(mx, my)) {
				menuState = MENUSTATE.Settings;
			}
		}

		// Game modes Menu
		else if (menuState == MENUSTATE.Gamemodes) {
			if (bitterEnd.mouseOver(mx, my)) {
				menuState = MENUSTATE.BitterEnd;
			} else if (bestUpperBound.mouseOver(mx, my)) {
				menuState = MENUSTATE.BestUpperBound;
			} else if (randomOrder.mouseOver(mx, my)) {
				menuState = MENUSTATE.RandomOrder;
			} else if (gamemodesBackButton.mouseOver(mx, my)) {
				menuState = MENUSTATE.Main;
			}
		}

		// Bitter End
		else if (menuState == MENUSTATE.BitterEnd) {
			if (bitterEndStartButton.mouseOver(mx, my)) {
				int nodes = nodesSlider.getKnobValue();
				int edges = edgesSlider.getKnobValue();

				if (nodes > edges) {
					notification.createNotification(TYPE.Error, "Error: You can't have more nodes than edges!", 3);
					return;
				} else if (edges > nodes * nodes / 2) {
					notification.createNotification(TYPE.Error, "Error: You have too many edges (" + nodes * nodes / 2 + " Max)", 3);
					return;
				}
				menuState = MENUSTATE.Main;
				game.gameState = Game.STATE.Loading;
				game.initilizeGame(nodes, edges, 0, Game.GAMEMODE.BitterEnd);
			} else if (selectionBackButton.mouseOver(mx, my)) {
				menuState = MENUSTATE.Gamemodes;
			}
		}

		// Best UpperBound
		else if (menuState == MENUSTATE.BestUpperBound) {
			if (bestUpperBoundStartButton.mouseOver(mx, my)) {
				int nodes = nodesSlider.getKnobValue();
				int edges = edgesSlider.getKnobValue();
				int time = timeSlider.getKnobValue();

				if (nodes > edges) {
					notification.createNotification(TYPE.Error, "Error: You can't have more nodes than edges!", 3);
					return;
				} else if (edges > nodes * nodes / 2) {
					notification.createNotification(TYPE.Error, "Error: You have too many edges (" + nodes * nodes / 2 + " Max)", 3);
					return;
				} else if (time < nodes * 3) {
					notification.createNotification(TYPE.Error, "Error: Your time should be at least " + (nodes * 3 + edges * 2) + " seconds", 3);
					return;
				}

				menuState = MENUSTATE.Main;
				game.gameState = Game.STATE.Loading;
				game.initilizeGame(nodes, edges, time, Game.GAMEMODE.BestUpperBound);
			} else if (selectionBackButton.mouseOver(mx, my)) {
				menuState = MENUSTATE.Gamemodes;
			}
		}

		// Random Order
		else if (menuState == MENUSTATE.RandomOrder) {
			if (randomOrderStartButton.mouseOver(mx, my)) {
				int nodes = nodesSlider.getKnobValue();
				int edges = edgesSlider.getKnobValue();

				if (nodes > edges) {
					notification.createNotification(TYPE.Error, "Error: You can't have more nodes than edges!", 3);
					return;
				} else if (edges > nodes * nodes / 2) {
					notification.createNotification(TYPE.Error, "Error: You have too many edges (" + nodes * nodes / 2 + " Max)", 3);
					return;
				}

				menuState = MENUSTATE.Main;
				game.gameState = Game.STATE.Loading;
				game.initilizeGame(nodes, edges, 0, Game.GAMEMODE.RandomOrder);
			} else if (selectionBackButton.mouseOver(mx, my)) {
				menuState = MENUSTATE.Gamemodes;
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseDragged(java.awt.event.MouseEvent)
	 */
	public void mouseDragged(MouseEvent e) {
		if (game.gameState != Game.STATE.Menu) {
			return;
		}

		int mx = e.getX();
		int my = e.getY();

		if (menuState == MENUSTATE.Sound) {
			if (menuMusicSlider.contains(mx, my)) {
				menuMusicSlider.setKnobX(mx);
				Game.MUSICVOLUME = menuMusicSlider.getKnobValue();
			} else if (soundFXSlider.contains(mx, my)) {
				soundFXSlider.setKnobX(mx);
				Game.VOLUME = soundFXSlider.getKnobValue();
			}
		} else if (menuState == MENUSTATE.BitterEnd || menuState == MENUSTATE.RandomOrder) {
			if (nodesSlider.contains(mx, my)) {
				nodesSlider.setKnobX(mx);
			} else if (edgesSlider.contains(mx, my)) {
				edgesSlider.setKnobX(mx);
			}
		} else if (menuState == MENUSTATE.BestUpperBound) {
			if (nodesSlider.contains(mx, my)) {
				nodesSlider.setKnobX(mx);
			} else if (edgesSlider.contains(mx, my)) {
				edgesSlider.setKnobX(mx);
			} else if (timeSlider.contains(mx, my)) {
				timeSlider.setKnobX(mx);
			}
		}
	}

	/**
	 * Tick.
	 */
	public void tick() {
		if (menuState == MENUSTATE.Settings) {
			antialiasingCheckBox.tick();
			ditheringCheckBox.tick();
			smallNodesCheckBox.tick();
			fixedRefreshRateCheckBox.tick();
		}

		handler.addObject(new MenuParticle(handler, random.nextInt(Game.WIDTH) - random.nextInt(Game.WIDTH), Game.HEIGHT, (float) Math.random(), 0.005f, ID.MenuParticle));
	}

	/**
	 * Render.
	 *
	 * @param g the g
	 */
	public void render(Graphics g) {
		if (game.gameState != Game.STATE.Menu) {
			return;
		}

		Font fnt = Game.getFont(2).deriveFont(Font.BOLD, 30f);
		g.setFont(fnt);
		g.setColor(Game.textColor);
		drawCenteredString("VerteX", Game.WIDTH, Game.HEIGHT / 15 + 30, g);

		if (menuState == MENUSTATE.Main) {
			mainMenu(g);
		} else if (menuState == MENUSTATE.Settings) {
			settingsMenu(g);
		} else if (menuState == MENUSTATE.Sound) {
			soundMenu(g);
		} else if (menuState == MENUSTATE.Gamemodes) {
			gamemodesMenu(g);
		} else if (menuState == MENUSTATE.BitterEnd) {
			bitterEndMenu(g);
		} else if (menuState == MENUSTATE.BestUpperBound) {
			bestUpperBoundMenu(g);
		} else if (menuState == MENUSTATE.RandomOrder) {
			randomOrderMenu(g);
		}
	}

	/**
	 * Main menu.
	 *
	 * @param g the g
	 */
	public void mainMenu(Graphics g) {
		playButton.drawButton(g);
		settingsButton.drawButton(g);
		quitButton.drawButton(g);
	}

	/**
	 * Gamemodes menu.
	 *
	 * @param g the g
	 */
	public void gamemodesMenu(Graphics g) {
		bitterEnd.drawButton(g);
		bestUpperBound.drawButton(g);
		randomOrder.drawButton(g);
		gamemodesBackButton.drawButton(g);
	}

	/**
	 * Bitter end menu.
	 *
	 * @param g the g
	 */
	public void bitterEndMenu(Graphics g) {
		nodesSlider.drawSlider(g);
		edgesSlider.drawSlider(g);
		bitterEndStartButton.drawButton(g);
		selectionBackButton.drawButton(g);
	}

	/**
	 * Best upper bound menu.
	 *
	 * @param g the g
	 */
	public void bestUpperBoundMenu(Graphics g) {
		nodesSlider.drawSlider(g);
		edgesSlider.drawSlider(g);
		timeSlider.drawSlider(g);
		bestUpperBoundStartButton.drawButton(g);
		selectionBackButton.drawButton(g);
	}

	/**
	 * Random order menu.
	 *
	 * @param g the g
	 */
	public void randomOrderMenu(Graphics g) {
		nodesSlider.drawSlider(g);
		edgesSlider.drawSlider(g);
		randomOrderStartButton.drawButton(g);
		selectionBackButton.drawButton(g);
	}

	/**
	 * Sets the tings menu.
	 *
	 * @param g the new tings menu
	 */
	public void settingsMenu(Graphics g) {
		soundButton.drawButton(g);
		fixedRefreshRateCheckBox.drawButton(g);
		antialiasingCheckBox.drawButton(g);
		ditheringCheckBox.drawButton(g);
		smallNodesCheckBox.drawButton(g);
		settingsBackButton.drawButton(g);
	}

	/**
	 * Sound menu.
	 *
	 * @param g the g
	 */
	public void soundMenu(Graphics g) {
		menuMusicSlider.drawSlider(g);
		soundFXSlider.drawSlider(g);
		soundBackButton.drawButton(g);
	}

	/**
	 * Draw centered string.
	 *
	 * @param s the s
	 * @param w the w
	 * @param h the h
	 * @param g the g
	 */
	public void drawCenteredString(String s, int w, int h, Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		int x = (w - fm.stringWidth(s)) / 2;
		int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
		g.drawString(s, x, y);
	}
}
