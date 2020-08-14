package p2.model.test;

import java.io.File;
import java.util.List;

import p2.model.json.PluginInfo;
import p2.model.json.PluginInfoWriter;
import p2.model.json.RepositoryProcessor;

public class RepositoryProcessorTest {

	public static void main(String[] args) {
		try {
			File rootFolder = new File("/Users/yayang/BW_660_DEV/update.tibco.com/eclipse/bw/6.5");
			RepositoryProcessor processor = new RepositoryProcessor(rootFolder);
			processor.run();

			List<PluginInfo> pluginInfos = processor.getPluginInfos();
			PluginInfoWriter writer = new PluginInfoWriter(pluginInfos);

			File targetFolder = new File("/Users/yayang/BW_660_DEV/update.tibco.com/eclipse/bw/6.5_json");
			File jsonFile = new File(targetFolder, "plugins.json");
			writer.write(targetFolder, jsonFile);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
