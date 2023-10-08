package com.example.lv.util;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @project xiaobai
 * @description AES  16对应128 24对应192  32对应256
 * @author lmh
 * @date 2023/10/7 09:28:47
 * @version 1.0
 */
public class AESEncryptUtils {

    private static final String AES_ALGORITHM = "AES";

    /**
     * 加解密密钥, 外部可以
     */
    public static final String AES_DATA_SECURITY_KEY = "4%YkW!@g5LGcf9Ut";
    /**
     * 算法/加密模式/填充方式
     */
    private static final String AES_PKCS5P = "AES/ECB/PKCS5Padding";

    private static final String AES_PERSON_KEY_SECURITY_KEY = "pisnyMyZYXuCNcRd";

    /**
     * 加密
     *
     * @param str
     *            需要加密的字符串
     * @param key
     *            密钥
     * @return
     * @throws Exception
     */
    public static String encrypt(String str, String key) {
        if (StringUtils.isEmpty(key)) {
            throw new RuntimeException("key不能为空");
        }
        try {
            if (str == null) {
                return null;
            }
            // 判断Key是否为16位
            if (key.length() != 16) {
                return null;
            }
            byte[] raw = key.getBytes("UTF-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            // "算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance(AES_PKCS5P);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));
            // 此处使用BASE64做转码功能，同时能起到2次加密的作用。
            return new BASE64Encoder().encode(encrypted);
        } catch (Exception ex) {
            return null;
        }

    }

    /**
     * 解密
     *
     * @param str 需要解密的字符串
     * @param key 密钥
     * @return
     */
    public static String decrypt(String str, String key) {
        if (StringUtils.isEmpty(key)) {
            throw new RuntimeException("key不能为空");
        }
        try {
            if (str == null) {
                return null;
            }
            // 判断Key是否为16位
            if (key.length() != 16) {
                return null;
            }
            byte[] raw = key.getBytes("UTF-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance(AES_PKCS5P);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            // 先用base64解密
            byte[] encrypted = new BASE64Decoder().decodeBuffer(str);
            try {
                byte[] original = cipher.doFinal(encrypted);
                String originalString = new String(original, "UTF-8");
                return originalString;
            } catch (Exception e) {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 加密
     *
     * @param str 需要加密的字符串
     * @return
     * @throws Exception
     */
    public static String encrypt(String str) {
        return encrypt(str,AES_DATA_SECURITY_KEY);
    }

    /**
     * 解密
     * @param str 需要解密的字符串
     * @return
     */
    public static String decrypt(String str) {
        return decrypt(str,AES_DATA_SECURITY_KEY);
    }

    /**
     * 查询的时候对某些字段解密
     *
     * @param str
     * @return
     */
    public static String aesDecrypt(String str) {
        if (StringUtils.isBlank(str)) {
            return " ";
        }
        String sql = " AES_DECRYPT(from_base64(" + str + ")," + "'" + AES_DATA_SECURITY_KEY + "')";
        return sql;
    }

    /**
     * 对personKey加密
     *
     * @param personKey
     * @return
     */
    public static String encryptPersonKey(String personKey) {
        return AESEncryptUtils.encrypt(personKey, AES_PERSON_KEY_SECURITY_KEY);
    }

    /**
     * 对personKey解密
     *
     * @param personKey
     * @return
     */
    public static String decryptPersonKey(String personKey) {
        return AESEncryptUtils.decrypt(personKey, AES_PERSON_KEY_SECURITY_KEY);
    }




    public static String encrypt2(String plaintext, String key) throws Exception {
        // 将密钥转换为字节数组
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);

        // 根据字节数组生成AES密钥
        SecretKey secretKey = new SecretKeySpec(keyBytes, AES_ALGORITHM);

        // 创建AES加密器
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // 加密
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

        // 对加密结果进行Base64编码
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);

        return encryptedText;
    }

    public static String decrypt2(String encryptedText, String key) throws Exception {
        // 将密钥转换为字节数组
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);

        // 根据字节数组生成AES密钥
        SecretKey secretKey = new SecretKeySpec(keyBytes, AES_ALGORITHM);

        // 创建AES解密器
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // 对加密结果进行Base64解码
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);

        // 解密
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        // 将解密结果转换为字符串
        String plaintext = new String(decryptedBytes, StandardCharsets.UTF_8);

        return plaintext;
    }

    /**
     * 生成密钥
     * @return
     */
    public static byte[] generateRandomKeySeed() {
        byte[] keySeed = new byte[16]; // 128 bits
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(keySeed);
        return keySeed;
    }

    public static SecretKeySpec generateKey(byte[] keySeed) {
        SecretKeySpec secretKey = new SecretKeySpec(keySeed, "AES");
        return secretKey;
    }









    public static void main(String[] args) {
        try {

            // 创建AES密钥生成器实例
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");

            // 配置密钥生成器为256位密钥长度
            keyGen.init(256);

            // 生成随机AES密钥
            SecretKey secretKey = keyGen.generateKey();

            // 获取密钥的字节表示
            byte[] keyBytes = secretKey.getEncoded();

            // 打印生成的密钥
            String hexKey = bytesToHex(keyBytes);
            System.out.println(hexKey.length());





            String plaintext = "Hello, AES!";
            String key = "0123456789abcdef0123456789abcdef";

            String encryptedText = encrypt2(plaintext, key);
            System.out.println("Encrypted: " + encryptedText);

            String decryptedText = decrypt2(encryptedText, key);
            System.out.println("Decrypted: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 将字节数组转换为十六进制字符串
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }




}
