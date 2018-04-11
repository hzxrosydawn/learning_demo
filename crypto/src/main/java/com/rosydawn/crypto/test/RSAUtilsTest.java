package com.rosydawn.crypto.test;

import com.rosydawn.crypto.asymmetric.RSAUtils;
import org.apache.commons.codec.binary.Base64;

import java.security.Key;
import java.util.Map;

/**
 * Created by Vincent.
 */
public class RSAUtilsTest {
    public static void main(String[] args) {
        String testString = "RSA加密算法测试";
        String mode = "ECB";
        String padding = "PKCS1Padding ";
        System.out.println("原文：\n" + testString);
        byte[] dataBytes = testString.getBytes();

        Map<String, Key> keyMap = RSAUtils.initKey(1024);
        byte[] publicKeyBytes = RSAUtils.getPublicKey(keyMap);
        byte[] privateKeyBytes = RSAUtils.getPrivateKey(keyMap);
        System.out.println("公钥：\n" + Base64.encodeBase64String(publicKeyBytes));
        System.out.println("私钥：\n" + Base64.encodeBase64String(privateKeyBytes));

        // 公钥加密，私钥解密。
        byte[] encryptedBytes = RSAUtils.encryptByPublicKey(dataBytes, publicKeyBytes, mode, padding);
        System.out.println("公钥加密的密文：\n" + Base64.encodeBase64String(encryptedBytes));
        byte[] decryptedBytes = RSAUtils.decryptByPrivateKey(encryptedBytes, privateKeyBytes, mode, padding);
        System.out.println("私钥解密后的原文：\n" + new String(decryptedBytes));

        // 私钥加密，公钥解密。公钥有泄露的风险。
        encryptedBytes = RSAUtils.encryptByPrivateKey(dataBytes, privateKeyBytes, mode, padding);
        System.out.println("私钥加密的密文：\n" + Base64.encodeBase64String(encryptedBytes));
        decryptedBytes = RSAUtils.decryptByPublicKey(encryptedBytes, publicKeyBytes, mode, padding);
        System.out.println("公钥解密后的原文：\n" + new String(decryptedBytes));
    }
}
