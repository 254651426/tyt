package com.bquan.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESUtil {

	/**
	 * 加密
	 * @param encData 要加密的数据
	 * @param secretKey 密钥 ,16位的数字和字母
	 * @param vector 初始化向量,16位的数字和字母
	 * @return
	 * @throws Exception
	 */
	public static String Encrypt(String encData ,String secretKey,String vector) throws Exception {
		
		if(secretKey == null) {
			return null;
		}
		if(secretKey.length() != 16) {
			return null;
		}
		byte[] raw = secretKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
		IvParameterSpec iv = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(encData.getBytes());
		return ObjectSerializer.encodeBytes( encrypted );
	}
	
	/**
	 * 解密
	 * @param decData 要解密的数据
	 * @param secretKey 密钥 ,16位的数字和字母
	 * @param vector 初始化向量,16位的数字和字母
	 * @return
	 * @throws Exception
	 */
	public static String Decrypt(String decData, String secretKey, String vector) throws Exception {
		if(secretKey == null) {
			return null;
		}
		if(secretKey.length() != 16) {
			return null;
		}
		try {
			byte[] raw = secretKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(vector.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(decodeBytes(decData));
			return new String(original);
		} catch (Exception ex) {
			return null;
		}
	}
		
	/**
	 * 转字节数组
	 * @param str
	 * @return
	 */
	public static byte[] decodeBytes(String str) {
		byte[] bytes = new byte[str.length() / 2];
		for (int i = 0; i < str.length(); i += 2) {
			char c = str.charAt(i);
			bytes[i / 2] = (byte) ((c - 'a') << 4);
			c = str.charAt(i + 1);
			bytes[i / 2] += (c - 'a');
		}
		return bytes;
	}	
	
	/**
	 * 加密(base64)
	 * @param sSrc 要加密的数据
	 * @param sKey 密钥 ,16位的数字和字母
	 * @param ivParameter 初始化向量,16位的数字和字母
	 * @return
	 * @throws Exception
	 */
	public static String EncryptForBase64(String sSrc, String sKey, String ivParameter) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
		return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
	}
	
	/**
	 * 解密(base64)
	 * @param decData 要解密的数据
	 * @param secretKey 密钥 ,16位的数字和字母
	 * @param vector 初始化向量,16位的数字和字母
	 * @return
	 * @throws Exception
	 */
	public static String DecryptForBase64(String decData, String secretKey, String vector) throws Exception {
		if(secretKey == null) {
			return null;
		}
		if(secretKey.length() != 16) {
			return null;
		}
		try {
			byte[] raw = secretKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(vector.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(new BASE64Decoder().decodeBuffer(decData));
			return new String(original);
		} catch (Exception ex) {
			return null;
		}
	}
	
	/**
	 *
	 * 十六进制转换字符串
	 */

	public static byte[] hexStr2Bytes(String hexStr) {
//		System.out.println("in len :" + hexStr.length());
		String str = "0123456789ABCDEF";
		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int n;
		for (int i = 0; i < bytes.length; i++) {
			n = str.indexOf(hexs[2 * i]) * 16;
			n += str.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (n & 0xff);
		}
//		System.out.println("out len :" + bytes.length);
//		System.out.println("ddd" + Arrays.toString(bytes));
		return bytes;
	}

	/**
	 * bytes转换成十六进制字符串
	 */
	public static String byte2HexStr(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			// if (n<b.length-1) hs=hs+":";
		}
		return hs.toUpperCase();
	}

	
	/**
	 * 加密(十六进制)
	 * @param value 要加密的的数据
	 * @param key 密钥 ,16位的数字和字母
	 * @param initVector 初始化向量,16位的数字和字母
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String key, String initVector, String value) {
		try {
//			System.out.println("key:\t" + Arrays.toString(key.getBytes("UTF-8")));
//			System.out.println("iv:\t" + Arrays.toString(initVector.getBytes("UTF-8")));
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			// Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());
//			System.out.println(Arrays.toString(encrypted));
			// System.out.println("encrypted string: "
			// + Base64.encodeBase64String(encrypted));

			return byte2HexStr(encrypted);
			// return Base64.encodeBase64String(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	/**
	 * 解密(十六进制)
	 * @param encrypted 要解密的数据
	 * @param key 密钥 ,16位的数字和字母
	 * @param initVector 初始化向量,16位的数字和字母
	 * @throws Exception
	 */
	public static String decrypt(String key, String initVector, String encrypted) {
		try {
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			// byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
			byte[] original = cipher.doFinal(hexStr2Bytes(encrypted));

			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
	public static void main(String[] args) throws Exception{
//		String content = "{\\\"request_no\\\":\\\"1000000004\\\",\\\"service_code\\\":\\\"FS0001\\\",\\\"channel_id\\\":\\\"1\\\",\\\"contract_id\\\":\\\"FBMP20140106001\\\",\\\"order_id\\\":\\\"0\\\",\\\"activity_id\\\":\\\"100005\\\",\\\"phone_id\\\":\\\"18022887432\\\",\\\"plat_offer_id\\\":\\\"100051\\\"}";
		String content="123";
		String jie="7EE70225D3FD9A9F2F89F9AE566AA89D";
		System.out.println(AESUtil.encrypt("H5gOslZshKZxWikN", "8888199201112173",content));
		System.out.println(AESUtil.decrypt("H5gOslZshKZxWikN", "8888199201112173",jie));
	}
}
