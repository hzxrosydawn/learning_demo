package com.rosydawn.crypto.test;

import com.rosydawn.crypto.base64.OracleBase64Utils;

/**
 * JDK自带的Base64实现测试
 *
 * @author Vincent
 **/
public class OracleBase64UtilsTest {
    public static void main(String[] args) {
        String testString = "我在学习Java加密与解密相关的技术。我在学习Java加密与解密相关的技术。";
        System.out.println("原文：" + testString);

        // 部分遵循RFC 2045规范（与Bouncy Castle实现相同）。不在编码后的字符串中添加换行符，即所有编码后的字符串在一行显示。
        System.out.println("Oracle一般Base64编码");
        String encodedString01 = OracleBase64Utils.commonEncode(testString);
        System.out.println("编码后：" + encodedString01);
        String decodedString01 = OracleBase64Utils.commonDecode(encodedString01);
        System.out.println("解码后：" + decodedString01);

        // 绝大部分遵循RFC 2045的Base64编码，每行以回车换行符为分隔符，只是最后一行除外，这是与Commons Codec不同的地方。
        System.out.println("Oracle规范的Base64编码");
        String encodedString02 = OracleBase64Utils.strictEncode(testString);
        System.out.println("编码后：" + encodedString02);
        String decodedString02 = OracleBase64Utils.strictDecode(encodedString02);
        System.out.println("解码后：" + decodedString02);

        System.out.println("Oracle自定义的的Base64编码");
        // 每行最多为30个字符（向下取4的最大整数倍，所以这里每行显示28个字符），以水平制表符为行分隔符而不是回车换行符。
        String encodedString03 = OracleBase64Utils.customizeEncode(testString, 30, new byte[]{9});
        System.out.println("编码后：" + encodedString03);
        String decodedString03 = OracleBase64Utils.customizeDecode(encodedString03);
        System.out.println("解码后：" + decodedString03);

        // 使用“-”代替“+”，使用“_”代替“/”，“=”不处理，依然输出。
        System.out.println("Oracle URL安全的Base64编码");
        String encodedString04 = OracleBase64Utils.urlSafeEncode(testString);
        System.out.println("编码后：" + encodedString04);
        String decodedString04 = OracleBase64Utils.urlSafeDecode(encodedString04);
        System.out.println("解码后：" + decodedString04);

    }
}
