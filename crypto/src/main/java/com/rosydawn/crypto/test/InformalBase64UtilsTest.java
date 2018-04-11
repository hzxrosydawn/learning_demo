package com.rosydawn.crypto.test;

import com.rosydawn.crypto.base64.InformalBase64Utils;

/**
 * Sun的非正式Base64实现测试类.
 *
 * @author Vincent Huang
 **/
public class InformalBase64UtilsTest {
    public static void main(String[] args) {
        String testString = "我在学习Java加密与解密相关的技术。我在学习Java加密与解密相关的技术。";
        System.out.println("原文：" + testString);

        System.out.println("严格的Base64编码，但不建议使用");
        String encodedString = InformalBase64Utils.encode(testString);
        // 编码后的字符串最多76个字符一行
        System.out.println("编码后：" + encodedString);
        String decodedString01 = InformalBase64Utils.decode(encodedString);
        System.out.println("解码后：" + decodedString01);
    }
}
