package p2.model.json;

import java.io.File;

import p2.model.resource.PluginXmlResource;
import p2.model.resource.RepositoryResource;

public class PluginInfo {

	protected File rootFolder;
	protected File pluginFolder;
	protected RepositoryResource repositoryResource;
	protected PluginXmlResource pluginXmlResource;

	protected File iconFile;
	protected String iconPath;

	public PluginInfo() {
	}

	public File getRootFolder() {
		return this.rootFolder;
	}

	public void setRootFolder(File rootFolder) {
		this.rootFolder = rootFolder;
	}

	public File getPluginFolder() {
		return this.pluginFolder;
	}

	public void setPluginFolder(File pluginFolder) {
		this.pluginFolder = pluginFolder;
	}

	public RepositoryResource getRepositoryResource() {
		return this.repositoryResource;
	}

	public void setRepositoryResource(RepositoryResource repositoryResource) {
		this.repositoryResource = repositoryResource;
	}

	public PluginXmlResource getPluginXmlResource() {
		return this.pluginXmlResource;
	}

	public void setPluginXmlResource(PluginXmlResource pluginXmlResource) {
		this.pluginXmlResource = pluginXmlResource;
	}

	public File getIconFile() {
		return this.iconFile;
	}

	public void setIconFile(File iconFile) {
		this.iconFile = iconFile;
	}

	public String getIconPath() {
		return this.iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

}
