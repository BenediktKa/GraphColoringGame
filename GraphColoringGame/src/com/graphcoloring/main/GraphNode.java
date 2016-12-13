package com.graphcoloring.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphNode extends GameObject {
	private Handler handler;

	private int nodeID;
	private int color;
	public boolean hover;
	private int adjecencyMatrix[][];
	private boolean outline = true;

	private int nodeSize;

	private Color colorArray[];

	private List<Integer> randomOrderList = new ArrayList<>();

	private Ellipse2D node;

	public GraphNode(Handler handler, int x, int y, int velX, int velY, ID id, int nodeID, int adjecencyMatrix[][], Color colorArray[], List<Integer> randomOrderList) {
		super(x, y, id);

		this.handler = handler;

		this.velX = velX;
		this.velY = velY;

		this.nodeID = nodeID;
		this.adjecencyMatrix = adjecencyMatrix;

		this.colorArray = colorArray;

		this.randomOrderList = randomOrderList;

		this.color = 0;

		if (Game.SMALLNODES) {
			nodeSize = Game.WIDTH / 25;
		} else {
			nodeSize = Game.WIDTH / 20;
		}
	}

	public GraphNode(int x, int y, int velX, int velY, ID id, Color colorArray[], boolean outline) {
		super(x, y, id);

		this.velX = velX;
		this.velY = velY;

		this.colorArray = colorArray;
		this.outline = outline;
	}

	public void tick() {
		if (node == null) {
			return;
		}

		x += velX;
		y += velY;
	}

	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(colorArray[color]);
		if (!hover) {
			node = new Ellipse2D.Double(x, y, nodeSize, nodeSize);
		}

		if (adjecencyMatrix != null) {
			generateEdges(g2d);
		}

		g2d.fill(node);
		g2d.setColor(colorArray[0]);

		if (outline) {
			g2d.setStroke(new BasicStroke(3));
			g2d.draw(node);
			g2d.setStroke(new BasicStroke(1));
		}
		if (!randomOrderList.isEmpty()) {
			if (randomOrderList.get(0) == nodeID) {
				g2d.setStroke(new BasicStroke(20));
				g2d.draw(node);
				g2d.setStroke(new BasicStroke(1));
			}
		}
	}

	public void generateEdges(Graphics2D g2d) {

		for (int i = 0; i < adjecencyMatrix[0].length; i++) {
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
		if (node == null)
			return false;

		return node.contains(x, y);
	}

	public void onHover() {
		hover = true;
	}

	public int getColor() {
		return color;
	}

	public void changeColor(int color) {
		if (!randomOrderList.isEmpty()) {
			if (randomOrderList.get(0) != nodeID) {
				return;
			}
		}

		boolean canColor = true;
		for (int i = 0; i < adjecencyMatrix.length; i++) {
			if (adjecencyMatrix[nodeID][i] != 1 && adjecencyMatrix[i][nodeID] != 1) {
				continue;
			}

			for (int j = 0; j < handler.object.size(); j++) {
				GameObject tempObject = handler.object.get(i);

				if (tempObject.getId() != ID.GraphNode) {
					continue;
				}
				GraphNode gn = (GraphNode) tempObject;

				if (gn.getNodeID() == i && gn.getNodeID() != nodeID) {
					if (gn.getColor() == color) {
						canColor = false;
						return;
					}
				}
			}
		}

		if (canColor) {
			if (!randomOrderList.isEmpty()) {
				randomOrderList.remove(0);
			}
			this.color = color;
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
