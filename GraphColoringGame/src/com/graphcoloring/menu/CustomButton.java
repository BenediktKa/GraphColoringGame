package com.graphcoloring.menu;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import com.graphcoloring.main.Game;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomButton.
 */
public class CustomButton {

	/** The y. */
	private int x, y;
	
	/** The height. */
	private int width, height;
	
	/** The centered. */
	private boolean centered;
	
	/** The text. */
	private String text;
	
	/** The border radius. */
	private int borderRadius;

	/** The body. */
	private RoundRectangle2D body;

	/**
	 * Instantiates a new custom button.
	 *
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 * @param centered the centered
	 * @param text the text
	 * @param borderRadius the border radius
	 */
	public CustomButton(int x, int y, int width, int height, boolean centered, String text, int borderRadius) {
		if (centered) {
			this.x = Game.WIDTH / 2 - width / 2;
		} else {
			this.x = x;
		}
		this.y = y;
		this.width = width;
		this.height = height;
		this.centered = centered;
		this.text = text;
		this.borderRadius = borderRadius;
	}

	/**
	 * Draw button.
	 *
	 * @param g the g
	 */
	public void drawButton(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Game.transparentColor);
		if (centered) {
			body = new RoundRectangle2D.Double(Game.WIDTH / 2 - width / 2, y, width, height, borderRadius, borderRadius);
		} else {
			body = new RoundRectangle2D.Double(x - width / 2, y, width, height, borderRadius, borderRadius);
		}

		g2d.draw(body);
		drawText(g);
	}

	/**
	 * Draw text.
	 *
	 * @param g the g
	 */
	public void drawText(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		Font fnt = Game.getFont(1).deriveFont(Font.PLAIN, 28f);
		g2d.setFont(fnt);
		g2d.setColor(Game.textColor);

		if (centered) {
			drawCenteredString(text, Game.WIDTH, y + height / 2 + 10, g);
		} else {
			FontMetrics metrics = g2d.getFontMetrics(fnt);
			int textWidth = metrics.stringWidth(text);

			g2d.drawString(text, x - textWidth / 2, y + height / 2 + 10);
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
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the witdh.
	 *
	 * @param width the new witdh
	 */
	public void setWitdh(int width) {
		this.width = width;
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height.
	 *
	 * @param height the new height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Mouse over.
	 *
	 * @param mx the mx
	 * @param my the my
	 * @return true, if successful
	 */
	public boolean mouseOver(int mx, int my) {
		if(body == null) {
			return false;
		}
		
		if (mx > body.getMinX() && mx < body.getMinX() + width) {
			if (my > body.getMinY() && my < body.getMinY() + height) {
				Game.soundPlayer.playSoundFX("MenuClick");
				return true;
			} else
				return false;
		} else
			return false;
	}
}
