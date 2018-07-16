package com.henry.wechat.util;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 检验 signature 的工具类。
 * @author Vincent Huang
 * @date 2018-07-16
 * @since 1.0
 * @version 1.0
 */
public class SignUtil {
    /**
     * 进行字符串和字节转换的字符集类型。
     */
    private static final String ENCODING_TYPE = "UTF-8";
    /**
     * 日志对象。
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SignUtil.class);

    /**
     * 根据微信开发文档中的描述的方法，判断签名是否来自微信公众平台。
     * 判断方法参考：https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421135319
     *
     * @param token 微信公众平台发来的 token
     * @param timestamp 微信公众平台发来的时间戳
     * @param nonce 微信公众平台发来的随机数
     * @param signature 微信公众平台发来的签名
     * @return 签名是否来自微信公众平台
     */
    public static boolean isSignFromWechat(String token, String timestamp, String nonce, String signature) {
        // 1. 先将token、timestamp、nonce三个参数进行字典序排序。
        String[] arr = new String[]{token, timestamp, nonce};
        // 根据数组元素类型进行自然升序排列
        Arrays.sort(arr);

        // 2. 将三个字符串拼接成一个字符串，然后进行sha1摘要计算。
        StringBuilder concatBuffer = new StringBuilder();
        for (String element : arr) {
            concatBuffer.append(element);
        }
        String mdString = null;
        try {
            // 初始化MessageDigest。
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            // 进行摘要计算。
            byte[] digestBytes = messageDigest.digest(concatBuffer.toString().getBytes(ENCODING_TYPE));
            // 将字节数组转换成16进制字符串。
            mdString = Hex.encodeHexString(digestBytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            LOGGER.error("对微信公众平台发来的token，timestamp，nonce进行摘要计算抛出异常", e);
        }

        // 3. 将sha1摘要计算后的字符串与signature对比，如果相同则说明该请求来源于微信公众平台。
        return mdString != null && mdString.equals(signature);
    }
}
