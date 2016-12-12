package com.graphcoloring.hud;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.graphcoloring.main.Game;

public class HUDFPS {

	private int FPS;

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		String FPSString = "FPS: " + FPS;

		Font fnt = new Font("Lucida Grande", Font.PLAIN, 15);
		g2d.setFont(fnt);
		g2d.setColor(Game.textColor);

		g2d.drawString(FPSString, 0, 15);
	}

	public int getFPS() {
		return FPS;
	}

	public void setFPS(int FPS) {
		this.FPS = FPS;
	}

}
