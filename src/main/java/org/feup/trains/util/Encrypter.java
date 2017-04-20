/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Renato
 */
public class Encrypter {

    private static SecretKeySpec secretKey;

    private static final String SECRET_KEY = "L£(iIab£qh#TXCLE§€DpOD]=b$WgZeliLirYDXQs£NoFdOB?nlFyRkyr€mV\"UtdffV%PoKDP#VitGTQYS=%Hu/xDHoYXS§e{iK!xmsAZjQFM=\"nx#UQA(EMWPt€€d%§w";

    private static void setKey() {
        if (secretKey != null) {
            return;
        }
        MessageDigest sha;
        byte[] key;
        try {
            key = SECRET_KEY.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-256");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(Encrypter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String encrypt(String text) {
        try {
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes("UTF-8")));
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

}
