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

// TODO: Auto-generated Javadoc
/**
 * The Class Notification.
 */
public class Notification {

	/**
	 * The Enum TYPE.
	 */
	// Notification Types
	public enum TYPE {
		
		/** The Error. */
		Error,
		
		/** The Hint. */
		Hint;
	};

	/** The error rectangle. */
	private RoundRectangle2D errorRectangle;
	
	/** The notification visible. */
	private boolean notificationVisible;
	
	/** The fade out. */
	private boolean fadeOut;
	
	/** The fade in. */
	private boolean fadeIn;
	
	/** The alpha. */
	private float alpha;

	/** The type. */
	private TYPE type;
	
	/** The message. */
	private String message;

	/** The timer. */
	private Timer timer;

	/**
	 * Creates the notification.
	 *
	 * @param type the type
	 * @param message the message
	 * @param seconds the seconds
	 */
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

	/**
	 * The Class CloseNotification.
	 */
	class CloseNotification extends TimerTask {
		
		/* (non-Javadoc)
		 * @see java.util.TimerTask#run()
		 */
		public void run() {
			fadeOut = true;
			timer.cancel();
		}
	}

	/**
	 * Display error.
	 *
	 * @param g the g
	 */
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
	
	/**
	 * Display hint.
	 *
	 * @param g the g
	 */
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

	/**
	 * Tick.
	 */
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

	/**
	 * Render.
	 *
	 * @param g the g
	 */
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

	/**
	 * Fade in.
	 */
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

	/**
	 * Fade out.
	 */
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
