package p2.model.repository;

import java.util.ArrayList;
import java.util.List;

import p2.model.resource.DOMElement;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class RepositoryUnits extends DOMElement {

	protected int size;
	protected List<RepositoryUnit> units;

	public RepositoryUnits() {
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<RepositoryUnit> getChildren() {
		if (this.units == null) {
			this.units = new ArrayList<RepositoryUnit>();
		}
		return this.units;
	}

	public void setChildren(List<RepositoryUnit> units) {
		this.units = units;
	}

}
