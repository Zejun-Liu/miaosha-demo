package com.jiuxian.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.UUID;

public class EncryptUtil implements Serializable {

    private static final long serialVersionUID = 2046569272788545499L;


    private static Logger logger = LoggerFactory.getLogger(EncryptUtil.class);

    /**
     * 将一个字符串通过MD5转换成加密串
     * @param sign 被加密字串
     * @return 加密后字串
     */
    public static String MD5ToUpperCase(String sign) {
        return MD5(sign).toUpperCase();
    }

    public static String MD5(String sign) {
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte[] strTemp = sign.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return "";
        }
    }


    public static String getNewSalt() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String generatePassword(String password, String salt, int count) {
        char[] chars = salt.toCharArray();
        for (int i = 0; i < count; i++) {
            password = EncryptUtil.MD5(chars[0] + chars[1] + chars[2] + password + salt);
        }
        return password;
    }

}
