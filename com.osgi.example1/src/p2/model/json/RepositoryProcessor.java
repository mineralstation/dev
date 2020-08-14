package p2.model.json;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import p2.model.pluginxml.DesignActivityTypeExtension;
import p2.model.pluginxml.DesignActivityTypeExtension.ActivityType;
import p2.model.pluginxml.DesignPaletteExtension;
import p2.model.pluginxml.Plugin;
import p2.model.pluginxml.PluginExtension;
import p2.model.repository.Repository;
import p2.model.repository.RepositoryProperties;
import p2.model.repository.RepositoryProperty;
import p2.model.repository.RepositoryUnit;
import p2.model.repository.RepositoryUnits;
import p2.model.repository.UnitProperties;
import p2.model.repository.UnitProperty;
import p2.model.resource.PluginXmlReader;
import p2.model.resource.PluginXmlResource;
import p2.model.resource.RepositoryReader;
import p2.model.resource.RepositoryResource;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class RepositoryProcessor {

	protected File rootFolder;
	protected List<PluginInfo> pluginInfos;

	/**
	 * 
	 * @param rootFolder
	 */
	public RepositoryProcessor(File rootFolder) {
		this.rootFolder = rootFolder;
		this.pluginInfos = new ArrayList<PluginInfo>();
	}

	public List<PluginInfo> getPluginInfos() {
		return this.pluginInfos;
	}

	public void run() throws Exception {
		this.pluginInfos.clear();

		File[] memberFiles = this.rootFolder.listFiles();
		int index = 0;
		for (File memberFile : memberFiles) {
			if (memberFile.isDirectory() && memberFile.getName().startsWith("TIB")) {
				// child is a plugin folder
				PluginInfo pluginInfo = createPluginInfo(index, this.rootFolder, memberFile);
				this.pluginInfos.add(pluginInfo);
			}
			index++;
		}
	}

	/**
	 * 
	 * @param index
	 * @param rootFolder
	 * @param pluginFolder
	 * @return
	 */
	protected PluginInfo createPluginInfo(int index, File rootFolder, File pluginFolder) {
		PluginInfo pluginInfo = new PluginInfo();

		pluginInfo.setRootFolder(rootFolder);
		pluginInfo.setPluginFolder(pluginFolder);

		processPluginContentJar(pluginInfo, index, rootFolder, pluginFolder);
		processPluginPlugins(pluginInfo, index, rootFolder, pluginFolder);

		return pluginInfo;
	}

	/**
	 * 
	 * @param pluginInfo
	 * @param index
	 * @param rootFolder
	 * @param pluginFolder
	 */
	protected void processPluginContentJar(PluginInfo pluginInfo, int index, File rootFolder, File pluginFolder) {
		File contentJarFile = new File(pluginFolder, "content.jar");
		if (!contentJarFile.exists()) {
			return;
		}

		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(contentJarFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (zipFile != null) {
			try {
				Enumeration<? extends ZipEntry> zipEntries = zipFile.entries();
				while (zipEntries.hasMoreElements()) {
					ZipEntry zipEntry = zipEntries.nextElement();

					String entryName = zipEntry.getName();
					if ("content.xml".equals(entryName)) {
						InputStream inputStream = null;
						try {
							inputStream = zipFile.getInputStream(zipEntry);
							if (inputStream != null) {
								String resourceId = contentJarFile.getAbsolutePath();
								RepositoryReader reader = new RepositoryReader();
								reader.read(resourceId, inputStream);

								RepositoryResource repositoryResource = reader.getResource();
								if (repositoryResource != null) {
									pluginInfo.setRepositoryResource(repositoryResource);

									Repository repository = repositoryResource.getRepository();
									System.out.println(repository);
									if (repository != null) {
										RepositoryProperties propertiesObj = repository.getProperties();
										if (propertiesObj != null) {
											List<RepositoryProperty> properties = propertiesObj.getChildren();
											for (RepositoryProperty property : properties) {
												System.out.println("    " + property);
											}
										}
										System.out.println();

										RepositoryUnits unitsObj = repository.getUnits();
										if (unitsObj != null) {
											List<RepositoryUnit> units = unitsObj.getChildren();
											for (RepositoryUnit unit : units) {
												System.out.println("    " + unit);

												UnitProperties unitPropertiesObj = unit.getProperties();
												if (unitPropertiesObj != null) {
													List<UnitProperty> unitProperties = unitPropertiesObj.getChildren();
													for (UnitProperty unitProperty : unitProperties) {
														String propName = unitProperty.getName();
														// license is huge. don't print it out.
														if ("df_LT.license".equals(propName)) {
															continue;
														}
														System.out.println("        " + unitProperty);
													}
												}
												System.out.println();
											}
										}
										System.out.println();
									}
								}
							}
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
			} finally {
				try {
					zipFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * @param pluginInfo
	 * @param index
	 * @param rootFolder
	 * @param pluginFolder
	 */
	protected void processPluginPlugins(PluginInfo pluginInfo, int index, File rootFolder, File pluginFolder) {
		File pluginsFolder = new File(pluginFolder, "plugins");
		if (!pluginsFolder.exists()) {
			return;
		}

		String rootPath = rootFolder.getAbsolutePath();
		String pluginPath = pluginFolder.getAbsolutePath();
		String relativePluginPath = pluginPath.substring(rootPath.length());
		System.out.println("[" + index + "] " + relativePluginPath);

		File[] memberFiles = pluginsFolder.listFiles();
		for (File memberFile : memberFiles) {
			String fileName = memberFile.getName();

			if (memberFile.isFile()) {
				if (fileName.endsWith(".jar") || fileName.endsWith(".zip")) {
					String filePath = memberFile.getAbsolutePath();
					String relativeFilePath = filePath.substring(rootPath.length());
					// System.out.println(" " + relativeFilePath);

					ZipFile zipFile = null;
					try {
						zipFile = new ZipFile(memberFile);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (zipFile != null) {
						try {
							Enumeration<? extends ZipEntry> zipEntries = zipFile.entries();
							while (zipEntries.hasMoreElements()) {
								ZipEntry zipEntry = zipEntries.nextElement();

								String entryName = zipEntry.getName();

								if ("plugin.xml".equals(entryName)) {
									PluginXmlResource pluginXmlResource = isPaletteDesignTimePlugin(zipFile, zipEntry);
									if (pluginXmlResource != null) {
										pluginInfo.setPluginXmlResource(pluginXmlResource);

										System.out.println("    " + relativeFilePath);
										System.out.println("        " + entryName);

										List<PluginExtension> paletteExtensions = pluginXmlResource.getPlugin().getExtensions(PluginExtension.EXTENSION_POINT__DESIGN_PALETTE);
										List<PluginExtension> activityTypeExtensions = pluginXmlResource.getPlugin().getExtensions(PluginExtension.EXTENSION_POINT__DESIGN_ACTIVITY_TYPE);

										for (PluginExtension extension : paletteExtensions) {
											DesignPaletteExtension paletteExtension = (DesignPaletteExtension) extension;
											System.out.println("            " + paletteExtension);
										}

										for (PluginExtension extension : activityTypeExtensions) {
											DesignActivityTypeExtension activityTypeExtension = (DesignActivityTypeExtension) extension;
											List<ActivityType> activityTypes = activityTypeExtension.getActivityTypes();
											for (ActivityType activityType : activityTypes) {
												System.out.println("            " + activityType);
											}
										}
									}

								} else if (entryName.endsWith(".zip")) {

								}
							}

						} finally {
							try {
								zipFile.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}

					System.out.println();
				}
			} else if (memberFile.isDirectory()) {

			}
		}
	}

	/**
	 * 
	 * @param zipFile
	 * @param zipEntry
	 * @return
	 */
	protected PluginXmlResource isPaletteDesignTimePlugin(ZipFile zipFile, ZipEntry zipEntry) {
		InputStream inputStream = null;
		try {
			inputStream = zipFile.getInputStream(zipEntry);
			if (inputStream != null) {
				String resourceId = "plugin.xml";
				PluginXmlReader reader = new PluginXmlReader();
				reader.read(resourceId, inputStream);

				PluginXmlResource pluginXmlResource = reader.getResource();
				if (pluginXmlResource != null) {
					Plugin plugin = pluginXmlResource.getPlugin();
					if (plugin != null) {
						List<PluginExtension> extensions = plugin.getExtensions();
						for (PluginExtension extension : extensions) {
							String point = extension.getExtensionPoint();
							if ("com.tibco.bw.design.Palette".equals(point) || "com.tibco.bw.design.ActivityType".equals(point)) {
								return pluginXmlResource;
							}
						}
					}
				}
			}
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
		return null;
	}

}
