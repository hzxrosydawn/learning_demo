package com.rosydawn.crypto.signature;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Java 6和7支持的RSA签名算法有NONEwithRSA、MD2withRSA、MD5withRSA、SHA1withRSA、SHA256withRSA、SHA384withRSA和
 * SHA512withRSA。Java 8开始增加了SHA224withRSA签名算法。Bouncy Castle实现的RSA签名算法有SHA1withRSA、SHA256withRSA、
 * SHA384withRSA和SHA512withRSA。RSA签名算法密钥长度也是512~65536位（必需是64的倍数），Java实现的密钥长度默认为1024位，Bouncy
 * Castle实现的密钥长度默认为2048位。
 *
 * Java 6和7支持DSA签名算法有NONEwithDSA（数据字节数必需为20）和SHA1withDSA，Java 8添加了SHA224withDSA、SHA256withDSA的支持。Bouncy Castle支持
 * SHA1withDSA、SHA224withDSA、SHA384withDSA、SHA512withDSA。DSA签名算法密钥长度是512~1024位（必需是64的倍
 * 数），默认的密钥长度为1024位。
 *
 * Java 6和7支持的ECDSA签名算法有NONEwithECDSA（签名长度128位）、SHA1withECDS（签名长度160位）、SHA256withECDS（签名长度256位）、
 * SHA384withECDS（签名长度384位）和SHA512withECDS（签名长度512位）。Java 8增加了SHA224withECDSA（签名长度224位）。Bouncy Castle
 * 支持的ECDSA签名算法有RipeMD160withECDSA（签名长度160位）。ECDSA签名算法密钥长度为112~571位，默认密钥长度为256。
 *
 * @author Vincent
 */
public class SignatureUtils {
    private static final String PUBLIC_KEY = "PublicKey";
    private static final String PRIVATE_KEY = "PrivateKey";

    /**
     * 初始化签名密钥对。
     *
     * @param keyAlgorithm 签名加密算法名。可以为"RSA"，"DSA"和"EC"。
     * @param keySize 指定的密钥大小。
     * @return 初始化后包装成Map对象的密钥对。
     */
    public static Map<String, Key> initKey(String keyAlgorithm, int keySize) {
        Map<String, Key> keyMap = null;
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(keyAlgorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (keyPairGenerator != null) {
            keyPairGenerator.initialize(keySize);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            keyMap = new HashMap<>();
            keyMap.put(PUBLIC_KEY, publicKey);
            keyMap.put(PRIVATE_KEY, privateKey);
        }
        return keyMap;
    }

    /**
     * 获取公钥。
     *
     * @param keyMap 包装成Map对象的密钥对。
     * @return 私钥。
     */
    public static byte[] getPublicKey(Map<String, Key> keyMap) {
        return keyMap.get(PUBLIC_KEY).getEncoded();
    }

    /**
     * 获取私钥。
     *
     * @param keyMap 包装成Map对象的密钥对。
     * @return 私钥。
     */
    public static byte[] getPrivateKey(Map<String, Key> keyMap) {
        return keyMap.get(PRIVATE_KEY).getEncoded();
    }

    /**
     * 签名操作。
     *
     * @param dataBytes 待签名的数据。
     * @param privateKeyBytes 签名所用的私钥。
     * @param keyAlgorithm 签名加密算法名。
     * @param signAlgorithm 签名算法名。
     * @return 生成的签名。
     */
    public static byte[] sign(byte[] dataBytes, byte[] privateKeyBytes, String keyAlgorithm, String signAlgorithm) {
        // 若需要加入Bouncy Castle支持则需要加入添加下面一行代码
        Security.addProvider(new BouncyCastleProvider());
        byte[] signedBytes = null;
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(keyAlgorithm);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Signature signature = Signature.getInstance(signAlgorithm);
            // 初始化签名对象
            signature.initSign(privateKey);
            // 更新
            signature.update(dataBytes);
            // 签名
            signedBytes = signature.sign();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return signedBytes;
    }

    /**
     * 签名验证操作。
     *
     * @param dataBytes 待验证的数据。
     * @param publicKeyBytes 验证所用的公钥。
     * @param signBytes 待验证的签名。
     * @param keyAlgorithm 签名加密算法名。
     * @param signAlgorithm 签名算法名。
     * @return 是否验证通过。
     */
    public static boolean verify(byte[] dataBytes, byte[] publicKeyBytes, byte[] signBytes, String keyAlgorithm,
                                 String signAlgorithm) {
        // 若需要加入Bouncy Castle支持则需要加入添加下面一行代码
        Security.addProvider(new BouncyCastleProvider());
        boolean verified = false;
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(keyAlgorithm);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Signature signature = Signature.getInstance(signAlgorithm);
            // 初始化签名对象
            signature.initVerify(publicKey);
            // 更新
            signature.update(dataBytes);
            // 验证
            verified = signature.verify(signBytes);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return verified;
    }
}
