package LC224_basic_calculator_ii;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @see LC772
 *
 */
public class Solution3 {

	/**
	 * 表达式 加减乘除运算 没有括号
	 * 
	 * @param expression
	 * @return
	 */
	public static int calculate(String expression) {
		if (expression == null || expression.isEmpty()) {
			return 0;
		}
		int result = 0;
		int currResult = 0;

		int n = expression.length();

		int prevOper = '+';
		int num = 0;
		for (int i = 0; i < n; i++) {
			char c = expression.charAt(i);

			if (c >= '0' && c <= '9') {
				num = num * 10 + (c - '0');
			}

			if (c == '+' || c == '-' || c == '*' || c == '/' || i == n - 1) {
				// oper and num can be calculated now
				if (prevOper == '+') {
					currResult += num;
				} else if (prevOper == '-') {
					currResult -= num;
				} else if (prevOper == '*') {
					currResult *= num;
				} else if (prevOper == '/') {
					currResult /= num;
				}

				if (c == '+' || c == '-' || i == n - 1) {
					result += currResult;
					currResult = 0;
				}

				prevOper = c;
				num = 0;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		Map<String, String> inputToExptected = new LinkedHashMap<String, String>();
		inputToExptected.put("3+2*2", "7");
		inputToExptected.put(" 3/2 ", "1");
		inputToExptected.put(" 3+5 / 2 ", "5");
		inputToExptected.put("3+2*2*2", "11");
		inputToExptected.put("3+2*3*4/8", "6");
		inputToExptected.put(" 3 + 120 / 10", "15");
		inputToExptected.put(" 3 + 120 / 12 * 20", "203");

		for (Iterator<String> itor = inputToExptected.keySet().iterator(); itor.hasNext();) {
			String s = itor.next();
			String expected = inputToExptected.get(s);

			System.out.println("Input: " + s);
			int output = calculate(s);

			System.out.println("Output: " + output + " //" + expected);
			System.out.println();
		}
	}

}
