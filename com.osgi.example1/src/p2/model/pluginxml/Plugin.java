package p2.model.pluginxml;

import java.util.ArrayList;
import java.util.List;

import p2.model.resource.DOMElement;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class Plugin extends DOMElement {

	protected List<PluginExtension> extensions;

	public List<PluginExtension> getExtensions() {
		if (this.extensions == null) {
			this.extensions = new ArrayList<PluginExtension>();
		}
		return this.extensions;
	}

	public void setExtensions(List<PluginExtension> extensions) {
		this.extensions = extensions;
	}

	/**
	 * 
	 * @param extensionPoint
	 * @return
	 */
	public List<PluginExtension> getExtensions(String extensionPoint) {
		List<PluginExtension> result = new ArrayList<PluginExtension>();
		if (extensionPoint != null) {
			for (PluginExtension extension : this.extensions) {
				if (extensionPoint.equals(extension.getExtensionPoint())) {
					result.add(extension);
				}
			}
		}
		return result;
	}

}
