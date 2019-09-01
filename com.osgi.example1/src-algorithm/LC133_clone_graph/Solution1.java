package LC133_clone_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Solution1 {

	public static GraphNode clone(GraphNode node) {
		Map<GraphNode, GraphNode> originalToCloneMap = new HashMap<GraphNode, GraphNode>();
		return clone(node, originalToCloneMap);
	}

	public static GraphNode clone(GraphNode node, Map<GraphNode, GraphNode> originalToCloneMap) {
		if (node == null) {
			return node;
		}
		GraphNode clone = originalToCloneMap.get(node);
		if (clone != null) {
			return clone;
		}

		clone = new GraphNode(node.val);
		clone.tag = "clone";
		originalToCloneMap.put(node, clone);

		for (GraphNode neighbor : node.neighbors) {
			GraphNode neighborClone = clone(neighbor, originalToCloneMap);
			clone.neighbors.add(neighborClone);
		}
		return clone;
	}

	public static void main(String[] args) {
		GraphNode node1 = new GraphNode(1, "source");
		GraphNode node2 = new GraphNode(2, "source");
		GraphNode node3 = new GraphNode(3, "source");
		GraphNode node4 = new GraphNode(4, "source");

		node1.neighbors.add(node2);
		node1.neighbors.add(node4);

		node2.neighbors.add(node1);
		node2.neighbors.add(node3);

		node3.neighbors.add(node2);
		node3.neighbors.add(node4);

		node4.neighbors.add(node1);
		node4.neighbors.add(node3);

		System.out.println("Input:");
		print(node1);
		System.out.println();

		GraphNode node1Clone = clone(node1);

		System.out.println("Output:");
		print(node1Clone);
		System.out.println();
	}

	protected static void print(GraphNode node) {
		Stack<GraphNode> queue = new Stack<GraphNode>();
		queue.push(node);

		List<GraphNode> nodes = new ArrayList<GraphNode>();

		while (!queue.isEmpty()) {
			GraphNode currNode = queue.pop();
			if (nodes.contains(currNode)) {
				continue;
			}
			nodes.add(currNode);

			for (GraphNode neighbor : currNode.neighbors) {
				queue.push(neighbor);
			}
		}

		for (GraphNode currNode : nodes) {
			System.out.println(currNode);
		}
	}

}
