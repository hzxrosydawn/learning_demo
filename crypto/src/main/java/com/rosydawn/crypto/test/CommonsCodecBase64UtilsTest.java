package com.rosydawn.crypto.test;

import com.rosydawn.crypto.base64.CommonsCodecBase64Utils;

/**
 * Commons Codec的Base64实现测试类。
 *
 * @author Vincent Huang
 **/
public class CommonsCodecBase64UtilsTest {
    public static void main(String[] args) {
        String testString = "我在学习Java加密与解密相关的技术。我在学习Java加密与解密相关的技术。";
        System.out.println("原文：" + testString);

        // 部分遵循RFC 2045的一般Base64编码，同Bouncy Castle。
        System.out.println("一般的Base64编码");
        String encodedString01 = CommonsCodecBase64Utils.commonEncode(testString);
        // 编码后的字符串显示在一行上。
        System.out.println("编码后：" + encodedString01);
        String decodedString01 = CommonsCodecBase64Utils.decode(encodedString01);
        System.out.println("解码后：" + decodedString01);

        // 严格遵循RFC 2045的Base64编码，每行以回车换行符为分隔符。
        System.out.println("规范的Base64编码");
        String encodedString02 = CommonsCodecBase64Utils.strictEncode(testString);
        // 编码后的字符串最多每76个（向下取4的最大整数倍，76正好是4的整数倍）一行。最后一行也有回车换行符。
        System.out.println("编码后：" + encodedString02);
        String decodedString02 = CommonsCodecBase64Utils.decode(encodedString02);
        System.out.println("解码后：" + decodedString02);

        System.out.println("自定义的Base64编码");
        // 每行最多为30个字符（向下取4的最大整数倍，所以这里每行显示28个字符），以水平制表符为行分隔符而不是回车换行符。
        byte[] tabBytes = new byte[]{9};
        String encodedString03 = CommonsCodecBase64Utils.customizeEncode(testString, 30, tabBytes, false);
        System.out.println("编码后：" + encodedString03);
        String decodedString03 = CommonsCodecBase64Utils.decode(encodedString03);
        System.out.println("解码后：" + decodedString03);

        System.out.println("URL安全的一般Base64编码");
        String encodedString04 = CommonsCodecBase64Utils.commonUrlSafeEncode(testString);
        System.out.println("编码后：" + encodedString04);
        // 使用“-”代替“+”，使用“_”代替“/”，为了安全去掉了“=”，不进行补位。
        String decodedString04 = CommonsCodecBase64Utils.decode(encodedString04);
        System.out.println("解码后：" + decodedString04);
    }
}
