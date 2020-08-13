package p2.model.compositerepository;

import p2.model.resource.DOMElement;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class CompositeRepositoryChild extends DOMElement {

	protected String location;

	public CompositeRepositoryChild() {
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "CompositeRepositoryChild [location=" + location + "]";
	}

}
