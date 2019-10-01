package common;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {

	public String tag;
	public int val;
	public List<GraphNode> neighbors = new ArrayList<GraphNode>();
	public List<GraphNode> incomings = new ArrayList<GraphNode>();
	public List<GraphNode> outgoings = new ArrayList<GraphNode>();

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

		str += ", incomings = {";
		for (GraphNode incoming : this.incomings) {
			str += incoming.val;
			if (incoming != this.incomings.get(this.incomings.size() - 1)) {
				str += ", ";
			}
		}
		str += "}";

		str += ", outgoings = {";
		for (GraphNode outgoing : this.outgoings) {
			str += outgoing.val;
			if (outgoing != this.outgoings.get(this.outgoings.size() - 1)) {
				str += ", ";
			}
		}
		str += "}";

		str += "]";
		return str;
	}

}
