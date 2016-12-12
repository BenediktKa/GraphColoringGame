package com.graphcoloring.menu;

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
	
	private int borderRadius = 25;
	
	private RoundRectangle2D selector;
	private RoundRectangle2D adder;
	
	private Color colorArray[];
	private int activeColor;
	
	private boolean selectorActive;
	
	private LinkedList<RoundRectangle2D> circleList = new LinkedList<RoundRectangle2D>();
	
	public ColorPickerHUD(Game game) {
		this.game = game;
		
		intialize();
	}
	
	public void intialize() {
		selector = new RoundRectangle2D.Double(Game.WIDTH - 75, 5, 50, 50, borderRadius, borderRadius);
	}

	public void tick() {
		if(selectorActive) {
		}
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		if(selectorActive) {
			
			for(int i = 0; i < circleList.size(); i++) {
				RoundRectangle2D circle = circleList.get(i);
				circleList.remove(i);
				circle = new RoundRectangle2D.Double(Game.WIDTH - 75 - selector.getWidth() - i * 60 - 10, 5, 50, 50, borderRadius, borderRadius);
				circleList.add(i, circle);
				
				g2d.setColor(colorArray[i]);
				g2d.fill(circle);
				
				if(i == activeColor) {
					g2d.setColor(Color.BLACK);
					g2d.setStroke(new BasicStroke(3));
					g2d.draw(circle);
					g2d.setStroke(new BasicStroke(1));
				}
			}
			
			g2d.setColor(Color.BLACK);
			adder = new RoundRectangle2D.Double(Game.WIDTH - 75 - selector.getWidth() - circleList.size() * 60 - 10, 5, 50, 50, borderRadius, borderRadius);
			g2d.fill(adder);
		}
		
		g2d.fill(selector);
	}

	public void addCircle(int color) {
		circleList.add(new RoundRectangle2D.Double());
	}
	
	public void mouseClicked(MouseEvent event) {
		if(game.gameState != Game.STATE.Game) {
			return;
		}
		
		int mx = event.getX();
		int my = event.getY();
		
		if(selector.contains(mx, my)) {
			selectorActive = selectorActive ? false : true;
		} else if(adder != null && adder.contains(mx, my)) {
			addCircle(1);
		} else {
			for(int i = 0; i < circleList.size(); i++) {
				RoundRectangle2D circle = circleList.get(i);
				if(circle.contains(mx, my)) {
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
		if(activeColor > circleList.size()) {
			this.activeColor = 0;
		} else {
			this.activeColor = activeColor;
		}
	}
}
