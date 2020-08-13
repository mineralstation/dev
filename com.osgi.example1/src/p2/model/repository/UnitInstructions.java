package p2.model.repository;

import java.util.ArrayList;
import java.util.List;

import p2.model.resource.DOMElement;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class UnitInstructions extends DOMElement {

	protected int size;
	protected List<UnitInstruction> children;

	public UnitInstructions() {
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<UnitInstruction> getChildren() {
		if (this.children == null) {
			this.children = new ArrayList<UnitInstruction>();
		}
		return this.children;
	}

	public void setChildren(List<UnitInstruction> children) {
		this.children = children;
	}

}
