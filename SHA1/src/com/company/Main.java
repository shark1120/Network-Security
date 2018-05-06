package com.company;

import javax.crypto.Mac;
//import org.apache.commons.codec.binary.Hex;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;
import java.util.Scanner;

public class Main {
    private static final String HMAC_SHA1 = "HmacSHA1";

    public static void main(String[] args) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {

        Scanner scan = new Scanner(System.in);                                              //open the scanner class so users can provide input
        System.out.println("Hi! \n" + "Please enter the string you want to hash: ");
        String input = scan.next();
        scan.close();                                                                       //closes the scanner to prevent memory leak
        String sha1 = "";                                                                   //initiate the value of SHA-1. Would later return the actual hash value



        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");                      //instantiates a secure one-way SHA-1 hash function
            digest.reset();
            digest.update(input.getBytes("utf8"));                              //converts input to bytes
            sha1 = String.format("%40x",  new BigInteger(1, digest.digest()));      //converts the bytes to a hash value
        } catch (Exception e) {
            e.printStackTrace();                                                            //needed to throw error messages
        }

        System.out.println("The hash of " + input + " is: " + sha1);                        //prints out the hash result
        String hmac = calculateHMAC(input, "key");                                     //calculates the hashed MAC for the message
        System.out.println("The hashed MAC for the input is: " + hmac);                     //prints the hashed MAC

    }

    private static String toHex(byte[] bytes) {                                             //to convert into Hex
        Formatter formatter = new Formatter();

        for (byte b: bytes) {
            formatter.format("%02x",b);                                                     //formatting required for the HMAC algorithm to work
        }
        return formatter.toString();                                                        //returns the hex value
    }
    public static String calculateHMAC(String data, String key)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException
    {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1);            //specifies a secret key in a provider-independent fashion and the algorithm being used
        Mac mac = Mac.getInstance(HMAC_SHA1);                                               //Provides the MAC Algorithm for SHA-1
        mac.init(signingKey);                                                               //initialize the mac instance
        return toHex(mac.doFinal(data.getBytes()));                                         //returns the result
    }
}
