package common;

public interface BstIterator {

	/** @return the next smallest number */
	int next();

	/** @return whether we have a next smallest number */
	boolean hasNext();

	int get();

}
