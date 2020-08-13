package p2.model.repository;

import p2.model.resource.DOMElement;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class UnitTouchpointData extends DOMElement {

	protected int size;
	protected UnitInstructions instructions;

	public UnitTouchpointData() {
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public UnitInstructions getInstructions() {
		return this.instructions;
	}

	public void setInstructions(UnitInstructions instructions) {
		this.instructions = instructions;
	}

}
