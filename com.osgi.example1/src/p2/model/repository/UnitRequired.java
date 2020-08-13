package p2.model.repository;

import p2.model.resource.DOMElement;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class UnitRequired extends DOMElement {

	protected String namespace;
	protected String name;
	protected String range;
	protected boolean optional;

	public UnitRequired() {
	}

	public String getNamespace() {
		return this.namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRange() {
		return this.range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public boolean isOptional() {
		return this.optional;
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
	}

}
