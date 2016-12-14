package com.graphcoloring.main;

import java.util.HashSet;
import java.util.Set;

public class GraphSolver {

	private int adjacencyMatrix[][];
	private int verticies;
	private int vertexColor[];

	private int chromaticNumber;

	public GraphSolver(int adjacencyMatrix[][], int verticies) {
		this.adjacencyMatrix = adjacencyMatrix;
		this.verticies = verticies;

		this.vertexColor = new int[verticies];

		this.chromaticNumber = verticies;
	}

	public void solveGraph(int vertex) {
		for (int color = 1; color <= getLowerBound(); color++) {
			if (canColor(vertex, color)) {
				vertexColor[vertex] = color;

				if (distinctNumberOfItems(vertexColor) > chromaticNumber) {
					continue;
				}

				if ((vertex + 1) < verticies) {
					solveGraph(vertex + 1);
				} else {
					for (int i = 0; i < vertexColor.length; i++) {
						System.out.print(vertexColor[i] + " ");
					}
					System.out.println("");
					if (chromaticNumber > distinctNumberOfItems(vertexColor)) {
						chromaticNumber = distinctNumberOfItems(vertexColor);
					}
				}
			}
		}
	}

	public boolean canColor(int vertex, int color) {
		for (int vertex_2 = 0; vertex_2 < verticies; vertex_2++) {
			if (adjacencyMatrix[vertex][vertex_2] == 1 && color == vertexColor[vertex_2]) {
				return false;
			}
		}
		return true;
	}

	public int getLowerBound() {
		return 5;
	}

	public int distinctNumberOfItems(int[] array) {
		if (array.length <= 1)
			return array.length;

		Set<Integer> set = new HashSet<Integer>();
		for (int i : array)
			if (i != 0)
				set.add(i);

		return set.size();
	}

	public int getChromaticNumber() {
		return chromaticNumber;
	}

	public void colorNodes(Handler handler) {
		for (int j = 0; j < handler.object.size(); j++) {
			GameObject tempObject = handler.object.get(j);

			if (tempObject.getId() != ID.GraphNode) {
				continue;
			}
			
			GraphNode gn = (GraphNode) tempObject;
			
			gn.setColor(vertexColor[gn.getNodeID()]);
		}
	}
}