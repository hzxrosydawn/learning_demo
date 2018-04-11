package com.rosydawn.crypto.digest;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * JDK 6和JDK 7原生支持MD2、MD5、SHA-1、SHA-256、SHA-384、SHA-512算法实现，需要手动实现字节数组和16进制字符串之间的转换。
 *
 * Bouncy Castle提供了JDK 6/7没有的MD4和SHA-224的支持，支持将字节数组和16进制字符串。
 * Commons Codec封装了JDK的MD算法实现，使用更加方便，也支持将字节数组和16进制字符串。
 *
 * JDK 6/7原生支持HmacMD5、HmacSHA1、HmacSHA256、HmacSHA384、HmacSHA512的算法支持。
 * Bouncy Castle提供了HmacMD2、HmacMD4、HmacSHA224三种算法的实现。
 *
 * JDK 8增加了SHA-224和HmacSHA224的支持。
 *
 */
public class DigestUtils {
    /**
     * 进行字符串和字节转换的字符集类型。
     */
    private static final String ENCODING_TYPE = "UTF-8";

    /**
     * 进行摘要计算。
     *
     * @param sourceString 要进行摘要计算的字符串。
     * @param digestName   摘要算法名，不区分大小写。该参数可以为“MD2”、“MD4”、“MD5”、“SHA”、“SHA-1”（同“SHA”）、“SHA-224”、
     *                     “SHA-256”、“SHA-384”和“SHA-512”。
     * @return 摘要计算结果，为16进制字符的字符串。MD2和MD5算法返回32个16进制字符的字符串，SHA-1算法返回40个16进制字符（160位），
     * SHA-224算法返回56个16进制字符（224位），SHA-256算法返回64个16进制字符（256位），SHA-384算法返回96个16进制字符（384位），
     * SHA-512算法返回128个16进制字符（512位）。
     */
    public static String digest(String sourceString, String digestName) {
        String mdString = null;
        try {
            // 初始化MessageDigest。
            // 如果选择Bouncy Castle的算法支持则需要额外选择下面一行语句。
            Security.addProvider(new BouncyCastleProvider());
            MessageDigest messageDigest = MessageDigest.getInstance(digestName);
            // 进行摘要计算。
            byte[] digestBytes = messageDigest.digest(sourceString.getBytes(ENCODING_TYPE));
            // 将字节数组转换成16进制字符串。
//            mdString = bytesToHexString(digestBytes);
            mdString = Hex.encodeHexString(digestBytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return mdString;
    }

    /**
     * 将字节数组转换为16进制字串。
     *
     * @param bytes 字节数组。
     * @return 16进制字串。
     */
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
        for (byte b : bytes) {
            int high = ((b & 0xf0) >> 4);
            int low = (b & 0x0f);
            hexString.append(hexDigits[high]);
            hexString.append(hexDigits[low]);
        }
        return hexString.toString();
    }

    /**
     * 进行Hmac摘要计算。
     *
     * @param dataBytes  要进行摘要计算的字符串。
     * @param keyBytes   要进行摘要计算的密钥。
     * @param digestName 要进行摘要计算的算法名称，可以是上面提到的各种算法名。
     * @return ，为16进制字符的字符串。MD2和MD5算法返回32个16进制字符的字符串，SHA-1算法返回40个16进制字符（160位），SHA-224算法返
     * 回56个16进制字符（224位）、SHA-256算法返回64个16进制字符（160位），SHA-384算法返回96个16进制字符（256位），SHA-512算法返回
     * 128个16进制字符（512位）。
     */
    public static String encodeHmac(byte[] dataBytes, byte[] keyBytes, String digestName) {
        String digestString = null;
        // 如果选择Bouncy Castle的算法支持则需要额外选择下面一行语句。
        Security.addProvider(new BouncyCastleProvider());
        // 还原密钥
        SecretKey secretKey = new SecretKeySpec(keyBytes, digestName);
        try {
            // 实例化Mac
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            // 初始化Mac
            mac.init(secretKey);
            // 执行消息摘要
            byte[] digestBytes = mac.doFinal(dataBytes);
            digestString = Hex.encodeHexString(digestBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return digestString;
    }


    /**
     * 进行Hmac算法的密钥初始化工作。JDK原生支持HmacMD5、HmacSHA1、HmacSHA256、HmacSHA384、HmacSHA512的算法支持。
     * Bouncy Castle提供了HmacMD2、HmacMD4、HmacSHA224三种算法的实现。
     *
     * @param digestName 要进行摘要计算的算法名称，可以是上面提到的各种算法名。
     * @return 初始化后的密钥。
     */
    public static byte[] initHmacKey(String digestName) {
        byte[] keyBytes = null;
        // 加入Bouncy Castle的支持
        Security.addProvider(new BouncyCastleProvider());
        try {
            // 初始化KeyGenerator
            KeyGenerator keyGenerator = KeyGenerator.getInstance(digestName);
            // 生成秘密密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 获得密钥
            keyBytes = secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyBytes;
    }
}
