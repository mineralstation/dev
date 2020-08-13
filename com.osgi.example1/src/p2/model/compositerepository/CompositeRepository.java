package p2.model.compositerepository;

import p2.model.resource.DOMElement;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class CompositeRepository extends DOMElement {

	protected String name;
	protected String type;
	protected String version;
	protected CompositeRepositoryProperties properties;
	protected CompositeRepositoryChildren plugins;

	public CompositeRepository() {
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

	public CompositeRepositoryProperties getProperties() {
		return this.properties;
	}

	public void setProperties(CompositeRepositoryProperties properties) {
		this.properties = properties;
	}

	public CompositeRepositoryChildren getPlugins() {
		return this.plugins;
	}

	public void setPlugins(CompositeRepositoryChildren plugins) {
		this.plugins = plugins;
	}

}
