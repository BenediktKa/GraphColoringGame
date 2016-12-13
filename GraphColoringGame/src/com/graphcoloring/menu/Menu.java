package com.graphcoloring.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.graphcoloring.hud.Notification;
import com.graphcoloring.hud.Notification.TYPE;
import com.graphcoloring.main.Game;
import com.graphcoloring.main.Handler;
import com.graphcoloring.main.SoundPlayer;

public class Menu extends MouseAdapter {

	private Game game;
	private Handler handler;
	private Notification notification;
	private SoundPlayer soundPlayer;

	private int borderRadius = 20;

	// Main Menu Elements
	private CustomButton playButton;
	private CustomButton settingsButton;
	private CustomButton quitButton;

	// Gamemode Elements
	private CustomButton bitterEnd;
	private CustomButton bestUpperBound;
	private CustomButton randomOrder;
	private CustomButton gamemodesBackButton;

	// Gamemode selection Elements
	private CustomSlider nodesSlider;
	private CustomSlider edgesSlider;

	// Bitter End Elements
	private CustomButton bitterEndStartButton;

	// Best UpperBound Elements
	private CustomSlider timeSlider;
	private CustomButton bestUpperBoundStartButton;

	// Gamemode Selection Back Button
	private CustomButton selectionBackButton;

	// Settings Menu Elements
	private CustomButton soundButton;
	private CustomCheckBox fixedRefreshRateCheckBox;
	private CustomCheckBox antialiasingCheckBox;
	private CustomCheckBox ditheringCheckBox;
	private CustomCheckBox smallNodesCheckBox;
	private CustomButton settingsBackButton;

	// Sound Menu
	private CustomSlider menuMusicSlider;
	private CustomSlider soundFXSlider;
	private CustomButton soundBackButton;

	// States
	public enum MENUSTATE {
		Main, Settings, Sound, Gamemodes, BitterEnd, BestUpperBound, RandomOrder;
	};

	public MENUSTATE menuState;

	public Menu(Game game, Handler handler, Notification notification, SoundPlayer soundPlayer) {
		this.game = game;
		this.handler = handler;
		this.notification = notification;
		this.soundPlayer = soundPlayer;

		menuState = MENUSTATE.Main;

		initialize();
	}

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
		nodesSlider = new CustomSlider(0, Game.HEIGHT / 6, 200, 25, true, 30, 10, "Nodes");
		edgesSlider = new CustomSlider(0, Game.HEIGHT / 6 * 2, 200, 25, true, 30, 15, "Edges");

		// Bitter End
		bitterEndStartButton = new CustomButton(0, Game.HEIGHT / 6 * 3, 200, 50, true, "Start", borderRadius);

		// BestUpperBound
		timeSlider = new CustomSlider(0, Game.HEIGHT / 6 * 3, 200, 25, true, 300, 20, "Seconds");
		bestUpperBoundStartButton = new CustomButton(0, Game.HEIGHT / 6 * 4, 200, 50, true, "Start", borderRadius);

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
					notification.createNotification(TYPE.Error, "Error: You can't have more nodes than edges!", 2);
					return;
				}

				game.gameState = Game.STATE.Game;
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
				} else if(time < nodes * 3) {
					notification.createNotification(TYPE.Error, "Error: Your time should be at least " + (nodes * 3 + edges * 2) + " seconds", 3);
					return;
				}

				game.gameState = Game.STATE.Game;
				game.initilizeGame(nodes, edges, time, Game.GAMEMODE.BestUpperBound);
			} else if (selectionBackButton.mouseOver(mx, my)) {
				menuState = MENUSTATE.Gamemodes;
			}
		}
	}

	public void mouseDragged(MouseEvent e) {
		if (game.gameState != Game.STATE.Menu) {
			return;
		}

		int mx = e.getX();
		int my = e.getY();

		if (menuState == MENUSTATE.Sound) {
			if (menuMusicSlider.contains(mx, my))
				menuMusicSlider.setKnobX(mx);
			else if (soundFXSlider.contains(mx, my))
				soundFXSlider.setKnobX(mx);
		} else if (menuState == MENUSTATE.BitterEnd) {
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

	public void tick() {
		if (menuState == MENUSTATE.Settings) {
			antialiasingCheckBox.tick();
			ditheringCheckBox.tick();
			smallNodesCheckBox.tick();
			fixedRefreshRateCheckBox.tick();
		}
	}

	public void render(Graphics g) {
		if (game.gameState != Game.STATE.Menu) {
			return;
		}

		Font fnt = Game.getFont(2).deriveFont(Font.BOLD, 30f);
		g.setFont(fnt);
		g.setColor(Game.textColor);
		drawCenteredString("GraphColoring Game", Game.WIDTH, Game.HEIGHT / 15 + 30, g);

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
		}
	}

	public void mainMenu(Graphics g) {
		playButton.drawButton(g);
		settingsButton.drawButton(g);
		quitButton.drawButton(g);
	}

	public void gamemodesMenu(Graphics g) {
		bitterEnd.drawButton(g);
		bestUpperBound.drawButton(g);
		randomOrder.drawButton(g);
		gamemodesBackButton.drawButton(g);
	}

	public void bitterEndMenu(Graphics g) {
		nodesSlider.drawSlider(g);
		edgesSlider.drawSlider(g);
		bitterEndStartButton.drawButton(g);
		selectionBackButton.drawButton(g);
	}

	public void bestUpperBoundMenu(Graphics g) {
		nodesSlider.drawSlider(g);
		edgesSlider.drawSlider(g);
		timeSlider.drawSlider(g);
		bestUpperBoundStartButton.drawButton(g);
		selectionBackButton.drawButton(g);
	}

	public void settingsMenu(Graphics g) {
		soundButton.drawButton(g);
		fixedRefreshRateCheckBox.drawButton(g);
		antialiasingCheckBox.drawButton(g);
		ditheringCheckBox.drawButton(g);
		smallNodesCheckBox.drawButton(g);
		settingsBackButton.drawButton(g);
	}

	public void soundMenu(Graphics g) {
		menuMusicSlider.drawSlider(g);
		soundFXSlider.drawSlider(g);
		soundBackButton.drawButton(g);
	}

	public void drawCenteredString(String s, int w, int h, Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		int x = (w - fm.stringWidth(s)) / 2;
		int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
		g.drawString(s, x, y);
	}
}
