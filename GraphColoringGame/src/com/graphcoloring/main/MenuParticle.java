package com.graphcoloring.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class MenuParticle extends GameObject {

	private Handler handler;
	private Ellipse2D circleParticle;
	private float alpha;
	private float decay;
	
	private Random random =  new Random();

	public MenuParticle(Handler handler, int x, int y, float alpha, float decay, ID id) {
		super(x, y, id);

		this.handler = handler;
		this.alpha = alpha;
		this.decay = decay;
	}

	public void tick() {
		
		if(alpha > 0) {
			alpha -= decay;
		} else {
			handler.removeObject(this);
		}
		
		x += velX;
		y += velY;
		
		velX = random.nextInt(5) - random.nextInt(5);
		velY = -random.nextInt(5);
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		if(alpha < 0)
		{
			return;
		}
		g2d.setColor(new Color(1,1,1,alpha));
		circleParticle = new Ellipse2D.Double(x, y, 5, 5);
		
		g2d.draw(circleParticle);
	}

}
