package com.company;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int p = 37;                                                                 //setting up the value of p as instructed in the challenge
        int g = 5;                                                                  //setting up the value of g as instructed in the challenge
        Scanner scanner = new Scanner(System.in);                                   //initiate the scanner class
        System.out.println("Please enter Alice's private key. Let's call it a: ");  //Ask user for the value of a
        int a1 = scanner.nextInt();
        System.out.println("Please enter Bob's private key. Let's call it b: ");    //Ask user for the value of b
        int b1 = scanner.nextInt();
        scanner.close();                                                            //close scanner to prevent memory leak
        int A = modexp(g, a1, p);                                                   //calculate A using g^a mod p
        int B = modexp(g, b1, p);                                                   //calculate B using g^b mod p
        System.out.println("Alice's public key is: " + A);
        System.out.println(" Bob's publick key is: " + B);
        int sA = modexp(B, a1, p);                                                  //calculate Alice's session key
        int sB = modexp(A, b1, p);                                                  //calculate Bob's session key
        System.out.println("Alice's session key: " + sA);
        System.out.println("Bob's session key: " + sB);
        String sAlice = Integer.toString(sA);                                       //convert the integer to string for hash calculation
        String sBob = Integer.toString(sB);                                         //convert the integer to string for hash calculation
        String sha1Bob = "";                                                        //create sha1Bob string to store hash results
        String sha1Alice = "";                                                      //create sha1Alice string to store hash results

        try {                                                                       //the actual SHA-1 hashing algorithm. Should return the same result for both Bob and Alice, since session key is the same
            MessageDigest digest = MessageDigest.getInstance("SHA-1");              //specify the hashing algorithm being used
            digest.reset();
            digest.update(sBob.getBytes("utf8"));                       //convert to bytes
            sha1Bob = String.format("%40x" , new BigInteger(1, digest.digest()));       //calculate Bob's hash result
            System.out.println("We know sha-1 always provides the same hash value for the same string, so both Alice & Bob's hash must be the same.");
            System.out.println("Bob's hash value: " + sha1Bob);
            digest.reset();                                                         //reset the counter
            digest.update(sAlice.getBytes("utf8"));                     //convert to bytes
            sha1Alice = String.format("%40x" , new BigInteger(1, digest.digest()));     //calculate Alice's hash results
            System.out.println("Alice's hash value : " + sha1Alice);
            if (sha1Alice.equals(sha1Bob)) {                                        //a simple way of comparing if both hash values are the same
                System.out.println("Woah! They are the same!");
            } else {
                System.out.println("Woops! Something went wrong.");
            }
        }
        catch (Exception e){                                                        //used in case of an error
            e.printStackTrace();
        }
    }

    public static int modexp(int a, int b, int n) {                                 //power mod calculator. The mathematical logic was found online
        if (b == 0) return 1;
        long t = modexp(a, b/2, n);                                             // use long for intermediate computations to eliminate overflow
        long c = (t * t) % n;
        if (b % 2 == 1)
            c = (c * a) % n;
        return (int) c;                                                             //returns result
    }
}
