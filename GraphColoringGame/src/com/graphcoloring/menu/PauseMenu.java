package com.graphcoloring.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.graphcoloring.main.Game;
import com.graphcoloring.main.Handler;

// TODO: Auto-generated Javadoc
/**
 * The Class PauseMenu.
 */
public class PauseMenu extends MouseAdapter {

	/** The game. */
	private Game game;
	
	/** The handler. */
	private Handler handler;
	
	/** The menu. */
	private Menu menu;

	/** The border radius. */
	private int borderRadius = 20;

	/** The resume button. */
	private CustomButton resumeButton;
	
	/** The quit button. */
	private CustomButton quitButton;

	/**
	 * Instantiates a new pause menu.
	 *
	 * @param game the game
	 * @param handler the handler
	 * @param menu the menu
	 */
	public PauseMenu(Game game, Handler handler, Menu menu) {
		this.game = game;
		this.handler = handler;
		this.menu = menu;

		resumeButton = new CustomButton(0, Game.HEIGHT / 4, 200, 50, true, "Resume", borderRadius);
		quitButton = new CustomButton(0, Game.HEIGHT / 4 * 2, 200, 50, true, "Quit", borderRadius);
	}

	/**
	 * Tick.
	 */
	public void tick() {
	}

	/**
	 * Render.
	 *
	 * @param g the g
	 */
	public void render(Graphics g) {

		g.setColor(new Color(0, 0, 0, 150));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(Color.BLACK);
		resumeButton.drawButton(g);
		quitButton.drawButton(g);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
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
