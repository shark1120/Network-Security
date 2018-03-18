import java.util.*; //needed to utilize the scanner class

public class Solution {
	
	public static void main(String args[]) {
			Scanner scanner = new Scanner(System.in); //initiate the scanner class so the users can type in the numbers
		    System.out.println("Please Enter First Integer:");
		    int firstInt = scanner.nextInt();		//user enters the first integer
		    System.out.println("Please Enter Second Integer:");
		    int secondInt = scanner.nextInt();		//user enters the second integer
		    scanner.close();				        //closing the scanner to prevent memory leak
		    System.out.println("The GCD of " + firstInt + " and "+ secondInt + " is: " + findGCD(firstInt, secondInt));		                //prints out the result after running the findGCD function
	}
	
	public static int findGCD(int firstNum, int secondNum) {	//takes in the two integers inputted by the user as parameters
	    if (secondNum == 0) {	                                //when the second integer returns 0, return the first number
	     return firstNum;
	    }
        //if second integer is not 0, use recursion to run the function again
	    return findGCD(secondNum, firstNum%secondNum);      //if second number is not 0, swap firstNum with secondNum and secondNum with firstNum%secondNum as parameters
        //the recursive function keeps running until we get 0 for the second parameter.
	  }

}
