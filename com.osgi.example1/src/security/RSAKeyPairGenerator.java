package security;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class RSAKeyPairGenerator {

	private PrivateKey privateKey;
	private PublicKey publicKey;

	public RSAKeyPairGenerator() throws NoSuchAlgorithmException {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(1024);
		KeyPair pair = keyGen.generateKeyPair();
		this.privateKey = pair.getPrivate();
		this.publicKey = pair.getPublic();
	}

	public PrivateKey getPrivateKey() {
		return this.privateKey;
	}

	public PublicKey getPublicKey() {
		return this.publicKey;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		// RSAKeyPairGenerator keyPairGenerator = new RSAKeyPairGenerator();
		// byte[] publicKey = keyPairGenerator.getPublicKey().getEncoded();
		// byte[] privateKey = keyPairGenerator.getPrivateKey().getEncoded();
		// System.out.println("RSA/publicKey: " + Base64.getEncoder().encodeToString(publicKey));
		// System.out.println("RSA/privateKey: " + Base64.getEncoder().encodeToString(privateKey));

		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(1024);
		KeyPair pair = keyGen.generateKeyPair();
		byte[] publicKey = pair.getPublic().getEncoded();
		byte[] privateKey = pair.getPrivate().getEncoded();
		System.out.println("RSA/publicKey: " + Base64.getEncoder().encodeToString(publicKey));
		System.out.println("RSA/privateKey: " + Base64.getEncoder().encodeToString(privateKey));
	}

}

// keyPairGenerator.writeToFile("RSA/publicKey", keyPairGenerator.getPublicKey().getEncoded());
// keyPairGenerator.writeToFile("RSA/privateKey", keyPairGenerator.getPrivateKey().getEncoded());

// public void writeToFile(String path, byte[] key) throws IOException {
// File f = new File(path);
// f.getParentFile().mkdirs();
//
// FileOutputStream fos = new FileOutputStream(f);
// fos.write(key);
// fos.flush();
// fos.close();
// }
