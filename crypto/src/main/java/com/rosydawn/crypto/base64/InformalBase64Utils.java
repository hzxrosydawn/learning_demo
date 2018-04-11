package com.rosydawn.crypto.base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 在JRE的sun.misc包中有BASE64Encoder和BASE64Decoder两个类，这两个类也可以实现严格遵循RFC 2045的Base64编解码
 * （编码后的字符串行尾都有换行符）。但以sun和com.sun开头的包中类是未被文档化的，大多数与底层平台有关，可能会在未来
 * 版本中删除，所以不推荐使用。
 * <p>
 * 在Eclipse中使用这些类默认无法通过编译，需要进行一下设置：
 * Properties --> Java Build Path --> Libraries --> JRE System Library --> Access rules -->
 * 双击Type Access Rules，在Accessible中添加accessible。
 * <p>
 * 正因为Sun提供的Base64实现是不推荐使用的，才有了Bouncy Castle和Commons Codec的实现。
 *
 * @author Vincent Huang
 */
public class InformalBase64Utils {
    /**
     * 进行编码的字符串的字符集类型
     */
    private static final String ENCODING_TYPE = "UtF-8";

    /**
     * sun.misc包中有BASE64Encoder和BASE64Decoder两个类，这两个类也可以实现严格遵循RFC 2045的Base64编解码
     * （编码后的字符串行尾都有换行符）。但不建议使用，因为以sun和com.sun开头的包中类是未被文档化的，大多数与底层平台有关，可能会在未来
     * 版本中删除。建议最好使用Commons Codec进行Base64编解码。
     *
     * @param sourceString 待进行Base64编码的源字符串。以UTF-8为字符集读取。
     * @return Base64编码后的16进制字符串。
     */
    public static String encode(String sourceString) {
        String encodedString = null;
        BASE64Encoder base64Encoder = new BASE64Encoder();
        try {
            encodedString = base64Encoder.encode(sourceString.getBytes(ENCODING_TYPE));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedString;
    }

    /**
     * Sun提供的一般Base64解码实现。
     *
     * @param base64String 待进行Base64解码的16进制字符串。以UTF-8为字符集读取。
     * @return Base64解码后的源字符串。
     */
    public static String decode(String base64String) {
        String decodedString = null;
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            byte[] decodedBytes = base64Decoder.decodeBuffer(base64String);
            decodedString = new String(decodedBytes, ENCODING_TYPE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return decodedString;
    }
}
