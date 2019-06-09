package xml.events;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.origin.common.util.SystemUtils;

import xml.utils.JAXBUtil;

public class Main {

	/**
	 * 
	 * @param args
	 * @throws JAXBException
	 */
	public static void main(String[] args) throws JAXBException {
		ObjectFactory factory = new ObjectFactory();

		Events events = factory.createEvents();

		for (int i = 0; i < 10000; i++) {
			Event event = factory.createEvent();
			event.setId("id-" + i);
			event.setBookIsbn("isbn-" + i);
			event.setAuthorName("author-" + i);
			event.setDescription("description-" + i);
			event.setSummary("summary-" + i);
			event.setDate("date-" + i);

			events.getEvent().add(event);
		}

		JAXBElement<Events> rootElement = factory.createEvents(events);

		try {
			File xmlDir = new File(SystemUtils.getUserDir(), "xml");
			File xmlFile = new File(xmlDir, "events_0_9999.xml");

			JAXBUtil.generateXml("xml.events", true, EventsNamespaceMapper.INSTANCE, rootElement, xmlFile);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
