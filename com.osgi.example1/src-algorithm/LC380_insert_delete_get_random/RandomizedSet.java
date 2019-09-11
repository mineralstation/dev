package LC380_insert_delete_get_random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomizedSet implements IRandomizedSet {

	protected List<Integer> valueList = new ArrayList<Integer>();
	protected Map<Integer, Integer> valueToIndexMap = new HashMap<Integer, Integer>();

	@Override
	public synchronized boolean insert(int value) {
		if (this.valueToIndexMap.containsKey(value)) {
			return false;
		}

		int index = this.valueList.size();
		this.valueList.add(value);
		this.valueToIndexMap.put(value, index);

		return true;
	}

	@Override
	public synchronized boolean remove(int value) {
		if (!this.valueToIndexMap.containsKey(value)) {
			return false;
		}

		int lastIndex = this.valueList.size() - 1;
		int lastValue = this.valueList.get(lastIndex);
		int index = this.valueToIndexMap.get(value);

		// switch value with last value in the list
		this.valueList.set(index, lastValue);
		this.valueList.set(lastIndex, value);

		this.valueToIndexMap.put(lastValue, index);
		this.valueToIndexMap.remove(value);

		this.valueList.remove(lastIndex);

		return true;
	}

	@Override
	public synchronized int getRandom() {
		int randomIndex = new Random().nextInt(this.valueList.size());
		return this.valueList.get(randomIndex);
	}

	@Override
	public String toString() {
		return Arrays.toString(this.valueList.toArray(new Integer[this.valueList.size()]));
	}

}
