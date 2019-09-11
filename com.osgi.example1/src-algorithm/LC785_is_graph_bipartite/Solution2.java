package LC785_is_graph_bipartite;

public class Solution2 {

	public static int NO_COLOR = 0;
	public static int COLOR_1 = 1;
	public static int COLOR_2 = -1;

	/**
	 * 
	 * @param graph
	 * @return
	 */
	public static boolean isBipartite(int[][] graph) {
		if (graph == null || graph.length <= 1) {
			return false;
		}

		int[] colors = new int[graph.length];

		for (int i = 0; i < graph.length; i++) {
			boolean isBipartite = isBipartite(colors, graph, i, COLOR_1);
			if (!isBipartite) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param colors
	 * @param graph
	 * @param i
	 * @param color
	 * @return
	 */
	protected static boolean isBipartite(int[] colors, int[][] graph, int i, int color) {
		if (colors[i] != NO_COLOR) {
			return true;
		}

		colors[i] = color;
		int otherColor = -1 * color;

		int[] jArray = graph[i];
		for (int j : jArray) {
			if (colors[j] == color) {
				return false;
			}

			boolean isBipartite = isBipartite(colors, graph, j, otherColor);
			if (!isBipartite) {
				return false;
			}
		}
		return true;
	}

}
