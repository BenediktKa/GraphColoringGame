package com.graphcoloring.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

// TODO: Auto-generated Javadoc
/**
 * The Class TestSprite.
 */
public class TestSprite extends GameObject {

	/** The sprite. */
	BufferedImage sprite;

	/**
	 * Instantiates a new test sprite.
	 *
	 * @param x the x
	 * @param y the y
	 * @param id the id
	 */
	public TestSprite(int x, int y, ID id) {
		super(x, y, id);

		try {
			sprite = ImageIO.read(new File("src\\img\\test.png"));
		} catch (IOException error) {
			System.out.println(error);
		}
		velX = (int)(Math.random() * 10 - Math.random() * 10);
		velY = (int)(Math.random() * 10 - Math.random() * 10);
	}

	/* (non-Javadoc)
	 * @see com.graphcoloring.main.GameObject#tick()
	 */
	public void tick() {

		if (x > Game.WIDTH) {
			velX = -velX;
		} else if (x < 0) {
			velX = -velX;
		}

		if (y > Game.HEIGHT) {
			velY = -velY;
		} else if (y < 0) {
			velY = -velY;
		}

		x += velX;
		y += velY;

		x += velX;
		y += velY;
	}

	/* (non-Javadoc)
	 * @see com.graphcoloring.main.GameObject#render(java.awt.Graphics)
	 */
	public void render(Graphics g) {
		g.drawImage(sprite, x, y, null);
	}

}
