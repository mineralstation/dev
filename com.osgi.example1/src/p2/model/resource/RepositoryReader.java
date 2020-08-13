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

import p2.model.repository.Repository;
import p2.model.repository.RepositoryProperties;
import p2.model.repository.RepositoryProperty;
import p2.model.repository.RepositoryUnit;
import p2.model.repository.RepositoryUnits;
import p2.model.repository.UnitArtifact;
import p2.model.repository.UnitArtifacts;
import p2.model.repository.UnitCopyright;
import p2.model.repository.UnitFilter;
import p2.model.repository.UnitInstruction;
import p2.model.repository.UnitInstructions;
import p2.model.repository.UnitLicense;
import p2.model.repository.UnitLicenses;
import p2.model.repository.UnitMetaRequirements;
import p2.model.repository.UnitProperties;
import p2.model.repository.UnitProperty;
import p2.model.repository.UnitProvided;
import p2.model.repository.UnitProvides;
import p2.model.repository.UnitRequired;
import p2.model.repository.UnitRequires;
import p2.model.repository.UnitTouchpoint;
import p2.model.repository.UnitTouchpointData;
import p2.model.repository.UnitUpdate;
import p2.model.util.DOMUtil;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class RepositoryReader {

	protected DocumentBuilder domBuilder;
	protected DOMParser domParser;
	protected Document doc;
	protected RepositoryResource resource;

	public RepositoryReader() {
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

	public RepositoryResource getResource() {
		return this.resource;
	}

	/**
	 * 
	 * @param document
	 * @return
	 */
	protected RepositoryResource doc2Resource(Document document) {
		if (document == null) {
			return null;
		}
		RepositoryResource resource = new RepositoryResource();
		resource.setDocument(document);

		Element repositoryElement = document.getDocumentElement();
		Repository repository = xml2Repository(repositoryElement);
		resource.setRepository(repository);

		return resource;
	}

	protected Repository xml2Repository(Element repositoryElement) {
		if (repositoryElement == null) {
			return null;
		}

		Repository repository = new Repository();
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
			RepositoryProperties properties = xml2Properties(propertiesElement);
			repository.setProperties(properties);
		}

		Element unitsElement = DOMUtil.getChildElementByLocalName(repositoryElement, "units");
		if (unitsElement != null) {
			RepositoryUnits units = xml2Units(unitsElement);
			repository.setUnits(units);
		}

		return repository;
	}

	/**
	 * 
	 * @param propertiesElement
	 * @return
	 */
	protected RepositoryProperties xml2Properties(Element propertiesElement) {
		if (propertiesElement == null) {
			return null;
		}

		RepositoryProperties propertiesObj = new RepositoryProperties();
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

		List<RepositoryProperty> properties = new ArrayList<RepositoryProperty>();
		List<Element> elements = DOMUtil.getChildElementsByLocalName(propertiesElement, "property");
		for (Element element : elements) {
			RepositoryProperty property = xml2Property(element);
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
	protected RepositoryProperty xml2Property(Element propertyElement) {
		if (propertyElement == null) {
			return null;
		}

		RepositoryProperty property = new RepositoryProperty();
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
	 * @param unitsElement
	 * @return
	 */
	protected RepositoryUnits xml2Units(Element unitsElement) {
		if (unitsElement == null) {
			return null;
		}

		RepositoryUnits unitsObj = new RepositoryUnits();
		unitsObj.setElement(unitsElement);

		if (unitsElement.hasAttribute("size")) {
			try {
				String sizeStr = unitsElement.getAttribute("size");
				int size = Integer.parseInt(sizeStr);
				unitsObj.setSize(size);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<RepositoryUnit> units = new ArrayList<RepositoryUnit>();
		List<Element> elements = DOMUtil.getChildElementsByLocalName(unitsElement, "unit");
		for (Element element : elements) {
			RepositoryUnit unit = xml2Unit(element);
			if (unit != null) {
				units.add(unit);
			}
		}
		unitsObj.setChildren(units);

		return unitsObj;
	}

	/**
	 * 
	 * @param unitElement
	 * @return
	 */
	protected RepositoryUnit xml2Unit(Element unitElement) {
		if (unitElement == null) {
			return null;
		}

		RepositoryUnit unit = new RepositoryUnit();
		unit.setElement(unitElement);

		if (unitElement.hasAttribute("id")) {
			unit.setId(unitElement.getAttribute("id"));
		}
		if (unitElement.hasAttribute("version")) {
			unit.setVersion(unitElement.getAttribute("version"));
		}

		// update
		Element updateElement = DOMUtil.getChildElementByLocalName(unitElement, "update");
		if (updateElement != null) {
			UnitUpdate update = xml2UnitUpdate(updateElement);
			unit.setUpdate(update);
		}

		// properties
		Element propertiesElement = DOMUtil.getChildElementByLocalName(unitElement, "properties");
		if (propertiesElement != null) {
			UnitProperties properties = xml2UnitProperties(propertiesElement);
			unit.setProperties(properties);
		}

		// metaRequirements
		Element metaRequirementsElement = DOMUtil.getChildElementByLocalName(unitElement, "metaRequirements");
		if (metaRequirementsElement != null) {
			UnitMetaRequirements metaRequirements = xml2UnitMetaRequirements(metaRequirementsElement);
			unit.setMetaRequirements(metaRequirements);
		}

		// provides
		Element providesElement = DOMUtil.getChildElementByLocalName(unitElement, "provides");
		if (providesElement != null) {
			UnitProvides provides = xml2UnitProvides(providesElement);
			unit.setProvides(provides);
		}

		// filter
		Element filterElement = DOMUtil.getChildElementByLocalName(unitElement, "filter");
		if (filterElement != null) {
			UnitFilter filter = xml2UnitFilter(filterElement);
			unit.setFilter(filter);
		}

		// requires
		Element requiresElement = DOMUtil.getChildElementByLocalName(unitElement, "requires");
		if (requiresElement != null) {
			UnitRequires requires = xml2UnitRequires(requiresElement);
			unit.setRequires(requires);
		}

		// artifacts
		Element artifactsElement = DOMUtil.getChildElementByLocalName(unitElement, "artifacts");
		if (artifactsElement != null) {
			UnitArtifacts artifacts = xml2UnitArtifacts(artifactsElement);
			unit.setArtifacts(artifacts);
		}

		// touchpoint
		Element touchpointElement = DOMUtil.getChildElementByLocalName(unitElement, "touchpoint");
		if (touchpointElement != null) {
			UnitTouchpoint touchpoint = xml2UnitTouchpoint(touchpointElement);
			unit.setTouchpoint(touchpoint);
		}

		// touchpointData
		Element touchpointDataElement = DOMUtil.getChildElementByLocalName(unitElement, "touchpointData");
		if (touchpointDataElement != null) {
			UnitTouchpointData touchpointData = xml2UnitTouchpointData(touchpointDataElement);
			unit.setTouchpointData(touchpointData);
		}

		// licenses
		Element licensesElement = DOMUtil.getChildElementByLocalName(unitElement, "licenses");
		if (licensesElement != null) {
			UnitLicenses licenses = xml2UnitLicenses(licensesElement);
			unit.setLicenses(licenses);
		}

		// copyright
		Element copyrightElement = DOMUtil.getChildElementByLocalName(unitElement, "copyright");
		if (copyrightElement != null) {
			UnitCopyright copyright = xml2UnitCopyright(copyrightElement);
			unit.setCopyright(copyright);
		}

		return unit;
	}

	/**
	 * 
	 * @param updateElement
	 * @return
	 */
	protected UnitUpdate xml2UnitUpdate(Element updateElement) {
		if (updateElement == null) {
			return null;
		}

		UnitUpdate update = new UnitUpdate();
		update.setElement(updateElement);

		if (updateElement.hasAttribute("id")) {
			update.setId(updateElement.getAttribute("id"));
		}
		if (updateElement.hasAttribute("range")) {
			update.setRange(updateElement.getAttribute("range"));
		}
		if (updateElement.hasAttribute("severity")) {
			update.setSeverity(updateElement.getAttribute("severity"));
		}

		return update;
	}

	/**
	 * 
	 * @param propertiesElement
	 * @return
	 */
	protected UnitProperties xml2UnitProperties(Element propertiesElement) {
		if (propertiesElement == null) {
			return null;
		}

		UnitProperties propertiesObj = new UnitProperties();
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

		List<UnitProperty> properties = new ArrayList<UnitProperty>();
		List<Element> elements = DOMUtil.getChildElementsByLocalName(propertiesElement, "property");
		for (Element element : elements) {
			UnitProperty property = xml2UnitProperty(element);
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
	protected UnitProperty xml2UnitProperty(Element propertyElement) {
		if (propertyElement == null) {
			return null;
		}

		UnitProperty property = new UnitProperty();
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
	 * @param metaRequirementsElement
	 * @return
	 */
	protected UnitMetaRequirements xml2UnitMetaRequirements(Element metaRequirementsElement) {
		if (metaRequirementsElement == null) {
			return null;
		}

		UnitMetaRequirements metaRequireObj = new UnitMetaRequirements();
		metaRequireObj.setElement(metaRequirementsElement);

		if (metaRequirementsElement.hasAttribute("size")) {
			try {
				String sizeStr = metaRequirementsElement.getAttribute("size");
				int size = Integer.parseInt(sizeStr);
				metaRequireObj.setSize(size);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<UnitRequired> requires = new ArrayList<UnitRequired>();
		List<Element> elements = DOMUtil.getChildElementsByLocalName(metaRequirementsElement, "required");
		for (Element element : elements) {
			UnitRequired required = xml2UnitRequired(element);
			if (required != null) {
				requires.add(required);
			}
		}
		metaRequireObj.setChildren(requires);

		return metaRequireObj;
	}

	/**
	 * 
	 * @param requiredElement
	 * @return
	 */
	protected UnitRequired xml2UnitRequired(Element requiredElement) {
		if (requiredElement == null) {
			return null;
		}

		UnitRequired required = new UnitRequired();
		required.setElement(requiredElement);

		if (requiredElement.hasAttribute("namespace")) {
			required.setNamespace(requiredElement.getAttribute("namespace"));
		}
		if (requiredElement.hasAttribute("name")) {
			required.setName(requiredElement.getAttribute("name"));
		}
		if (requiredElement.hasAttribute("range")) {
			required.setRange(requiredElement.getAttribute("range"));
		}
		if (requiredElement.hasAttribute("optional")) {
			try {
				String optionalStr = requiredElement.getAttribute("optional");
				boolean optional = Boolean.parseBoolean(optionalStr);
				required.setOptional(optional);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return required;
	}

	/**
	 * 
	 * @param providesElement
	 * @return
	 */
	protected UnitProvides xml2UnitProvides(Element providesElement) {
		if (providesElement == null) {
			return null;
		}

		UnitProvides providesObj = new UnitProvides();
		providesObj.setElement(providesElement);

		if (providesElement.hasAttribute("size")) {
			try {
				String sizeStr = providesElement.getAttribute("size");
				int size = Integer.parseInt(sizeStr);
				providesObj.setSize(size);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<UnitProvided> provides = new ArrayList<UnitProvided>();
		List<Element> elements = DOMUtil.getChildElementsByLocalName(providesElement, "provided");
		for (Element element : elements) {
			UnitProvided provided = xml2UnitProvided(element);
			if (provided != null) {
				provides.add(provided);
			}
		}
		providesObj.setChildren(provides);

		return providesObj;
	}

	/**
	 * 
	 * @param providedElement
	 * @return
	 */
	protected UnitProvided xml2UnitProvided(Element providedElement) {
		if (providedElement == null) {
			return null;
		}

		UnitProvided provided = new UnitProvided();
		provided.setElement(providedElement);

		if (providedElement.hasAttribute("namespace")) {
			provided.setNamespace(providedElement.getAttribute("namespace"));
		}
		if (providedElement.hasAttribute("name")) {
			provided.setName(providedElement.getAttribute("name"));
		}
		if (providedElement.hasAttribute("version")) {
			provided.setVersion(providedElement.getAttribute("version"));
		}

		return provided;

	}

	/**
	 * 
	 * @param filterElement
	 * @return
	 */
	protected UnitFilter xml2UnitFilter(Element filterElement) {
		if (filterElement == null) {
			return null;
		}

		UnitFilter filter = new UnitFilter();
		filter.setElement(filterElement);

		String content = DOMUtil.getText(filterElement);
		filter.setContent(content);

		return filter;
	}

	/**
	 * 
	 * @param requiresElement
	 * @return
	 */
	protected UnitRequires xml2UnitRequires(Element requiresElement) {
		if (requiresElement == null) {
			return null;
		}

		UnitRequires requiresObj = new UnitRequires();
		requiresObj.setElement(requiresElement);

		if (requiresElement.hasAttribute("size")) {
			try {
				String sizeStr = requiresElement.getAttribute("size");
				int size = Integer.parseInt(sizeStr);
				requiresObj.setSize(size);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<UnitRequired> requires = new ArrayList<UnitRequired>();
		List<Element> elements = DOMUtil.getChildElementsByLocalName(requiresElement, "required");
		for (Element element : elements) {
			UnitRequired required = xml2UnitRequired(element);
			if (required != null) {
				requires.add(required);
			}
		}
		requiresObj.setChildren(requires);

		return requiresObj;
	}

	/**
	 * 
	 * @param artifactsElement
	 * @return
	 */
	protected UnitArtifacts xml2UnitArtifacts(Element artifactsElement) {
		if (artifactsElement == null) {
			return null;
		}

		UnitArtifacts artifactsObj = new UnitArtifacts();
		artifactsObj.setElement(artifactsElement);

		if (artifactsElement.hasAttribute("size")) {
			try {
				String sizeStr = artifactsElement.getAttribute("size");
				int size = Integer.parseInt(sizeStr);
				artifactsObj.setSize(size);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<UnitArtifact> requires = new ArrayList<UnitArtifact>();
		List<Element> elements = DOMUtil.getChildElementsByLocalName(artifactsElement, "artifact");
		for (Element element : elements) {
			UnitArtifact artifact = xml2UnitArtifact(element);
			if (artifact != null) {
				requires.add(artifact);
			}
		}
		artifactsObj.setChildren(requires);

		return artifactsObj;
	}

	/**
	 * 
	 * @param artifactElement
	 * @return
	 */
	protected UnitArtifact xml2UnitArtifact(Element artifactElement) {
		if (artifactElement == null) {
			return null;
		}

		UnitArtifact artifact = new UnitArtifact();
		artifact.setElement(artifactElement);

		if (artifactElement.hasAttribute("classifier")) {
			artifact.setClassifier(artifactElement.getAttribute("classifier"));
		}
		if (artifactElement.hasAttribute("id")) {
			artifact.setId(artifactElement.getAttribute("id"));
		}
		if (artifactElement.hasAttribute("version")) {
			artifact.setVersion(artifactElement.getAttribute("version"));
		}

		return artifact;
	}

	/**
	 * 
	 * @param touchpointElement
	 * @return
	 */
	protected UnitTouchpoint xml2UnitTouchpoint(Element touchpointElement) {
		if (touchpointElement == null) {
			return null;
		}

		UnitTouchpoint touchpoint = new UnitTouchpoint();
		touchpoint.setElement(touchpointElement);

		if (touchpointElement.hasAttribute("id")) {
			touchpoint.setId(touchpointElement.getAttribute("id"));
		}
		if (touchpointElement.hasAttribute("version")) {
			touchpoint.setVersion(touchpointElement.getAttribute("version"));
		}

		return touchpoint;
	}

	/**
	 * 
	 * @param touchpointDataElement
	 * @return
	 */
	protected UnitTouchpointData xml2UnitTouchpointData(Element touchpointDataElement) {
		if (touchpointDataElement == null) {
			return null;
		}

		UnitTouchpointData touchpointData = new UnitTouchpointData();
		touchpointData.setElement(touchpointDataElement);

		if (touchpointDataElement.hasAttribute("size")) {
			try {
				String sizeStr = touchpointDataElement.getAttribute("size");
				int size = Integer.parseInt(sizeStr);
				touchpointData.setSize(size);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Element instructionsElement = DOMUtil.getChildElementByLocalName(touchpointDataElement, "instructions");
		if (instructionsElement != null) {
			UnitInstructions instructions = xml2UnitInstructions(instructionsElement);
			touchpointData.setInstructions(instructions);
		}

		return touchpointData;
	}

	/*-
	 Element element = DOMUtil.getChildElementByLocalName(unitElement, "properties");
	 List<Element> elements = DOMUtil.getChildElementsByLocalName(unitElement, "property");
	 */

	/**
	 * 
	 * @param instructionsElement
	 * @return
	 */
	protected UnitInstructions xml2UnitInstructions(Element instructionsElement) {
		if (instructionsElement == null) {
			return null;
		}

		UnitInstructions instructionsObj = new UnitInstructions();
		instructionsObj.setElement(instructionsElement);

		if (instructionsElement.hasAttribute("size")) {
			try {
				String sizeStr = instructionsElement.getAttribute("size");
				int size = Integer.parseInt(sizeStr);
				instructionsObj.setSize(size);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<UnitInstruction> instructions = new ArrayList<UnitInstruction>();
		List<Element> elements = DOMUtil.getChildElementsByLocalName(instructionsElement, "instruction");
		for (Element element : elements) {
			UnitInstruction instruction = xml2UnitInstruction(element);
			if (instruction != null) {
				instructions.add(instruction);
			}
		}
		instructionsObj.setChildren(instructions);

		return instructionsObj;
	}

	/**
	 * 
	 * @param instructionElement
	 * @return
	 */
	protected UnitInstruction xml2UnitInstruction(Element instructionElement) {
		if (instructionElement == null) {
			return null;
		}

		UnitInstruction instruction = new UnitInstruction();
		instruction.setElement(instructionElement);

		if (instructionElement.hasAttribute("key")) {
			instruction.setKey(instructionElement.getAttribute("key"));
		}

		String content = DOMUtil.getText(instructionElement);
		instruction.setContent(content);

		return instruction;
	}

	/**
	 * 
	 * @param licensesElement
	 * @return
	 */
	protected UnitLicenses xml2UnitLicenses(Element licensesElement) {
		if (licensesElement == null) {
			return null;
		}

		UnitLicenses licensesObj = new UnitLicenses();
		licensesObj.setElement(licensesElement);

		if (licensesElement.hasAttribute("size")) {
			try {
				String sizeStr = licensesElement.getAttribute("size");
				int size = Integer.parseInt(sizeStr);
				licensesObj.setSize(size);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		List<UnitLicense> licenses = new ArrayList<UnitLicense>();
		List<Element> elements = DOMUtil.getChildElementsByLocalName(licensesElement, "license");
		for (Element element : elements) {
			UnitLicense license = xml2UnitLicense(element);
			if (license != null) {
				licenses.add(license);
			}
		}
		licensesObj.setChildren(licenses);

		return licensesObj;
	}

	/**
	 * 
	 * @param licenseElement
	 * @return
	 */
	protected UnitLicense xml2UnitLicense(Element licenseElement) {
		if (licenseElement == null) {
			return null;
		}

		UnitLicense license = new UnitLicense();
		license.setElement(licenseElement);

		if (licenseElement.hasAttribute("uri")) {
			license.setUri(licenseElement.getAttribute("uri"));
		}
		if (licenseElement.hasAttribute("url")) {
			license.setUrl(licenseElement.getAttribute("url"));
		}

		String content = DOMUtil.getText(licenseElement);
		license.setContent(content);

		return license;
	}

	/**
	 * 
	 * @param copyrightElement
	 * @return
	 */
	protected UnitCopyright xml2UnitCopyright(Element copyrightElement) {
		if (copyrightElement == null) {
			return null;
		}

		UnitCopyright copyright = new UnitCopyright();
		copyright.setElement(copyrightElement);

		String content = DOMUtil.getText(copyrightElement);
		copyright.setContent(content);

		return copyright;
	}

}
