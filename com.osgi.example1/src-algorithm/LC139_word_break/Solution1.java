package LC139_word_break;

import java.util.HashSet;
import java.util.Set;

public class Solution1 {

	public static boolean wordBreak(String sentence, String[] wordsArray) {
		Set<String> words = new HashSet<String>();
		for (String word : wordsArray) {
			words.add(word);
		}

		int[] canBreakMemo = new int[sentence.length()];

		boolean canBreak = canBreak(sentence, words, 0, canBreakMemo);
		return canBreak;
	}

	/**
	 * memo[i] 定义为范围为 [i, n] 的子字符串是否可以拆分。
	 * 
	 * 初始化为0，表示没有计算过。
	 * 
	 * 如果可以拆分，则赋值为1。
	 * 
	 * 如果不能拆分，赋值为2。
	 * 
	 * @param sentence
	 * @param words
	 * @param i
	 * @param canBreakMemo
	 * @return
	 */
	protected static boolean canBreak(String sentence, Set<String> words, int i, int[] canBreakMemo) {
		if (canBreakMemo[i] == 1) {
			return true; // can break
		} else if (canBreakMemo[i] == 2) {
			return false; // can not break
		}

		boolean canBreak = false;
		if (i >= sentence.length() || words.contains(sentence.substring(i))) {
			canBreak = true;

		} else {
			for (int j = i + 1; j <= sentence.length(); j++) {
				String part1 = sentence.substring(i, j - i);
				if (words.contains(part1) && canBreak(sentence, words, j, canBreakMemo)) {
					canBreak = true;
					break;
				}
			}
		}

		if (canBreak) {
			canBreakMemo[i] = 1; // can break
		} else {
			canBreakMemo[i] = 2; // cannot break
		}

		return canBreak;
	}

}
