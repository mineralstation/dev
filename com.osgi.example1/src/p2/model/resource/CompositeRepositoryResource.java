package p2.model.resource;

import p2.model.compositerepository.CompositeRepository;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class CompositeRepositoryResource extends DOMDocument {

	protected CompositeRepository repository;

	public CompositeRepositoryResource() {
	}

	public CompositeRepository getRepository() {
		return this.repository;
	}

	public void setRepository(CompositeRepository repository) {
		this.repository = repository;
	}

}
