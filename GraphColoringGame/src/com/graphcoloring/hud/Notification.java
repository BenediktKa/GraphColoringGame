package com.graphcoloring.hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Timer;
import java.util.TimerTask;

import com.graphcoloring.main.Game;

public class Notification {

	// Notification Types
	public enum TYPE {
		Error,
		Hint;
	};

	private RoundRectangle2D errorRectangle;
	private boolean notificationVisible;
	private boolean fadeOut;
	private boolean fadeIn;
	private float alpha;

	private TYPE type;
	private String message;

	private Timer timer;

	public void createNotification(TYPE type, String message, int seconds) {
		this.type = type;
		this.message = message;

		notificationVisible = true;
		fadeIn = true;
		alpha = 0;

		// Timer
		timer = new Timer();
		timer.schedule(new CloseNotification(), seconds * 1000);
	}

	class CloseNotification extends TimerTask {
		public void run() {
			fadeOut = true;
			timer.cancel();
		}
	}

	public void displayError(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		int rectangleHeight = Game.HEIGHT / 3;

		Font fnt = Game.getFont(1).deriveFont(Font.BOLD, 25f);
		g2d.setFont(fnt);
		
		FontMetrics metrics = g2d.getFontMetrics(fnt);
		int textWidth = metrics.stringWidth(message) + 50;
		
		errorRectangle = new RoundRectangle2D.Double(Game.WIDTH / 2 - (textWidth / 2), Game.HEIGHT / 2 - (rectangleHeight / 2), textWidth, rectangleHeight, 25, 25);

		Color color, colorBorder;
		if (alpha < 0) {
			color = new Color(125 / 255.0f, 38 / 255.0f, 28 / 255.0f, 0);
			colorBorder = new Color(168 / 255.0f, 186 / 255.0f, 189 / 255.0f, 0);
		} else {
			color = new Color(125 / 255.0f, 38 / 255.0f, 28 / 255.0f, alpha);
			colorBorder = new Color(168 / 255.0f, 186 / 255.0f, 189 / 255.0f, alpha);
		}
		
		g2d.setPaint(color);
		g2d.fill(errorRectangle);
		g2d.setPaint(colorBorder);
		g2d.draw(errorRectangle);
		
		if (alpha < 0) {
			color = new Color(236.0f / 255.0f, 240.0f / 255.0f, 241.0f / 255.0f, 0);
		} else {
			color = new Color(236.0f / 255.0f, 240.0f / 255.0f, 241.0f / 255.0f, alpha);
		}
		g2d.setPaint(color);
		
		
		drawCenteredString(message, Game.WIDTH, Game.HEIGHT, g);
	}
	
	public void displayHint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		int rectangleHeight = Game.HEIGHT / 3;

		Font fnt = Game.getFont(1).deriveFont(Font.BOLD, 25f);
		g2d.setFont(fnt);
		
		FontMetrics metrics = g2d.getFontMetrics(fnt);
		int textWidth = metrics.stringWidth(message) + 50;
		
		errorRectangle = new RoundRectangle2D.Double(Game.WIDTH / 2 - (textWidth / 2), Game.HEIGHT / 2 - (rectangleHeight / 2), textWidth, rectangleHeight, 25, 25);

		Color color, colorBorder;
		if (alpha < 0) {
			color = new Color(44 / 255.0f, 62 / 255.0f, 80 / 255.0f, 0);
			colorBorder = new Color(168 / 255.0f, 186 / 255.0f, 189 / 255.0f, 0);
		} else {
			color = new Color(44 / 255.0f, 62 / 255.0f, 80 / 255.0f, alpha);
			colorBorder = new Color(168 / 255.0f, 186 / 255.0f, 189 / 255.0f, alpha);
		}
		
		g2d.setPaint(color);
		g2d.fill(errorRectangle);
		g2d.setPaint(colorBorder);
		g2d.draw(errorRectangle);
		
		if (alpha < 0) {
			color = new Color(236.0f / 255.0f, 240.0f / 255.0f, 241.0f / 255.0f, 0);
		} else {
			color = new Color(236.0f / 255.0f, 240.0f / 255.0f, 241.0f / 255.0f, alpha);
		}
		g2d.setPaint(color);
		
		
		drawCenteredString(message, Game.WIDTH, Game.HEIGHT, g);
	}

	public void tick() {
		if (!notificationVisible) {
			return;
		}

		if (fadeOut) {
			fadeOut();
		} else if (fadeIn) {
			fadeIn();
		}
	}

	public void render(Graphics g) {
		if (!notificationVisible) {
			return;
		}

		if (type == TYPE.Error) {
			displayError(g);
		} else if(type == TYPE.Hint) {
			displayHint(g);
		}
	}

	public void drawCenteredString(String s, int w, int h, Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		int x = (w - fm.stringWidth(s)) / 2;
		int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
		g.drawString(s, x, y);
	}

	public void fadeIn() {
		if (alpha < 1.0) {
			alpha += 0.05;

			if (alpha > 1.0) {
				alpha = 1.0f;
			}

		} else {
			fadeIn = false;
		}
	}

	public void fadeOut() {
		if (alpha > 0.0) {
			alpha -= 0.05;

			if (alpha < 0.0) {
				alpha = 0.0f;
			}

		} else {
			fadeOut = false;
			notificationVisible = false;
		}
	}
}
