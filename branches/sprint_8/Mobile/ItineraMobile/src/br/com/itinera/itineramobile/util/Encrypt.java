/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.itinera.itineramobile.util;

import java.security.MessageDigest;


/**
 *
 * @author LeticiaSena
 */

public class Encrypt {
    private String encryptedString;
    private boolean hasError;
    private String errorMsg;
    
    @SuppressWarnings("all")
    public Encrypt(String plaintext,String algorithm, String encoding){
        MessageDigest msgDigest;
        encryptedString = "";
        errorMsg = "";
        hasError = true;
        try{
            msgDigest = MessageDigest.getInstance(algorithm);
            msgDigest.update(plaintext.getBytes(encoding));
            byte rawByte[] = msgDigest.digest();
            //encryptedString = (new sun.misc.BASE64Encoder()).encode(rawByte);
            hasError = false;
        }catch(Exception e){
            errorMsg = e.getMessage();
        }
    }
    
    public boolean hasError(){
        return hasError;
    }
    
    public String getErrorMsg(){
        
        return errorMsg;
    }
    
    public String getResult(){
        return encryptedString;
    }
}