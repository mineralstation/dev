package calculator.util;

public class EmptyDequeException extends RuntimeException {

	private static final long serialVersionUID = 2365915024113205810L;

	public EmptyDequeException() {
		super("Attempted to read from an empty Deque.");
	}

}
