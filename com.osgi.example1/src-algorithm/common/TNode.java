package common;

public class TNode<T> {

	public T value;
	public TNode<T> next;

	public TNode(T value) {
		this.value = value;
	}

	public static void print(TNode<?> node) {
		if (node == null) {
			System.out.println("node is null");
		} else {
			String content = "";
			TNode<?> curr = node;
			while (curr != null) {
				content += curr.value + "->";
				curr = curr.next;
			}
			content += "null";
			System.out.println(content);
		}
	}

}
