package p2.model.pluginxml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class DesignActivityTypeExtension extends PluginExtension {

	public static class ActivityType {
		protected String activityTypeID;
		protected String name;
		protected String tooltip;
		protected int priority;
		protected boolean visible;
		protected List<String> paletteIDs;

		public String getActivityTypeID() {
			return this.activityTypeID;
		}

		public void setActivityTypeID(String activityTypeID) {
			this.activityTypeID = activityTypeID;
		}

		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTooltip() {
			return this.tooltip;
		}

		public void setTooltip(String tooltip) {
			this.tooltip = tooltip;
		}

		public int getPriority() {
			return this.priority;
		}

		public void setPriority(int priority) {
			this.priority = priority;
		}

		public boolean isVisible() {
			return this.visible;
		}

		public void setVisible(boolean visible) {
			this.visible = visible;
		}

		public List<String> getPaletteIDs() {
			return this.paletteIDs;
		}

		public void setPaletteIDs(List<String> paletteIDs) {
			this.paletteIDs = paletteIDs;
		}

		@Override
		public String toString() {
			String paletteIdsStr = null;
			if (this.paletteIDs != null) {
				String[] paletteIdArray = this.paletteIDs.toArray(new String[this.paletteIDs.size()]);
				paletteIdsStr = Arrays.toString(paletteIdArray);
			}

			return "ActivityType [activityTypeID=" + activityTypeID + ", name=" + name + ", tooltip=" + tooltip + ", priority=" + priority + ", visible=" + visible + ", paletteIDs=" + paletteIdsStr + "]";
		}
	}

	protected List<ActivityType> activityTypes;

	public DesignActivityTypeExtension() {
	}

	public List<ActivityType> getActivityTypes() {
		if (this.activityTypes == null) {
			this.activityTypes = new ArrayList<ActivityType>();
		}
		return this.activityTypes;
	}

	public void setActivityTypes(List<ActivityType> activityTypes) {
		this.activityTypes = activityTypes;
	}

}
