package com.rosydawn.crypto.test;

import com.rosydawn.crypto.signature.SignatureUtils;

import java.security.Key;
import java.util.Map;

/**
 * 签名实现测试类。
 *
 * @author Vincent Huang
 */
public class SignatureUtilsTest {
    public static void main(String[] args) {
        String testString = "签名测试字符串";
        byte[] dataBytes = testString.getBytes();
        // 签名加密算法名
        String keyAlgorithm = "EC";
        // 签名算法名
        String signAlgorithm = "RipeMD160withECDSA";
        Map<String, Key> keyMap = SignatureUtils.initKey(keyAlgorithm, 256);
        byte[] publicKeyBytes = SignatureUtils.getPublicKey(keyMap);
        byte[] privateKeyByes = SignatureUtils.getPrivateKey(keyMap);
        byte[] signBytes =SignatureUtils.sign(dataBytes, privateKeyByes, keyAlgorithm, signAlgorithm);
        boolean verified = SignatureUtils.verify(dataBytes, publicKeyBytes, signBytes, keyAlgorithm, signAlgorithm);
        System.out.println("是否验证通过：" + verified);
    }
}
