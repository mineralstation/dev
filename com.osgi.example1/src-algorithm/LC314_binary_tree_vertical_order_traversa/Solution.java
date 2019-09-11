package LC314_binary_tree_vertical_order_traversa;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TreeMap;

import common.TreeNode;

public class Solution {

	/**
	 * 
	 * @param root
	 * @return
	 */
	public static int[][] verticalOrder(TreeNode root) {
		Comparator<TreeNode> comparator = new Comparator<TreeNode>() {
			@Override
			public int compare(TreeNode o1, TreeNode o2) {
				return o1.row - o2.row;
			}
		};

		TreeMap<Integer, PriorityQueue<TreeNode>> colToNodes = new TreeMap<Integer, PriorityQueue<TreeNode>>();
		midOrder(root, 0, 0, colToNodes, comparator);

		int[][] result = toArray(colToNodes);
		return result;
	}

	/**
	 * 
	 * @param node
	 * @param row
	 * @param col
	 * @param colToNodes
	 * @param comparator
	 */
	protected static void midOrder(TreeNode node, int row, int col, TreeMap<Integer, PriorityQueue<TreeNode>> colToNodes, Comparator<TreeNode> comparator) {
		if (node == null) {
			return;
		}

		node.row = row;
		node.col = col;

		PriorityQueue<TreeNode> nodes = colToNodes.get(col);
		if (nodes == null) {
			nodes = new PriorityQueue<TreeNode>(comparator);
			colToNodes.put(col, nodes);
		}
		nodes.add(node);

		midOrder(node.left, row + 1, col - 1, colToNodes, comparator);
		midOrder(node.right, row + 1, col + 1, colToNodes, comparator);
	}

	/**
	 * 
	 * @param colToValues
	 * @return
	 */
	protected static int[][] toArray(TreeMap<Integer, PriorityQueue<TreeNode>> colToValues) {
		int M = colToValues.size();
		int N = 0;
		for (Iterator<Integer> itor = colToValues.keySet().iterator(); itor.hasNext();) {
			Integer col = itor.next();
			PriorityQueue<TreeNode> nodes = colToValues.get(col);
			if (nodes.size() > N) {
				N = nodes.size();
			}
		}

		int[][] result = new int[M][N];
		int i = 0;
		for (Iterator<Integer> itor = colToValues.keySet().iterator(); itor.hasNext();) {
			Integer col = itor.next();
			PriorityQueue<TreeNode> nodes = colToValues.get(col);

			int[] rows = new int[nodes.size()];
			int j = 0;
			while (!nodes.isEmpty()) {
				rows[j++] = nodes.poll().val;
			}
			result[i++] = rows;
		}
		return result;
	}

	public static void main(String[] args) {
		{
			TreeNode root = new TreeNode(3);
			TreeNode node1 = new TreeNode(9);
			TreeNode node2 = new TreeNode(20);
			root.left = node1;
			root.right = node2;

			TreeNode node21 = new TreeNode(15);
			TreeNode node22 = new TreeNode(7);
			node2.left = node21;
			node2.right = node22;

			int[][] output = verticalOrder(root);
			System.out.println("Output: {");
			System.out.println();
			for (int[] row : output) {
				System.out.println("  " + Arrays.toString(row));
			}
			System.out.println("}");
			System.out.println();

		}

		{
			TreeNode root = new TreeNode(3);
			TreeNode node1 = new TreeNode(9);
			TreeNode node2 = new TreeNode(20);
			root.left = node1;
			root.right = node2;

			TreeNode node11 = new TreeNode(4);
			TreeNode node12 = new TreeNode(5);
			node1.left = node11;
			node1.right = node12;

			TreeNode node21 = new TreeNode(2);
			TreeNode node22 = new TreeNode(7);
			node2.left = node21;
			node2.right = node22;

			int[][] output = verticalOrder(root);
			System.out.println("Output: {");
			System.out.println();
			for (int[] row : output) {
				System.out.println("  " + Arrays.toString(row));
			}
			System.out.println("}");
			System.out.println();

		}
	}

}
