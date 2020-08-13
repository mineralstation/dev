package p2.model.repository;

import p2.model.resource.DOMElement;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class UnitArtifact extends DOMElement {

	protected String classifier;
	protected String id;
	protected String version;

	public UnitArtifact() {
	}

	public String getClassifier() {
		return this.classifier;
	}

	public void setClassifier(String classifier) {
		this.classifier = classifier;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
