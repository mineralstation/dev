package calculator.util;

/**
 * 
 * interface required for implementing a stack
 */
public interface Stack {

	Object pop();

	void push(Object O);

	int size();

	Object top();

	boolean isEmpty();

}
