package com.graphcoloring.menu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import com.graphcoloring.main.Game;

public class CustomButton {

	private int x, y;
	private int width, height;
	private boolean centered;
	private String text;
	private int borderRadius;

	private RoundRectangle2D body;

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
