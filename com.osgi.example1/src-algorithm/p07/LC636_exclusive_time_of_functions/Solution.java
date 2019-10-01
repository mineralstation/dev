package p07.LC636_exclusive_time_of_functions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

/*
Given the running logs of n functions that are executed in a nonpreemptive single threaded CPU, find the exclusive time of these functions.

Each function has a unique id, start from 0 to n-1. A function may be called recursively or by another function.

A log is a string has this format : function_id:start_or_end:timestamp. For example, "0:start:0" means function 0 starts from the very beginning of time 0. "0:end:0" means function 0 ends to the very end of time 0.

Exclusive time of a function is defined as the time spent within this function, the time spent by calling other functions should not be considered as this function's exclusive time. You should return the exclusive time of each function sorted by their function id.

Example 1:
Input:
n = 2
logs = 
["0:start:0",
 "1:start:2",
 "1:end:5",
 "0:end:6"]
Output:[3, 4]

Explanation:
Function 0 starts at time 0, then it executes 2 units of time // [0-2)
Now function 0 calls function 1, function 1 starts at time 2, executes 4 units of time and end at time 5. // [2-5]
Function 0 is running again at time 6, and also end at the time 6, thus executes 1 unit of time. // [5-6)
So function 0 totally execute 2 + 1 = 3 units of time, and function 1 totally execute 4 units of time.
 
Note:
Input logs will be sorted by timestamp, NOT log id.
Your output should be sorted by function id, which means the 0th element of your output corresponds to the exclusive time of function 0.
Two functions won't start or end at the same time.
Functions could be called recursively, and will always end.
1 <= n <= 100

 */
public class Solution {

	public static class Func {
		public int id;
		public int startTime;
		public int endTime;
		public int timeToExclude;

		public Func(int id) {
			this.id = id;
		}

		public int getTotalTime() {
			return this.endTime - this.startTime + 1;
		}

		public int getExclusiveTime() {
			return getTotalTime() - this.timeToExclude;
		}
	}

	public static int[] getExclusiveTime(String[] logs) {
		Map<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
		Stack<Func> callStack = new Stack<Func>();
		getExclusiveTime(logs, 0, resultMap, callStack);

		int[] result = new int[resultMap.size()];
		for (Iterator<Integer> itor = resultMap.keySet().iterator(); itor.hasNext();) {
			Integer id = itor.next();
			Integer time = resultMap.get(id);
			result[id] = time;
		}
		return result;
	}

	protected static void getExclusiveTime(String[] logs, int i, Map<Integer, Integer> resultMap, Stack<Func> callStack) {
		if (i >= logs.length) {
			return;
		}

		String log = logs[i];
		String[] parts = log.split(":");
		int id = Integer.parseInt(parts[0]);
		boolean isStart = "start".equals(parts[1]);
		boolean isEnd = "end".equals(parts[1]);
		int time = Integer.parseInt(parts[2]);

		if (isStart) {
			Func func = new Func(id);
			func.startTime = time;
			callStack.push(func);

		} else if (isEnd) {
			Func func = callStack.pop();
			func.endTime = time;

			Integer allTime = resultMap.get(func.id);
			if (allTime == null) {
				allTime = new Integer(0);
			}
			resultMap.put(func.id, new Integer(allTime.intValue() + func.getExclusiveTime()));

			if (!callStack.isEmpty()) {
				Func parentFunc = callStack.peek();
				parentFunc.timeToExclude = func.getTotalTime();
			}
		}

		getExclusiveTime(logs, i + 1, resultMap, callStack);
	}

	public static void main(String[] args) {
		// Input:
		// n = 2
		// logs =
		// ["0:start:0",
		// "1:start:2",
		// "1:end:5",
		// "0:end:6"]
		// Output:[3, 4]
		String[] logs = new String[] { //
				"0:start:0", //
				"1:start:2", //
				"1:end:5", //
				"0:end:6", //
		};
		System.out.println("Input:");
		for (String log : logs) {
			System.out.println("  " + log);
		}
		int[] result = getExclusiveTime(logs);
		System.out.println("Output:");
		System.out.println(Arrays.toString(result));
	}

}
