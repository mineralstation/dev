package LC133_clone_graph;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {

	public String tag;
	public int val;
	public List<GraphNode> neighbors = new ArrayList<GraphNode>();

	public GraphNode(int val) {
		this.val = val;
	}

	public GraphNode(int val, String tag) {
		this.val = val;
		this.tag = tag;
	}

	public GraphNode(int val, List<GraphNode> list) {
		this.val = val;
		this.neighbors = list;
		if (this.neighbors == null) {
			this.neighbors = new ArrayList<GraphNode>();
		}
	}

	@Override
	public String toString() {
		String str = "Node [";

		str += "tag = " + tag;

		str += ", val = " + val;

		str += ", neighbor = {";
		for (GraphNode neighbor : this.neighbors) {
			str += neighbor.val;
			if (neighbor != this.neighbors.get(this.neighbors.size() - 1)) {
				str += ", ";
			}
		}
		str += "}";

		str += "]";
		return str;
	}

}
