package LC200_number_of_islands;

import java.util.Stack;

import common.Point;

public class Solution2 {

	public static int numIslands(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
			return 0;
		}

		int number = 0;

		int m = grid.length; // row
		int n = grid[0].length; // col

		boolean[][] isVisited = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (isVisited[i][j]) {
					// location belongs to already visited other islands
					continue;
				}

				boolean isIsland = (grid[i][j] == 1) ? true : false;
				if (isIsland) {
					// walk the whole island
					Stack<Point> queue = new Stack<Point>();

					queue.push(new Point(i, j));
					while (!queue.isEmpty()) {
						Point location = queue.pop();
						int x = location.x;
						int y = location.y;

						if (x < 0 || x >= m) {
							// out of map
							continue;
						}
						if (y < 0 || y >= n) {
							// out of map
							continue;
						}
						if (grid[x][y] == 0) {
							// is water
							continue;
						}
						if (isVisited[x][y]) {
							// same island, already visited
							continue;
						}
						isVisited[x][y] = true;

						queue.push(new Point(x - 1, y)); // up
						queue.push(new Point(x, y + 1)); // right
						queue.push(new Point(x + 1, y)); // down
						queue.push(new Point(x, y - 1)); // left
					}

					number++;
				}
			}
		}

		return number;
	}

}
