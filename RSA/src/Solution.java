import java.math.*;																//needed to use BigInteger
import java.util.*;																//needed to use the scanner functionality


public class Solution {
	
	 public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);								//initiating the scanner
		 int firstPrime;														//choosing firstPrime to store the value of p
		 do {																	//do-while loop to keep asking the user to enter a valid value for p
			 System.out.println("Please enter a valid prime number p: ");
			 firstPrime = scanner.nextInt();									//enables user to input a number
		 }while(!isPrime(firstPrime));											//runs the isPrime function to check if the user-inputted number is prime or not
		 
		 int secondPrime;														//choosing secondPrime to store the value of q
		 do{																	//do-while loop to keep asking the user to enter a valid value for q
			 System.out.println("Please enter a valid prime number q: ");
			 secondPrime = scanner.nextInt();									//enables user to input a number
			 
		 }while(!isPrime(secondPrime));											//runs the isPrime function to check if the user-inputted number is prime or not
		 
		 int valueOfN = firstPrime * secondPrime;								//calculates the value of n
		 BigInteger p = BigInteger.valueOf(firstPrime);							//converts firstPrime to BigInteger p
		 BigInteger q = BigInteger.valueOf(secondPrime);						//converts secondPrime to BigInteger q
		 BigInteger n = BigInteger.valueOf(valueOfN);							//converts valueOfN to BigInteger n
		 BigInteger phi;														
		 phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));	//calculates the value of phi using the formula phi = (p-1)(q-1)
		 System.out.println(phi);
		 BigInteger e = new BigInteger("17");									//chose the value of e to be 17 since this is one of the most common values
		 BigInteger d = e.modInverse(phi);										//calculates private key d using the formula d = e^(-1)mod n
		 
		 System.out.println("The public key is: [" + e + "," + n + "]");		//prints the public key value
		 System.out.println("The private key is: [" + d + "," + n + "]");		//prints the private key value
		 
		 System.out.println("Please enter the integer value of the message M: ");	//asks user to input an integer message
		 int m = scanner.nextInt();
		 scanner.close();														//closes the scanner to prevent memory leak
		 int n1 = n.intValue();													//converts n back to integer n1
		 int e1 = e.intValue();													//converts e back to integer e1
		 int d1 = d.intValue();													//converts d back to integer d1
		 
		 int c = modexp(m,e1,n1);												//calculates the encrypted message using the a^b mod(n) formula. Here, a=m, b=e1, n=n1
		 int decrypt = modexp(c,d1,n1);											//calculates the decrypted message using the a^b mod(n) formula. Here, a=c, b=d1, n=n1
		 
		 
		 System.out.println("The encrypted message is: " + c );					//prints out the encrypted message
		 System.out.println("The decrypted message is: " + decrypt);			//prints out the decrypted message
		 
	    }
	

    public static int modexp(int x, int y, int p)			  //find a^b mod n
    {
        
        int res = 1; 										 // Initialize result
        x = x % p; 											 // Update x if it is more than or equal to p
        while (y > 0)
        {
            if((y & 1)==1)									// If y is odd, multiply x with result
                res = (res * x) % p;
            y = y >> 1; 									// y must be even now, so y = y /2
            x = (x * x) % p; 
        }
        return res;
    }
    
    public static boolean isPrime(int n) {					//checks if a number is prime or not
    	int i;
    	for(i = 2; i <= n/2; i++) {							//starting at 2, keeps incrementing until it's n/2	
    		if (n%i == 0){									//if n is divisible by any of these numbers, return false
    			return false;
    		}
    	}
    	return true;										//returns true if n is not divisible
    }

}
