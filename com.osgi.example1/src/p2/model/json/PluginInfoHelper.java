package p2.model.json;

import java.util.ArrayList;
import java.util.List;

import p2.model.pluginxml.DesignActivityTypeExtension;
import p2.model.pluginxml.DesignActivityTypeExtension.ActivityType;
import p2.model.pluginxml.DesignPaletteExtension;
import p2.model.pluginxml.Plugin;
import p2.model.pluginxml.PluginExtension;
import p2.model.repository.Repository;
import p2.model.repository.RepositoryUnit;
import p2.model.repository.RepositoryUnits;
import p2.model.repository.UnitProperties;
import p2.model.repository.UnitProperty;
import p2.model.resource.P2Constants;
import p2.model.resource.PluginXmlResource;
import p2.model.resource.RepositoryResource;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class PluginInfoHelper {

	/**
	 * 
	 * @param pluginInfo
	 * @return
	 */
	public static String getSimpleId(PluginInfo pluginInfo) {
		// remove fixed prefix
		String simpleId = pluginInfo.getPluginFolder().getName();
		if (simpleId.startsWith("TIB_")) {
			simpleId = simpleId.substring("TIB_".length());
		}

		// remove ending version (DO NOT DO THIS!)
		// int index = simpleId.lastIndexOf("_");
		// if (index > 0) {
		// simpleId = simpleId.substring(0, index);
		// }

		return simpleId;
	}

	/**
	 * 
	 * @param pluginInfo
	 * @return
	 */
	public static String getPaletteIcon(PluginInfo pluginInfo) {
		String iconPath = null;

		PluginXmlResource pluginXmlResource = pluginInfo.getPluginXmlResource();
		if (pluginXmlResource != null) {
			Plugin plugin = pluginXmlResource.getPlugin();
			if (plugin != null) {
				List<PluginExtension> paletteExtensions = plugin.getExtensions(PluginExtension.EXTENSION_POINT__DESIGN_PALETTE);
				for (PluginExtension extension : paletteExtensions) {
					DesignPaletteExtension paletteExtension = (DesignPaletteExtension) extension;

					String largeIcon = paletteExtension.getLargeIcon();
					String smallIcon = paletteExtension.getSmallIcon();
					String icon = (largeIcon != null) ? largeIcon : smallIcon;
					if (icon != null) {
						iconPath = icon;
						break;
					}
				}
			}
		}

		return iconPath;
	}

	/**
	 * 
	 * @param pluginInfo
	 * @return
	 */
	public static List<String> getActivityTypeIds(PluginInfo pluginInfo) {
		List<String> activityTypeIds = new ArrayList<String>();

		PluginXmlResource pluginXmlResource = pluginInfo.getPluginXmlResource();
		if (pluginXmlResource != null) {
			Plugin plugin = pluginXmlResource.getPlugin();
			if (plugin != null) {
				List<PluginExtension> activityTypeExtensions = plugin.getExtensions(PluginExtension.EXTENSION_POINT__DESIGN_ACTIVITY_TYPE);
				for (PluginExtension extension : activityTypeExtensions) {
					DesignActivityTypeExtension activityTypeExtension = (DesignActivityTypeExtension) extension;
					List<ActivityType> activityTypes = activityTypeExtension.getActivityTypes();
					for (ActivityType activityType : activityTypes) {
						String activityTypeId = activityType.getActivityTypeID();
						activityTypeIds.add(activityTypeId);
					}
				}
			}
		}

		return activityTypeIds;
	}

	/**
	 * 
	 * @param pluginInfo
	 * @return
	 */
	public static String getVersion(PluginInfo pluginInfo) {
		String version = pluginInfo.getPluginFolder().getName();
		int index = version.lastIndexOf("_");
		if (index > 0) {
			version = version.substring(index + 1);
		}
		return version;
	}

	/**
	 * 
	 * @param pluginInfo
	 * @param includeFeatureGroup
	 * @return
	 */
	public static String getName(PluginInfo pluginInfo, boolean includeFeatureGroup) {
		return getUnitProperty(pluginInfo, P2Constants.UNIT_PROP__NAME, includeFeatureGroup);
	}

	/**
	 * 
	 * @param pluginInfo
	 * @param includeFeatureGroup
	 * @return
	 */
	public static String getDescription(PluginInfo pluginInfo, boolean includeFeatureGroup) {
		return getUnitProperty(pluginInfo, P2Constants.UNIT_PROP__DESCRIPTION, includeFeatureGroup);
	}

	/**
	 * 
	 * @param pluginInfo
	 * @param propertyName
	 * @param includeFeatureGroups
	 * @return
	 */
	public static String getUnitProperty(PluginInfo pluginInfo, String propertyName, boolean includeFeatureGroups) {
		String propertyValue = "";
		List<RepositoryUnit> units = null;
		RepositoryResource repoResource = pluginInfo.getRepositoryResource();
		if (repoResource != null) {
			Repository repo = repoResource.getRepository();
			if (repo != null) {
				RepositoryUnits unitsObj = repo.getUnits();
				if (unitsObj != null) {
					units = unitsObj.getChildren();
				}
			}
		}
		if (units != null) {
			boolean found = false;
			for (RepositoryUnit unit : units) {
				if (isFeatureCategory(unit)) {
					UnitProperties properties = unit.getProperties();
					UnitProperty property = properties.getChild(propertyName);
					if (property != null) {
						propertyValue = property.getValue();
						found = true;
						break;
					}
				}
			}

			if (!found) {
				if (includeFeatureGroups) {
					for (RepositoryUnit unit : units) {
						if (isFeatureGroup(unit)) {
							UnitProperties properties = unit.getProperties();
							UnitProperty property = properties.getChild(propertyName);
							if (property != null) {
								propertyValue = property.getValue();
								found = true;
								break;
							}
						}
					}
				}
			}
		}
		return propertyValue;
	}

	/**
	 * 
	 * @param unit
	 * @return
	 */
	public static boolean isFeatureCategory(RepositoryUnit unit) {
		if (unit != null && unit.getProperties() != null) {
			List<UnitProperty> children = unit.getProperties().getChildren();
			for (UnitProperty property : children) {
				String propName = property.getName();
				String propValue = property.getValue();
				if (P2Constants.UNIT_PROP__TYPE_CATEGORY.equals(propName) && "true".equalsIgnoreCase(propValue)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param unit
	 * @return
	 */
	public static boolean isFeatureGroup(RepositoryUnit unit) {
		if (unit != null && unit.getProperties() != null) {
			List<UnitProperty> children = unit.getProperties().getChildren();
			for (UnitProperty property : children) {
				String propName = property.getName();
				String propValue = property.getValue();
				if (P2Constants.UNIT_PROP__TYPE_GROUP.equals(propName) && "true".equalsIgnoreCase(propValue)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param pluginInfo
	 * @param includeFeatureGroup
	 * @return
	 */
	public static String getName(RepositoryUnit unit) {
		return getUnitProperty(unit, P2Constants.UNIT_PROP__NAME);
	}

	/**
	 * 
	 * @param pluginInfo
	 * @param includeFeatureGroup
	 * @return
	 */
	public static String getDescription(RepositoryUnit unit) {
		return getUnitProperty(unit, P2Constants.UNIT_PROP__DESCRIPTION);
	}

	/**
	 * 
	 * @param pluginInfo
	 * @param includeFeatureGroup
	 * @return
	 */
	public static String getProvider(RepositoryUnit unit) {
		return getUnitProperty(unit, P2Constants.UNIT_PROP__PROVIDER);
	}

	/**
	 * 
	 * @param pluginInfo
	 * @param includeFeatureGroup
	 * @return
	 */
	public static String getCopyright(RepositoryUnit unit) {
		return getUnitProperty(unit, P2Constants.UNIT_PROP__COPYRIGHT);
	}

	/**
	 * 
	 * @param pluginInfo
	 * @param includeFeatureGroup
	 * @return
	 */
	public static String getLicense(RepositoryUnit unit) {
		return getUnitProperty(unit, P2Constants.UNIT_PROP__LICENSE);
	}

	/**
	 * 
	 * @param unit
	 * @param propertyName
	 * @return
	 */
	public static String getUnitProperty(RepositoryUnit unit, String propertyName) {
		String propertyValue = null;
		UnitProperties properties = unit.getProperties();
		UnitProperty property = properties.getChild(propertyName);
		if (property != null) {
			propertyValue = property.getValue();
		}
		return propertyValue;
	}

}
