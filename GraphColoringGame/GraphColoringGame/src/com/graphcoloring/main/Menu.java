package com.graphcoloring.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.graphcoloring.main.Game.STATE;

public class Menu extends MouseAdapter {
	
	private Game game;
	private Handler handler;
	
	private int spacing = 20;
	
	//Main Menu Elements
	Rectangle playButton;
	Rectangle settingsButton;
	Rectangle quitButton;
	
	//Settings Menu Elements
	Rectangle soundButton;
	
	//Back Button
	Rectangle backButton;
	
	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}

	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu) {
			if(mouseOver(mx, my, playButton.x, playButton.y, playButton.width, playButton.height)) {
				game.gameState = STATE.Game;
				game.initilizeGame();
			} else if(mouseOver(mx, my, settingsButton.x, settingsButton.y, settingsButton.width, settingsButton.height)) {
				game.gameState = STATE.Settings;
			} else if(mouseOver(mx, my, quitButton.x, quitButton.y, quitButton.width, quitButton.height)) {
				System.exit(1);
			} 
		} else if(game.gameState == STATE.Settings) {
			if(mouseOver(mx, my, backButton.x, backButton.y, backButton.width, backButton.height)) {
				game.gameState = STATE.Menu;
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(game.gameState == STATE.Menu) {
			mainMenu(g);
		} else if (game.gameState == STATE.Settings) {
			settingsMenu(g);
		} else if(game.gameState == STATE.Gamemodes) {
			gamemodesMenu(g);
		}
	}
	
	public void mainMenu(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt);
		g.setColor(Color.black);
		drawCenteredString("Graph Coloring", Game.WIDTH, 50, g);
		
		playButton = new Rectangle(0, 75, Game.WIDTH / 3, 50);
		settingsButton = new Rectangle(0, playButton.y + playButton.height + spacing, Game.WIDTH / 3, 50);
		quitButton = new Rectangle(0, settingsButton.y + settingsButton.height + spacing, Game.WIDTH / 3, 50);
		
		Font fnt1 = new Font("arial", Font.BOLD, 20);
		g.setFont(fnt1);
		
		g2d.drawString("Play", playButton.x + 20, playButton.y + (playButton.height / 2) + 10);
		g2d.drawString("Settings", settingsButton.x + 20, settingsButton.y + (settingsButton.height / 2) + 10);
		g2d.drawString("Quit", quitButton.x + 20, quitButton.y + (quitButton.height / 2) + 10);
		
		g2d.draw(playButton);
		g2d.draw(settingsButton);
		g2d.draw(quitButton);
	}
	
	public void settingsMenu(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt);
		g.setColor(Color.black);
		drawCenteredString("Graph Coloring", Game.WIDTH, 50, g);
		
		soundButton = new Rectangle(0, 75, Game.WIDTH / 3, 50);
		backButton = new Rectangle(Game.WIDTH - Game.WIDTH / 3, Game.HEIGHT - 100, Game.WIDTH / 3, 50);
		
		Font fnt1 = new Font("arial", Font.BOLD, 20);
		g.setFont(fnt1);
		
		g2d.drawString("Sound", soundButton.x + 20, soundButton.y + (soundButton.height / 2) + 10);
		g2d.drawString("Back", backButton.x + 20, backButton.y + (backButton.height / 2) + 10);
		
		g2d.draw(soundButton);
		g2d.draw(backButton);
	}
	
	public void gamemodesMenu(Graphics g) {
		
	}
	
	public void drawCenteredString(String s, int w, int h, Graphics g) {
	    FontMetrics fm = g.getFontMetrics();
	    int x = (w - fm.stringWidth(s)) / 2;
	    int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
	    g.drawString(s, x, y);
	  }
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			} else return false;
		} else return false;
	}
}
