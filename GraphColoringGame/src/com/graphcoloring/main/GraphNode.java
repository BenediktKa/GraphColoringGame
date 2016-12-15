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

// TODO: Auto-generated Javadoc
/**
 * The Class GraphNode.
 */
public class GraphNode extends GameObject {
	
	/** The handler. */
	private Handler handler;

	/** The node ID. */
	private int nodeID;
	
	/** The color. */
	private int color;
	
	/** The hover. */
	public boolean hover;
	
	/** The adjecency matrix. */
	private int adjecencyMatrix[][];
	
	/** The outline. */
	private boolean outline = true;

	/** The node size. */
	private int nodeSize;

	/** The color array. */
	private Color colorArray[];

	/** The random order list. */
	private List<Integer> randomOrderList = new ArrayList<>();

	/** The node. */
	private Ellipse2D node;

	/**
	 * Instantiates a new graph node.
	 *
	 * @param handler the handler
	 * @param x the x
	 * @param y the y
	 * @param velX the vel X
	 * @param velY the vel Y
	 * @param id the id
	 * @param nodeID the node ID
	 * @param adjecencyMatrix the adjecency matrix
	 * @param colorArray the color array
	 * @param randomOrderList the random order list
	 */
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

	/**
	 * Instantiates a new graph node.
	 *
	 * @param x the x
	 * @param y the y
	 * @param velX the vel X
	 * @param velY the vel Y
	 * @param id the id
	 * @param colorArray the color array
	 * @param outline the outline
	 */
	public GraphNode(int x, int y, int velX, int velY, ID id, Color colorArray[], boolean outline) {
		super(x, y, id);

		this.velX = velX;
		this.velY = velY;

		this.colorArray = colorArray;
		this.outline = outline;
	}

	/* (non-Javadoc)
	 * @see com.graphcoloring.main.GameObject#tick()
	 */
	public void tick() {
		if (node == null) {
			return;
		}

		x += velX;
		y += velY;
	}

	/* (non-Javadoc)
	 * @see com.graphcoloring.main.GameObject#render(java.awt.Graphics)
	 */
	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(colorArray[color]);
		
		node = new Ellipse2D.Double(x, y, nodeSize, nodeSize);
		
		g2d.fill(node);
		g2d.setColor(colorArray[0]);

		if (outline) {
			g2d.setStroke(Game.stroke3);
			g2d.draw(node);
			g2d.setStroke(Game.stroke1);
		}
		if (!randomOrderList.isEmpty()) {
			if (randomOrderList.get(0) == nodeID) {
				g2d.setStroke(Game.stroke20);
				g2d.draw(node);
				g2d.setStroke(Game.stroke1);
			}
		}
	}

	/**
	 * Clicked.
	 *
	 * @param x the x
	 * @param y the y
	 * @return true, if successful
	 */
	public boolean clicked(int x, int y) {
		if (node == null)
			return false;

		return node.contains(x, y);
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public int getColor() {
		return color;
	}
	
	/**
	 * Sets the color.
	 *
	 * @param color the new color
	 */
	public void setColor(int color) {
		this.color = color;
	}
	
	/**
	 * Gets the color object.
	 *
	 * @return the color object
	 */
	public Color getColorObject() {
		return colorArray[color];
	}

	/**
	 * Change color.
	 *
	 * @param color the color
	 */
	public void changeColor(int color) {
		if (!randomOrderList.isEmpty()) {
			if (randomOrderList.get(0) != nodeID) {
				return;
			}
		}

		boolean canColor = true;
		for (int i = 0; i < adjecencyMatrix[0].length; i++) {
			if (adjecencyMatrix[nodeID][i] != 1 && adjecencyMatrix[i][nodeID] != 1) {
				continue;
			}

			for (int j = 0; j < handler.object.size(); j++) {
				GameObject tempObject = handler.object.get(j);

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

	/**
	 * Gets the node ID.
	 *
	 * @return the node ID
	 */
	public int getNodeID() {
		return nodeID;
	}

	/**
	 * Gets the center X.
	 *
	 * @return the center X
	 */
	public int getCenterX() {
		if (node == null)
			return 0;

		return (int) node.getCenterX();
	}

	/**
	 * Gets the center Y.
	 *
	 * @return the center Y
	 */
	public int getCenterY() {
		if (node == null)
			return 0;

		return (int) node.getCenterY();
	}

	/**
	 * Gets the radius.
	 *
	 * @return the radius
	 */
	public int getRadius() {
		return nodeSize;
	}

	/**
	 * Gets the node.
	 *
	 * @return the node
	 */
	public Ellipse2D getNode() {
		return node;
	}

	/**
	 * Random color.
	 */
	public void randomColor() {
		this.color = new Random().nextInt(colorArray.length);
	}
}
