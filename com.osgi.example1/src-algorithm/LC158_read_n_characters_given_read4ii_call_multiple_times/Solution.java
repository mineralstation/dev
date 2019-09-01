package LC158_read_n_characters_given_read4ii_call_multiple_times;

import java.util.Arrays;

public class Solution extends Reader4 {

	char[] buffer = new char[4];
	int bufferAvailable = 0;
	int bufferIndex = 0;

	public Solution(char[] input) {
		super(input);
	}

	/**
	 * 
	 * @param buf
	 * @param n
	 * @return
	 */
	public synchronized int read(char[] buf, int n) {
		int index = 0;
		while (index < n) {
			while (index < n && this.bufferAvailable > 0) {
				buf[index++] = this.buffer[this.bufferIndex++];
				this.bufferAvailable--;
			}

			if (index < n && this.bufferAvailable == 0) {
				// internal buffer becomes empty, need to read again
				this.bufferAvailable = read4(this.buffer); // read4() again. fills the internal buffer and returns actual length.
				this.bufferIndex = 0; // reset index for reading the internal buffer

				if (this.bufferAvailable == 0) {
					// nothing is read from read4()
					break;
				}
			}
		}

		return index;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		char[] input = "abcdefghijklmno".toCharArray();
		System.out.println("input = " + Arrays.toString(input));
		Solution solution = new Solution(input);

		char[] buf = new char[16];
		int len1 = solution.read(buf, 1);
		System.out.println("len1 = " + len1 + ", buf = " + Arrays.toString(buf));
		int len2 = solution.read(buf, 2);
		System.out.println("len2 = " + len2 + ", buf = " + Arrays.toString(buf));
		int len3 = solution.read(buf, 3);
		System.out.println("len3 = " + len3 + ", buf = " + Arrays.toString(buf));

		int len4 = solution.read(buf, 5);
		System.out.println("len4 = " + len4 + ", buf = " + Arrays.toString(buf));
		int len5 = solution.read(buf, 7);
		System.out.println("len4 = " + len5 + ", buf = " + Arrays.toString(buf));
	}

}
