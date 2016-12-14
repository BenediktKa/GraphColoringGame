package com.graphcoloring.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class GraphEdge extends GameObject {
	
	private Line2D line;
	private GraphNode node1, node2;

	public GraphEdge(int x, int y, GraphNode node1, GraphNode node2, ID id) {
		super(x, y, id);
		
		this.node1 = node1; 
		this.node2 = node2;
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		if(node1.getColor() != 0) {
			g2d.setColor(node2.getColorObject());
		} else if (node2.getColor() != 0) {
			g2d.setColor(node2.getColorObject());
		} else {
			g2d.setColor(Game.silverColor);
		}
		
		line = new Line2D.Double(node1.getX() + node1.getRadius() / 2, node1.getY()  + node1.getRadius() / 2, node2.getX()  + node1.getRadius() / 2, node2.getY()  + node1.getRadius() / 2);
		
		g2d.setStroke(Game.stroke3);
		g2d.draw(line);
		g2d.setStroke(Game.stroke1);
	}
}
