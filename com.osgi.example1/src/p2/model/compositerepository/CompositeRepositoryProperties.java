package p2.model.compositerepository;

import java.util.ArrayList;
import java.util.List;

import p2.model.resource.DOMElement;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class CompositeRepositoryProperties extends DOMElement {

	protected int size;
	protected List<CompositeRepositoryProperty> children;

	public CompositeRepositoryProperties() {
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<CompositeRepositoryProperty> getChildren() {
		if (this.children == null) {
			this.children = new ArrayList<CompositeRepositoryProperty>();
		}
		return this.children;
	}

	public void setChildren(List<CompositeRepositoryProperty> children) {
		this.children = children;
	}

}
