package com.graphcoloring.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.graphcoloring.main.Game;

public class CustomButton {

	private int x, y;
	private int width, height;
	private boolean centered;
	private String text;
	
	private Rectangle body;

	public CustomButton(int x, int y, int width, int height, boolean centered, String text) {
		if(centered) {
			this.x = Game.WIDTH / 2 - width / 2;
		} else {
			this.x = x;
		}
		this.y = y;
		this.width = width;
		this.height = height;
		this.centered = centered;
		this.text = text;
	}
	
	public void drawButton(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.black);
		if (centered) {
			body = new Rectangle(Game.WIDTH / 2 - width / 2, y, width, height);
		} else {
			body = new Rectangle(x - width / 2, y, width, height);
		}
		
		g2d.draw(body);
		drawText(g);
	}
	
	public void drawText(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt = new Font("arial", Font.BOLD, 20);
		g2d.setFont(fnt);
		g2d.setColor(Color.black);

		if(centered) {
			drawCenteredString(text, Game.WIDTH, y + height / 2 + 10, g);
		} else {
			drawCenteredString(text, x + width, y + height / 2 + 10, g);
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
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}

}
