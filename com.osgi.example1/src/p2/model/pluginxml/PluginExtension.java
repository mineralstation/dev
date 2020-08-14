package p2.model.pluginxml;

import p2.model.resource.DOMElement;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class PluginExtension extends DOMElement {

	public static String EXTENSION_POINT__DESIGN_PALETTE = "com.tibco.bw.design.Palette";
	public static String EXTENSION_POINT__DESIGN_ACTIVITY_TYPE = "com.tibco.bw.design.ActivityType";

	protected String extensionPoint;

	public String getExtensionPoint() {
		return this.extensionPoint;
	}

	public void setExtensionPoint(String extensionPoint) {
		this.extensionPoint = extensionPoint;
	}

	@Override
	public String toString() {
		return "PluginExtension [extensionPoint=" + extensionPoint + "]";
	}

}
