package LC200_number_of_islands;

public class Solution1 {

	public static int numIslands(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
			return 0;
		}

		int number = 0;

		int m = grid.length; // row
		int n = grid[0].length; // col

		boolean[][] visited = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j]) {
					// location belongs to already visited other islands
					continue;
				}

				boolean isIsland = (grid[i][j] == 1) ? true : false;
				if (isIsland) {
					walkTheWholeIsland(grid, m, n, i, j, visited);
					number++;
				}
			}
		}

		return number;
	}

	/**
	 * 
	 * @param grid
	 * @param m
	 * @param n
	 * @param i
	 * @param j
	 * @param visited
	 */
	protected static void walkTheWholeIsland(int[][] grid, int m, int n, int i, int j, boolean[][] visited) {
		if (i < 0 || i >= m) {
			// out of map is water
			// - stop walking
			return;
		}
		if (j < 0 || j >= n) {
			// out of map is water
			// - stop walking
			return;
		}
		if (grid[i][j] == 0) {
			// is water
			// - stop walking
			return;
		}
		if (visited[i][j]) {
			// location is on the same island and is already visited.
			// - stop walking
			return;
		}
		visited[i][j] = true;

		walkTheWholeIsland(grid, m, n, i - 1, j, visited); // go up
		walkTheWholeIsland(grid, m, n, i, j + 1, visited); // go right
		walkTheWholeIsland(grid, m, n, i + 1, j, visited); // go down
		walkTheWholeIsland(grid, m, n, i, j - 1, visited); // go left
	}

}
