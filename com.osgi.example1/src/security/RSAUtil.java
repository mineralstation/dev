package security;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAUtil {

	// private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgFGVfrY4jQSoZQWWygZ83roKXWD4YeT2x2p41dGkPixe73rT2IW04glagN2vgoZoHuOPqa5and6kAmK2ujmCHu6D1auJhE2tXP+yLkpSiYMQucDKmCsWMnW9XlC5K7OSL77TXXcfvTvyZcjObEz6LIBRzs6+FqpFbUO9SJEfh6wIDAQAB";
	// private static String privateKey =
	// "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKAUZV+tjiNBKhlBZbKBnzeugpdYPhh5PbHanjV0aQ+LF7vetPYhbTiCVqA3a+Chmge44+prlqd3qQCYra6OYIe7oPVq4mETa1c/7IuSlKJgxC5wMqYKxYydb1eULkrs5IvvtNddx+9O/JlyM5sTPosgFHOzr4WqkVtQ71IkR+HrAgMBAAECgYAkQLo8kteP0GAyXAcmCAkA2Tql/8wASuTX9ITD4lsws/VqDKO64hMUKyBnJGX/91kkypCDNF5oCsdxZSJgV8owViYWZPnbvEcNqLtqgs7nj1UHuX9S5yYIPGN/mHL6OJJ7sosOd6rqdpg6JRRkAKUV+tmN/7Gh0+GFXM+ug6mgwQJBAO9/+CWpCAVoGxCA+YsTMb82fTOmGYMkZOAfQsvIV2v6DC8eJrSa+c0yCOTa3tirlCkhBfB08f8U2iEPS+Gu3bECQQCrG7O0gYmFL2RX1O+37ovyyHTbst4s4xbLW4jLzbSoimL235lCdIC+fllEEP96wPAiqo6dzmdH8KsGmVozsVRbAkB0ME8AZjp/9Pt8TDXD5LHzo8mlruUdnCBcIo5TMoRG2+3hRe1dHPonNCjgbdZCoyqjsWOiPfnQ2Brigvs7J4xhAkBGRiZUKC92x7QKbqXVgN9xYuq7oIanIM0nz/wq190uq0dh5Qtow7hshC/dSK3kmIEHe8z++tpoLWvQVgM538apAkBoSNfaTkDZhFavuiVl6L8cWCoDcJBItip8wKQhXwHp0O3HLg10OEd14M58ooNfpgt+8D8/8/2OOFaR0HzA+2Dm";
	private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJVFNaPVLUEzkhKsnTodDCeCOGiKviu/Gaz55+s9MqCGMA/qGd52y6pGEQAPhK+PdODwThLb9AXhGSgTfWq8216Im1kfpjO6nldXlmtkO8xMMOyFijUI4swOuBfxlr/5Ts0jqsGjCm+J1w5aaFTfLIfTs2KzsbP4cQJwn/7Kag3wIDAQAB";
	private static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIlUU1o9UtQTOSEqydOh0MJ4I4aIq+K78ZrPnn6z0yoIYwD+oZ3nbLqkYRAA+Er4904PBOEtv0BeEZKBN9arzbXoibWR+mM7qeV1eWa2Q7zEww7IWKNQjizA64F/GWv/lOzSOqwaMKb4nXDlpoVN8sh9OzYrOxs/hxAnCf/spqDfAgMBAAECgYBHYi/ZjlrJphxiIy259jjffTdqLQy71NnnkNhgH/U5uhZMZA96VttrwRo7CWYHApb6WlA9BFyPhtY3gzeVPMS2bzPvGlKmxw8gbjdH2+1scmQ041/+PkJwENkdnZw6woAJcXVRxZdqV7DCdY4k3r+zKlyz3K+KFI+K0tT7sdTiMQJBANZuhf12rdRfMTWyEo/xGUCswONG5GK5FdYe3zAwG94V2CORN0z9Yd4OChDyloFiz6YhyDuPKVhW/O20hvCQ9ssCQQCj832BxjyROaoQYJXhW22i1TdeVkD6itcyZEeZTy5iQNA53F4R5FHXMGIUQjr2bkLcXFb5uyJXxxj+FqULpqe9AkEAmL8KO0867DkS+MjsaBS6hFOK41swfRKS93/uRIlAkKbG3C8pUXJ9MIyXpxLnGyuj9xfBZovIS+FLMpiSNvNMmQJAUxj5twoD7tOcV9qsfOF7Sznoa18qZhJzCi57f7/7UX9qSrlf0I+5z2jNfVkoS3kNWGVRDBiGSpk2GKrfkm0wvQJAc+VR6EfaGeNOxXihGMEblsdJg/FYzqYUGsqXg8UnlBK/BNZK3i1lyAy7Y4+3EbqSY1vJJK60eraDWkdjI6bWdw==";

	/**
	 * 
	 * @param base64PublicKey
	 * @return
	 */
	public static PublicKey getPublicKey(String base64PublicKey) {
		PublicKey publicKey = null;
		try {
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			publicKey = keyFactory.generatePublic(keySpec);
			return publicKey;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return publicKey;
	}

	/**
	 * 
	 * @param base64PrivateKey
	 * @return
	 */
	public static PrivateKey getPrivateKey(String base64PrivateKey) {
		PrivateKey privateKey = null;
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
		KeyFactory keyFactory = null;
		try {
			keyFactory = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			privateKey = keyFactory.generatePrivate(keySpec);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return privateKey;
	}

	/**
	 * 
	 * @param data
	 * @param publicKey
	 * @return
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeyException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
		return cipher.doFinal(data.getBytes());
	}

	/**
	 * 
	 * @param data
	 * @param privateKey
	 * @return
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return new String(cipher.doFinal(data));
	}

	public static String decrypt(String data, String base64PrivateKey) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		return decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(base64PrivateKey));
	}

	public static void main(String[] args) {
		try {
			String originalString = "Ocean is the author.";
			System.out.println("originalString: " + originalString);

			String encryptedString = Base64.getEncoder().encodeToString(encrypt(originalString, publicKey));
			System.out.println("encryptedString: " + encryptedString);

			String decryptedString = RSAUtil.decrypt(encryptedString, privateKey);
			System.out.println("decryptedString: " + decryptedString);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

		} catch (InvalidKeyException e) {
			e.printStackTrace();

		} catch (BadPaddingException e) {
			e.printStackTrace();

		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();

		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

}
