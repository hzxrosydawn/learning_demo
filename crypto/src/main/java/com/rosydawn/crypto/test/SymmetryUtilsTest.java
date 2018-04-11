package com.rosydawn.crypto.test;

import com.rosydawn.crypto.symmetric.SymmetryCryptoUtils;
import org.apache.commons.codec.binary.Base64;

/**
 * DESede算法实现测试。
 */
public class SymmetryUtilsTest {
    public static void main(String[] args) {
        String dataString = "对称加密测试字符串";
        byte[] dataBytes = dataString.getBytes();
        System.out.println("原文：" + dataString);

        // DESede
        /*String cipherString = "DESede/ECB/PKCS5Padding";
        String algorithmName = "DESede";
        // 初始化指定长度的密钥。可以指定为JDK支持的112和168，也可以指定为Bouncy Castle支持的128和192。
        byte[] keyBytes = SymmetryCryptoUtils.initKeyBytes(168, algorithmName);
        System.out.println("DESede密钥：" + Base64.encodeBase64String(keyBytes));
        // 加密
        byte[] encryptedBytes = SymmetryCryptoUtils.encrypt(dataBytes, keyBytes, algorithmName, cipherString);
        System.out.println("DESede密文：" + Base64.encodeBase64String(encryptedBytes));
        // 解密
        byte[] decryptedBytes = SymmetryCryptoUtils.decrypt(encryptedBytes, keyBytes, algorithmName, cipherString);
        System.out.println("DESede解密后：" + new String(decryptedBytes));*/


        // AES
        /*String cipherString = "AES/ECB/PKCS5Padding";
        String algorithmName = "AES";
        // 初始化指定长度的密钥。使用192和256长度的密钥，需要获得无政策权限限制文件配置。
        byte[] keyBytes = SymmetryCryptoUtils.initKeyBytes(256, algorithmName);
        System.out.println("AES密钥：" + Base64.encodeBase64String(keyBytes));
        // 加密
        byte[] encryptedBytes = SymmetryCryptoUtils.encrypt(dataBytes, keyBytes, algorithmName, cipherString);
        System.out.println("AES密文：" + Base64.encodeBase64String(encryptedBytes));
        // 解密
        byte[] decryptedBytes = SymmetryCryptoUtils.decrypt(encryptedBytes, keyBytes, algorithmName, cipherString);
        System.out.println("AES解密后：" + new String(decryptedBytes));*/

        // IDEA
        String cipherString = "IDEA/ECB/PKCS7Padding";
        String algorithmName = "IDEA";
        // 初始化指定长度的密钥。使用192和256长度的密钥，需要获得无政策权限限制文件配置。
        byte[] keyBytes = SymmetryCryptoUtils.initKeyBytes(128, algorithmName);
        System.out.println("IDEA密钥：" + Base64.encodeBase64String(keyBytes));
        // 加密
        byte[] encryptedBytes = SymmetryCryptoUtils.encrypt(dataBytes, keyBytes, algorithmName, cipherString);
        System.out.println("IDEA密文：" + Base64.encodeBase64String(encryptedBytes));
        // 解密
        byte[] decryptedBytes = SymmetryCryptoUtils.decrypt(encryptedBytes, keyBytes, algorithmName, cipherString);
        System.out.println("IDEA解密后：" + new String(decryptedBytes));

    }
}
