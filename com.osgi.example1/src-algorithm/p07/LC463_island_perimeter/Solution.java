package p07.LC463_island_perimeter;

/*
You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. 
Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, 
and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water 
inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid 
is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

Example:
[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Answer: 16
Explanation: The perimeter is the 16 yellow stripes in the image below:

 */
public class Solution {

	public static int getIslandPerimeter(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int result = 0;

		int m = grid.length;
		int n = grid[0].length;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					result += 4;

					if (j - 1 >= 0 && grid[i][j - 1] == 1) { // left grid is island
						result -= 2;
					}
					if (i - 1 >= 0 && grid[i - 1][j] == 1) { // top grid is island
						result -= 2;
					}
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {
		int[][] grid = new int[][] { //
				{ 0, 1, 0, 0 }, //
				{ 1, 1, 1, 0 }, //
				{ 0, 1, 0, 0 }, //
				{ 1, 1, 0, 0 } //
		};

		int perimeter = getIslandPerimeter(grid);
		System.out.println("Output: perimeter = " + perimeter);
	}

}
