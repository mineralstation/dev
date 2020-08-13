package p2.model.test;

import java.io.File;
import java.io.IOException;

import p2.model.resource.UpdateSiteDownloader;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class DownloadUpdateSiteTest {

	public static void main(String[] args) {
		String host = "http://update.tibco.com";
		String baseContextPath = "/eclipse/bw/6.5";
		File rootFolder = new File("/Users/yayang/BW_660_DEV/update.tibco.com/eclipse/bw/6.5");

		UpdateSiteDownloader downloader = new UpdateSiteDownloader(host, baseContextPath, rootFolder);
		try {
			downloader.download();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
