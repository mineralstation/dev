package LC380_insert_delete_get_random;

public class Solution {

	public static void main(String[] args) {
		IRandomizedSet set = new RandomizedSet();

		boolean result1 = set.insert(1);
		boolean result2 = set.insert(2);
		boolean result3 = set.insert(3);
		boolean result4 = set.insert(4);
		int random1 = set.getRandom();
		int random2 = set.getRandom();
		int random3 = set.getRandom();
		int random4 = set.getRandom();

		System.out.println("result1 = " + result1);
		System.out.println("result2 = " + result2);
		System.out.println("result3 = " + result3);
		System.out.println("result4 = " + result4);
		System.out.println("random1 = " + random1);
		System.out.println("random2 = " + random2);
		System.out.println("random3 = " + random3);
		System.out.println("random4 = " + random4);

		System.out.println("Set is: " + set);

		boolean result5 = set.remove(1);
		boolean result6 = set.remove(4);
		int random5 = set.getRandom();
		int random6 = set.getRandom();
		int random7 = set.getRandom();
		int random8 = set.getRandom();

		System.out.println("result5 = " + result5);
		System.out.println("result6 = " + result6);
		System.out.println("random5 = " + random5);
		System.out.println("random6 = " + random6);
		System.out.println("random7 = " + random7);
		System.out.println("random8 = " + random8);
	}

}
