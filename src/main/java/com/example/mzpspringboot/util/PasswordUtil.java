package com.example.mzpspringboot.util;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/8/27 20:05
 * @File : PasswordUtil
 * @Software: IntelliJ IDEA 2019.3.15
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 *加密与解密
 * @author maozp3
 * @description:
 * @date: 2019/8/27 20:05
 */
public class PasswordUtil {
    private static Logger logger = LoggerFactory.getLogger(PasswordUtil.class);

    /**
     * JAVA6支持以下任意一种算法 PBEWITHMD5ANDDES PBEWITHMD5ANDTRIPLEDES
     * PBEWITHSHAANDDESEDE PBEWITHSHA1ANDRC2_40 PBKDF2WITHHMACSHA1
     * */

    /**
     * 定义使用的算法为:PBEWITHMD5andDES算法
     */
    public static final String ALGORITHM = "PBEWITHMD5ANDDES";//加密算法
    public static final String Salt = "12345678";//密钥

    /**
     * 定义迭代次数为1000次
     */
    private static final int ITERATIONCOUNT = 157;

    /**
     * 获取加密算法中使用的盐值,解密中使用的盐值必须与加密中使用的相同才能完成操作. 盐长度必须为8字节
     *
     * @return byte[] 盐值
     * */
    public static byte[] getSalt() throws Exception {
        // 实例化安全随机数
        SecureRandom random = new SecureRandom();
        // 产出盐
        return random.generateSeed(8);
    }

    public static byte[] getStaticSalt() {
        // 产出盐
        return Salt.getBytes();
    }

    /**
     * 根据PBE密码生成一把密钥
     *
     * @param password
     *            生成密钥时所使用的密码
     * @return Key PBE算法密钥
     * */
    private static Key getPBEKey(String password) {
        // 实例化使用的算法
        SecretKeyFactory keyFactory;
        SecretKey secretKey = null;
        try {
            keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            // 设置PBE密钥参数
            PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
            // 生成密钥
            secretKey = keyFactory.generateSecret(keySpec);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return secretKey;
    }

    /**
     * 加密明文字符串 plaintext
     * @param plaintext 待加密的明文字符串
     * @param password 生成密钥时所使用的密码
     * @return
     * @throws Exception
     */
    public static String encrypt(String plaintext, String password) throws Exception {

        Key key = getPBEKey(password);
        byte[] encipheredData = null;
        PBEParameterSpec parameterSpec = new PBEParameterSpec(getStaticSalt(), ITERATIONCOUNT);
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);

            cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);

            encipheredData = cipher.doFinal(plaintext.getBytes());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
        return bytesToHexString(encipheredData);
    }

    /**
     * 解密密文字符串 ciphertext
     * @param ciphertext 待解密的密文字符串
     * @param password 解密时所使用的密码
     * @return
     * @throws Exception
     */
    public static String decrypt(String ciphertext, String password) throws Exception {

        Key key = getPBEKey(password);
        byte[] passDec = null;
        String newPasswod="";
        PBEParameterSpec parameterSpec = new PBEParameterSpec(getStaticSalt(), ITERATIONCOUNT);
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);

            cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);

            passDec = cipher.doFinal(hexStringToBytes(ciphertext));
            newPasswod=new String(passDec);
        }
        catch (Exception e) {
            // TODO: handle exception
            logger.error(e.getMessage());
            throw e;
        }
        return newPasswod;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param src
     *            字节数组
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 将十六进制字符串转换为字节数组
     *
     * @param hexString
     *            十六进制字符串
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static void main(String[] args) {
        String str = "admin123";   // root1237
        String password = "bss@2018";  //bss@2018  root


        try {

            String ciphertext = PasswordUtil.encrypt(str, password);
            System.out.println(ciphertext);
            String plaintext = PasswordUtil.decrypt(ciphertext, password);
            System.out.println(plaintext);
//            String result = PasswordUtil.decrypt("6727370611410f6d", "root", salt);
//            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
