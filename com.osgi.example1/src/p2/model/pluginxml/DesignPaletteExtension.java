package p2.model.pluginxml;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class DesignPaletteExtension extends PluginExtension {

	protected String productID;
	protected String paletteId;
	protected String name;
	protected String tooltip;
	protected int priority;
	protected boolean expanded;
	protected String smallIcon;
	protected String largeIcon;

	public DesignPaletteExtension() {
	}

	public String getProductID() {
		return this.productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getPaletteId() {
		return this.paletteId;
	}

	public void setPaletteId(String paletteId) {
		this.paletteId = paletteId;
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

	public boolean isExpanded() {
		return this.expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public String getSmallIcon() {
		return this.smallIcon;
	}

	public void setSmallIcon(String smallIcon) {
		this.smallIcon = smallIcon;
	}

	public String getLargeIcon() {
		return this.largeIcon;
	}

	public void setLargeIcon(String largeIcon) {
		this.largeIcon = largeIcon;
	}

	@Override
	public String toString() {
		return "PaletteExtensionPaletteDrawer [productID=" + productID + ", paletteId=" + paletteId + ", name=" + name + ", tooltip=" + tooltip + ", priority=" + priority + ", expanded=" + expanded + ", smallIcon=" + smallIcon + ", largeIcon=" + largeIcon + "]";
	}

}
