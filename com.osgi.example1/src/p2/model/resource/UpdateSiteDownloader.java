package p2.model.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import p2.model.compositerepository.CompositeRepository;
import p2.model.compositerepository.CompositeRepositoryChild;
import p2.model.compositerepository.CompositeRepositoryChildren;
import p2.model.util.DateUtil;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class UpdateSiteDownloader {

	protected String host = "http://update.tibco.com";
	protected String baseContextPath = "/eclipse/bw/6.5";
	protected File rootFolder;

	/**
	 * 
	 * @param host
	 * @param baseContextPath
	 * @param rootFolder
	 */
	public UpdateSiteDownloader(String host, String baseContextPath, File rootFolder) {
		this.host = host;
		this.baseContextPath = baseContextPath;
		this.rootFolder = rootFolder;
	}

	public void download() throws IOException {
		long t1 = System.currentTimeMillis();

		URL compositeContentUrl = null;
		try {
			compositeContentUrl = new URL(this.host + this.baseContextPath + "/compositeContent.xml");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return;
		}

		CompositeRepositoryReader reader = new CompositeRepositoryReader();
		reader.read(compositeContentUrl);
		CompositeRepositoryResource compositeContentResource = reader.getResource();
		if (compositeContentResource == null) {
			return;
		}
		File compositeContentFile = new File(this.rootFolder, "compositeContent.xml");
		System.out.println("--- download [" + compositeContentFile.getName() + "] starts ---");
		download(compositeContentUrl, compositeContentFile);
		long t2 = System.currentTimeMillis();

		long compositeContentTime = t2 - t1;
		String compositeContentTimeStr = DateUtil.printDuration(compositeContentTime);

		System.out.println("--- download [" + compositeContentFile.getName() + "] ends ---");
		System.out.println("time taken: " + compositeContentTimeStr);
		System.out.println();

		CompositeRepository repo = compositeContentResource.getRepository();
		if (repo != null) {
			CompositeRepositoryChildren pluginsObj = repo.getPlugins();
			if (pluginsObj != null) {
				List<CompositeRepositoryChild> plugins = pluginsObj.getChildren();
				int size = plugins.size();
				int i = 0;
				for (CompositeRepositoryChild plugin : plugins) {
					if (i < 76) {
						i++;
						continue;
					}

					String numToAll = "(" + (i + 1) + "/" + size + ")";

					System.out.println("--- download plugin [" + plugin.getLocation() + " " + numToAll + "] starts ---");
					long t21 = System.currentTimeMillis();
					downloadPluginDirectory(plugin.getLocation(), true);
					long t22 = System.currentTimeMillis();

					long pluginTime = t22 - t21;
					String pluginTimeStr = DateUtil.printDuration(pluginTime);

					System.out.println("--- download plugin [" + plugin.getLocation() + " " + numToAll + "] ends ---");
					System.out.println("time taken: " + pluginTimeStr);
					System.out.println();

					i++;
				}
			}
		}

		long t3 = System.currentTimeMillis();

		long totalTime = t3 - t1;
		String totalTimeStr = DateUtil.printDuration(totalTime);
		System.out.println("Total time: " + totalTimeStr);
	}

	/**
	 * 
	 * @param location
	 * @param clearPluginFolder
	 */
	protected void downloadPluginDirectory(String location, boolean clearPluginFolder) {
		File pluginFolder = new File(this.rootFolder, location);

		if (clearPluginFolder) {
			if (pluginFolder.exists()) {
				try {
					FileUtils.deleteDirectory(pluginFolder);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (!pluginFolder.exists()) {
			pluginFolder.mkdirs();
		}

		String directoryContextPath = this.baseContextPath + "/" + location + "/";
		String directoryFullPath = this.host + directoryContextPath;

		Elements hrefElements = null;
		try {
			org.jsoup.nodes.Document doc = Jsoup.connect(directoryFullPath).get();
			hrefElements = doc.getElementsByTag("a");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (hrefElements != null) {
			for (org.jsoup.nodes.Element hrefElement : hrefElements) {
				String href = hrefElement.attr("href");
				String name = hrefElement.text();

				if (href.startsWith(directoryContextPath)) {
					System.out.println(href);
					if (name.endsWith("/")) {
						// directory list
						downloadPluginSubDirectory(location, name, true);

					} else {
						// download file to plugin folder
						String fileFullPath = directoryFullPath + name;
						File file = new File(pluginFolder, name);
						download(fileFullPath, file);
					}

				} else {
					System.out.println(href + " (ignored)");
				}
			}
		}
	}

	/**
	 * 
	 * @param location
	 * @param subDirLocation
	 * @param clearPluginSubFolder
	 */
	protected void downloadPluginSubDirectory(String location, String subDirLocation, boolean clearPluginSubFolder) {
		File pluginSubFolder = new File(this.rootFolder, location + "/" + subDirLocation);

		if (clearPluginSubFolder) {
			if (pluginSubFolder.exists()) {
				try {
					FileUtils.deleteDirectory(pluginSubFolder);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (!pluginSubFolder.exists()) {
			pluginSubFolder.mkdirs();
		}

		String subDirectoryContextPath = this.baseContextPath + "/" + location + "/" + subDirLocation;
		String subDirectoryFullPath = this.host + subDirectoryContextPath;

		Elements hrefElements = null;
		try {
			org.jsoup.nodes.Document doc = Jsoup.connect(subDirectoryFullPath).get();
			hrefElements = doc.getElementsByTag("a");
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		if (hrefElements != null) {
			for (org.jsoup.nodes.Element hrefElement : hrefElements) {
				String href = hrefElement.attr("href");
				String name = hrefElement.text();

				if (href.startsWith(subDirectoryContextPath)) {
					System.out.println("    " + href);
					if (name.endsWith("/")) {
						// directory list
						downloadPluginSubDirectory(location + "/" + subDirLocation, name, false);

					} else {
						// download file to plugin folder
						String fileFullPath = subDirectoryFullPath + name;
						File file = new File(pluginSubFolder, name);
						download(fileFullPath, file);
					}
				} else {
					System.out.println("    " + href + " (ignored)");
				}
			}
		}
	}

	/**
	 * 
	 * @param fileFullPath
	 * @param file
	 */
	protected void download(URL fileURL, File file) {
		InputStream inputStream = null;
		try {
			inputStream = fileURL.openStream();

			if (file.exists()) {
				file.delete();
			}
			Files.copy(inputStream, file.toPath());

		} catch (IOException e) {
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
	 * @param fileFullPath
	 * @param file
	 */
	protected void download(String fileFullPath, File file) {
		InputStream inputStream = null;
		try {
			URL fileURL = new URL(fileFullPath);
			inputStream = fileURL.openStream();

			if (file.exists()) {
				file.delete();
			}
			Files.copy(inputStream, file.toPath());

		} catch (IOException e) {
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

}
