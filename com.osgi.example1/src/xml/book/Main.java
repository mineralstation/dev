package xml.book;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.origin.common.util.SystemUtils;

import xml.utils.JAXBUtil;

/**
 * https://stackoverflow.com/questions/12147428/creating-an-xml-file-from-xsd-from-jaxb
 *
 */
public class Main {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ObjectFactory factory = new ObjectFactory();

		BookType bookType = factory.createBookType();
		bookType.setISBN("0001");
		bookType.setTitle("Science-0001");
		bookType.setAuthor("Author-0001");
		bookType.setPublisher("Pub-0001");

		JAXBElement<BookType> rootElement = factory.createBook(bookType);

		try {
			File xmlDir = new File(SystemUtils.getUserDir(), "xml");
			File xmlFile = new File(xmlDir, "book_0001.xml");

			JAXBUtil.generateXml("xml.book", true, BookNamespaceMapper.INSTANCE, rootElement, xmlFile);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
