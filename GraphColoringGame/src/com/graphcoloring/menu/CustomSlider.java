package com.graphcoloring.menu;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

import com.graphcoloring.main.Game;

public class CustomSlider {

	private int x, y;
	private int knobX, knobY;
	private int width, height;
	private boolean centered;
	private int limit;
	private String text;

	private int knobValue;

	private RoundRectangle2D body;
	private Ellipse2D knob;

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

	public void drawCenteredString(String s, int w, int y, Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		int x = (w - fm.stringWidth(s + " " + knobValue)) / 2;
		g.drawString(s + " " + knobValue, x, y);
	}

	public int getKnobX() {
		return knobX;
	}

	public int getKnobY() {
		return knobY;
	}

	public void setKnobX(int x) {
		if (x - body.getMinX() < 0 || x - body.getMinX() > width) {
			return;
		}

		this.knobX = (int) (x - body.getMinX());
		
		setKnobValue((int) Math.ceil(((double)knobX / ((double)width / (double)limit))));
	}

	public void setKnobY(int knobY) {
		this.knobY = knobY;
	}

	public boolean contains(double x, double y) {
		if(knob == null) {
			return false;
		}
		
		return knob.contains(x, y);
	}

	public int getKnobValue() {
		return knobValue;
	}

	public void setKnobValue(int knobValue) {
		this.knobValue = knobValue;
	}
}
