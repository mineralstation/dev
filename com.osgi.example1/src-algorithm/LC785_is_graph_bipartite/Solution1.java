package LC785_is_graph_bipartite;

import java.util.Stack;

public class Solution1 {

	public static int NO_COLOR = 0;
	public static int COLOR_1 = 1;
	public static int COLOR_2 = -1;

	/**
	 * 
	 * @param graph
	 * @return
	 */
	public static boolean isBipartite(int[][] graph) {
		int[] colors = new int[graph.length];

		Stack<Integer> queue = new Stack<Integer>();

		for (int i = 0; i < graph.length; ++i) {
			if (colors[i] != NO_COLOR) {
				continue;
			}

			colors[i] = COLOR_1;
			int otherColor = -1 * COLOR_1;
			queue.push(i);

			while (!queue.isEmpty()) {
				int currI = queue.pop();
				int[] jArray = graph[currI];

				for (int j : jArray) {
					if (colors[j] == colors[currI]) {
						return false;
					}

					if (colors[j] == 0) {
						colors[j] = otherColor;
						queue.push(j);
					}
				}
			}
		}
		return true;
	}

}
