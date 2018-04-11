package com.rosydawn.crypto.asymmetric;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA算法实现。
 *
 * Java 6、7提供的RSA算法密钥长度为512~65536位（必需是64的倍数），默认长度为1024位，工作模式为ECB，填充方式有NoPadding、
 * PKCS1Padding、OAEPWithMD5AndMGF1Padding、OAEPWithSHA1AndMGF1Padding、OAEPWithSHA256AndMGF1Padding、
 * OAEPWithSHA384AndMGF1Padding、OAEPWithSHA512AndMGF1Padding（OAEP系列的填充方式不能用于签名）。
 *
 * Bouncy Castle提供的RSA算法密钥长度也是512~65536位（必需是64的倍数），默认长度为2048位，工作模式为NONE和ECB，填充方式比Java 6、7
 * 的实现增加了OAEPWithSHA224AndMGF1Padding和ISO9796-1Padding（OAEP系列的填充方式不能用于签名）。
 *
 * @author Vincent
 **/
public class RSAUtils {
    private static final String KEY_ALGORITHM = "RSA";
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 初始化指定长度的密钥。
     *
     * @param keySize 指定的密钥长度。
     * @return 初始化后包装成Map对象的密钥对。
     */
    public static Map<String, Key> initKey(int keySize) {
        Map<String, Key> keyMap = null;
        // 实例化密钥对生成器
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (keyPairGenerator != null) {
            // 初始化密钥对生成器的密钥长度
            keyPairGenerator.initialize(keySize);
            // 生成密钥对
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            // 公钥
            PublicKey publicKey = keyPair.getPublic();
            // 私钥
            PrivateKey privateKey = keyPair.getPrivate();
            // 封装密钥
            keyMap = new HashMap<>(2);
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
     * 使用公钥加密。
     *
     * @param dataBytes 待加密的数据。
     * @param keyBytes  加密所用的公钥。
     * @return 加密后的数据。
     */
    public static byte[] encryptByPublicKey(byte[] dataBytes, byte[] keyBytes, String mode, String padding) {
        byte[] encryptedBytes = null;
        // 获得公钥
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            // 对数据进行解密
            String transformation = keyFactory.getAlgorithm() + "/" + mode + "/" + padding;
            Cipher cipher = Cipher.getInstance(transformation, new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            encryptedBytes = cipher.doFinal(dataBytes);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | NoSuchPaddingException |
                IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return encryptedBytes;

    }

    /**
     * 使用私钥加密。如果采用“私钥加密公钥解密”的方式，由于公钥在公布时有可能被窃取，那么密文就有被破解的风险。
     *
     * @param dataBytes 待加密的数据。
     * @param keyBytes  加密所用的私钥。
     * @return 加密后的数据。
     */
    public static byte[] encryptByPrivateKey(byte[] dataBytes, byte[] keyBytes, String mode, String padding) {
        byte[] encryptedBytes = null;
        // 获取私钥
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            // 对数据解密
            String transformation = keyFactory.getAlgorithm() + "/" + mode + "/" + padding;
            Cipher cipher = Cipher.getInstance(transformation, new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            encryptedBytes = cipher.doFinal(dataBytes);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | NoSuchPaddingException |
                IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return encryptedBytes;
    }

    /**
     * 使用公钥解密。如果采用“私钥加密公钥解密”的方式，由于公钥在公布时有可能被窃取，那么密文就有被破解的风险。
     *
     * @param encryptedBytes 已加密数据。
     * @param keyBytes       解密所用的公钥。
     * @return 解密后的数据。
     */
    public static byte[] decryptByPublicKey(byte[] encryptedBytes, byte[] keyBytes, String mode, String padding) {
        byte[] decryptBytes = null;
        // 获得公钥
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            // 对数据进行解密
            String transformation = keyFactory.getAlgorithm() + "/" + mode + "/" + padding;
            Cipher cipher = Cipher.getInstance(transformation, new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            decryptBytes = cipher.doFinal(encryptedBytes);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | NoSuchPaddingException |
                IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return decryptBytes;
    }

    /**
     * 使用私钥解密。
     *
     * @param encryptedBytes 已加密数据。
     * @param keyBytes       解密所用的私钥。
     * @return 解密后的数据。
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedBytes, byte[] keyBytes, String mode, String padding) {
        byte[] decryptBytes = null;
        // 获取私钥
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            // 对数据解密
            String transformation = keyFactory.getAlgorithm() + "/" + mode + "/" + padding;
            Cipher cipher = Cipher.getInstance(transformation, new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            decryptBytes = cipher.doFinal(encryptedBytes);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | NoSuchPaddingException |
                IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return decryptBytes;
    }
}
