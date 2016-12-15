package com.graphcoloring.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import com.graphcoloring.main.Game;
import com.graphcoloring.main.Handler;

// TODO: Auto-generated Javadoc
/**
 * The Class ScoreMenu.
 */
public class ScoreMenu extends MouseAdapter {

	/** The game. */
	private Game game;
	
	/** The handler. */
	private Handler handler;
	
	/** The menu. */
	private Menu menu;

	/** The score. */
	private int score;
	
	/** The display score. */
	private int displayScore;
	
	/** The border radius. */
	private int borderRadius = 20;
	
	/** The win. */
	private boolean win;
	
	/** The time. */
	private double time;
	
	/** The time left. */
	private boolean timeLeft;

	/** The quit button. */
	private CustomButton quitButton;

	/**
	 * Instantiates a new score menu.
	 *
	 * @param game the game
	 * @param handler the handler
	 * @param menu the menu
	 */
	public ScoreMenu(Game game, Handler handler, Menu menu) {
		this.game = game;
		this.handler = handler;
		this.menu = menu;

		quitButton = new CustomButton(0, Game.HEIGHT / 4 * 2, 200, 50, true, "Quit", borderRadius);
	}

	/**
	 * Tick.
	 */
	public void tick() {
		if(!win) {
			return;
		}
		
		if(displayScore < score && displayScore + 10 > score) {
			displayScore++;
		} else if(displayScore < score) {
			displayScore += 10;
		}
	}

	/**
	 * Render.
	 *
	 * @param g the g
	 */
	public void render(Graphics g) {
		
		g.setColor(new Color(0, 0, 0, 150));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		if(win) {
			g.setColor(Game.textColor);
			Font fnt = Game.getFont(2).deriveFont(Font.BOLD, 28f);
			g.setFont(fnt);
		
			DecimalFormat df = new DecimalFormat("#.##");
			
			if(timeLeft) {
				drawCenteredString("You finished with " + df.format(time) + " seconds left on the clock", Game.WIDTH, 100, g);
			} else {
				drawCenteredString("You finished in " + df.format(time) + " seconds", Game.WIDTH, 100, g);
			}
			drawCenteredString("Score: " + displayScore, Game.WIDTH, 200, g);
			quitButton.drawButton(g);
		} else {
			g.setColor(Game.textColor);
			Font fnt = Game.getFont(2).deriveFont(Font.BOLD, 28f);
			g.setFont(fnt);
		
			drawCenteredString("You Lost!", Game.WIDTH, 100, g);
			quitButton.drawButton(g);
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e) {
		if (game.gameState != Game.STATE.Score) {
			return;
		}

		int mx = e.getX();
		int my = e.getY();

		if (quitButton.mouseOver(mx, my)) {
			handler.removeAllObjects();
			game.gameState = Game.STATE.Menu;
		}
	}

	/**
	 * Draw centered string.
	 *
	 * @param s the s
	 * @param w the w
	 * @param y the y
	 * @param g the g
	 */
	public void drawCenteredString(String s, int w, int y, Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		int x = (w - fm.stringWidth(s)) / 2;
		g.drawString(s, x, y);
	}
	
	/**
	 * Sets the win.
	 *
	 * @param win the new win
	 */
	public void setWin(boolean win) {
		this.win = win;
	}
	
	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(double time) {
		this.time = time;
	}
	
	/**
	 * Sets the time left.
	 *
	 * @param timeLeft the new time left
	 */
	public void setTimeLeft(boolean timeLeft) {
		this.timeLeft = timeLeft;
	}

}
