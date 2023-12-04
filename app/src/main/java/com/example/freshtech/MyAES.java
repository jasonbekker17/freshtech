package com.example.freshtech;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MyAES {
    private static SecretKeySpec secretkey;
    private static byte[] key;

    public  static void setKey(String mykey){
        try {
            key=mykey.getBytes("UTF-8");

            MessageDigest sha=MessageDigest.getInstance("SHA-256");
            key= sha.digest(key);
            key= Arrays.copyOf(key,16);
            secretkey=new SecretKeySpec(key,"AES");


        }
        catch (Exception e){


        }

    }
    public  static  String encrypt(String enc  ,String sec){
        try {
            setKey(sec);
            Cipher cipher= Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,secretkey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(enc.getBytes("UTF-8")));
        }
        catch (Exception e){

        }
        return null;
    }
    public  static  String dencrypt(String dec  ,String sec){
        try {
            setKey(sec);
            Cipher cipher= Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,secretkey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(dec)));
        }
        catch (Exception e){

        }
        return null;
    }

}
