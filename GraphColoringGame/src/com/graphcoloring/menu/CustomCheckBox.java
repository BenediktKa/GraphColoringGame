package com.graphcoloring.menu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

import com.graphcoloring.main.Game;

public class CustomCheckBox {

	private int x, y;
	private int width, height;
	private boolean centered;
	private String text;

	private boolean knobState;
	private int knobX;

	private RoundRectangle2D body;
	private Ellipse2D knob;

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

	public void tick() {
		if (knobState) {
			setKnobX(knobX + 4);
		} else {
			setKnobX(knobX - 4);
		}
	}

	public void drawButton(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.black);
		if (centered) {
			body = new RoundRectangle2D.Double(Game.WIDTH / 2 - width / 2, y, width, height, 25, 25);
			knob = new Ellipse2D.Double(Game.WIDTH / 2 - width / 2 + knobX, y, height, height);
		} else {
			body = new RoundRectangle2D.Double(x - width / 2, y, width, height, 25, 25);
			knob = new Ellipse2D.Double(x - width / 2 + knobX, y, height, height);
		}

		g2d.setStroke(new BasicStroke(3));
		g2d.draw(body);

		if(knobState) {
			g2d.setColor(new Color(46, 204, 113, 255));
			g2d.fill(body);
			g2d.setColor(Color.black);
		}
		
		g2d.draw(knob);
		g2d.setColor(Color.white);
		g2d.fill(knob);
		g2d.setStroke(new BasicStroke(1));
		drawText(g);
	}

	public void drawText(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		Font fnt = new Font("arial", Font.BOLD, 20);
		g2d.setFont(fnt);
		g2d.setColor(Color.black);

		if (centered) {
			drawCenteredString(text, Game.WIDTH, y - 20, g);
		} else {
			FontMetrics metrics = g2d.getFontMetrics(fnt);
			int textWidth = metrics.stringWidth(text);

			g2d.drawString(text, x - textWidth / 2, y - 20);
		}
	}

	public void drawCenteredString(String s, int w, int y, Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		int x = (w - fm.stringWidth(s)) / 2;
		g.drawString(s, x, y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWitdh(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getKnobX() {
		return knobX;
	}

	public void setKnobX(int knobX) {
		if (knobX > width - height || knobX < 0) {
			return;
		}
		this.knobX = knobX;
	}

	public boolean getKnobState() {
		return knobState;
	}

	public void setKnobState() {
		this.knobState = this.knobState ? false : true;
	}

	public boolean mouseOver(int mx, int my) {
		if (mx > body.getMinX() && mx < body.getMinX() + width) {
			if (my > body.getMinY() && my < body.getMinY() + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}

}
