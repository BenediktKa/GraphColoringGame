package com.graphcoloring.menu;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

import com.graphcoloring.main.Game;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomSlider.
 */
public class CustomSlider {

	/** The y. */
	private int x, y;
	
	/** The knob Y. */
	private int knobX, knobY;
	
	/** The height. */
	private int width, height;
	
	/** The centered. */
	private boolean centered;
	
	/** The limit. */
	private int limit;
	
	/** The text. */
	private String text;

	/** The knob value. */
	private int knobValue;

	/** The body. */
	private RoundRectangle2D body;
	
	/** The knob. */
	private Ellipse2D knob;

	/**
	 * Instantiates a new custom slider.
	 *
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 * @param centered the centered
	 * @param limit the limit
	 * @param defaultValue the default value
	 * @param text the text
	 */
	public CustomSlider(int x, int y, int width, int height, boolean centered, int limit, int defaultValue, String text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.centered = centered;
		this.limit = limit;
		this.text = text;
		
		if(defaultValue > 0) {
			this.knobValue = defaultValue;
			this.knobX = (int) Math.ceil(((double)defaultValue * ((double)width / (double)limit)));
		}
	}

	/**
	 * Draw slider.
	 *
	 * @param g the g
	 */
	public void drawSlider(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Game.transparentColor);
		
		if (centered) {
			body = new RoundRectangle2D.Double(Game.WIDTH / 2 - width / 2, y, width, height, 25 , 25);
		} else {
			body = new RoundRectangle2D.Double(x, y, width, height, 25, 25);
		}
		
		g2d.draw(body);
		
		drawSliderKnob(g);

		Font fnt = Game.getFont(1).deriveFont(Font.PLAIN, 20f);
		g2d.setFont(fnt);
		g2d.setColor(Game.textColor);

		if(centered) {
			drawCenteredString(text, Game.WIDTH, y - 20, g);
		} else {
			FontMetrics metrics = g2d.getFontMetrics(fnt);
			int textWidth = metrics.stringWidth(text + " " + knobValue);
			
			g2d.drawString(text + " " + knobValue, x + width / 2 - textWidth / 2, y - 20);
		}
	}

	/**
	 * Draw slider knob.
	 *
	 * @param g the g
	 */
	public void drawSliderKnob(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		if (centered) {
			knob = new Ellipse2D.Double(Game.WIDTH / 2 - width / 2 - height + knobX, y - height / 2, height * 2, height * 2);
			g2d.setColor(Game.dimWhiteColor);
			g2d.fill(knob);
		} else {
			knob = new Ellipse2D.Double(x - height + knobX, y - height / 2, height * 2, height * 2);
			g2d.setColor(Game.dimWhiteColor);
			g2d.fill(knob);
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
		int x = (w - fm.stringWidth(s + " " + knobValue)) / 2;
		g.drawString(s + " " + knobValue, x, y);
	}

	/**
	 * Gets the knob X.
	 *
	 * @return the knob X
	 */
	public int getKnobX() {
		return knobX;
	}

	/**
	 * Gets the knob Y.
	 *
	 * @return the knob Y
	 */
	public int getKnobY() {
		return knobY;
	}

	/**
	 * Sets the knob X.
	 *
	 * @param x the new knob X
	 */
	public void setKnobX(int x) {
		if (x - body.getMinX() < 0 || x - body.getMinX() > width) {
			return;
		}

		this.knobX = (int) (x - body.getMinX());
		
		setKnobValue((int) Math.ceil(((double)knobX / ((double)width / (double)limit))));
	}

	/**
	 * Sets the knob Y.
	 *
	 * @param knobY the new knob Y
	 */
	public void setKnobY(int knobY) {
		this.knobY = knobY;
	}

	/**
	 * Contains.
	 *
	 * @param x the x
	 * @param y the y
	 * @return true, if successful
	 */
	public boolean contains(double x, double y) {
		if(knob == null) {
			return false;
		}
		
		return knob.contains(x, y);
	}

	/**
	 * Gets the knob value.
	 *
	 * @return the knob value
	 */
	public int getKnobValue() {
		return knobValue;
	}

	/**
	 * Sets the knob value.
	 *
	 * @param knobValue the new knob value
	 */
	public void setKnobValue(int knobValue) {
		this.knobValue = knobValue;
	}
}
