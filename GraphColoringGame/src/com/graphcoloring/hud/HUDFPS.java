package com.graphcoloring.hud;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.graphcoloring.main.Game;

// TODO: Auto-generated Javadoc
/**
 * The Class HUDFPS.
 */
public class HUDFPS {

	/** The fps. */
	private int FPS;

	/**
	 * Render.
	 *
	 * @param g the g
	 */
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		String FPSString = "FPS: " + FPS;

		Font fnt = Game.getFont(1).deriveFont(Font.PLAIN, 20f);
		g2d.setFont(fnt);
		g2d.setColor(Game.transparentColor);

		g2d.drawString(FPSString, 0, 20);
	}

	/**
	 * Gets the fps.
	 *
	 * @return the fps
	 */
	public int getFPS() {
		return FPS;
	}

	/**
	 * Sets the fps.
	 *
	 * @param FPS the new fps
	 */
	public void setFPS(int FPS) {
		this.FPS = FPS;
	}

}
