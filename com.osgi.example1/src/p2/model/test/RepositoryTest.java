package p2.model.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import p2.model.repository.Repository;
import p2.model.repository.RepositoryProperties;
import p2.model.repository.RepositoryProperty;
import p2.model.repository.RepositoryUnit;
import p2.model.repository.RepositoryUnits;
import p2.model.repository.UnitProperties;
import p2.model.repository.UnitProperty;
import p2.model.resource.RepositoryReader;
import p2.model.resource.RepositoryResource;

/**
 * 
 * @author <a href="mailto:yangyang4j@gmail.com">Yang Yang</a>
 *
 */
public class RepositoryTest {

	public static void main(String[] args) {
		try {
			testDeserialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testDeserialize() throws Exception {
		File rootFolder = new File("/Users/yayang/BW_660_DEV/update.tibco.com/eclipse/bw/6.5");

		File[] memberFiles = rootFolder.listFiles();
		for (File memberFile : memberFiles) {
			if (memberFile.isDirectory() && memberFile.getName().startsWith("TIB")) {
				// child is a plugin folder
				processPlugin(rootFolder, memberFile);
			}
		}
	}

	/**
	 * 
	 * @param rootFolder
	 * @param pluginFolder
	 */
	protected static void processPlugin(File rootFolder, File pluginFolder) {
		// processPluginContentJar(rootFolder, pluginFolder);
		processPluginPlugins(rootFolder, pluginFolder);
	}

	/**
	 * 
	 * @param rootFolder
	 * @param pluginFolder
	 */
	protected static void processPluginContentJar(File rootFolder, File pluginFolder) {
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

								RepositoryResource resource = reader.getResource();
								if (resource != null) {
									Repository repository = resource.getRepository();
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
	 * @param rootFolder
	 * @param pluginFolder
	 */
	protected static void processPluginPlugins(File rootFolder, File pluginFolder) {
		File pluginsFolder = new File(pluginFolder, "plugins");
		if (!pluginsFolder.exists()) {
			return;
		}

		String rootPath = rootFolder.getAbsolutePath();

		File[] memberFiles = pluginsFolder.listFiles();
		for (File memberFile : memberFiles) {
			String fileName = memberFile.getName();

			if (fileName.contains(".payload") || fileName.contains(".design")) {
				if (memberFile.isFile()) {
					if (fileName.endsWith(".jar") || fileName.endsWith(".zip")) {
						String filePath = memberFile.getAbsolutePath();
						String relativeFilePath = filePath.substring(rootPath.length());
						System.out.println(relativeFilePath);

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
									if (entryName.startsWith("payload/") && !entryName.equals("payload/")) {
										if (entryName.contains("common") && entryName.endsWith(".zip")) {
											System.out.println("    " + entryName);
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

						System.out.println();
					}
				} else if (memberFile.isDirectory()) {

				}
			}
		}
	}

}
