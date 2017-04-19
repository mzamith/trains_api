/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.feup.trains.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Renato
 */
public class Encrypter {

    private static PublicKey PUBLIC_KEY = null;

    private static PublicKey getPublicKey() {
        if (PUBLIC_KEY == null) {
            PublicKeyReader reader = new PublicKeyReader();
            try {
                PUBLIC_KEY = reader.get("/private.key");
            } catch (IOException ex) {
                Logger.getLogger(Encrypter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return PUBLIC_KEY;
    }

    public static byte[] encrypt(String text) {
        return rsa(text.getBytes(StandardCharsets.UTF_8));
    }

//    private static byte[] sha256(byte[] data) {
//        MessageDigest digest;
//        try {
//            digest = MessageDigest.getInstance("SHA-256");
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(Encrypter.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//        return digest.digest(data);
//    }
    private static byte[] rsa(byte[] data) {
        final Cipher cipher;

        try {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey());
            return cipher.doFinal(data);
        } catch (IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException ex) {
            Logger.getLogger(Encrypter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static class PublicKeyReader {

        public PublicKey get(String filename) throws IOException {

            byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());

            PKCS8EncodedKeySpec spec
                    = new PKCS8EncodedKeySpec(keyBytes);
            try {
                KeyFactory kf = KeyFactory.getInstance("RSA");
                return kf.generatePublic(spec);
            } catch (InvalidKeySpecException | NoSuchAlgorithmException ex) {
                Logger.getLogger(Encrypter.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
    }

}
