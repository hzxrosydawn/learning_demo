package com.rosydawn.crypto.base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * java.util.Base64（JDK 1.8开始支持）支持的Base64编解码有三种形式：
 * Basic类型。按照RFC 2045规范中的Table 1: The Base 64 Alphabet映射表（RFC 4648规范也有该表）进行编解码，但不在编码后的每行字
 * 符串末尾添加换行符，所有编码后的字符串在一行显示，与BouncyCastle实现相同；
 * MIME类型。按照RFC 2045规范中的Table 1: The Base 64 Alphabet映射表（RFC 4648规范也有该表）进行编解码，每行不超过76个字符。 除了
 * 最后一行以外，所有行都以“\r\n”结尾。还可以自定义每行最多的字符数、行分隔符；
 * URL and Filename safe类型。遵循RFC 4648中表2规定的URL和文件名安全的编码类型，使用“-”代替“+”，使用“_”代替“/”，但“=”不处理。
 *
 * @author Vincent Huang
 */
public class OracleBase64Utils {
    /**
     * 进行编码的字符串的字符集类型
     */
    private final static String ENCODING_TYPE = "UTF-8";

    public static String commonEncode(String sourceString) {
        String encodedString = null;
        try {
            encodedString = Base64.getEncoder().encodeToString(sourceString.getBytes(ENCODING_TYPE));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedString;
    }

    public static String commonDecode(String base64String) {
        String decodedString = null;
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64String.getBytes(ENCODING_TYPE));
            decodedString = new String(decodedBytes, ENCODING_TYPE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodedString;
    }

    public static String strictEncode(String sourceString) {
        String encodedString = null;
        try {
            encodedString = Base64.getMimeEncoder().encodeToString(sourceString.getBytes(ENCODING_TYPE));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedString;
    }

    public static String strictDecode(String base64String) {
        String decodedString = null;
        try {
            byte[] decodedBytes = Base64.getMimeDecoder().decode(base64String.getBytes(ENCODING_TYPE));
            decodedString = new String(decodedBytes, ENCODING_TYPE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodedString;
    }

    public static String customizeEncode(String sourceString, int lineLength, byte[] lineSeparator) {
        String encodedString = null;
        try {
            // 每行最多为30个字符（向下取4的最大整数倍，所以这里每行显示28个字符），以水平制表符为行分隔符而不是回车换行符。
            encodedString = Base64.getMimeEncoder(lineLength, lineSeparator).encodeToString(sourceString.getBytes(ENCODING_TYPE));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedString;
    }

    public static String customizeDecode(String base64String) {
        return strictDecode(base64String);
    }

    public static String urlSafeEncode(String sourceString) {
        String encodedString = null;
        try {
            encodedString = Base64.getUrlEncoder().encodeToString(sourceString.getBytes(ENCODING_TYPE));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedString;
    }

    public static String urlSafeDecode(String base64String) {
        String decodedString = null;
        try {
            byte[] decodedBytes = Base64.getUrlDecoder().decode(base64String.getBytes(ENCODING_TYPE));
            decodedString = new String(decodedBytes, ENCODING_TYPE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodedString;
    }
}
