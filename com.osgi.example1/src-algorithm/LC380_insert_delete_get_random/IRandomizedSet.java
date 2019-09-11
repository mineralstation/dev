package LC380_insert_delete_get_random;

/*

1. insert(val): Inserts an item val to the set if not already present.

2. remove(val): Removes an item val from the set if present.

3. getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.

 */
public interface IRandomizedSet {

	boolean insert(int value);

	boolean remove(int value);

	int getRandom();

}
