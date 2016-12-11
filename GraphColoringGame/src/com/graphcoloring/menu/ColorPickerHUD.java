package com.graphcoloring.menu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;

import com.graphcoloring.main.Game;

public class ColorPickerHUD extends MouseAdapter {
	
	private Game game;
	
	private Ellipse2D selector;
	private Ellipse2D adder;
	
	private boolean selectorActive;
	
	private LinkedList<Integer> circleList = new LinkedList<Integer>();
	
	public ColorPickerHUD(Game game) {
		this.game = game;
		
		intialize();
	}
	
	public void intialize() {
		selector = new Ellipse2D.Double(Game.WIDTH - 75, 5, 50, 50);
	}

	public void tick() {
		if(selectorActive) {
		}
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		if(selectorActive) {
			
			for(int i = 0; i < circleList.size(); i++) {
				g2d.draw(new Ellipse2D.Double(Game.WIDTH - 75 - selector.getWidth() - i * 60 - 10, 5, 50, 50));
			}
			adder = new Ellipse2D.Double(Game.WIDTH - 75 - selector.getWidth() - circleList.size() * 60 - 10, 5, 50, 50);
			g2d.fill(adder);
		}
		
		g2d.fill(selector);
	}

	public void addCircle(int color) {
		circleList.add(color);
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
		}
	}
}
