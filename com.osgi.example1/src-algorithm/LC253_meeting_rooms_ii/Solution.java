package LC253_meeting_rooms_ii;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * https://github.com/mintycc/OnlineJudge-Solutions/blob/master/Leetcode/253_Meeting_Rooms_II.java
 * 
 */
public class Solution {

	/**
	 * 
	 * @param meetings
	 * @return
	 */
	public int minMeetingRooms(int[][] meetings) {
		// Sort meetings by starting time
		Arrays.sort(meetings, Comparator.comparing((int[] interval) -> interval[0]));

		// room with earliest ending meeting first
		PriorityQueue<Integer> rooms = new PriorityQueue<>();

		for (int[] currMeeting : meetings) {
			int currStart = currMeeting[0];
			int currEnd = currMeeting[1];

			if (!rooms.isEmpty() && rooms.peek() <= currStart) {
				// the meeting room with earliest ending meeting frees before this meeting starts.
				// - that meeting room can be used for this meeting. no new room is needed.
				rooms.poll();
				rooms.add(currEnd); // replace that meeting's ending time with this meeting's ending time

			} else {
				// no meeting ends before this meeting starts.
				// - another room is needed.
				rooms.add(currEnd);
			}
		}

		return rooms.size();
	}

}
