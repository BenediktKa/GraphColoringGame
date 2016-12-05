package com.graphcoloring.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.Random;

public class GraphNode extends GameObject {
	private Handler handler;
	
	private int nodeID;
	private int color;
	private int adjecencyMatrix[][];
	
	private Color colorArray[] = { Color.black, Color.blue, Color.cyan, Color.gray, Color.green };

	private Ellipse2D node;

	public GraphNode(Handler handler, int x, int y, ID id, int nodeID, int adjecencyMatrix[][]) {
		super(x, y, id);

		this.handler = handler;
		
		this.nodeID = nodeID;
		this.adjecencyMatrix = adjecencyMatrix;
		
		this.color = 0;

		/*
		 * Random r = new Random(); velX = r.nextInt(10); velY = r.nextInt(10);
		 */

	}

	public void tick() {
		/*
		 * Random r = new Random();
		 * 
		 * if (node.getCenterX() > Game.WIDTH) { velX = -r.nextInt(10); } else
		 * if(node.getCenterX() < 0) { velX = r.nextInt(10); }
		 * 
		 * if (node.getCenterY() > Game.HEIGHT) { velY = -r.nextInt(10); } else
		 * if(node.getCenterY() < 0) { velY = r.nextInt(10); }
		 * 
		 * x += velX; y += velY;
		 */
		/*
		Random r = new Random();
		int centerX = Game.WIDTH / 2 - Game.WIDTH / 50;
		int centerY = Game.HEIGHT / 2 - Game.WIDTH / 50;
		int radiusCircle = (Game.HEIGHT - 100) / 2;
		x = (int)(centerX + radiusCircle * Math.cos(Math.PI * i * 0.1));
		y = (int)(centerY + radiusCircle * Math.sin(Math.PI * i * 0.1));
		*/
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(colorArray[color]);
		node = new Ellipse2D.Double(x, y, Game.WIDTH / 25, Game.WIDTH / 25);

		
		generateEdges(g);
		g2d.fill(node);
	}
	
	public void generateEdges(Graphics g) {
		
		for(int i = 0; i < adjecencyMatrix.length; i++) {
			if(adjecencyMatrix[nodeID][i] != 1) {
				continue;
			}
			
			for(int j = 0; j < handler.object.size(); j++) {
				GameObject tempObject = handler.object.get(i);

				if (tempObject.getId() != ID.GraphNode) {
					continue;
				}
				GraphNode gn = (GraphNode) tempObject;
				
				if(gn.getNodeID() == i) {
					g.drawLine(this.getCenterX(), this.getCenterY(), gn.getCenterX(), gn.getCenterY());
				}
			}
		}
	}

	public boolean clicked(int x, int y) {
		if (node.contains(x, y)) {
			return true;
		} else {
			return false;
		}
	}

	public void changeColor() {
		if (color == colorArray.length - 1) {
			color = 0;
		} else {
			color++;
		}
	}
	
	public int getNodeID() {
		return nodeID;
	}
	
	public int getCenterX() {
		if(node == null)
			return 0;
		
		return (int)node.getCenterX();
	}
	
	public int getCenterY() {
		if(node == null)
			return 0;
		
		return (int)node.getCenterY();
	}
}
