package com.rosydawn.crypto.base64;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.UrlBase64;

import java.io.UnsupportedEncodingException;

/**
 * Bouncy Castle的一般Base64编码没有严格遵循RFC 2045规范。不在编码后的每行字符串末尾添加换行符，即所有编码后的字符串在一行显示。
 * <p>
 * RFC 4648建议URL安全的Base64编码分别使用“-”和“_”代替“+”和“/”，使用“~”或“.”来代替“=”，
 * 来达到URL传输安全的目的（+，/和=在URL中都有特殊意义）。但“~”与文件系统冲突，不能使用，而“.”在某些文件系统中出现两次就为错误。
 * Bouncy Castle的UrlBase64类使用“.”来代替“=”来实现定长的URL安全的Base编码，但连续出现两次“.”可能会有问题。
 *
 * @author Vincent Huang
 */
public class BouncyCastleBase64Utils {
    /**
     * 进行编码的字符串的字符集类型。
     */
    private static final String ENCODING_TYPE = "UtF-8";

    /**
     * Bouncy Castle提供的一般Base64编码实现，该实现没有严格遵循RFC 2045规范，即不在编码后的每行字符串末尾添加换行符，所有编码后的字
     * 符串在一行显示。
     *
     * @param sourceString 待进行Base64编码的源字符串。以UTF-8为字符集读取。
     * @return Base64编码后的16进制字符串。
     */
    public static String encode(String sourceString) {
        String encodedString = null;
        try {
            encodedString = Base64.toBase64String(sourceString.getBytes(ENCODING_TYPE));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedString;
    }

    /**
     * Bouncy Castle提供的一般Base64解码实现。
     *
     * @param base64String 待进行Base64解码的16进制字符串。以UTF-8为字符集读取。
     * @return Base64解码后的源字符串。
     */
    public static String decode(String base64String) {
        String decodedString = null;
        try {
            byte[] decodedBytes = Base64.decode(base64String.getBytes(ENCODING_TYPE));
            decodedString = new String(decodedBytes, ENCODING_TYPE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodedString;
    }

    /**
     * Bouncy Castle提供的URL安全的Base64编码实现。RFC 4648建议URL安全的Base64编码分别使用“-”和“_”代替“+”和“/”，使用“~”或“.”来代
     * 替“=”。但“~”与文件系统冲突，不能使用，而“.”在某些文件系统中出现两次就为错误。Bouncy Castle的UrlBase64类使用“.”来代替“=”来实
     * 现定长的URL安全的Base编码，但连续出现两次“.”可能会有问题。
     *
     * @param sourceString 待进行URL安全的Base64编码的源字符串。以UTF-8为字符集读取。
     * @return 经过URL安全的Base64编码后的字符串。
     */
    public static String safeEncode(String sourceString) {
        String encodedString = null;
        try {
            byte[] encodedBytes = UrlBase64.encode(sourceString.getBytes(ENCODING_TYPE));
            encodedString = new String(encodedBytes, ENCODING_TYPE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedString;
    }

    /**
     * Bouncy Castle提供的URL安全的Base64解码实现。
     *
     * @param base64String 待进行URL安全的Base64解码的16进制字符串。以UTF-8为字符集读取。
     * @return 经过URL安全的Base64解码后的源字符串。
     */
    public static String safeDecode(String base64String) {
        String decodedString = null;
        try {
            byte[] decodedBytes = UrlBase64.decode(base64String.getBytes(ENCODING_TYPE));
            decodedString = new String(decodedBytes, ENCODING_TYPE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodedString;
    }
}
