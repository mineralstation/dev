package p2.model.repository;

import java.util.ArrayList;
import java.util.List;

import p2.model.resource.DOMElement;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class UnitMetaRequirements extends DOMElement {

	protected int size;
	protected List<UnitRequired> children;

	public UnitMetaRequirements() {
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<UnitRequired> getChildren() {
		if (this.children == null) {
			this.children = new ArrayList<UnitRequired>();
		}
		return this.children;
	}

	public void setChildren(List<UnitRequired> children) {
		this.children = children;
	}

}