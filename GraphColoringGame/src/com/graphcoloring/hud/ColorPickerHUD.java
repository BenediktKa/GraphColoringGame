package com.graphcoloring.hud;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedList;

import com.graphcoloring.main.Game;

// TODO: Auto-generated Javadoc
/**
 * The Class ColorPickerHUD.
 */
public class ColorPickerHUD extends MouseAdapter {

	/** The game. */
	private Game game;
	
	/** The notification. */
	private Notification notification;

	/** The border radius. */
	private int borderRadius = 25;

	/** The selector. */
	private RoundRectangle2D selector;
	
	/** The adder. */
	private RoundRectangle2D adder;
	
	/** The remover. */
	private RoundRectangle2D remover;

	/** The color array. */
	private Color colorArray[];
	
	/** The active color. */
	private int activeColor;

	/** The selector active. */
	private boolean selectorActive;

	/** The circle list. */
	private LinkedList<RoundRectangle2D> circleList = new LinkedList<RoundRectangle2D>();

	/**
	 * Instantiates a new color picker HUD.
	 *
	 * @param game the game
	 * @param notification the notification
	 */
	public ColorPickerHUD(Game game, Notification notification) {
		this.game = game;
		this.notification = notification;

		intialize();
	}

	/**
	 * Intialize.
	 */
	public void intialize() {
		selector = new RoundRectangle2D.Double(Game.WIDTH - 75, 5, 50, 50, borderRadius, borderRadius);
	}

	/**
	 * Tick.
	 */
	public void tick() {
	}

	/**
	 * Render.
	 *
	 * @param g the g
	 */
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		if (selectorActive) {
			for (int i = 1; i < circleList.size(); i++) {
				RoundRectangle2D circle = circleList.get(i);
				circleList.remove(i);
				circle = new RoundRectangle2D.Double(Game.WIDTH - 75 - selector.getWidth() - (i - 1) * 60 - 10, 5, 50, 50, borderRadius, borderRadius);
				circleList.add(i, circle);

				g2d.setColor(colorArray[i]);
				g2d.fill(circle);

				if (i == activeColor) {
					g2d.setColor(Game.dimWhiteColor);
					g2d.setStroke(new BasicStroke(2));
					g2d.draw(circle);
					g2d.setStroke(new BasicStroke(1));
				}
			}
			
			Font fnt = Game.getFont(2).deriveFont(Font.BOLD, 100f);
			g2d.setFont(fnt);
			
			g2d.setColor(Game.transparentColor);
			
			if(circleList.size() > 1) {
				g2d.drawString("-",(int) (Game.WIDTH - (circleList.size() * 60) - selector.getWidth() - 16.5), 65);
			}
			g2d.drawString("+",(int) (Game.WIDTH - (circleList.size() * 60) - selector.getWidth() * 2 - 32), 72);
			
			remover = new RoundRectangle2D.Double(Game.WIDTH - 75 - selector.getWidth() - (circleList.size() - 1) * 60 - 10, 5, 50, 50, borderRadius, borderRadius);
			adder = new RoundRectangle2D.Double(Game.WIDTH - 75 - selector.getWidth() - (circleList.size() - 1) * 60 - 20 - remover.getWidth(), 5, 50, 50, borderRadius, borderRadius);
			g2d.draw(adder);
			if(circleList.size() > 1) {
				g2d.draw(remover);	
			}
		}
		
		Font fnt = Game.getFont(2).deriveFont(Font.BOLD, 50f);
		g2d.setFont(fnt);
		
		if(selectorActive) {
			g2d.drawString(">",(int) (Game.WIDTH - 60), 47);
		} else {
			g2d.drawString("<",(int) (Game.WIDTH - 60), 47);
		}
		g2d.draw(selector);
	}

	/**
	 * Adds the circle.
	 */
	public void addCircle() {
		if(circleList.isEmpty()) {
			circleList.add(new RoundRectangle2D.Double());
			circleList.add(new RoundRectangle2D.Double());
		} else if (circleList.size() < colorArray.length) {
			circleList.add(new RoundRectangle2D.Double());
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent event) {
		if (game.gameState != Game.STATE.Game) {
			return;
		}

		int mx = event.getX();
		int my = event.getY();

		if (selector.contains(mx, my)) {
			selectorActive = selectorActive ? false : true;
		} else if (adder != null && adder.contains(mx, my)) {
			if(circleList.size() >= colorArray.length) {
				return;
			}
			addCircle();
		} else if (remover != null && remover.contains(mx, my)) {
			if (circleList.size() >= 2) {
				if(activeColor == circleList.size() - 1) {
					activeColor -= 1;
				}
				
				circleList.removeLast();
			}
		} else {
			for (int i = 1; i < circleList.size(); i++) {
				RoundRectangle2D circle = circleList.get(i);
				if (circle.contains(mx, my)) {
					activeColor = i;
					break;
				}
			}
		}
	}

	/**
	 * Sets the color array.
	 *
	 * @param colorArray the new color array
	 */
	public void setColorArray(Color colorArray[]) {
		this.colorArray = colorArray;
	}

	/**
	 * Gets the active color.
	 *
	 * @return the active color
	 */
	public int getActiveColor() {
		return activeColor;
	}

	/**
	 * Sets the active color.
	 *
	 * @param activeColor the new active color
	 */
	public void setActiveColor(int activeColor) {
		if (activeColor > circleList.size() - 1) {
			this.activeColor = 1;
		} else if (activeColor < 1) {
			this.activeColor = circleList.size() - 1;
		} else {
			this.activeColor = activeColor;
		}
	}

	/**
	 * Removes the all objects.
	 */
	public void removeAllObjects() {
		while (!this.circleList.isEmpty()) {
			this.circleList.removeFirst();
			this.activeColor = 0;
		}
	}
	
	/**
	 * Sets the selector.
	 *
	 * @param selectorActive the new selector
	 */
	public void setSelector(boolean selectorActive) {
		this.selectorActive = selectorActive;
	}
}
