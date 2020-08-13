package p2.model.resource;

import p2.model.repository.Repository;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class RepositoryResource extends DOMDocument {

	protected Repository repository;

	public RepositoryResource() {
	}

	public Repository getRepository() {
		return this.repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

}
