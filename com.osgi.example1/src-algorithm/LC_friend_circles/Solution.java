package LC_friend_circles;

public class Solution {

	public static int findCircleNum(int[][] M) {
		if (M == null || M.length == 0 || M[0] == null || M[0].length == 0) {
			return 0;
		}
		int result = 0;

		int m = M.length;
		int n = M[0].length;

		boolean[][] visited = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j]) {
					continue;
				}
				
				
			}
		}

		return result;
	}

}
