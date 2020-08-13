package p2.model.compositerepository;

import java.util.ArrayList;
import java.util.List;

import p2.model.resource.DOMElement;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class CompositeRepositoryChildren extends DOMElement {

	protected int size;
	protected List<CompositeRepositoryChild> children;

	public CompositeRepositoryChildren() {
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<CompositeRepositoryChild> getChildren() {
		if (this.children == null) {
			this.children = new ArrayList<CompositeRepositoryChild>();
		}
		return this.children;
	}

	public void setChildren(List<CompositeRepositoryChild> children) {
		this.children = children;
	}

}
