package p2.model.resource;

import p2.model.pluginxml.Plugin;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class PluginXmlResource extends DOMDocument {

	protected Plugin plugin;

	public Plugin getPlugin() {
		return this.plugin;
	}

	public void setPlugin(Plugin plugin) {
		this.plugin = plugin;
	}

}
