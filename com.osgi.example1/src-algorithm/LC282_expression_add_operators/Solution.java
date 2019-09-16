package LC282_expression_add_operators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binaryoperators (not unary) +, -, or * between the digits so they evaluate to the target value.
 * 
 * @see 224 basic calculator ii;
 * 
 */
public class Solution {

	public static List<String> getExpressions(String digits, int target) {
		List<String> results = new ArrayList<String>();
		getExpressions(digits, target, results, "", 0, null, 0);
		return results;
	}

	public static void getExpressions(String digits, int target, List<String> results, String accumulatedExpr, long accumulatedVal, String prevOper, long prevArg) {
		// Check whether the whole digits can produce a value equal to the target
		boolean isZeroPrefixed = false;
		if (digits.length() > 1 && digits.charAt(0) == '0') {
			isZeroPrefixed = true;
		}

		if (!isZeroPrefixed) {
			long digitsVal = Long.parseLong(digits);

			if (accumulatedExpr.isEmpty()) {
				if (digitsVal == target) {
					results.add(digits);
				}
			} else {
				// +
				// Check "<accumulatedExpr> + <digits>"
				if (accumulatedVal + digitsVal == target) {
					results.add(accumulatedExpr + "+" + digits);
				}
				// -
				// Check "<accumulatedExpr> - <digits>"
				if (accumulatedVal - digitsVal == target) {
					results.add(accumulatedExpr + "-" + digits);
				}
				// *
				// Check "<accumulatedExpr> * <digits>"
				boolean match = false;
				if ("+".equals(prevOper)) {
					if ((accumulatedVal - prevArg) + (prevArg * digitsVal) == target) {
						match = true;
					}
				} else if ("-".equals(prevOper)) {
					if ((accumulatedVal + prevArg) - (prevArg * digitsVal) == target) {
						match = true;
					}
				} else {
					if (accumulatedVal * digitsVal == target) {
						match = true;
					}
				}
				if (match) {
					results.add(accumulatedExpr + "*" + digits);
				}
			}
		}

		for (int i = 1; i < digits.length(); i++) {
			String digits1 = digits.substring(0, i);
			String digits2 = digits.substring(i);
			long digits1Val = Integer.parseInt(digits1);

			if (accumulatedExpr.isEmpty()) {
				String newAccumulatedExpr = digits1;
				long newAccumulated = digits1Val;
				getExpressions(digits2, target, results, newAccumulatedExpr, newAccumulated, null, 0);

			} else {
				{
					// +
					// Check "<accumulatedExpr> + <digits1>" followed by digits2
					String newAccumulatedExpr = accumulatedExpr + "+" + digits1;
					long newAccumulated = accumulatedVal + digits1Val;
					getExpressions(digits2, target, results, newAccumulatedExpr, newAccumulated, "+", digits1Val);
				}
				{
					// -
					// Check "<accumulatedExpr> - <digits1>" followed by digits2
					String newAccumulatedExpr = accumulatedExpr + "-" + digits1;
					long newAccumulatedVal = accumulatedVal - digits1Val;
					getExpressions(digits2, target, results, newAccumulatedExpr, newAccumulatedVal, "-", digits1Val);
				}
				{
					// *
					// Check "<accumulatedExpr> * <digits1>" followed by digits2
					String newAccumulatedExpr = accumulatedExpr + "*" + digits1;
					long newAccumulated = 0;
					if ("+".equals(prevOper)) {
						newAccumulated = (accumulatedVal - prevArg) + (prevArg * digits1Val);
					} else if ("-".equals(prevOper)) {
						newAccumulated = (accumulatedVal + prevArg) - (prevArg * digits1Val);
					} else {
						newAccumulated = accumulatedVal * digits1Val;
					}
					getExpressions(digits2, target, results, newAccumulatedExpr, newAccumulated, "*", digits1Val);
				}
			}
		}
	}

	/*
	 * Example 1: Input: num = "123", target = 6 Output: ["1+2+3", "1*2*3"]
	 * 
	 * Example 2: Input: num = "232", target = 8 Output: ["2*3+2", "2+3*2"]
	 * 
	 * Example 3: Input: num = "105", target = 5 Output: ["1*0+5","10-5"]
	 * 
	 * Example 4: Input: num = "00", target = 0 Output: ["0+0", "0-0", "0*0"]
	 * 
	 * Example 5: Input: num = "3456237490", target = 9191 Output: []
	 */
	public static void main(String[] args) {
		{
			String digits = "110";
			int target = 2;
			System.out.println("Input:  digits = " + digits + ", target = " + target);
			List<String> expressions = getExpressions(digits, target);
			System.out.println("Output: " + Arrays.toString(expressions.toArray(new String[expressions.size()])));
		}

		{
			String digits = "110";
			int target = 110;
			System.out.println("Input:  digits = " + digits + ", target = " + target);
			List<String> expressions = getExpressions(digits, target);
			System.out.println("Output: " + Arrays.toString(expressions.toArray(new String[expressions.size()])));
		}

		{
			String digits = "123";
			int target = 6;
			System.out.println("Input:  digits = " + digits + ", target = " + target);
			List<String> expressions = getExpressions(digits, target);
			System.out.println("Output: " + Arrays.toString(expressions.toArray(new String[expressions.size()])));
		}

		{
			String digits = "232";
			int target = 8;
			System.out.println("Input:  digits = " + digits + ", target = " + target);
			List<String> expressions = getExpressions(digits, target);
			System.out.println("Output: " + Arrays.toString(expressions.toArray(new String[expressions.size()])));
		}

		{
			String digits = "105";
			int target = 5;
			System.out.println("Input:  digits = " + digits + ", target = " + target);
			List<String> expressions = getExpressions(digits, target);
			System.out.println("Output: " + Arrays.toString(expressions.toArray(new String[expressions.size()])));
		}

		{
			String digits = "00";
			int target = 0;
			System.out.println("Input:  digits = " + digits + ", target = " + target);
			List<String> expressions = getExpressions(digits, target);
			System.out.println("Output: " + Arrays.toString(expressions.toArray(new String[expressions.size()])));
		}

		{
			String digits = "3456237490";
			int target = 9191;
			System.out.println("Input:  digits = " + digits + ", target = " + target);
			List<String> expressions = getExpressions(digits, target);
			System.out.println("Output: " + Arrays.toString(expressions.toArray(new String[expressions.size()])));
		}
	}

}

// public static void main2(String[] args) {
// int val = 1;
// int val2 = -val;
// int val3 = -val2;
// int val4 = +val2;
//
// System.out.println("val = " + val);
// System.out.println("val2 = " + val2);
// System.out.println("val3 = " + val3);
// System.out.println("val4 = " + val4);
// }
