package p2.model.resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xerces.jaxp.DocumentBuilderFactoryImpl;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import p2.model.pluginxml.DesignActivityTypeExtension;
import p2.model.pluginxml.DesignActivityTypeExtension.ActivityType;
import p2.model.pluginxml.DesignPaletteExtension;
import p2.model.pluginxml.Plugin;
import p2.model.pluginxml.PluginExtension;
import p2.model.util.DOMUtil;

public class PluginXmlReader {

	protected DocumentBuilder domBuilder;
	protected DOMParser domParser;
	protected Document doc;
	protected PluginXmlResource resource;

	public PluginXmlReader() {
		this.domParser = new DOMParser();
		try {
			DocumentBuilderFactory builderFactory = new DocumentBuilderFactoryImpl();
			builderFactory.setNamespaceAware(true);
			builderFactory.setValidating(false);
			this.domBuilder = builderFactory.newDocumentBuilder();

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param url
	 * @throws IOException
	 */
	public void read(URL url) throws IOException {
		InputStream inputStream = null;
		try {
			String resourceId = url.toURI().toString();
			inputStream = url.openStream();
			read(resourceId, inputStream);

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * @param resourceId
	 * @param inputStream
	 * @throws IOException
	 */
	public void read(String resourceId, InputStream inputStream) throws IOException {
		try {
			InputSource inputSource = new InputSource(inputStream);
			inputSource.setPublicId(resourceId);
			inputSource.setSystemId(resourceId);

			if (this.domBuilder != null) {
				this.doc = this.domBuilder.parse(inputSource);
			} else {
				this.domParser.parse(inputSource);
				this.doc = this.domParser.getDocument();
			}
		} catch (SAXException e) {
			e.printStackTrace();
		}

		this.resource = doc2Resource(this.doc);
	}

	public PluginXmlResource getResource() {
		return this.resource;
	}

	/**
	 * 
	 * @param document
	 * @return
	 */
	protected PluginXmlResource doc2Resource(Document document) {
		if (document == null) {
			return null;
		}
		PluginXmlResource resource = new PluginXmlResource();
		resource.setDocument(document);

		Element pluginElement = document.getDocumentElement();
		Plugin plugin = xml2Plugin(pluginElement);
		resource.setPlugin(plugin);

		return resource;
	}

	/**
	 * 
	 * @param pluginElement
	 * @return
	 */
	protected Plugin xml2Plugin(Element pluginElement) {
		if (pluginElement == null) {
			return null;
		}

		Plugin plugin = new Plugin();
		plugin.setElement(pluginElement);

		List<PluginExtension> extensions = new ArrayList<PluginExtension>();
		List<Element> elements = DOMUtil.getChildrenElementsByLocalName(pluginElement, "extension");
		for (Element element : elements) {
			PluginExtension extension = xml2PluginExtension(element);
			if (extension != null) {
				extensions.add(extension);
			}
		}
		plugin.setExtensions(extensions);

		return plugin;
	}

	/**
	 * 
	 * @param extensionElement
	 * @return
	 */
	protected PluginExtension xml2PluginExtension(Element extensionElement) {
		if (extensionElement == null) {
			return null;
		}

		PluginExtension extension = null;

		String point = null;
		if (extensionElement.hasAttribute("point")) {
			point = extensionElement.getAttribute("point");
		}

		if (PluginExtension.EXTENSION_POINT__DESIGN_PALETTE.equals(point)) {
			extension = xml2PluginExtensionDesignPalette(extensionElement);

		} else if (PluginExtension.EXTENSION_POINT__DESIGN_ACTIVITY_TYPE.equals(point)) {
			extension = xml2PluginExtensionDesignActivityType(extensionElement);
		}

		if (extension == null) {
			extension = new PluginExtension();
			extension.setElement(extensionElement);
			extension.setExtensionPoint(point);
		}

		return extension;
	}

	/**
	 * 
	 * @param extensionElement
	 * @return
	 */
	protected DesignPaletteExtension xml2PluginExtensionDesignPalette(Element extensionElement) {
		String point = null;
		if (extensionElement.hasAttribute("point")) {
			point = extensionElement.getAttribute("point");
		}

		DesignPaletteExtension designPaletteExtension = new DesignPaletteExtension();
		designPaletteExtension.setElement(extensionElement);
		designPaletteExtension.setExtensionPoint(point);

		Element paletteDrawerElement = DOMUtil.getChildElementByLocalName(extensionElement, "paletteDrawer");
		if (paletteDrawerElement != null) {
			if (paletteDrawerElement.hasAttribute("productID")) {
				designPaletteExtension.setProductID(paletteDrawerElement.getAttribute("productID"));
			}
			if (paletteDrawerElement.hasAttribute("paletteID")) {
				designPaletteExtension.setPaletteId(paletteDrawerElement.getAttribute("paletteID"));
			}
			if (paletteDrawerElement.hasAttribute("name")) {
				designPaletteExtension.setName(paletteDrawerElement.getAttribute("name"));
			}
			if (paletteDrawerElement.hasAttribute("tooltip")) {
				designPaletteExtension.setTooltip(paletteDrawerElement.getAttribute("tooltip"));
			}
			if (paletteDrawerElement.hasAttribute("priority")) {
				try {
					String priorityStr = paletteDrawerElement.getAttribute("priority");
					int priority = Integer.parseInt(priorityStr);
					designPaletteExtension.setPriority(priority);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (paletteDrawerElement.hasAttribute("expanded")) {
				try {
					String expandedStr = paletteDrawerElement.getAttribute("expanded");
					boolean expanded = Boolean.parseBoolean(expandedStr);
					designPaletteExtension.setExpanded(expanded);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (paletteDrawerElement.hasAttribute("smallIcon")) {
				designPaletteExtension.setSmallIcon(paletteDrawerElement.getAttribute("smallIcon"));
			}
			if (paletteDrawerElement.hasAttribute("largeIcon")) {
				designPaletteExtension.setLargeIcon(paletteDrawerElement.getAttribute("largeIcon"));
			}
		}

		return designPaletteExtension;
	}

	/**
	 * 
	 * @param extensionElement
	 * @return
	 */
	protected DesignActivityTypeExtension xml2PluginExtensionDesignActivityType(Element extensionElement) {
		String point = null;
		if (extensionElement.hasAttribute("point")) {
			point = extensionElement.getAttribute("point");
		}

		DesignActivityTypeExtension designActivityTypeExtension = new DesignActivityTypeExtension();
		designActivityTypeExtension.setElement(extensionElement);
		designActivityTypeExtension.setExtensionPoint(point);

		List<ActivityType> activityTypes = new ArrayList<ActivityType>();
		List<Element> activityTypeElements = DOMUtil.getChildrenElementsByLocalName(extensionElement, "activityType");
		for (Element activityTypeElement : activityTypeElements) {
			ActivityType activityType = new ActivityType();

			if (activityTypeElement.hasAttribute("activityTypeID")) {
				activityType.setActivityTypeID(activityTypeElement.getAttribute("activityTypeID"));
			}
			if (activityTypeElement.hasAttribute("name")) {
				activityType.setName(activityTypeElement.getAttribute("name"));
			}
			if (activityTypeElement.hasAttribute("tooltip")) {
				activityType.setTooltip(activityTypeElement.getAttribute("tooltip"));
			}
			if (activityTypeElement.hasAttribute("priority")) {
				try {
					String priorityStr = activityTypeElement.getAttribute("priority");
					int priority = Integer.parseInt(priorityStr);
					activityType.setPriority(priority);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (activityTypeElement.hasAttribute("visible")) {
				try {
					String visibleStr = activityTypeElement.getAttribute("visible");
					boolean visible = Boolean.parseBoolean(visibleStr);
					activityType.setVisible(visible);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			List<String> paletteIDs = new ArrayList<String>();
			List<Element> paletteElements = DOMUtil.getChildrenElementsByLocalName(activityTypeElement, "palette");
			for (Element paletteElement : paletteElements) {
				if (paletteElement.hasAttribute("paletteID")) {
					String paletteID = paletteElement.getAttribute("paletteID");
					if (paletteID != null) {
						paletteIDs.add(paletteID);
					}
				}
			}
			activityType.setPaletteIDs(paletteIDs);

			activityTypes.add(activityType);
		}
		designActivityTypeExtension.setActivityTypes(activityTypes);

		return designActivityTypeExtension;
	}

}
