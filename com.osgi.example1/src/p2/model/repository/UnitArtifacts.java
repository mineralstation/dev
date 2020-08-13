package p2.model.repository;

import java.util.ArrayList;
import java.util.List;

import p2.model.resource.DOMElement;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class UnitArtifacts extends DOMElement {

	protected int size;
	protected List<UnitArtifact> children;

	public UnitArtifacts() {
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<UnitArtifact> getChildren() {
		if (this.children == null) {
			this.children = new ArrayList<UnitArtifact>();
		}
		return this.children;
	}

	public void setChildren(List<UnitArtifact> children) {
		this.children = children;
	}

}
