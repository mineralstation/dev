package LC297_serialize_and_deserialize_binary_tree;

import common.TreeNode;

public class SerializerImpl implements Serializer {

	@Override
	public String serialize(TreeNode node) {
		return toString(node);
	}

	protected String toString(TreeNode node) {
		if (node == null) {
			return "";
		}
		String str = node.val + "";
		if (node.left != null || node.right != null) {
			str += "{" + toString(node.left) + "," + toString(node.right) + "}";
		}
		return str;
	}

}
