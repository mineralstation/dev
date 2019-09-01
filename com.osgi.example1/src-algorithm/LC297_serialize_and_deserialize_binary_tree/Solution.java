package LC297_serialize_and_deserialize_binary_tree;

import LC173_binary_search_tree_iterator.Solution2.BstIteratorImpl;
import LC173_binary_search_tree_iterator.TreeNode;

public class Solution {

	public static void main(String[] args) {
		{
			System.out.println("------------------------------------------------------------------------");
			TreeNode root = new TreeNode(7);
			TreeNode node01 = new TreeNode(3);
			TreeNode node011 = new TreeNode(1);
			TreeNode node02 = new TreeNode(15);
			TreeNode node021 = new TreeNode(9);
			TreeNode node022 = new TreeNode(20);

			root.left = node01;
			root.right = node02;

			node01.left = node011;

			node02.left = node021;
			node02.right = node022;

			Serializer serializer = new SerializerImpl();
			String result = serializer.serialize(root);
			System.out.println("result = " + result);

			Deserializer deserializer = new DeserializerImpl();
			TreeNode root2 = deserializer.deserialize(result);

			BstIteratorImpl itor = new BstIteratorImpl(root2);
			while (itor.hasNext()) {
				int val = itor.next();
				System.out.println("-> " + val);
			}

			String result2 = serializer.serialize(root2);
			System.out.println("result2 = " + result2);
		}

		{
			System.out.println("------------------------------------------------------------------------");
			TreeNode root = new TreeNode(7);

			Serializer serializer = new SerializerImpl();
			String result = serializer.serialize(root);
			System.out.println("result = " + result);

			Deserializer deserializer = new DeserializerImpl();
			TreeNode root2 = deserializer.deserialize(result);

			BstIteratorImpl itor = new BstIteratorImpl(root2);
			while (itor.hasNext()) {
				int val = itor.next();
				System.out.println("-> " + val);
			}

			String result2 = serializer.serialize(root2);
			System.out.println("result2 = " + result2);
		}

		{
			System.out.println("------------------------------------------------------------------------");
			String input = "7{{3{1,},15{9,20}}";
			System.out.println("input = " + input);
			Deserializer deserializer = new DeserializerImpl();
			TreeNode root2 = deserializer.deserialize(input);

			BstIteratorImpl itor = new BstIteratorImpl(root2);
			while (itor.hasNext()) {
				int val = itor.next();
				System.out.println("-> " + val);
			}
		}
	}

}
