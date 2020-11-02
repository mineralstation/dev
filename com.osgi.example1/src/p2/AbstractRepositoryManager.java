package p2;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 * @see org.eclipse.equinox.internal.p2.repository.helpers.AbstractRepositoryManager
 */
public class AbstractRepositoryManager {

	private final Map<URI, Thread> loadLocks = new HashMap<URI, Thread>();

	/**
	 * Obtains an exclusive right to load a repository at the given location. Blocks if another thread is currently loading at that location. Invocation of this method must be followed by a subsequent call to {@link #exitLoad(URI)}.
	 * 
	 * To avoid deadlock between the loadLock and repositoryLock, this method must not be called when repositoryLock is held.
	 * 
	 * @param location
	 *            The location to lock
	 */
	public void enterLoad(URI location, IProgressMonitor monitor) {
		Thread current = Thread.currentThread();
		synchronized (this.loadLocks) {
			while (true) {
				Thread owner = this.loadLocks.get(location);
				if (owner == null || current.equals(owner)) {
					break;
				}
				if (monitor.isCanceled())
					throw new CancellationException();
				try {
					this.loadLocks.wait(1000);
				} catch (InterruptedException e) {
					// keep trying
				}
			}
			this.loadLocks.put(location, current);
		}
	}

	/**
	 * Relinquishes the exclusive right to load a repository at the given location. Unblocks other threads waiting to load at that location.
	 * 
	 * @param location
	 *            The location to unlock
	 */
	public void exitLoad(URI location) {
		synchronized (this.loadLocks) {
			this.loadLocks.remove(location);
			this.loadLocks.notifyAll();
		}
	}

}
