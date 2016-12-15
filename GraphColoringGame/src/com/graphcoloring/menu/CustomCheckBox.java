package com.graphcoloring.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

import com.graphcoloring.main.Game;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomCheckBox.
 */
public class CustomCheckBox {

	/** The y. */
	private int x, y;
	
	/** The height. */
	private int width, height;
	
	/** The centered. */
	private boolean centered;
	
	/** The text. */
	private String text;

	/** The knob state. */
	private boolean knobState;
	
	/** The knob X. */
	private int knobX;

	/** The body. */
	private RoundRectangle2D body;
	
	/** The knob. */
	private Ellipse2D knob;

	/**
	 * Instantiates a new custom check box.
	 *
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 * @param centered the centered
	 * @param text the text
	 * @param knobState the knob state
	 */
	public CustomCheckBox(int x, int y, int width, int height, boolean centered, String text, boolean knobState) {
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
		this.knobState = knobState;

		if (knobState) {
			knobX = width - height;
		}
	}

	/**
	 * Tick.
	 */
	public void tick() {
		if (knobState) {
			setKnobX(knobX + 4);
		} else {
			setKnobX(knobX - 4);
		}
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
			body = new RoundRectangle2D.Double(Game.WIDTH / 2 - width / 2, y, width, height, 25, 25);
			knob = new Ellipse2D.Double(Game.WIDTH / 2 - width / 2 + knobX, y, height, height);
		} else {
			body = new RoundRectangle2D.Double(x - width / 2, y, width, height, 25, 25);
			knob = new Ellipse2D.Double(x - width / 2 + knobX, y, height, height);
		}

		if(knobState) {
			g2d.setColor(new Color(39, 174, 96, 255));
			g2d.fill(body);
			g2d.setColor(Game.transparentColor);
		}

		
		g2d.draw(body);
		
		g2d.draw(knob);
		g2d.setColor(Game.textColor);
		g2d.fill(knob);
		
		drawText(g);
	}

	/**
	 * Draw text.
	 *
	 * @param g the g
	 */
	public void drawText(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		Font fnt = Game.getFont(1).deriveFont(Font.PLAIN, 20f);
		g2d.setFont(fnt);
		g2d.setColor(Game.textColor);

		if (centered) {
			drawCenteredString(text, Game.WIDTH, y - 20, g);
		} else {
			FontMetrics metrics = g2d.getFontMetrics(fnt);
			int textWidth = metrics.stringWidth(text);

			g2d.drawString(text, x - textWidth / 2, y - 20);
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
	 * Gets the knob X.
	 *
	 * @return the knob X
	 */
	public int getKnobX() {
		return knobX;
	}

	/**
	 * Sets the knob X.
	 *
	 * @param knobX the new knob X
	 */
	public void setKnobX(int knobX) {
		if (knobX > width - height || knobX < 0) {
			return;
		}
		this.knobX = knobX;
	}

	/**
	 * Gets the knob state.
	 *
	 * @return the knob state
	 */
	public boolean getKnobState() {
		return knobState;
	}

	/**
	 * Sets the knob state.
	 */
	public void setKnobState() {
		this.knobState = this.knobState ? false : true;
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
				return true;
			} else
				return false;
		} else
			return false;
	}

}
