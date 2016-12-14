package com.graphcoloring.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.graphcoloring.hud.ColorPickerHUD;
import com.graphcoloring.hud.Notification;

public class Graph {

	private Game game;
	private Handler handler;
	private Notification notification;
	private ColorPickerHUD coloPickerHUD;
	private int vertices;
	private int edges;
	private boolean randomOrder;
	private int adjacencyMatrix[][];
	private int adjacencySimple[][];
	private Color colorArray[];
	
	private GraphSolver graphSolver;

	private List<Integer> randomOrderList = new ArrayList<>();

	LinkedList<GraphNode> nodeList = new LinkedList<GraphNode>();

	public Graph(Game game, Handler handler, Notification notification, ColorPickerHUD colorPickerHUD, int verticies, int edges, boolean randomOrder) {
		this.game = game;
		this.handler = handler;
		this.notification = notification;
		this.coloPickerHUD = colorPickerHUD;
		this.vertices = verticies;
		this.edges = edges;
		this.randomOrder = randomOrder;
		this.colorArray = new RandomColors(20, 0.05f).getPalette();
		colorPickerHUD.setColorArray(colorArray);

		// Temporary
		generateRandomGraph();
		
		graphSolver = new GraphSolver(adjacencyMatrix, verticies);
		graphSolver.solveGraph(0);
		if(Game.SOLVEGRAPH) {
			graphSolver.colorNodes(handler);
		}
		game.gameState = Game.STATE.Game;
		
		Game.chromaticNumber = graphSolver.getChromaticNumber();
		System.out.println(Game.chromaticNumber);
	}

	public void generateRandomGraph() {

		// the edges we still need to create after creating 1 edge for each
		// vertices
		int edgeToGenerate = edges - vertices;
		// create 1 edges for each vertices
		adjacencyMatrix = new int[vertices][vertices];
		int e = 0;
		while (e < vertices) {
			int random = (int) (Math.random() * vertices);
			if (random != e && (adjacencyMatrix[e][random] == 0) && (adjacencyMatrix[random][e] == 0)) {
				adjacencyMatrix[e][random] = 1;
				adjacencyMatrix[random][e] = 1;
				e++;
			}
		}
		while (edgeToGenerate > 0) {
			int random1 = (int) (Math.random() * vertices);
			int random2 = (int) (Math.random() * vertices);
			if ((adjacencyMatrix[random1][random2] == 0) && (adjacencyMatrix[random2][random1] != random2)) {
				adjacencyMatrix[random1][random2] = 1;
				adjacencyMatrix[random2][random1] = 1;
				edgeToGenerate--;
			}
		}

		adjSimple();
		positionNodes();

		if (randomOrder) {
			generateRandomOrder();
		}
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
					adjacencySimple, colorArray, randomOrderList);
			handler.addObject(node);
			addObject(node);
		}
		
		
		for (int i = 0; i < adjacencySimple.length; i++) {
			for(int j = 0; j < adjacencySimple[0].length; j++) {
				if(adjacencySimple[i][j] == 1) {
					
					GraphNode node1 = null, node2 = null;
					
					for(int k = 0; k < handler.object.size(); k++) {
						GameObject tempObject = handler.object.get(k);
						
						if (tempObject.getId() != ID.GraphNode) {
							continue;
						}
						GraphNode tempNode = (GraphNode) tempObject;
						if(tempNode.getNodeID() == i) {
							node1 = tempNode;
						} else if(tempNode.getNodeID() == j) {
							node2 = tempNode;
						}
					}
					
					if(node1 != null && node2 != null) {
						handler.addObjectFirst(new GraphEdge(0, 0, node1, node2, ID.GraphEdge));
					}
				}
			}
		}
	}

	public void generateRandomOrder() {
		for (int i = 0; i < vertices; i++) {
			randomOrderList.add(i);
		}
		Collections.shuffle(randomOrderList);
	}

	public List<Integer> getRandomOrder() {
		return randomOrderList;
	}

	public void addObject(GraphNode object) {
		this.nodeList.add(object);
	}

	public void removeObject(GraphNode object) {
		this.nodeList.remove(object);
	}
}
