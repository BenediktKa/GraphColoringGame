package com.graphcoloring.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.graphcoloring.main.Game;
import com.graphcoloring.main.Handler;

public class PauseMenu extends MouseAdapter {

	private Game game;
	private Handler handler;
	private Menu menu;

	private int borderRadius = 20;

	private CustomButton resumeButton;
	private CustomButton quitButton;

	public PauseMenu(Game game, Handler handler, Menu menu) {
		this.game = game;
		this.handler = handler;
		this.menu = menu;

		resumeButton = new CustomButton(0, Game.HEIGHT / 4, 200, 50, true, "Resume", borderRadius);
		quitButton = new CustomButton(0, Game.HEIGHT / 4 * 2, 200, 50, true, "Quit", borderRadius);
	}

	public void tick() {
	}

	public void render(Graphics g) {

		g.setColor(new Color(0, 0, 0, 150));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(Color.BLACK);
		resumeButton.drawButton(g);
		quitButton.drawButton(g);
	}

	public void mousePressed(MouseEvent e) {
		if (game.gameState != Game.STATE.Pause) {
			return;
		}

		int mx = e.getX();
		int my = e.getY();

		if (resumeButton.mouseOver(mx, my)) {
			game.gameState = Game.STATE.Game;
		} else if (quitButton.mouseOver(mx, my)) {
			handler.removeAllObjects();
			game.gameState = Game.STATE.Menu;
		}
	}
}
