package p07.LC688_knight_probability_in_chessboard;

import java.util.HashMap;
import java.util.Map;

public class Solution {

	public static int[][] DIRECTIONS = new int[][] { //
			{ 1, -2 }, { 2, -1 }, //
			{ 2, 1 }, { 1, 2 }, //
			{ -1, 2 }, { -2, 1 }, //
			{ -2, -1 }, { -1, -2 } //
	};

	public static double knightProbability(int N, int K, int r, int c) {
		Map<String, Integer> memo = new HashMap<String, Integer>();
		int validMoves = moveKnight(N, K, r, c, memo);
		double allMoves = Math.pow(8, K);

		return validMoves / allMoves;
	}

	protected static int moveKnight(int N, int k, int r, int c, Map<String, Integer> memo) {
		if (k == 0) {
			return 1;
		}

		String key = k + ":(" + r + "," + c + ")";
		Integer value = memo.get(key);
		if (value != null) {
			return value;
		}

		int validMoves = 0;
		for (int[] direction : DIRECTIONS) {
			int x = r + direction[0];
			int y = c + direction[1];
			if (x >= 0 && x < N && y >= 0 && y < N) {
				validMoves += moveKnight(N, k - 1, x, y, memo);
			}
		}

		memo.put(key, new Integer(validMoves));
		return validMoves;
	}

	public static void main(String[] args) {
		// Input: 3, 2, 0, 0
		// Output: 0.0625
		int N = 3;
		int K = 2;
		int r = 0;
		int c = 0;
		double output = knightProbability(N, K, r, c);
		System.out.println("Output: " + output);
	}

}
