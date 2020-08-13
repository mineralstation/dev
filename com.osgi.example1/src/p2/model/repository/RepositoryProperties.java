package p2.model.repository;

import java.util.ArrayList;
import java.util.List;

import p2.model.resource.DOMElement;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class RepositoryProperties extends DOMElement {

	protected int size;
	protected List<RepositoryProperty> children;

	public RepositoryProperties() {
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<RepositoryProperty> getChildren() {
		if (this.children == null) {
			this.children = new ArrayList<RepositoryProperty>();
		}
		return this.children;
	}

	public void setChildren(List<RepositoryProperty> children) {
		this.children = children;
	}

}
