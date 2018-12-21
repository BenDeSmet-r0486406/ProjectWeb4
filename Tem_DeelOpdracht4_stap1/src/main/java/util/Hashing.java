package util;

import domain.DomainException;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {

	public static String SHA256(String str, String salt) {
		String hashedStr;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-256");
			crypt.reset();
			crypt.update(salt.getBytes("UTF-8"));
			crypt.update(str.getBytes("UTF-8"));
			hashedStr = new BigInteger(1, crypt.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		} catch (UnsupportedEncodingException e) {
			throw new DomainException(e.getMessage(), e);
		}
		return hashedStr;
	}

}
