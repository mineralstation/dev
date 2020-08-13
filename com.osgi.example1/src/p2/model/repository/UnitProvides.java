package p2.model.repository;

import java.util.ArrayList;
import java.util.List;

import p2.model.resource.DOMElement;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class UnitProvides extends DOMElement {

	protected int size;
	protected List<UnitProvided> children;

	public UnitProvides() {
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<UnitProvided> getChildren() {
		if (this.children == null) {
			this.children = new ArrayList<UnitProvided>();
		}
		return this.children;
	}

	public void setChildren(List<UnitProvided> children) {
		this.children = children;
	}

}
