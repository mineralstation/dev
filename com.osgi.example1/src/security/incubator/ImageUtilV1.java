package security.incubator;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtilV1 {

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
				int r = toAscii(content, i * 4 + 0);
				int g = toAscii(content, i * 4 + 1);
				int b = toAscii(content, i * 4 + 2);
				int a = toAscii(content, i * 4 + 3);

				System.out.println("  r, g, b, a=" + r + "," + g + "," + b + "," + a);

				inputArray[j++] = b;
				inputArray[j++] = g;
				inputArray[j++] = r;
				inputArray[j++] = a;

			} else {
				int r = (remain >= 1) ? toAscii(content, i * 4 + 0) : 0;
				int g = (remain >= 2) ? toAscii(content, i * 4 + 1) : 0;
				int b = (remain >= 3) ? toAscii(content, i * 4 + 2) : 0;
				int a = 0;

				System.out.println("  r, g, b, a=" + r + "," + g + "," + b + "," + a);

				inputArray[j++] = r;
				inputArray[j++] = g;
				inputArray[j++] = b;
				inputArray[j++] = a;
			}
		}

		return getImageFromArray(inputArray, 1, pixelNum);
	}

	public static String decode(Image image) {
		System.out.println("decode()");

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
			System.out.println("  reload image --- byte[] bytes (length=" + bytes.length + ")");
			for (int i = 0; i < bytes.length; i += 4) {
				char r = (char) bytes[i + 3];
				char g = (char) bytes[i + 2];
				char b = (char) bytes[i + 1];
				char a = (char) bytes[i + 0];

				content += String.valueOf(r);
				content += String.valueOf(g);
				content += String.valueOf(b);
				content += String.valueOf(a);

				System.out.println("  r, g, b, a=" + r + "," + g + "," + b + "," + a);
			}

		} else if (pixels != null) {
			System.out.println("  same image --- int[] pixels (length=" + pixels.length + ")");

			for (int pixel : pixels) {
				int a = (pixel >> 24) & 0xff;
				int r = (pixel >> 16) & 0xff;
				int g = (pixel >> 8) & 0xff;
				int b = (pixel >> 0) & 0xff;

				System.out.println("  r, g, b, a=" + r + "," + g + "," + b + "," + a);

				content += String.valueOf((char) r);
				content += String.valueOf((char) g);
				content += String.valueOf((char) b);
				content += String.valueOf((char) a);
			}
		}

		content = content.trim();
		return content;
	}

	public static void main(String[] args) {
		// System.out.println("Format names: " + Arrays.toString(ImageIO.getReaderFormatNames()));

		// String content1 = "";
		// content1 = "word";
		// content1 += "ball";
		// content1 += "game";

		// System.out.println("content1=" + content1);
		// BufferedImage image1 = encode(content1);

		// int r = Character.getNumericValue('w');
		// int g = Character.getNumericValue('o');
		// int b = Character.getNumericValue('r');
		// int a = Character.getNumericValue('d');
		int r = 0;
		int g = 0;
		int b = 0;
		int a = 0;
		// r = 255;
		// g = 0;
		// b = 0;
		// a = 10;
		r = 97;
		g = 98;
		b = 99;
		a = 100;
		// r = 65; // 66
		// g = 66; // 66
		// b = 67; // 67
		// a = 155; // 155
		// r = 119; // 119
		// g = 120; // 119
		// b = 121; // 121
		// a = 122; // 122
		// r = 97; // 97
		// g = 98; // 99
		// b = 99; // 99
		// a = 155; // 155
		// r = 'a';
		// g = 'b';
		// b = 'c';
		// a = 'd';
		r += 127;
		g += 127;
		b += 127;
		a += 127;
		System.out.println("r, g, b, a=" + r + "," + g + "," + b + "," + a);
		BufferedImage image1 = getImageFromArray(new int[] { r, g, b, a }, 1, 1);

		// png, gif, jpge
		String format = "png";

		String path = System.getProperty("user.dir");
		File file1 = new File(path + "/doc/bg0." + format);
		try {
			if (file1.exists()) {
				file1.delete();
			}
			ImageIO.write(image1, format, file1);

		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedImage image2 = null;
		try {
			image2 = ImageIO.read(file1);

		} catch (IOException e) {
			e.printStackTrace();
		}

		String content2 = decode(image2);
		System.out.println("content2=" + content2);
		// System.out.println("content1.equals(content2)=" + content1.equals(content2));
	}

}
