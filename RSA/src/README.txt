Implement the RSA key algorithm and encrypt/decrypt a message using any programming language you like

SOLUTION:

I used java to implement the RSA key algorithm. In order to implement the algorithm, I used the steps described in the textbook and in the lecture slides to calculate n, phiN and d. I set the value of e to 17, since the textbook said it is one of the most common values. I implemented the fast algorithm, a^b mod(n), using the lecture slide that breaks it down in mathematical terms.

I have included the Solution.java file with the assignment. I successfully compiled the program and found the correct results for various inputs in my terminal using the following commands:
	- cd src
	- javac Solution.java
	- java Solution