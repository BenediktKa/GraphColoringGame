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

public class ScoreMenu extends MouseAdapter {

	private Game game;
	private Handler handler;
	private Menu menu;

	private int score;
	private int displayScore;
	private int borderRadius = 20;
	private boolean win;
	
	private double time;
	private boolean timeLeft;

	private CustomButton quitButton;

	public ScoreMenu(Game game, Handler handler, Menu menu) {
		this.game = game;
		this.handler = handler;
		this.menu = menu;

		quitButton = new CustomButton(0, Game.HEIGHT / 4 * 2, 200, 50, true, "Quit", borderRadius);
	}

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

	public void drawCenteredString(String s, int w, int y, Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		int x = (w - fm.stringWidth(s)) / 2;
		g.drawString(s, x, y);
	}
	
	public void setWin(boolean win) {
		this.win = win;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void setTime(double time) {
		this.time = time;
	}
	
	public void setTimeLeft(boolean timeLeft) {
		this.timeLeft = timeLeft;
	}

}
