package com.api.common.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
//import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import jakarta.xml.bind.DatatypeConverter;

@Service
public class AESUtil {

	public String decrypt(String strEncrypted) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] encryptedData = DatatypeConverter.parseBase64Binary(strEncrypted);
		byte[] raw = DatatypeConverter.parseHexBinary("788becf1635547f59321b3f1a6f0ef28");
		// IvParameterSpec iv = new IvParameterSpec(raw);
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		// cipher.init(2, skeySpec, iv);
		cipher.init(2, skeySpec);
		byte[] plainTextBytes = cipher.doFinal(encryptedData);

		return new String(plainTextBytes);
	}

//	public static void main(String[] args) {
//		System.out.println("Decripted: " + decrypt("JUhLRVkk3Ns1GRvBY8wY3Q=="));
//	}

}
