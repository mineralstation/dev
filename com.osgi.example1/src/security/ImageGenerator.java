package security;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageGenerator {

	/**
	 * 
	 * @param content
	 * @param format
	 * @param file
	 */
	public void generate(String content, String format, File file) {
		try {
			BufferedImage image = ImageUtil.encodeV3(content);
			if (file.exists()) {
				file.delete();
			}
			ImageIO.write(image, format, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String userDir = System.getProperty("user.dir");

		ImageGenerator gen = new ImageGenerator();
		// window.addEventListener("load", onLoad);
		// window.addEventListener("resize", onResize);
		// initGlobalVariables();
		// window.addEventListener("wheel", onWheel, {passive: false});
		String content1 = "";
		content1 += "window.addEventListener(\"load\", onLoad);"; // capsule_03.js
		content1 += "window.addEventListener(\"resize\", onResize);"; // capsule_03.js
		content1 += "initGlobalVariables();"; // capsule_04.js
		content1 += "window.addEventListener(\"wheel\", onWheel, {passive: false});"; // capsule.jsp
		gen.generate(content1, "png", new File(userDir + "/doc/bg01.png"));
	}

}
