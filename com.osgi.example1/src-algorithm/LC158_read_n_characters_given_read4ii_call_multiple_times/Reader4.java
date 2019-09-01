package LC158_read_n_characters_given_read4ii_call_multiple_times;

public class Reader4 {

	protected char[] input;
	protected int index = 0;

	public Reader4(char[] input) {
		this.input = input;
	}

	public int read4(char[] buffer) {
		if (this.index >= this.input.length) {
			// no more data
			return 0;
		}

		int length = 4;
		int available = this.input.length - this.index;
		if (available < length) {
			length = available;
		}
		System.arraycopy(this.input, this.index, buffer, 0, length);
		this.index += length;
		return length;
	}

}
