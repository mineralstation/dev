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

import p2.model.compositerepository.CompositeRepository;
import p2.model.compositerepository.CompositeRepositoryChild;
import p2.model.compositerepository.CompositeRepositoryChildren;
import p2.model.compositerepository.CompositeRepositoryProperties;
import p2.model.compositerepository.CompositeRepositoryProperty;
import p2.model.util.DOMUtil;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class CompositeRepositoryReader {

	protected DocumentBuilder domBuilder;
	protected DOMParser domParser;
	protected Document doc;
	protected CompositeRepositoryResource resource;

	public CompositeRepositoryReader() {
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

	public CompositeRepositoryResource getResource() {
		return this.resource;
	}

	/**
	 * 
	 * @param document
	 * @return
	 */
	protected CompositeRepositoryResource doc2Resource(Document document) {
		if (document == null) {
			return null;
		}
		CompositeRepositoryResource resource = new CompositeRepositoryResource();
		resource.setDocument(document);

		Element repositoryElement = document.getDocumentElement();
		CompositeRepository repository = xml2Repository(repositoryElement);
		resource.setRepository(repository);

		return resource;
	}

	/**
	 * 
	 * @param repositoryElement
	 * @return
	 */
	protected CompositeRepository xml2Repository(Element repositoryElement) {
		if (repositoryElement == null) {
			return null;
		}

		CompositeRepository repository = new CompositeRepository();
		repository.setElement(repositoryElement);

		if (repositoryElement.hasAttribute("name")) {
			repository.setName(repositoryElement.getAttribute("name"));
		}
		if (repositoryElement.hasAttribute("type")) {
			repository.setType(repositoryElement.getAttribute("type"));
		}
		if (repositoryElement.hasAttribute("version")) {
			repository.setVersion(repositoryElement.getAttribute("version"));
		}

		Element propertiesElement = DOMUtil.getChildElementByLocalName(repositoryElement, "properties");
		if (propertiesElement != null) {
			CompositeRepositoryProperties properties = xml2Properties(propertiesElement);
			repository.setProperties(properties);
		}

		Element childrenElement = DOMUtil.getChildElementByLocalName(repositoryElement, "children");
		if (childrenElement != null) {
			CompositeRepositoryChildren plugins = xml2Children(childrenElement);
			repository.setPlugins(plugins);
		}

		return repository;
	}

	/**
	 * 
	 * @param propertiesElement
	 * @return
	 */
	protected CompositeRepositoryProperties xml2Properties(Element propertiesElement) {
		if (propertiesElement == null) {
			return null;
		}

		CompositeRepositoryProperties propertiesObj = new CompositeRepositoryProperties();
		propertiesObj.setElement(propertiesElement);

		if (propertiesElement.hasAttribute("size")) {
			try {
				String sizeStr = propertiesElement.getAttribute("size");
				int size = Integer.parseInt(sizeStr);
				propertiesObj.setSize(size);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<CompositeRepositoryProperty> properties = new ArrayList<CompositeRepositoryProperty>();
		List<Element> elements = DOMUtil.getChildrenElementsByLocalName(propertiesElement, "property");
		for (Element element : elements) {
			CompositeRepositoryProperty property = xml2Property(element);
			if (property != null) {
				properties.add(property);
			}
		}
		propertiesObj.setChildren(properties);

		return propertiesObj;
	}

	/**
	 * 
	 * @param propertyElement
	 * @return
	 */
	protected CompositeRepositoryProperty xml2Property(Element propertyElement) {
		if (propertyElement == null) {
			return null;
		}

		CompositeRepositoryProperty property = new CompositeRepositoryProperty();
		property.setElement(propertyElement);

		if (propertyElement.hasAttribute("name")) {
			property.setName(propertyElement.getAttribute("name"));
		}
		if (propertyElement.hasAttribute("value")) {
			property.setValue(propertyElement.getAttribute("value"));
		}

		return property;
	}

	/**
	 * 
	 * @param pluginsElement
	 * @return
	 */
	protected CompositeRepositoryChildren xml2Children(Element pluginsElement) {
		if (pluginsElement == null) {
			return null;
		}

		CompositeRepositoryChildren childrenObj = new CompositeRepositoryChildren();
		childrenObj.setElement(pluginsElement);

		if (pluginsElement.hasAttribute("size")) {
			try {
				String sizeStr = pluginsElement.getAttribute("size");
				int size = Integer.parseInt(sizeStr);
				childrenObj.setSize(size);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<CompositeRepositoryChild> children = new ArrayList<CompositeRepositoryChild>();
		List<Element> elements = DOMUtil.getChildrenElementsByLocalName(pluginsElement, "child");
		for (Element element : elements) {
			CompositeRepositoryChild child = xml2Child(element);
			if (child != null) {
				children.add(child);
			}
		}
		childrenObj.setChildren(children);

		return childrenObj;
	}

	/**
	 * 
	 * @param pluginElement
	 * @return
	 */
	protected CompositeRepositoryChild xml2Child(Element pluginElement) {
		if (pluginElement == null) {
			return null;
		}

		CompositeRepositoryChild plugin = new CompositeRepositoryChild();
		plugin.setElement(pluginElement);

		if (pluginElement.hasAttribute("location")) {
			plugin.setLocation(pluginElement.getAttribute("location"));
		}

		return plugin;
	}

}
