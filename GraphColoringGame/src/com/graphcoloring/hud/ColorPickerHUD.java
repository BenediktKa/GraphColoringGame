package com.graphcoloring.hud;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedList;

import com.graphcoloring.main.Game;

public class ColorPickerHUD extends MouseAdapter {

	private Game game;
	private Notification notification;

	private int borderRadius = 25;

	private RoundRectangle2D selector;
	private RoundRectangle2D adder;
	private RoundRectangle2D remover;

	private Color colorArray[];
	private int activeColor;

	private boolean selectorActive;

	private LinkedList<RoundRectangle2D> circleList = new LinkedList<RoundRectangle2D>();

	public ColorPickerHUD(Game game, Notification notification) {
		this.game = game;
		this.notification = notification;

		intialize();
	}

	public void intialize() {
		selector = new RoundRectangle2D.Double(Game.WIDTH - 75, 5, 50, 50, borderRadius, borderRadius);
	}

	public void tick() {
	}

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

			g2d.setColor(Color.BLACK);
			remover = new RoundRectangle2D.Double(Game.WIDTH - 75 - selector.getWidth() - (circleList.size() - 1) * 60 - 10, 5, 50, 50, borderRadius, borderRadius);
			adder = new RoundRectangle2D.Double(Game.WIDTH - 75 - selector.getWidth() - (circleList.size() - 1) * 60 - 20 - remover.getWidth(), 5, 50, 50, borderRadius, borderRadius);
			g2d.fill(adder);
			g2d.fill(remover);
		}

		g2d.fill(selector);
	}

	public void addCircle() {
		if(circleList.isEmpty()) {
			circleList.add(new RoundRectangle2D.Double());
			circleList.add(new RoundRectangle2D.Double());
		} else if (circleList.size() < colorArray.length) {
			circleList.add(new RoundRectangle2D.Double());
		}
	}

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

	public void setColorArray(Color colorArray[]) {
		this.colorArray = colorArray;
	}

	public int getActiveColor() {
		return activeColor;
	}

	public void setActiveColor(int activeColor) {
		if (activeColor > circleList.size() - 1) {
			this.activeColor = 1;
		} else if (activeColor < 1) {
			this.activeColor = circleList.size() - 1;
		} else {
			this.activeColor = activeColor;
		}
	}

	public void removeAllObjects() {
		while (!this.circleList.isEmpty()) {
			this.circleList.removeFirst();
			this.activeColor = 0;
		}
	}
	
	public void setSelector(boolean selectorActive) {
		this.selectorActive = selectorActive;
	}
}
