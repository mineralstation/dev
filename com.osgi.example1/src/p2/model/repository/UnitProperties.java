package p2.model.repository;

import java.util.ArrayList;
import java.util.List;

import p2.model.resource.DOMElement;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class UnitProperties extends DOMElement {

	protected int size;
	protected List<UnitProperty> children;

	public UnitProperties() {
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<UnitProperty> getChildren() {
		if (this.children == null) {
			this.children = new ArrayList<UnitProperty>();
		}
		return this.children;
	}

	public void setChildren(List<UnitProperty> children) {
		this.children = children;
	}

	public UnitProperty getChild(String name) {
		UnitProperty result = null;
		if (this.children != null && name != null) {
			for (UnitProperty child : this.children) {
				if (name.equals(child.getName())) {
					result = child;
					break;
				}
			}
		}
		return result;
	}

}
