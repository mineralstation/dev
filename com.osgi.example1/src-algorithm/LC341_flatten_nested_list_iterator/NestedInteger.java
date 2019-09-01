package LC341_flatten_nested_list_iterator;

import java.util.ArrayList;
import java.util.List;

public class NestedInteger {

	public boolean isInteger;
	public List<NestedInteger> list;
	public int val;

	public NestedInteger(int val) {
		this.val = val;
		this.isInteger = true;
	}

	public NestedInteger() {
		this.list = new ArrayList<NestedInteger>();
	}

}
