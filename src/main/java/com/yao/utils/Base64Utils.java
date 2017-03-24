package com.yao.utils;

import org.apache.commons.codec.binary.Base64;

/**
 * 字节码转换
 * 
 * @author yaoyuxiao
 * @createDate 2016年8月2日 下午1:36:40
 */
public class Base64Utils {

    
    /**
     * BASE64字符串解码为二进制数据
     * @param base64
     * @return
     * @throws Exception
     */
    public static byte[] decode(String base64) throws Exception {
    	Base64 df=new Base64();
    	return df.decode(base64.getBytes());
    }
    
    /**
     * 二进制数据编码为BASE64字符串
     * @param bytes
     * @return
     * @throws Exception
     */
    public static String encode(byte[] bytes) throws Exception {
    	Base64 df=new Base64();
        return new String(df.encode(bytes));
    }
}