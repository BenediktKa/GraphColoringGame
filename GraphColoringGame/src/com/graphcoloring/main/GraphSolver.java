package com.graphcoloring.main;

import java.util.HashSet;
import java.util.Set;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;

public class GraphSolver {

	private int adjacencyMatrix[][];
	private int verticies;
	private int vertexColor[];
	private int chromaticArray[];

	private int chromaticNumber;

	public GraphSolver(int adjacencyMatrix[][], int verticies) {
		this.adjacencyMatrix = adjacencyMatrix;
		this.verticies = verticies;

		this.vertexColor = new int[verticies];

		this.chromaticNumber = verticies;
	}

	public void solveGraph(int vertex) {
		for (int color = 1; color <= getUpperBound(); color++) {
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
						chromaticArray = vertexColor;
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

	public int getUpperBound() {
		Matrix A = new Matrix(verticies, verticies);
		
		for(int i = 0; i < adjacencyMatrix.length; i++) {
			for(int j = 0; j < adjacencyMatrix[0].length; j++) {
				A.set(i, j, adjacencyMatrix[i][j]);
			}
		}
		
		
		
		EigenvalueDecomposition eigen = A.eig();
		
		Matrix B = (Matrix) eigen.getD();
		
		int min = 0;
		int max = 0;
		
		for(int i = 0; i < B.getColumnDimension(); i++) {
			for(int j = 0; j < B.getRowDimension(); j++) {				
				if(min == 0) {
					min = (int)B.get(i, j);
				} else {
					if(A.get(i, j) < min) {
						min = (int)B.get(i, j);
					}
				}
				
				if(max == 0) {
					max = (int)B.get(i, j);
				} else {
					if(B.get(i, j) > max) {
						max = (int)B.get(i, j);
					}
				}
			}
		}
		
		return max + 1;
		
		//Lower Bound 1 - max/min
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
			
			gn.setColor(chromaticArray[gn.getNodeID()]);
		}
	}
}