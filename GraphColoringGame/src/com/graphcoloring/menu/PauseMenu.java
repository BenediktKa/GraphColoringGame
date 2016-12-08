package com.graphcoloring.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.graphcoloring.main.Game;

public class PauseMenu extends MouseAdapter {
	
	private Game game;
	private Menu menu;
	
	private CustomButton resumeButton;
	private CustomButton settingsButton;
	private CustomButton quitButton;

	public PauseMenu(Game game, Menu menu) {
		this.game = game;
		this.menu = menu;
		
		resumeButton = new CustomButton(0, Game.HEIGHT / 4, 200, 50, true, "Resume");
		settingsButton = new CustomButton(0, Game.HEIGHT / 4 * 2, 200, 50, true, "Settings");
		quitButton = new CustomButton(0, Game.HEIGHT / 4 * 3, 200, 50, true, "Quit");
	}

	public void tick() {

	}

	public void render(Graphics g) {

		g.setColor(new Color(255, 255, 255, 150));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(Color.BLACK);
		resumeButton.drawButton(g);
		quitButton.drawButton(g);
		settingsButton.drawButton(g);
	}
	
	public void mousePressed(MouseEvent e) {
		if (game.gameState != Game.STATE.Pause) {
			return;
		}
		
		int mx = e.getX();
		int my = e.getY();
		
		if(resumeButton.mouseOver(mx, my)) {
			game.gameState = Game.STATE.Game;
		} else if(settingsButton.mouseOver(mx, my)) {
			menu.menuState = Menu.MENUSTATE.Settings;
			game.gameState = Game.STATE.Menu;
		} else if(quitButton.mouseOver(mx, my)) {
			menu.menuState = Menu.MENUSTATE.Main;
			game.gameState = Game.STATE.Menu;
		}
	}
}
