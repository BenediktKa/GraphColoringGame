package com.graphcoloring.menu;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.graphcoloring.main.Game;

public class LoadingMenu {

	public void tick() {

	}

	public void render(Graphics g) {
		g.setColor(Game.textColor);
		g.setFont(Game.getFont(2).deriveFont(Font.BOLD, 200f));
		drawCenteredString("Loading...", Game.WIDTH, Game.HEIGHT / 2, g);
	}

	public void drawCenteredString(String s, int w, int y, Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		int x = (w - fm.stringWidth(s)) / 2;
		g.drawString(s, x, y);
	}
}
