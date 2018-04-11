package com.rosydawn.crypto.symmetric;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * JDK 6和JDK 7支持的对称加密算法有DES、DESede、AES和Blowfish，以及RC2和RC4。Bouncy Castle支持IDEA对称加密算法。
 * <p>
 * JDK 6和JDK 7提供的DESede算法实现所支持的密钥长度为112位和168位，工作模式支持ECB、CBC、PCBC、CTR、CTS、CFB、CFB8至CFB128、OFB、
 * OFB8至OFB128，一般使用CTR的较多。填充方式仅支持NoPadding、PKCS5Padding、ISO10126Padding共三种。
 * Bouncy Castle的DESede算法实现可支持128位和192位，工作模式支持同JDK 7，填充模式支持PKCS7Padding、ISO10126d2Padding、
 * X932Padding、ISO7816d4Padding、ZeroBytePadding等。
 * <p>
 * JDK 6/7/8提供的AES算法实现可支持128位（默认）、192位和256位。JDK 6/7/8需要获得无政策限制权限文件（Unlimited Strength
 * Jurisdiction Policy File）才支持192位和256位密钥，工作模式支持ECB、CBC、PCBC、CTR、CTS、CFB、CFB8至CFB128、OFB、
 * OFB8至OFB128，一般使用CTR的较多。填充方式仅支持NoPadding、PKCS5Padding、ISO10126Padding共三种。
 * Bouncy Castle的AES算法实现在JDK 6/7的基础上增加了PKCS7Padding、ZeroBytePadding两种填充模式支持。
 * <p>
 * Bouncy Castle提供了JDK不支持的IDEA算法支持。该实现支持密钥长度为128位，工作模式支持ECB，填充方式支持PKCS5Padding、PKCS7Padding、
 * ISO10126Padding和ZeroBytePadding。
 *
 * @author Vincent
 **/
public class SymmetryCryptoUtils {
    /**
     * 获得指定位数的密钥的字节数组。
     *
     * @param keyLength     指定的密钥长度。
     * @param algorithmName 加密算法名。可以是上面提到各种加密算法名。
     * @return 密钥的字节数组。
     */
    public static byte[] initKeyBytes(int keyLength, String algorithmName) {
        byte[] keyBytes = null;
        try {
            // 如果选择Bouncy Castle的算法支持则需要额外选择下面一行语句。
            Security.addProvider(new BouncyCastleProvider());
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithmName);
            keyGenerator.init(keyLength);
            SecretKey secretKey = keyGenerator.generateKey();
            keyBytes = secretKey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyBytes;
    }

    /**
     * 进行加密操作。
     *
     * @param dataBytes       待加密数据的字节数组。
     * @param keyBytes        加密所用密钥的字节数组。
     * @param algorithmName   对称加密算法名。可以是上面提到各种加密算法名。
     * @param cipherAlgorithm 加密所用具体算法格式。格式为“加密解密算法名/工作模式/填充方式”。
     * @return 加密后的字节数组。
     */
    public static byte[] encrypt(byte[] dataBytes, byte[] keyBytes, String algorithmName, String cipherAlgorithm) {
        byte[] encryptedBytes = null;
        Key key = toKey(keyBytes, algorithmName);
        try {
            // 如果选择Bouncy Castle的算法支持则需要额外选择下面一行语句。
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encryptedBytes = cipher.doFinal(dataBytes);
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | NoSuchPaddingException | InvalidKeyException
                | BadPaddingException e) {
            e.printStackTrace();
        }
        return encryptedBytes;
    }

    /**
     * 进行解密操作。
     *
     * @param encryptedBytes  待解密数据的字节数组。
     * @param keyBytes        解密所用密钥的字节数组。
     * @param algorithmName   对称加密算法名。可以是上面提到各种加密算法名。
     * @param cipherAlgorithm 解密所用具体算法格式。格式为“加密解密算法名/工作模式/填充方式”。
     * @return 解密后的字节数组。
     */
    public static byte[] decrypt(byte[] encryptedBytes, byte[] keyBytes, String algorithmName, String cipherAlgorithm) {
        byte[] decryptedBytes = null;
        Key key = toKey(keyBytes, algorithmName);
        try {
            // 如果选择Bouncy Castle的算法支持则需要额外选择下面一行语句。
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            cipher.init(Cipher.DECRYPT_MODE, key);
            decryptedBytes = cipher.doFinal(encryptedBytes);
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | NoSuchPaddingException | InvalidKeyException
                | BadPaddingException e) {
            e.printStackTrace();
        }
        return decryptedBytes;
    }

    /**
     * 将密钥的字节数组转换成的Key对象。
     *
     * @param keyBytes      密钥的字节数组。
     * @param algorithmName 对称算法名。可以是上面提到各种加密算法名。
     * @return 密钥对应的Key对象。
     */
    private static Key toKey(byte[] keyBytes, String algorithmName) {
        return new SecretKeySpec(keyBytes, algorithmName);
    }
}
