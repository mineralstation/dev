package p2.model.test;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import p2.model.compositerepository.CompositeRepositoryChild;
import p2.model.resource.CompositeRepositoryReader;
import p2.model.resource.CompositeRepositoryResource;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class ParseCompositeRepositoryTest {

	public static void main(String[] args) {
		try {
			testDeserialize1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testDeserialize1() throws Exception {
		String host = "http://update.tibco.com";
		String baseContextPath = "/eclipse/bw/6.5";
		URL compositeContentUrl = new URL(host + baseContextPath + "/compositeContent.xml");

		CompositeRepositoryReader reader = new CompositeRepositoryReader();
		reader.read(compositeContentUrl);

		CompositeRepositoryResource resource = reader.getResource();
		if (resource != null) {
			List<CompositeRepositoryChild> children = resource.getRepository().getPlugins().getChildren();
			int i = 0;
			for (CompositeRepositoryChild child : children) {
				String location = child.getLocation();
				String repositoryFullPath = host + baseContextPath + "/" + location;

				System.out.println("child[" + i + "]: " + location + " - " + repositoryFullPath);
				i++;
			}
		}
	}

	public static void testDeserialize2() throws Exception {
		String host = "http://update.tibco.com";
		String baseContextPath = "/eclipse/bw/6.5";
		URL compositeContentUrl = new URL(host + baseContextPath + "/compositeContent.xml");

		CompositeRepositoryReader reader = new CompositeRepositoryReader();
		reader.read(compositeContentUrl);

		CompositeRepositoryResource resource = reader.getResource();
		// System.out.println("compositeContent = " + compositeContent);
		if (resource != null) {
			List<CompositeRepositoryChild> plugins = resource.getRepository().getPlugins().getChildren();
			/*-
			int i = 0;
			for (RepositoryPlugin plugin : plugins) {
				System.out.println("location[" + i + "]: " + plugin.getLocation());
				i++;
			}
			*/

			CompositeRepositoryChild plugin = plugins.get(0);
			String location = plugin.getLocation();
			String pluginContextPath = baseContextPath + "/" + location + "/";
			URL pluginUrl = new URL(host + pluginContextPath);

			String externalForm = pluginUrl.toExternalForm();
			File directory = new File(externalForm);
			String[] files = directory.list();

			Object content = pluginUrl.getContent();
			// InputStream inputStream = pluginUrl.openStream();
			// InputSource inputSource = new InputSource(inputStream);
			// inputSource.setPublicId(pluginUrl.toURI().toString());
			// inputSource.setSystemId(pluginUrl.toURI().toString());
			// DocumentBuilderFactory builderFactory = new DocumentBuilderFactoryImpl();
			// builderFactory.setNamespaceAware(true);
			// builderFactory.setValidating(false);
			// DocumentBuilder domBuilder = builderFactory.newDocumentBuilder();
			// Document doc = domBuilder.parse(inputSource);

			System.out.println("host = " + host);
			System.out.println("pluginUrl = " + pluginUrl);
			System.out.println("pluginContextPath = " + pluginContextPath);

			org.jsoup.nodes.Document doc = Jsoup.connect(host + pluginContextPath).get();
			Elements links = doc.getElementsByTag("a");
			System.out.println("----------------------------------------------------------------------");
			for (org.jsoup.nodes.Element link : links) {
				String href = link.attr("href");
				String name = link.text();

				// if (text.contains("Up To " + baseContextPath)) {
				// continue;
				// }
				if (href.startsWith(pluginContextPath)) {
					System.out.println(href + " - " + name);

					if (name.endsWith("/")) {
						// directory list
					} else {
						// download file
					}
				}
			}
			System.out.println("----------------------------------------------------------------------");

			System.out.println();
			System.out.println();
		}
	}

}
