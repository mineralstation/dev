package LC297_serialize_and_deserialize_binary_tree;

import java.util.Stack;

import LC173_binary_search_tree_iterator.TreeNode;

public class DeserializerImpl implements Deserializer {

	@Override
	public TreeNode deserialize(String string) {
		if (string == null || string.isEmpty()) {
			return null;
		}

		TreeNode result = null;

		char prev = '0';
		int valL = -1;
		int valR = -1;
		Integer val = null;
		boolean seeComma = false;

		Stack<TreeNode> stack = new Stack<TreeNode>();

		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if ('{' == c) {
				if (val == null) {
					throw new RuntimeException("Invalid input '" + c + "' at index [" + i + "]: '" + string + "'");
				}
				TreeNode node = new TreeNode(val);
				TreeNode parentNode = (!stack.isEmpty()) ? stack.peek() : null;
				if (parentNode != null) {
					if (!seeComma) {
						parentNode.left = node;
					} else {
						parentNode.right = node;
					}
				}
				stack.push(node);

				// reset
				val = null;
				seeComma = false;

			} else if (',' == c) {
				// for node without children
				if (val != null) {
					TreeNode node = new TreeNode(val);
					TreeNode parentNode = (!stack.isEmpty()) ? stack.peek() : null;
					if (parentNode != null) {
						parentNode.left = node;
					}
				}

				// reset
				val = null;
				seeComma = true;

			} else if ('}' == c) {
				// for node without children
				if (val != null) {
					TreeNode node = new TreeNode(val);
					TreeNode parentNode = (!stack.isEmpty()) ? stack.peek() : null;
					if (parentNode != null) {
						parentNode.right = node;
					}
				}

				result = stack.pop();

				// reset
				val = null;
				seeComma = false;

			} else {
				// keep tracking value of a node
				if (prev == '0' || prev == '{' || prev == '}' || prev == ',') {
					valL = i;
				}
				valR = i;
				try {
					val = Integer.valueOf(string.substring(valL, valR + 1));
				} catch (Exception e) {
					throw new RuntimeException("Invalid value '" + c + "' at index [" + i + "]: '" + string + "'");
				}
			}
			prev = c;
		}

		// for root node without children
		if (val != null) {
			result = new TreeNode(val);
		}

		return result;
	}

}
