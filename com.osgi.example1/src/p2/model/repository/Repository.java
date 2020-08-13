package p2.model.repository;

import p2.model.resource.DOMElement;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class Repository extends DOMElement {

	protected String name;
	protected String type;
	protected String version;
	protected RepositoryProperties properties;
	protected RepositoryUnits units;

	public Repository() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public RepositoryProperties getProperties() {
		return this.properties;
	}

	public void setProperties(RepositoryProperties properties) {
		this.properties = properties;
	}

	public RepositoryUnits getUnits() {
		return this.units;
	}

	public void setUnits(RepositoryUnits units) {
		this.units = units;
	}

	@Override
	public String toString() {
		return "Repository [name=" + name + ", type=" + type + ", version=" + version + "]";
	}

}
