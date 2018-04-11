package com.rosydawn.crypto.test;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 摘要算法测试类。
 *
 * @author Vincent Huang
 **/
public class DigestUtilsTest {
    public static void main(String[] args) throws Exception {
        String testString = "消息摘要算法测试字符串";

        System.out.println("JDK原生MD2编码结果：\n" +
                com.rosydawn.crypto.digest.DigestUtils.digest(testString, "MD2"));
        System.out.println("Commons Codec的MD2编码结果：\n" + DigestUtils.md2Hex(testString));
        System.out.println("JDK原生MD5编码结果：\n" +
                com.rosydawn.crypto.digest.DigestUtils.digest(testString, "MD5"));
        System.out.println("Commons Codec的MD5编码结果：\n" + DigestUtils.md5Hex(testString));
        System.out.println("Bouncy Castle的MD4编码结果：\n" +
                com.rosydawn.crypto.digest.DigestUtils.digest(testString, "MD4"));

        System.out.println("JDK原生SHA-1编码结果：\n" +
                com.rosydawn.crypto.digest.DigestUtils.digest(testString, "SHA-1"));
        System.out.println("Commons Codec的SHA-1编码结果：\n" + DigestUtils.sha1Hex(testString));
        System.out.println("JDK原生的SHA-224编码结果：\n" +
                com.rosydawn.crypto.digest.DigestUtils.digest(testString, "SHA-224"));
        System.out.println("JDK原生SHA-256编码结果：\n" +
                com.rosydawn.crypto.digest.DigestUtils.digest(testString, "SHA-256"));
        System.out.println("Commons Codec的SHA-256编码结果：\n" + DigestUtils.sha256Hex(testString));
        System.out.println("JDK原生SHA-384编码结果：\n" +
                com.rosydawn.crypto.digest.DigestUtils.digest(testString, "SHA-384"));
        System.out.println("Commons Codec的SHA-384编码结果：\n" + DigestUtils.sha384Hex(testString));
        System.out.println("JDK原生SHA-512编码结果：\n" +
                com.rosydawn.crypto.digest.DigestUtils.digest(testString, "SHA-512"));
        System.out.println("Commons Codec的SHA-512编码结果：\n" + DigestUtils.sha512Hex(testString));


        // 由于需要初始化密钥，每次密钥可能不同，所以摘要结果也可能不同
        byte[] hmacMD2KeyBytes = com.rosydawn.crypto.digest.DigestUtils.initHmacKey("HmacMD2");
        System.out.println("Bouncy Castle的HmacMD2编码结果：\n" +
                com.rosydawn.crypto.digest.DigestUtils.encodeHmac(testString.getBytes(), hmacMD2KeyBytes,
                        "HmacMD2"));
        byte[] hmacMD5KeyBytes = com.rosydawn.crypto.digest.DigestUtils.initHmacKey("HmacMD5");
        System.out.println("JDK原生的HmacMD5编码结果：\n" +
                com.rosydawn.crypto.digest.DigestUtils.encodeHmac(testString.getBytes(), hmacMD5KeyBytes,
                        "HmacMD5"));
        byte[] hmacMD4KeyBytes = com.rosydawn.crypto.digest.DigestUtils.initHmacKey("HmacMD4");
        System.out.println("Bouncy Castle的HmacMD4编码结果：\n" +
                com.rosydawn.crypto.digest.DigestUtils.encodeHmac(testString.getBytes(), hmacMD4KeyBytes,
                        "HmacMD4"));
        byte[] hmacSHA1KeyBytes = com.rosydawn.crypto.digest.DigestUtils.initHmacKey("HmacSHA1");
        System.out.println("JDK原生的HmacSHA1编码结果：\n" +
                com.rosydawn.crypto.digest.DigestUtils.encodeHmac(testString.getBytes(), hmacSHA1KeyBytes,
                        "HmacSHA1"));
        byte[] hmacSHA224KeyBytes = com.rosydawn.crypto.digest.DigestUtils.initHmacKey("HmacSHA224");
        System.out.println("Bouncy Castle的HmacSHA224编码结果：\n" +
                com.rosydawn.crypto.digest.DigestUtils.encodeHmac(testString.getBytes(), hmacSHA224KeyBytes,
                        "HmacSHA224"));

        byte[] hmacSHA256KeyBytes = com.rosydawn.crypto.digest.DigestUtils.initHmacKey("HmacSHA256");
        System.out.println("JDK原生的HmacSHA256编码结果：\n" +
                com.rosydawn.crypto.digest.DigestUtils.encodeHmac(testString.getBytes(), hmacSHA256KeyBytes,
                        "HmacSHA256"));
        byte[] hmacSHA384KeyBytes = com.rosydawn.crypto.digest.DigestUtils.initHmacKey("HmacSHA384");
        System.out.println("JDK原生的HmacSHA384编码结果：\n" +
                com.rosydawn.crypto.digest.DigestUtils.encodeHmac(testString.getBytes(), hmacSHA384KeyBytes,
                        "HmacSHA384"));
        byte[] hmacSHA512KeyBytes = com.rosydawn.crypto.digest.DigestUtils.initHmacKey("HmacSHA512");
        System.out.println("JDK原生的HmacSHA512编码结果：\n" +
                com.rosydawn.crypto.digest.DigestUtils.encodeHmac(testString.getBytes(), hmacSHA512KeyBytes,
                        "HmacSHA512"));

        // 将字节数组转换成16进制表示的字符串
        System.out.println("自定义的16进制转换实现：\n" +
                com.rosydawn.crypto.digest.DigestUtils.bytesToHexString(testString.getBytes()));
        System.out.println("Commons Codec的16进制转换实现：\n" + Hex.encodeHexString(testString.getBytes()));
    }
}
