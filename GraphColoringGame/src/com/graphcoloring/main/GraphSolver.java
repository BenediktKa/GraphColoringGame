package com.graphcoloring.main;

import java.util.HashSet;
import java.util.Set;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;

// TODO: Auto-generated Javadoc
/**
 * The Class GraphSolver.
 */
public class GraphSolver {

	/** The adjacency matrix. */
	private int adjacencyMatrix[][];
	
	/** The verticies. */
	private int verticies;
	
	/** The vertex color. */
	private int vertexColor[];
	
	/** The chromatic array. */
	private int chromaticArray[];

	/** The chromatic number. */
	private int chromaticNumber;

	/**
	 * Instantiates a new graph solver.
	 *
	 * @param adjacencyMatrix the adjacency matrix
	 * @param verticies the verticies
	 */
	public GraphSolver(int adjacencyMatrix[][], int verticies) {
		this.adjacencyMatrix = adjacencyMatrix;
		this.verticies = verticies;

		this.vertexColor = new int[verticies];
		this.chromaticArray = new int[verticies];

		this.chromaticNumber = verticies;
	}

	/**
	 * Solve graph.
	 *
	 * @param vertex the vertex
	 */
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
					if (chromaticNumber > distinctNumberOfItems(vertexColor)) {
						System.arraycopy(vertexColor, 0, chromaticArray, 0, vertexColor.length);
						chromaticNumber = distinctNumberOfItems(vertexColor);
					}
				}
			}
		}
	}

	/**
	 * Can color.
	 *
	 * @param vertex the vertex
	 * @param color the color
	 * @return true, if successful
	 */
	public boolean canColor(int vertex, int color) {
		for (int vertex_2 = 0; vertex_2 < verticies; vertex_2++) {
			if (adjacencyMatrix[vertex][vertex_2] == 1 && color == vertexColor[vertex_2]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gets the upper bound.
	 *
	 * @return the upper bound
	 */
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

	/**
	 * Distinct number of items.
	 *
	 * @param array the array
	 * @return the int
	 */
	public int distinctNumberOfItems(int[] array) {
		if (array.length <= 1)
			return array.length;

		Set<Integer> set = new HashSet<Integer>();
		for (int i : array)
			if (i != 0)
				set.add(i);

		return set.size();
	}

	/**
	 * Gets the chromatic number.
	 *
	 * @return the chromatic number
	 */
	public int getChromaticNumber() {
		return chromaticNumber;
	}

	/**
	 * Color nodes.
	 *
	 * @param handler the handler
	 */
	public void colorNodes(Handler handler) {
		
		System.out.println("Number of items " + distinctNumberOfItems(chromaticArray));
		
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