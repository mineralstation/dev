package p2.model.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.origin.common.json.JSONUtil;
import org.origin.common.util.IOUtil;

import p2.model.repository.RepositoryUnit;
import p2.model.resource.P2Constants;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class PluginInfoWriter {

	protected List<PluginInfo> pluginInfos;

	/**
	 * 
	 * @param pluginInfos
	 */
	public PluginInfoWriter(List<PluginInfo> pluginInfos) {
		this.pluginInfos = pluginInfos;
	}

	/**
	 * 
	 * @param targetFolder
	 * @param file
	 * @throws IOException
	 */
	public void write(File targetFolder, File file) throws IOException {
		FileOutputStream fos = null;
		try {
			if (!targetFolder.exists()) {
				targetFolder.mkdirs();
			}

			fos = new FileOutputStream(file);
			write(targetFolder, fos);

		} catch (FileNotFoundException e) {
			throw e;

		} finally {
			IOUtil.closeQuietly(fos, true);
		}
	}

	/**
	 * 
	 * @param targetFolder
	 * @param output
	 * @throws IOException
	 */
	public void write(File targetFolder, OutputStream output) throws IOException {
		JSONObject rootJSONObject = contentsToDocument();
		if (rootJSONObject != null) {
			JSONUtil.save(rootJSONObject, output, false);
		}
	}

	/**
	 * 
	 * @param manifest
	 * @return
	 */
	protected JSONObject contentsToDocument() {
		JSONObject rootJSONObject = new JSONObject();

		// paletteIDs
		JSONObject paletteIDsJSONObject = new JSONObject();
		for (PluginInfo pluginInfo : this.pluginInfos) {
			String simpleId = PluginInfoHelper.getSimpleId(pluginInfo);
			String name = PluginInfoHelper.getName(pluginInfo, true);
			paletteIDsJSONObject.put(simpleId, name);
		}
		rootJSONObject.put("paletteIDs", paletteIDsJSONObject);

		// pluginMappings
		JSONObject pluginMappingsJSONObject = new JSONObject();
		for (PluginInfo pluginInfo : this.pluginInfos) {
			String simpleId = PluginInfoHelper.getSimpleId(pluginInfo);
			JSONObject pluginInfoJSONObject = pluginInfoToJsonObject(pluginInfo);
			if (pluginInfoJSONObject != null) {
				pluginMappingsJSONObject.put(simpleId, pluginInfoJSONObject);
			}
		}
		rootJSONObject.put("pluginMappings", pluginMappingsJSONObject);

		return rootJSONObject;
	}

	/**
	 * 
	 * @param pluginInfo
	 * @return
	 */
	protected JSONObject pluginInfoToJsonObject(PluginInfo pluginInfo) {
		JSONObject pluginInfoJSONObject = new JSONObject();

		// version
		String version = PluginInfoHelper.getVersion(pluginInfo);
		pluginInfoJSONObject.put("version", version);

		// icon
		String iconPath = PluginInfoHelper.getPaletteIcon(pluginInfo);
		if (iconPath != null && !iconPath.isEmpty() && !"null".equals(iconPath)) {
			String simpleId = PluginInfoHelper.getSimpleId(pluginInfo);
			String iconFileName = iconPath;
			int index = iconPath.lastIndexOf("/");
			if (index > 0) {
				iconFileName = iconPath.substring(index + 1);
			}
			String iconFullPath = "http://update.tibco.com/eclipse/bw/icons/" + simpleId + "/" + iconFileName;
			pluginInfoJSONObject.put("icon", iconFullPath);
		}

		/*-
		// name
		String name = PluginInfoHelper.getName(pluginInfo, true);
		if (name != null && !name.isEmpty() && !"null".equals(name)) {
			pluginInfoJSONObject.put("name", name);
		}
		*/

		// description
		String description = PluginInfoHelper.getDescription(pluginInfo, true);
		if (description != null && !description.isEmpty() && !"null".equals(description)) {
			pluginInfoJSONObject.put("description", description);
		}

		// units
		JSONObject unitsJSONObject = new JSONObject();
		List<RepositoryUnit> units = pluginInfo.getRepositoryResource().getRepository().getUnits().getChildren();
		for (RepositoryUnit unit : units) {
			String unitId = unit.getId();
			if (P2Constants.UNIT_ID__ECLIPSE_P2_INSTALLER.equals(unitId)) {
				continue;
			}

			boolean isFeatureCategory = PluginInfoHelper.isFeatureCategory(unit);
			boolean isFeatureGroup = PluginInfoHelper.isFeatureGroup(unit);
			if (isFeatureCategory || isFeatureGroup) {
				JSONObject unitJSONObject = unitToJsonObject(unit);
				if (unitJSONObject != null) {
					unitsJSONObject.put(unitId, unitJSONObject);
				}
			}
		}
		pluginInfoJSONObject.put("units", unitsJSONObject);

		// activityTypeIds
		List<String> activityTypeIds = PluginInfoHelper.getActivityTypeIds(pluginInfo);
		if (!activityTypeIds.isEmpty()) {
			JSONArray activityTypeIdsJSONArray = new JSONArray();
			int index = 0;
			for (String activityTypeId : activityTypeIds) {
				activityTypeIdsJSONArray.put(index++, activityTypeId);
			}
			pluginInfoJSONObject.put("activityTypeIds", activityTypeIdsJSONArray);
		}

		return pluginInfoJSONObject;
	}

	/**
	 * 
	 * @param unit
	 * @return
	 */
	protected JSONObject unitToJsonObject(RepositoryUnit unit) {
		JSONObject unitJSONObject = new JSONObject();

		// name
		String name = PluginInfoHelper.getName(unit);
		if (name != null && !name.startsWith("%")) {
			unitJSONObject.put("name", name);
		}

		// description
		String description = PluginInfoHelper.getDescription(unit);
		if (description != null && !description.startsWith("%")) {
			unitJSONObject.put("description", description);
		}

		// provider
		String provider = PluginInfoHelper.getProvider(unit);
		if (provider != null && !provider.startsWith("%")) {
			unitJSONObject.put("provider", provider);
		}

		// copyright
		String copyright = PluginInfoHelper.getCopyright(unit);
		if (copyright != null && !copyright.startsWith("%")) {
			unitJSONObject.put("copyright", copyright);
		}

		return unitJSONObject;
	}

}
