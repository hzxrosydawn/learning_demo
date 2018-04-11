package com.rosydawn.crypto.test;

import com.rosydawn.crypto.base64.BouncyCastleBase64Utils;

/**
 * Bouncy Castle的Base64实现测试类.
 *
 * @author Vincent
 **/
public class BouncyCastleBase64UtilsTest {
    public static void main(String[] args) {
        String testString = "我在学习Java加密与解密相关的技术。我在学习Java加密与解密相关的技术。";
        System.out.println("原文：" + testString);

        System.out.println("一般的Base64编码");
        String encodedString01 = BouncyCastleBase64Utils.encode(testString);
        // 编码后的字符串显示在一行上。
        System.out.println("编码后：" + encodedString01);
        String decodedString01 = BouncyCastleBase64Utils.decode(encodedString01);
        System.out.println("解码后：" + decodedString01);

        System.out.println("URL安全的Base64编码");
        String encodedString02 = BouncyCastleBase64Utils.safeEncode(testString);
        // 使用“-”代替“+”，使用“_”代替“/”，使用“.”代替“=”。
        System.out.println("编码后：" + encodedString02);
        String decodedString02 = BouncyCastleBase64Utils.safeDecode(encodedString02);
        System.out.println("解码后：" + decodedString02);

    }
}
