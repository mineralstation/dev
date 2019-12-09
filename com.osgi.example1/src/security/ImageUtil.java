package security;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {

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
	 * @param shift
	 * @return
	 */
	protected static int toAscii(String content, int index, int shift) {
		int ascii = content.charAt(index);
		ascii += shift;
		return ascii;
	}

	/****
	 *
	 * @param content
	 * @return
	 */
	public static BufferedImage encode1(String content, int shift) {
		// System.out.println("encode()");
		// System.out.println(" content=" + content);
		int numOfPixels = content.length() / 4;
		int remain = content.length() % 4;
		if (remain > 0) {
			numOfPixels += 1;
		}

		int[] inputArray = new int[numOfPixels * 4];
		int j = 0;
		for (int i = 0; i < numOfPixels; i++) {
			if (i < numOfPixels - 1 || remain == 0) {
				int r = toAscii(content, i * 4 + 0, shift);
				int g = toAscii(content, i * 4 + 1, shift);
				int b = toAscii(content, i * 4 + 2, shift);
				int a = toAscii(content, i * 4 + 3, shift);

				// System.out.println(" r, g, b, a=" + r + "," + g + "," + b + "," + a);
				inputArray[j++] = r;
				inputArray[j++] = g;
				inputArray[j++] = b;
				inputArray[j++] = a;

			} else {
				int r = (remain >= 1) ? toAscii(content, i * 4 + 0, shift) : shift;
				int g = (remain >= 2) ? toAscii(content, i * 4 + 1, shift) : shift;
				int b = (remain >= 3) ? toAscii(content, i * 4 + 2, shift) : shift;
				int a = shift;

				// System.out.println(" r, g, b, a=" + r + "," + g + "," + b + "," + a);
				inputArray[j++] = r;
				inputArray[j++] = g;
				inputArray[j++] = b;
				inputArray[j++] = a;
			}
		}

		return getImageFromArray(inputArray, 1, numOfPixels);
	}

	/**
	 * 
	 * @param content
	 * @param shift
	 * @return
	 */
	public static BufferedImage encode2(String content, int shift) {
		int numOfPixels = content.length();
		int[] inputArray = new int[numOfPixels * 4];
		int j = 0;
		for (int i = 0; i < numOfPixels; i++) {
			int ascii = toAscii(content, i, shift);
			inputArray[j++] = ascii;
			inputArray[j++] = ascii;
			inputArray[j++] = ascii;
			inputArray[j++] = ascii;
		}
		return getImageFromArray(inputArray, 1, numOfPixels);
	}

	/**
	 * 
	 * @param content
	 * @return
	 */
	public static BufferedImage encodeV3(String content) {
		int numOfPixels = content.length();
		int[] inputArray = new int[numOfPixels * 4];
		int j = 0;
		for (int i = 0; i < numOfPixels; i++) {
			inputArray[j++] = 0; // r
			inputArray[j++] = 0; // g
			inputArray[j++] = 0; // b
			inputArray[j++] = content.charAt(i); // a
		}

		int width = numOfPixels;
		int height = 1;

		// additional adjust of width and height
		int idealWidth = 50;
		if (numOfPixels > idealWidth) {
			height = numOfPixels / idealWidth;
			int remain = numOfPixels % idealWidth;
			if (remain > 0) {
				height += 1;
			}
			width = idealWidth;

			int newNumOfPixels = width * height;
			if (newNumOfPixels > numOfPixels) {
				int[] newInputArray = new int[newNumOfPixels * 4];
				System.arraycopy(inputArray, 0, newInputArray, 0, inputArray.length);
				inputArray = newInputArray;
			}
		}

		return getImageFromArray(inputArray, width, height);
	}

	/**
	 * 
	 * @param image
	 * @param shift
	 * @return
	 */
	public static String decode1(Image image, int shift) {
		// System.out.println("decode()");
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
				char r = (char) (bytes[i + 3] + shift);
				char g = (char) (bytes[i + 2] + shift);
				char b = (char) (bytes[i + 1] + shift);
				char a = (char) (bytes[i + 0] + shift);

				content += String.valueOf(r);
				content += String.valueOf(g);
				content += String.valueOf(b);
				content += String.valueOf(a);

				System.out.println(" r, g, b, a=" + r + "," + g + "," + b + "," + a);
			}

		} else if (pixels != null) {
			System.out.println("  same image --- int[] pixels (length=" + pixels.length + ")");

			for (int pixel : pixels) {
				int a = (pixel >> 24) & 0xff;
				int r = (pixel >> 16) & 0xff;
				int g = (pixel >> 8) & 0xff;
				int b = (pixel >> 0) & 0xff;

				a += shift;
				r += shift;
				g += shift;
				b += shift;

				System.out.println(" r, g, b, a=" + r + "," + g + "," + b + "," + a);
				content += String.valueOf((char) r);
				content += String.valueOf((char) g);
				content += String.valueOf((char) b);
				content += String.valueOf((char) a);
			}
		}

		content = content.trim();
		return content;
	}

	public static String decode2(Image image, int shift) {
		byte[] bytes = null;
		int[] pixels = null;
		if (image instanceof BufferedImage) {
			BufferedImage bufferedImage = (BufferedImage) image;
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

		if (pixels != null) {
			System.out.println("  use same image instance --- int[] pixels (length=" + pixels.length + ")");

			for (int pixel : pixels) {
				int a = (pixel >> 24) & 0xff;
				int r = (pixel >> 16) & 0xff;
				int g = (pixel >> 8) & 0xff;
				int b = (pixel >> 0) & 0xff;

				a += shift;
				r += shift;
				g += shift;
				b += shift;

				System.out.println(" r, g, b, a=" + r + "," + g + "," + b + "," + a);
				// content += String.valueOf((char) r);
				// content += String.valueOf((char) g);
				// content += String.valueOf((char) b);
				// content += String.valueOf((char) a);
				content += String.valueOf(getInt(r, g, b, a));
			}

		} else if (bytes != null) {
			System.out.println("  use new image instance --- byte[] bytes (length=" + bytes.length + ")");
			for (int i = 0; i < bytes.length; i += 4) {
				char r = (char) (bytes[i + 3] + shift);
				char g = (char) (bytes[i + 2] + shift);
				char b = (char) (bytes[i + 1] + shift);
				char a = (char) (bytes[i + 0] + shift);

				// content += String.valueOf(r);
				// content += String.valueOf(g);
				// content += String.valueOf(b);
				// content += String.valueOf(a);
				content += String.valueOf(getChar(r, g, b, a));

				System.out.println(" r, g, b, a=" + r + "," + g + "," + b + "," + a);
			}
		}

		content = content.trim();
		return content;
	}

	public static String decode3(Image image, int shift) {
		byte[] bytes = null;
		int[] pixels = null;
		if (image instanceof BufferedImage) {
			BufferedImage bufferedImage = (BufferedImage) image;
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
		if (pixels != null) {
			System.out.println("  use same image instance --- int[] pixels (length=" + pixels.length + ")");

			for (int pixel : pixels) {
				int a = (pixel >> 24) & 0xff;
				int r = (pixel >> 16) & 0xff;
				int g = (pixel >> 8) & 0xff;
				int b = (pixel >> 0) & 0xff;

				a += shift;
				r += shift;
				g += shift;
				b += shift;

				System.out.println(" r, g, b, a=" + r + "," + g + "," + b + "," + a);
				content += String.valueOf((char) a);
				// content += String.valueOf((char) g);
				// content += String.valueOf((char) b);
				// content += String.valueOf((char) a);
			}

		} else if (bytes != null) {
			System.out.println("  use new image instance --- byte[] bytes (length=" + bytes.length + ")");
			for (int i = 0; i < bytes.length; i += 4) {
				char r = (char) (bytes[i + 3] + shift);
				char g = (char) (bytes[i + 2] + shift);
				char b = (char) (bytes[i + 1] + shift);
				char a = (char) (bytes[i + 0] + shift);

				System.out.println(" r, g, b, a=" + r + "," + g + "," + b + "," + a);

				content += String.valueOf(a);
				// content += String.valueOf(g);
				// content += String.valueOf(b);
				// content += String.valueOf(a);
			}
		}

		content = content.trim();
		return content;
	}

	/**
	 * 
	 * @param r
	 * @param g
	 * @param b
	 * @param a
	 * @return
	 */
	protected static char getChar(char r, char g, char b, char a) {
		int[] map = new int[255];
		map[r] = map[r]++;
		map[g] = map[g]++;
		map[b] = map[b]++;
		map[a] = map[a]++;

		char result = r;
		int max = map[r];
		if (map[g] > max) {
			max = map[g];
			result = g;
		}
		if (map[b] > max) {
			max = map[b];
			result = b;
		}
		if (map[a] > max) {
			max = map[a];
			result = a;
		}

		return result;
	}

	/**
	 * 
	 * @param r
	 * @param g
	 * @param b
	 * @param a
	 * @return
	 */
	protected static int getInt(int r, int g, int b, int a) {
		int[] map = new int[255];
		map[r] = map[r]++;
		map[g] = map[g]++;
		map[b] = map[b]++;
		map[a] = map[a]++;

		int result = r;
		int max = map[r];
		if (map[g] > max) {
			max = map[g];
			result = g;
		}
		if (map[b] > max) {
			max = map[b];
			result = b;
		}
		if (map[a] > max) {
			max = map[a];
			result = a;
		}

		return result;
	}

	public static void main(String[] args) {
		int shift = 0;
		String content0 = "function function function function";
		content0 = "initGlobalVariables();";

		System.out.println("content0=" + content0);
		// BufferedImage image1 = encode(content0, shift);
		BufferedImage image1 = encodeV3(content0);

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

		// String content1 = decode(image1, -shift);
		// String content2 = decode(image2, -shift);
		String content1 = decode3(image1, -shift);
		String content2 = decode3(image2, -shift);
		System.out.println("content1=" + content1);
		System.out.println("content2=" + content2);
		// System.out.println("content1.equals(content2)=" + content1.equals(content2));
	}

}

// System.out.println("Format names: " + Arrays.toString(ImageIO.getReaderFormatNames()));

// int l1_r = 'f' + shift;
// int l1_g = 'u' + shift;
// int l1_b = 'n' + shift;
// int l1_a = 'c' + shift;
//
// int l2_r = 't' + shift;
// int l2_g = 'i' + shift;
// int l2_b = 'o' + shift;
// int l2_a = 'n' + shift;
//
// int l3_r = '{' + shift;
// int l3_g = ' ' + shift;
// int l3_b = ' ' + shift;
// int l3_a = '}' + shift;
//
// int[] data = new int[] { //
// l1_r, l1_g, l1_b, l1_a, //
// l2_r, l2_g, l2_b, l2_a, //
// l3_r, l3_g, l3_b, l3_a, //
// };
// BufferedImage image1 = getImageFromArray(data, 1, 3);
