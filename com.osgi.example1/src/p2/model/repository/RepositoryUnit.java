package p2.model.repository;

import p2.model.resource.DOMElement;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class RepositoryUnit extends DOMElement {

	protected String id;
	protected String version;

	protected UnitUpdate update;
	protected UnitProperties properties;
	protected UnitMetaRequirements metaRequirements;
	protected UnitProvides provides;
	protected UnitFilter filter;
	protected UnitRequires requires;
	protected UnitArtifacts artifacts;
	protected UnitTouchpoint touchpoint;
	protected UnitTouchpointData touchpointData;
	protected UnitLicenses licenses;
	protected UnitCopyright copyright;

	public RepositoryUnit() {
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

	public UnitUpdate getUpdate() {
		return this.update;
	}

	public void setUpdate(UnitUpdate update) {
		this.update = update;
	}

	public UnitProperties getProperties() {
		return this.properties;
	}

	public void setProperties(UnitProperties properties) {
		this.properties = properties;
	}

	public UnitMetaRequirements getMetaRequirements() {
		return this.metaRequirements;
	}

	public void setMetaRequirements(UnitMetaRequirements metaRequirements) {
		this.metaRequirements = metaRequirements;
	}

	public UnitProvides getProvides() {
		return this.provides;
	}

	public void setProvides(UnitProvides provides) {
		this.provides = provides;
	}

	public UnitFilter getFilter() {
		return this.filter;
	}

	public void setFilter(UnitFilter filter) {
		this.filter = filter;
	}

	public UnitRequires getRequires() {
		return this.requires;
	}

	public void setRequires(UnitRequires requires) {
		this.requires = requires;
	}

	public UnitArtifacts getArtifacts() {
		return this.artifacts;
	}

	public void setArtifacts(UnitArtifacts artifacts) {
		this.artifacts = artifacts;
	}

	public UnitTouchpoint getTouchpoint() {
		return this.touchpoint;
	}

	public void setTouchpoint(UnitTouchpoint touchpoint) {
		this.touchpoint = touchpoint;
	}

	public UnitTouchpointData getTouchpointData() {
		return this.touchpointData;
	}

	public void setTouchpointData(UnitTouchpointData touchpointData) {
		this.touchpointData = touchpointData;
	}

	public UnitLicenses getLicenses() {
		return this.licenses;
	}

	public void setLicenses(UnitLicenses licenses) {
		this.licenses = licenses;
	}

	public UnitCopyright getCopyright() {
		return this.copyright;
	}

	public void setCopyright(UnitCopyright copyright) {
		this.copyright = copyright;
	}

	@Override
	public String toString() {
		return "RepositoryUnit [id=" + id + ", version=" + version + "]";
	}

}
