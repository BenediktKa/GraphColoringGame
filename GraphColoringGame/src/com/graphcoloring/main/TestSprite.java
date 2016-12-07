package com.graphcoloring.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestSprite extends GameObject {

	BufferedImage sprite;

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

	public void render(Graphics g) {
		g.drawImage(sprite, x, y, null);
	}

}
