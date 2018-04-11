package com.rosydawn.crypto.base64;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Common Codec的一般Base64编解码有以下几种：
 * 一，部分遵循RFC 2045规范（与Bouncy Castle实现相同）。不在编码后的字符串中添加换行符，即所有编码后的字符串在一行显示；
 * 二，严格遵循RFC 2045规范。在编码后的每行字符串（默认每行最多76个字符）末尾添加回车换行符（\r\n）。
 * 三，也可以指定每行最多的字符数、自定义的行分隔符以及是否为URL安全来进行Base64编码，具有很强的定制性。
 * <p>
 * Common Codec的URL安全的Base64为编码遵照RFC 4648使用“-”代替“+”，使用“_”代替“/”，但为了安全去掉了“=”而不进行补位。
 * 所以，这样生成的URL安全的Base64编码是不定长的。
 *
 * @author Vincent Huang
 */
public class CommonsCodecBase64Utils {
    /**
     * 进行编码的字符串的字符集类型。
     */
    private final static String ENCODING_TYPE = "UTF-8";


    public static String commonEncode(String sourceString) {
        String encodedString = null;
        try {
            byte[] encodedBytes = Base64.encodeBase64(sourceString.getBytes(ENCODING_TYPE));
            encodedString = new String(encodedBytes, ENCODING_TYPE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedString;
    }

    public static String strictEncode(String sourceString) {
        String encodedString = null;
        try {
            byte[] encodedBytes = Base64.encodeBase64Chunked(sourceString.getBytes(ENCODING_TYPE));
            encodedString = new String(encodedBytes, ENCODING_TYPE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedString;
    }

    public static String customizeEncode(String sourceString, int lineLength, byte[] lineSeparator, boolean urlSafe) {
        String encodedString = null;
        Base64 base64 = new Base64(lineLength, lineSeparator, urlSafe);
        try {
            byte[] encodedBytes = base64.encode(sourceString.getBytes(ENCODING_TYPE));
            encodedString = new String(encodedBytes, ENCODING_TYPE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedString;
    }

    public static String commonUrlSafeEncode(String sourceString) {
        String encodedString = null;
        try {
            byte[] encodedBytes = Base64.encodeBase64URLSafe(sourceString.getBytes(ENCODING_TYPE));
            encodedString = new String(encodedBytes, ENCODING_TYPE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedString;
    }

    public static String decode(String base64String) {
        String decodedString = null;
        byte[] bytes = Base64.decodeBase64(base64String);
        try {
            decodedString = new String(bytes, ENCODING_TYPE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodedString;
    }
}
