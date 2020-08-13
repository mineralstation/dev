package p2.model.repository;

import java.util.ArrayList;
import java.util.List;

import p2.model.resource.DOMElement;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class UnitLicenses extends DOMElement {

	protected int size;
	protected List<UnitLicense> children;

	public UnitLicenses() {
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<UnitLicense> getChildren() {
		if (this.children == null) {
			this.children = new ArrayList<UnitLicense>();
		}
		return this.children;
	}

	public void setChildren(List<UnitLicense> children) {
		this.children = children;
	}

}
