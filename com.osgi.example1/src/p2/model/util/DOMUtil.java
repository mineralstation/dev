package p2.model.util;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class DOMUtil {

	/**
	 * 
	 * @param parentElement
	 * @param localName
	 * @return
	 */
	public static Element getChildElementByLocalName(Element parentElement, String localName) {
		NodeList children = parentElement.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node childNode = children.item(i);
			if (localName.equals(childNode.getLocalName())) {
				return (Element) childNode;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param parentElement
	 * @param localName
	 * @return
	 */
	public static List<Element> getChildrenElementsByLocalName(Element parentElement, String localName) {
		List<Element> elements = new ArrayList<Element>();
		NodeList children = parentElement.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node node = children.item(i);
			if (localName.equals(node.getLocalName())) {
				elements.add((Element) node);
			}
		}
		return elements;
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	public static String getText(Node node) {
		StringBuilder sb = new StringBuilder(128);

		if (node instanceof Element) {
			node = ((Element) node).getFirstChild();
		}

		boolean bCData = false;

		while (node != null) {
			switch (node.getNodeType()) {
			case Node.TEXT_NODE:
				if (bCData) {
					break;
				}
				Text text = (Text) node;
				sb.append(text.getData());
				break;
			case Node.CDATA_SECTION_NODE:
				if (bCData == false) {
					sb.setLength(0);
					bCData = true;
				}
				CDATASection cdata = (CDATASection) node;
				sb.append(cdata.getData());
				break;
			}
			node = node.getNextSibling();
		}
		String data = sb.toString();
		if (isEmptyOrWhitespace(data)) {
			return null;
		}
		return data;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmptyOrWhitespace(String value) {
		if (value == null || value.length() == 0) {
			return true;
		}
		for (int i = 0, j = value.length(); i < j; i++) {
			if (!Character.isWhitespace(value.charAt(i))) {
				return false;
			}
		}
		return true;
	}

}
