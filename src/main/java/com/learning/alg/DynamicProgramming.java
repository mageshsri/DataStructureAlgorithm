package com.learning.alg;

public class DynamicProgramming {

	public int numberOfPaths(int row, int column) {
		int[][] grid = new int[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (i == 0 || j == 0)
					grid[i][j] = 1;
				else {
					grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
				}
			}
		}
		return grid[row - 1][column - 1];
	}

	public int minCostPath(int[][] grid) {
		int row = grid.length;
		int column = grid[0].length;
		int[][] minCostGrid = new int[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (i == 0 && j == 0)
					minCostGrid[i][j] = grid[i][j];
				else if (i == 0)
					minCostGrid[i][j] = minCostGrid[i][j - 1] + grid[i][j];
				else if (j == 0)
					minCostGrid[i][j] = minCostGrid[i - 1][j] + grid[i][j];
				else
					minCostGrid[i][j] = Math.min(minCostGrid[i][j - 1], minCostGrid[i - 1][j]) + grid[i][j];
			}
		}
		return minCostGrid[row - 1][column - 1];
	}

	public static void main(String[] args) {
		DynamicProgramming dynamicProgramming= new DynamicProgramming();
		System.out.println(" number of paths ::"+dynamicProgramming.numberOfPaths(3, 3));
		System.out.println(" Min Cost Path====== "+dynamicProgramming.minCostPath(new int[][] {{1,2,3},{1,4,2},{5,3,2}}));

	}

}
