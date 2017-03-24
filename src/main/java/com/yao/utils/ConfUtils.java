package com.yao.utils;

/**
 * 项目配置文件
 * 
 * @author yaoyuxiao
 * @createDate 2016年8月2日 下午4:44:55
 */
public class ConfUtils {

	//公钥文件
	private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDOj0b49P+bgin1epHj3GC9d18QI+vkOynyczqlGUkZOLmBNPPlH2BTi3yg6NHOWNnqtOttO/NmKGj6/Bot41ARAybhip26FWOJ/jZZGiBqtz5zVTKP2hd63HeCmEFxCyS6SwiORHGTE2aTKRQ4M4zEvELcia5e87LLyeLxiLKdpwIDAQAB";
	//私钥文件
	private static String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAM6PRvj0/5uCKfV6kePcYL13XxAj6+Q7KfJzOqUZSRk4uYE08+UfYFOLfKDo0c5Y2eq0620782YoaPr8Gi3jUBEDJuGKnboVY4n+NlkaIGq3PnNVMo/aF3rcd4KYQXELJLpLCI5EcZMTZpMpFDgzjMS8QtyJrl7zssvJ4vGIsp2nAgMBAAECgYEAr0s2PtakCRPIifOv1YDTdFbWjPr/GFwBOCkDztKLj2TtQLJwfLRI6f2JEYEjdAtBPAa3eKcU41BN7xl9Gg47hf4j0gNs5Sg47rS1pJdfAHxFDzUFOu84DKJJXUA/ndypJ6YkwZHHUv49IQU1NnG8+27yzaxv23i2LcS6Mn1E51ECQQDoo2jdqTFC7d5Wv5IZoFIAbIJx9FIUnVjuFyb4cl/M84BytKbogOwpaSCfHGpfi7YF7zc75ooInV7w8c9tOW5ZAkEA401yQzs93ie9LwXMLPF+laBBRcMEW3QonO/oB1CpaMO3euVidiPqaJL2Z/hGbd9xDuwlfThnzcA/Axh0c4Lr/wJBAL3wTTpNXY8LrzCckKijoM//yDFKDYoPPgCGnqbSmy020nTU0kGaoTbeQmgmRyNjamZF/Y7Gl4SZGF8YzwaakWECQDSkWRQtAjUTKwNa6mB2RYTNqpBHjBjKHCXMggY/rhxvR20U9uq5gq5ZauvFWjpUB1EXa89GYvQJ1A4Zec7OwW0CQA/eWmlIZdDdP5hc/vk9iN4mE4+uNtMGA8V0DvhUQck5KnEbgGlf6xIlhmg8xH8HY7C1gyAtAUTxTQgJxMUvhLc=";

	public static String getPublicKey() {
		return publicKey;
	}

	public static String getPrivateKey() {
		return privateKey;
	}

	//项目网址
	private static String projectUrl = "http://localhost/xianglaiju";

	public static String getProjectUrl() {
		return projectUrl;
	}

}
