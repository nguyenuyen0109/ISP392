/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author NGUYEN MINH
 */

//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import javax.xml.bind.DatatypeConverter;

//public class MD5{
//    
//    public String convertPassword(String password) throws NoSuchAlgorithmException {
//
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        md.update(password.getBytes());
//        byte[] digest = md.digest();
//        String passwordMD5 = DatatypeConverter.printHexBinary(digest).toUpperCase();
//
//        return passwordMD5;
//    }
    
   
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 
//package Util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


 
  

// Java program to calculate MD5 hash value
public class MD5 {

    public static String getMd5(String input) {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    // Driver code
} 
 

 
    

