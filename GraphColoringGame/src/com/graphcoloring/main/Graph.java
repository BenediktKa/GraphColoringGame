package com.graphcoloring.main;

import java.util.LinkedList;

import com.graphcoloring.hud.Notification;
import com.graphcoloring.hud.Notification.TYPE;

public class Graph {

	private Handler handler;
	private Notification notification;
	private int vertices;
	private int edges;
	private int adjacencyMatrix[][];
	private int adjacencySimple[][];

	LinkedList<GraphNode> nodeList = new LinkedList<GraphNode>();

	public Graph(Handler handler, Notification notification, int verticies, int edges) {
		this.handler = handler;
		this.notification = notification;
		this.vertices = verticies;
		this.edges = edges;

		// Temporary
		generateRandomGraph();
	}

	public void generateRandomGraph() {

		// the edges we still need to create after creating 1 edge for each
		// vertices
		int edgeToGenerate = edges - vertices;
		// create 1 edges for each vertices
		adjacencyMatrix = new int[vertices][vertices];
		for (int i = 0; i < vertices; i++) {
			int random = (int) (Math.random() * vertices);
			if (random != i && (adjacencyMatrix[i][random] == 0)) {
				adjacencyMatrix[i][random] = 1;
				adjacencyMatrix[random][i] = 1;
			} else {
				edgeToGenerate++;
			}
		}
		while (edgeToGenerate > 0) {
			int random1 = (int) (Math.random() * vertices);
			int random2 = (int) (Math.random() * vertices);
			if ((adjacencyMatrix[random1][random2] == 0) && random1 != random2) {
				adjacencyMatrix[random1][random2] = 1;
				adjacencyMatrix[random2][random1] = 1;
				edgeToGenerate--;
			}
		}
		
		adjSimple();
		positionNodes();
	}

	public void adjSimple() {
		adjacencySimple = new int[vertices][vertices];
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				adjacencySimple[i][j] = adjacencyMatrix[i][j];
			}
		}
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				if (i > j) {
					adjacencySimple[i][j] = 0;
				}
			}
		}
	}

	public void positionNodes() {

		int centerX = Game.WIDTH / 2 - Game.WIDTH / 50;
		int centerY = Game.HEIGHT / 2 - Game.WIDTH / 50;
		int radiusCircle = (Game.HEIGHT - 100) / 2;

		double divisions = 2.0 / vertices;

		for (int i = 0; i < vertices; i++) {
			GraphNode node = new GraphNode(handler, (int) (centerX + radiusCircle * Math.cos(Math.PI * i * divisions)),
					(int) (centerY + radiusCircle * Math.sin(Math.PI * i * divisions)), 0, 0, ID.GraphNode, i,
					adjacencySimple);
			handler.addObject(node);
			addObject(node);
		}
	}

	public void addObject(GraphNode object) {
		this.nodeList.add(object);
	}

	public void removeObject(GraphNode object) {
		this.nodeList.remove(object);
	}
}
