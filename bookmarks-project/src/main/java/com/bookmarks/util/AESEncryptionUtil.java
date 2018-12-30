package com.bookmarks.util;

import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.Logger;
import sun.misc.*;

public class AESEncryptionUtil {

	private final static Logger logger = Logger.getLogger(AESEncryptionUtil.class);
	private static final String ALGORITHM = "AES";
	private static final int ITERATIONS = 2;
	private static final byte[] keyValue = new byte[] { 'g', 'A', '!', '6', 'H', '8', 'l', 'Y', 'f', 'G', 'f', 'S', 'a',
			'd', 'D', 'R' };
	private static String salt = "dWcs?;wsW1SD";

	@SuppressWarnings("restriction")
	public static String encrypt(String value) {
		String valueToEnc = null;
		String eValue = value;
		try {
			Key key = generateKey();
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, key);

			for (int i = 0; i < ITERATIONS; i++) {
				valueToEnc = salt + eValue;
				byte[] encValue = c.doFinal(valueToEnc.getBytes());
				eValue = new BASE64Encoder().encode(encValue);
			}
		} catch (Exception e) {
			logger.error("String could not be encrypted: " + e.getMessage());
		}
		return eValue;
	}

	@SuppressWarnings("restriction")
	public static String decrypt(String value) {
		String dValue = null;
		String valueToDecrypt = value;
		try {
			Key key = generateKey();
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, key);

			for (int i = 0; i < ITERATIONS; i++) {
				byte[] decordedValue = new BASE64Decoder().decodeBuffer(valueToDecrypt);
				byte[] decValue = c.doFinal(decordedValue);
				dValue = new String(decValue).substring(salt.length());
				valueToDecrypt = dValue;
			}
		} catch (Exception e) {
			logger.error("String could not be decrypted: " + e.getMessage());
		}
		return dValue;
	}

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGORITHM);
		return key;
	}
}
