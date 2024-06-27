package org.apache.dolphinscheduler.plugin.task.api.utils;

import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 完全兼容微信所使用的AES加密工具类 aes的key必须是256byte长（比如32个字符），可以使用AesKit.genAesKey()来生成一组key
 *
 * @author L.cm
 */
public class AesUtil {

    public static final Charset DEFAULT_CHARSET = Charsets.UTF_8;

    /**
     * 加密
     *
     * @param content 文本内容
     * @param aesTextKey 文本密钥
     * @return byte[]
     */
    public static byte[] encrypt(String content, String aesTextKey) {
        return encrypt(content.getBytes(DEFAULT_CHARSET), aesTextKey);
    }

    /**
     * 加密
     *
     * @param content 文本内容
     * @param charset 编码
     * @param aesTextKey 文本密钥
     * @return byte[]
     */
    public static byte[] encrypt(String content, Charset charset, String aesTextKey) {
        return encrypt(content.getBytes(charset), aesTextKey);
    }

    /**
     * 加密
     *
     * @param content 内容
     * @param aesTextKey 文本密钥
     * @return byte[]
     */
    public static byte[] encrypt(byte[] content, String aesTextKey) {
        return encrypt(content, Objects.requireNonNull(aesTextKey).getBytes(DEFAULT_CHARSET));
    }

    /**
     * Base64加密
     *
     * @param content 文本内容
     * @param aesTextKey 文本密钥
     * @return {String}
     */
    public static String encryptToBase64(String content, String aesTextKey) {
        return Base64.getEncoder().encodeToString(encrypt(content, aesTextKey));
    }

    /**
     * Base64加密
     *
     * @param content 内容
     * @param aesTextKey 文本密钥
     * @return {String}
     */
    public static String encryptToBase64(byte[] content, String aesTextKey) {
        return Base64.getEncoder().encodeToString(encrypt(content, aesTextKey));
    }

    /**
     * Base64解密
     *
     * @param content 文本内容
     * @param aesTextKey 文本密钥
     * @return {String}
     */
    public static String decryptFormBase64ToString(String content, String aesTextKey) {
        byte[] hexBytes = decryptFormBase64(content, aesTextKey);
        if (hexBytes == null) {
            return null;
        }
        return new String(hexBytes, DEFAULT_CHARSET);
    }

    /**
     * Base64解密
     *
     * @param content 文本内容
     * @param aesTextKey 文本密钥
     * @return byte[]
     */
    public static byte[] decryptFormBase64(String content, String aesTextKey) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        return decryptFormBase64(content.getBytes(DEFAULT_CHARSET), aesTextKey);
    }

    /**
     * Base64解密
     *
     * @param content 内容
     * @param aesTextKey 文本密钥
     * @return byte[]
     */
    public static byte[] decryptFormBase64(byte[] content, String aesTextKey) {
        return decrypt(Base64.getDecoder().decode(content), aesTextKey);
    }

    /**
     * 解密
     *
     * @param content 内容
     * @param aesTextKey 文本密钥
     * @return {String}
     */
    public static String decryptToString(byte[] content, String aesTextKey) {
        return new String(decrypt(content, aesTextKey), DEFAULT_CHARSET);
    }

    /**
     * 解密
     *
     * @param content 内容
     * @param aesTextKey 文本密钥
     * @return byte[]
     */
    public static byte[] decrypt(byte[] content, String aesTextKey) {
        return decrypt(content, Objects.requireNonNull(aesTextKey).getBytes(DEFAULT_CHARSET));
    }

    /**
     * 解密
     *
     * @param content 内容
     * @param aesKey 密钥
     * @return byte[]
     */
    public static byte[] encrypt(byte[] content, byte[] aesKey) {
        return aes(Pkcs7Encoder.encode(content), aesKey, Cipher.ENCRYPT_MODE);
    }

    /**
     * 加密
     *
     * @param encrypted 内容
     * @param aesKey 密钥
     * @return byte[]
     */
    public static byte[] decrypt(byte[] encrypted, byte[] aesKey) {
        return Pkcs7Encoder.decode(aes(encrypted, aesKey, Cipher.DECRYPT_MODE));
    }

    /**
     * ase加密
     *
     * @param encrypted 内容
     * @param aesKey 密钥
     * @param mode 模式
     * @return byte[]
     */
    private static byte[] aes(byte[] encrypted, byte[] aesKey, int mode) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
            IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));
            cipher.init(mode, keySpec, iv);
            return cipher.doFinal(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 提供基于PKCS7算法的加解密接口. */
    private static class Pkcs7Encoder {

        private static final int BLOCK_SIZE = 32;

        private static byte[] encode(byte[] src) {
            int count = src.length;
            // 计算需要填充的位数
            int amountToPad = BLOCK_SIZE - (count % BLOCK_SIZE);
            // 获得补位所用的字符
            byte pad = (byte) (amountToPad & 0xFF);
            byte[] pads = new byte[amountToPad];
            for (int index = 0; index < amountToPad; index++) {
                pads[index] = pad;
            }
            int length = count + amountToPad;
            byte[] dest = new byte[length];
            System.arraycopy(src, 0, dest, 0, count);
            System.arraycopy(pads, 0, dest, count, amountToPad);
            return dest;
        }

        private static byte[] decode(byte[] decrypted) {
            int pad = decrypted[decrypted.length - 1];
            if (pad < 1 || pad > BLOCK_SIZE) {
                pad = 0;
            }
            if (pad > 0) {
                return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);
            }
            return decrypted;
        }
    }
}
