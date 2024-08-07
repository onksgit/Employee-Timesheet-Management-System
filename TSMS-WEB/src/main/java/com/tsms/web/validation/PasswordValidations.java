package com.tsms.web.validation;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.tsms.web.utils.AESEncDecUtils;

public class PasswordValidations {

	public static boolean isValidPassword(String userPassword, String encryptedPass, String secretKeys, String secretIv) throws Exception {
		SecretKey decodeSecretKey = AESEncDecUtils.decodeSecretKey(secretKeys);
		IvParameterSpec decodeIv = AESEncDecUtils.decodeIv(secretIv);
		String decodePassword = AESEncDecUtils.decrypt(encryptedPass,decodeSecretKey,decodeIv);
		boolean flag = false;
		if(decodePassword.equals(userPassword)) {
			flag = true;
		}
		return flag;
	}

}
