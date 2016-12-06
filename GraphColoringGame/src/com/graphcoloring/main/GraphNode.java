package com.graphcoloring.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Random;

public class GraphNode extends GameObject {
	private Handler handler;

	private int nodeID;
	private int color;
	public boolean hover;
	private int adjecencyMatrix[][];
	private boolean outline = true;

	private Color colorArray[];

	private Ellipse2D node;

	public GraphNode(Handler handler, int x, int y, int velX, int velY, ID id, int nodeID, int adjecencyMatrix[][],
			Color colorArray[]) {
		super(x, y, id);

		this.handler = handler;

		this.velX = velX;
		this.velY = velY;

		this.nodeID = nodeID;
		this.adjecencyMatrix = adjecencyMatrix;

		this.colorArray = colorArray;

		this.color = 0;
	}

	public GraphNode(int x, int y, int velX, int velY, ID id, Color colorArray[], boolean outline) {
		super(x, y, id);

		this.velX = velX;
		this.velY = velY;

		this.colorArray = colorArray;
		this.outline = outline;

		randomColor();

	}

	public void tick() {
		if (node == null) {
			return;
		}

		if (node.getCenterX() > Game.WIDTH) {
			velX = -velX;
			randomColor();
		} else if (node.getCenterX() < 0) {
			velX = -velX;
			randomColor();
		}

		if (node.getCenterY() > Game.HEIGHT) {
			velY = -velY;
			randomColor();
		} else if (node.getCenterY() < 0) {
			velY = -velY;
			randomColor();
		}

		x += velX;
		y += velY;
	}

	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		if (Game.ANTIALIASING) {
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}

		g2d.setColor(colorArray[color]);
		if (!hover) {
			node = new Ellipse2D.Double(x, y, Game.WIDTH / 20, Game.WIDTH / 20);
		}

		if (adjecencyMatrix != null) {
			generateEdges(g2d);
		}
		
		g2d.fill(node);
		g2d.setColor(colorArray[0]);
		
		if(outline) {
			g2d.setStroke(new BasicStroke(3));
			g2d.draw(node);
		}
	}

	public void generateEdges(Graphics2D g2d) {

		for (int i = 0; i < adjecencyMatrix.length; i++) {
			if (adjecencyMatrix[nodeID][i] != 1) {
				continue;
			}

			for (int j = 0; j < handler.object.size(); j++) {
				GameObject tempObject = handler.object.get(i);

				if (tempObject.getId() != ID.GraphNode) {
					continue;
				}
				GraphNode gn = (GraphNode) tempObject;

				if (gn.getNodeID() == i) {
					g2d.setStroke(new BasicStroke(2));
					g2d.draw(new Line2D.Double(this.getCenterX(), this.getCenterY(), gn.getCenterX(), gn.getCenterY()));
				}
			}
		}
	}

	public boolean clicked(int x, int y) {
		return node.contains(x, y);
	}

	public void onHover() {
		hover = true;
	}

	public int getColor() {
		return color;
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
		if (node == null)
			return 0;

		return (int) node.getCenterX();
	}

	public int getCenterY() {
		if (node == null)
			return 0;

		return (int) node.getCenterY();
	}

	public int getRadius() {
		return (int) node.getWidth();
	}

	public Ellipse2D getNode() {
		return node;
	}

	public void randomColor() {
		this.color = new Random().nextInt(colorArray.length);
	}
}
