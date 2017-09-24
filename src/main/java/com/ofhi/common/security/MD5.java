package com.ofhi.common.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.ofhi.common.user.UserConst;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5 {
	private MD5 (){}
	private static Logger logger = LoggerFactory.getLogger(MD5.class);
	/**
	 * 利用MD5进行加密 　　
	 * @param plainText 待加密的字符串 　
	 * @return 加密后的字符串 　　
	 */
	public static String encryption(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// 32位加密
			return buf.toString();
			// 16位的加密
			// return buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			logger.error("MD5加密异常", e);
			return "";
		}
	}
	
	public static String createPassword(String password) {
		return new Md5Hash(password, UserConst.PASSWORD_SALT, 10).toString();
	}
}
