package com.graphcoloring.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.graphcoloring.hud.Notification;
import com.graphcoloring.hud.Notification.TYPE;
import com.graphcoloring.main.Game;
import com.graphcoloring.main.Handler;

public class Menu extends MouseAdapter {

	private Game game;
	private Handler handler;
	private Notification notification;

	private int spacing = 20;

	// Main Menu Elements
	private CustomButton playButton;
	private CustomButton settingsButton;
	private CustomButton quitButton;
	
	// Main Menu Elements
	private CustomButton bitterEnd;
	private CustomButton bestUpperBound;
	private CustomButton randomOrder;
	private CustomButton gamemodesBackButton;

	// Settings Menu Elements
	private CustomButton soundButton;
	private CustomButton antialiasingButton;
	private CustomButton settingsBackButton;

	// Sound Menu
	private CustomSlider menuMusicSlider;
	private CustomSlider soundFXSlider;
	private CustomButton soundBackButton;

	// States
	public enum MENUSTATE {
		Main, Settings, Sound, Gamemodes;
	};

	public MENUSTATE menuState;

	public Menu(Game game, Handler handler, Notification notification) {
		this.game = game;
		this.handler = handler;
		this.notification = notification;

		menuState = MENUSTATE.Main;

		/*
		 * for (int i = 0; i < 10; i++) { GraphNode node = new GraphNode((int)
		 * (Math.random() * Game.WIDTH), (int) (Math.random() * Game.HEIGHT),
		 * (int) (Math.random() * 10 - Math.random() * 10), (int) (Math.random()
		 * * 10 - Math.random() * 10), ID.GraphNode, new RandomColors(100,
		 * 0.05f).getPalette(), false); handler.addObject(node); }
		 */

		// testSlider = new CustomSlider(0, 100, 200, 20, true, 20, "Test:");

		// Main Menu
		playButton = new CustomButton(0, Game.HEIGHT / 4, 200, 50, true, "Play");
		settingsButton = new CustomButton(0, Game.HEIGHT / 4 * 2, 200, 50, true, "Settings");
		quitButton = new CustomButton(0, Game.HEIGHT / 4 * 3, 200, 50, true, "Quit");
		
		//Gamemodes Menu
		bitterEnd = new CustomButton(0, Game.HEIGHT / 5, 200, 50, true, "Bitter End");
		bestUpperBound = new CustomButton(0, Game.HEIGHT / 5 * 2, 200, 50, true, "Best Upper Bound");
		randomOrder = new CustomButton(0, Game.HEIGHT / 5 * 3, 200, 50, true, "Random Order");
		gamemodesBackButton = new CustomButton(0, Game.HEIGHT / 5 * 4, 200, 50, true, "Back");

		// Settings Menu
		soundButton = new CustomButton(0, Game.HEIGHT / 4, 200, 50, true, "Sounds");
		antialiasingButton = new CustomButton(0, Game.HEIGHT / 4 * 2, 200, 50, true, "Antialiasing");
		settingsBackButton = new CustomButton(0, Game.HEIGHT / 4 * 3, 200, 50, true, "Back");

		// Sound Menu
		menuMusicSlider = new CustomSlider(0, Game.HEIGHT / 4, 200, 25, true, 100, 100, "Menu Music:");
		soundFXSlider = new CustomSlider(0, Game.HEIGHT / 4 * 2, 200, 25, true, 100, 100, "SoundFX");
		soundBackButton = new CustomButton(0, Game.HEIGHT / 4 * 3, 200, 50, true, "Back");
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
			} else if (antialiasingButton.mouseOver(mx, my)) {
				Game.ANTIALIASING = Game.ANTIALIASING ? false : true;
			} else if (settingsBackButton.mouseOver(mx, my)) {
				menuState = MENUSTATE.Main;
			}
		}
		
		//Sound Menu
		else if(menuState == MENUSTATE.Sound) {
			if(soundBackButton.mouseOver(mx, my)) {
				menuState = MENUSTATE.Settings;
			}
		}
		
		//Game modes Menu
		else if(menuState == MENUSTATE.Gamemodes) {
			if(bitterEnd.mouseOver(mx, my)) {
				handler.removeAllObjects();
				game.initilizeGame();
				game.gameState = Game.STATE.Game;
			} else if(gamemodesBackButton.mouseOver(mx, my)) {
				menuState = MENUSTATE.Main;
			}
		}
	}

	public void mouseDragged(MouseEvent e) {
		if(game.gameState != Game.STATE.Menu) {
			return;
		}
		
		int mx = e.getX();
		int my = e.getY();
		
		if(menuState == MENUSTATE.Sound) {
			if(menuMusicSlider.contains(mx, my)) menuMusicSlider.setKnobX(mx);
			else if(soundFXSlider.contains(mx, my)) soundFXSlider.setKnobX(mx);
		}
	}

	public void tick() {
	}

	public void render(Graphics g) {
		if (game.gameState != Game.STATE.Menu) {
			return;
		}

		if (menuState == MENUSTATE.Main) {
			mainMenu(g);
		} else if (menuState == MENUSTATE.Settings) {
			settingsMenu(g);
		} else if(menuState == MENUSTATE.Sound) {
			soundMenu(g);
		} else if (menuState == MENUSTATE.Gamemodes) {
			gamemodesMenu(g);
		}
	}

	public void mainMenu(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		playButton.drawButton(g);
		settingsButton.drawButton(g);
		quitButton.drawButton(g);
	}
	
	public void gamemodesMenu(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		bitterEnd.drawButton(g);
		bestUpperBound.drawButton(g);
		randomOrder.drawButton(g);
		gamemodesBackButton.drawButton(g);
	}

	public void settingsMenu(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		soundButton.drawButton(g);
		antialiasingButton.drawButton(g);
		settingsBackButton.drawButton(g);
	}
	
	public void soundMenu(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
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

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}
}
