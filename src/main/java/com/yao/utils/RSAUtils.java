package com.yao.utils;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

/**
 * RSA算法非对称加解密
 * 
 * @author yaoyuxiao
 * @createDate 2016年8月2日 下午1:37:00
 */
public class RSAUtils {

	/**
	 * 加密算法RSA
	 */
	public static final String KEY_ALGORITHM = "RSA";

	/**
	 * 签名算法
	 */
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	/**
	 * 获取公钥的key
	 */
	private static final String PUBLIC_KEY = ConfUtils.getPublicKey();

	/**
	 * 获取私钥的key
	 */
	private static final String PRIVATE_KEY = ConfUtils.getPrivateKey();

	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	/**
	 * 生成密钥对(公钥和私钥)
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> genKeyPair() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(512);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put("publicKey", publicKey);
		keyMap.put("privateKey", privateKey);
		return keyMap;
	}

	/**
	 * 用私钥对信息生成数字签名
	 * 
	 * @param data 已加密数据
	 * @param privateKey 私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	private static String sign(byte[] data, String privateKey) throws Exception {
		byte[] keyBytes = Base64Utils.decode(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(privateK);
		signature.update(data);
		return Base64Utils.encode(signature.sign());
	}

	/**
	 * 校验数字签名
	 * 
	 * @param data 已加密数据
	 * @param publicKey  公钥(BASE64编码)
	 * @param sign 数字签名
	 * @return
	 * @throws Exception
	 */
	private static boolean verify(byte[] data, String publicKey, String sign)
			throws Exception {
		byte[] keyBytes = Base64Utils.decode(publicKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicK = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(publicK);
		signature.update(data);
		return signature.verify(Base64Utils.decode(sign));
	}

	/**
	 * 私钥解密
	 * 
	 * @param encryptedData 已加密数据
	 * @param privateKey 私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	private static byte[] decryptByPrivateKey(byte[] encryptedData,
			String privateKey) throws Exception {
		byte[] keyBytes = Base64Utils.decode(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * 公钥解密
	 * 
	 * @param encryptedData 已加密数据
	 * @param publicKey 公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	private static byte[] decryptByPublicKey(byte[] encryptedData,
			String publicKey) throws Exception {
		byte[] keyBytes = Base64Utils.decode(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * 公钥加密
	 * 
	 * @param data 源数据
	 * @param publicKey 公钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	private static byte[] encryptByPublicKey(byte[] data, String publicKey)
			throws Exception {
		byte[] keyBytes = Base64Utils.decode(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * 私钥加密
	 * 
	 * @param data 源数据
	 * @param privateKey 私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	private static byte[] encryptByPrivateKey(byte[] data, String privateKey)
			throws Exception {
		byte[] keyBytes = Base64Utils.decode(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * 获取私钥
	 * 
	 * @param keyMap 密钥对
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public static String getPrivateKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get("privateKey");
		return Base64Utils.encode(key.getEncoded());
	}

	/**
	 * 获取公钥
	 * 
	 * @param keyMap 密钥对
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public static String getPublicKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get("publicKey");
		return Base64Utils.encode(key.getEncoded());
	}

	/******** 下面为封装的接口，供加密、解密和签名、验签使用的接口******下面为封装的接口，供加密、解密和签名、验签使用的接口 ********************/

	/**
	 * 公钥加密的接口
	 * 
	 * @param source  数据源
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptPublic(String source) throws Exception {
		byte[] encodedData = source.getBytes();
		byte[] decodedData = RSAUtils.encryptByPublicKey(encodedData, PUBLIC_KEY);
		return decodedData;
	}

	/**
	 * （公钥加密--私钥解密）的验证接口
	 * 
	 * @param source  数据源
	 * @param encodedData  已加密的数据
	 * @return
	 * @throws Exception
	 */
	public static boolean publicKeyTOprivateKey(String source,
			byte[] encodedData) throws Exception {
		boolean result = false;
		byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData, PRIVATE_KEY);
		String target = new String(decodedData);
		if (source.equals(target)) {
			result = true;
		}
		return result;
	}

	/**
	 * 私钥加密的接口
	 * 
	 * @param source  数据源
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptPrivate(String source)
			throws Exception {
		byte[] encodedData = source.getBytes();
		byte[] decodedData = RSAUtils.encryptByPrivateKey(encodedData,PRIVATE_KEY);
		return decodedData;

	}

	/**
	 * （私钥加密--公钥解密）的验证接口
	 * 
	 * @param source 数据源
	 * @param encodedData  已加密的数据
	 * @return
	 * @throws Exception
	 */
	public static boolean privateKeyTOpublicKey(String source,
			byte[] encodedData) throws Exception {
		boolean result = false;
		byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, PUBLIC_KEY);
		String target = new String(decodedData);
		if (source.equals(target)) {
			result = true;
		}
		return result;
	}

	/**
	 * 私钥签名的接口
	 * 
	 * @param source 已加密的数据
	 * @return
	 * @throws Exception
	 */
	public static String privateSign(byte[] source)
			throws Exception {
		String sign = RSAUtils.sign(source, PRIVATE_KEY);
		return sign;
	}

	/**
	 * （私钥签名——公钥验证签）的验证接口
	 * 
	 * @param source 已签名的数据
	 * @param encodedData 已加密的数据
	 * @return
	 * @throws Exception
	 */
	public static boolean privatesignTOpublicsign(String source,
			byte[] encodedData) throws Exception {
		boolean status = RSAUtils.verify(encodedData, PUBLIC_KEY, source);
		return status;
	}
	
	/**
	 * 私钥解密接口
	 * 
	 * @param source 已加密的数据
	 * @return
	 * @throws Exception
	 */
	public static String  decryptPrivate(String source) throws Exception {
		byte[] encryptedData=source.getBytes();
		String  stat= RSAUtils.decryptByPrivateKey(encryptedData, ConfUtils.getPrivateKey()).toString();
		return stat;
	}
	
}
