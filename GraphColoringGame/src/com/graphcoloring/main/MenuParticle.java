package com.graphcoloring.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuParticle.
 */
public class MenuParticle extends GameObject {

	/** The handler. */
	private Handler handler;
	
	/** The circle particle. */
	private Ellipse2D circleParticle;
	
	/** The alpha. */
	private float alpha;
	
	/** The decay. */
	private float decay;
	
	/** The random. */
	private Random random =  new Random();

	/**
	 * Instantiates a new menu particle.
	 *
	 * @param handler the handler
	 * @param x the x
	 * @param y the y
	 * @param alpha the alpha
	 * @param decay the decay
	 * @param id the id
	 */
	public MenuParticle(Handler handler, int x, int y, float alpha, float decay, ID id) {
		super(x, y, id);

		this.handler = handler;
		this.alpha = alpha;
		this.decay = decay;
	}

	/* (non-Javadoc)
	 * @see com.graphcoloring.main.GameObject#tick()
	 */
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

	/* (non-Javadoc)
	 * @see com.graphcoloring.main.GameObject#render(java.awt.Graphics)
	 */
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
