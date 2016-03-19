import java.util.*;

public class CeasarCipher{
	
	public static final int alphaLen = 26;
	
	public static void main(String[] args) 
	{
		
	}

/*COP 3503 Recitation – Spring 2015
The aim of this recitation is to write a Java program to crack the 
Caesar cipher using simple frequency analysis. 
Submit any necessary files to the assignment as a .zip file
webcourses.
Introduction
A cipher is a method for encoding a message by replacing each character of the 
message by another character. One of the simplest examples is the Caesar cipher, 
which is said to have been used for military purposes by Julius Caesar. To encode 
a message, Caesar simply replaced each letter in the message by the letter three 
places further down the alphabet, wrapping around at the end of the alphabet. 

More generally, the shift factor of three used by Caesar can be replaced by any 
natural number between one and twenty-five, thereby giving twenty-five 
different ways to encode a message. 

The aim of this assignment is to write a java program that can automatically 
decode such messages, by using letter frequencies to determine the most likely 
shift factor that was used to encode the message.
Encoding and Decoding
*/


/*
• Exercise: Define a method
int let2nat( char c );
that converts a lower-case letter in the range ’a’ to ’z’ into the corresponding 
natural number in the range 0 to 25. 
*/
public static int let2nat( char c ){
	// char c has an int value that minus a's gives natural number
	return c - 'a';
}

/*
• Exercise: Define a method
char nat2let(int code)
that performs the inverse method to let2nat. 
*/
public static char nat2let( int code){
	// 'a' + natural number gives char
	return (char)('a' + code);
}

/*
• Exercise: Using let2nat and nat2let, define a method
char shift(int shftAmt, char c)
that applies a shift factor in the range 0 to 25 to a lower-case letter in the range 
’a’ to ’z’. Characters outside this range, such as upper-case letters and 
punctuation, should be returned unshifted. Take care to ensure that your 
method wraps around at the end of the alphabet. 
*/
public static char shift(int shiftAmt, char c){
	// if let2nat gives 0 through 25, return shift with wrap around, else return c
	return (let2nat(c)< alphaLen && let2nat(c)>=0)? (char)( 'a'+(c -'a' + shiftAmt)%alphaLen):c;
}

/*
• Exercise:
Using shift, define a method
String encode(int shftAmt, String str)
that encodes a string using a given shift factor.
*/
public static String encode(int shiftAmt, String str){
	//base case
	//out of string
	if( str.length() == 0 ) return "";
	// return shifted char and recurse
	return shift( shiftAmt, str.charAt(0) ) + encode( shiftAmt, str.substring(1) );
}

/*
• Exercise: Define a method
String decode(int shftAmt, String str)
that performs the inverse method to encode. 
*/
public static String decode(int shiftAmt, String str){
	//base case
	//out of string
	if( str.length() == 0 ) return "";
	// return shifted char and recurse
	return shift( alphaLen-shiftAmt, str.charAt(0) ) + decode( shiftAmt, str.substring(1) );
}

/*
Frequency Analysis:
*/
static double[] es = {8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0, 0.2, 0.8, 4.0, 2.4, 6.7,
7.5, 1.9, 0.1, 6.0, 6.3, 9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1};

/*
• Exercise: Define a method
int lowers(String str)
calculates the number of lower-case letters in a string.
*/
public static int lowers( String str ){
	int counter=0;
	//count number of lowercase numbers
	for(int i=0; i<str.length(); i++)
		if( let2nat(str.charAt(i))<alphaLen && let2nat(str.charAt(i))>=0 ) 
			counter++;
	return counter;
}

/*
Define a method
int count(char c, String str)
that calculates the number of a given character in a string. 
*/
public static int count( char c, String str ){
	int counter=0;
	//count number of selected number
	for(int i=0; i<str.length(); i++)
		if( let2nat(str.charAt(i)) == c -'a' ) 
			counter++;
	return counter;
}

/*
• Exercise: Define a method
double percent(int num1, int num2)
that calculates the percentage of one integer with respect to another, returning 
the result as a floating-point number. 
*/
public static double percent(int num1, int num2){
	//cast ints to doubles and divide * 100 for percent
	return (double)num1 / (double)num2 * 100;
}

/*
• Exercise: Using lowers, count and percent, define a method
double[] freqs(String str) 
that returns the list of percentage frequencies of each of the lower-case letters 
’a’ to ’z’ in a string of characters. 
*/
public static double[] freqs(String str){
	double[] temp = new double[26];
	// for all letters find percent of that letter's frequency
	for( int i=0; i<alphaLen; i++)
		temp[i] = percent( count( (char)('a'+i), str), lowers( str ) );
	
	//return array of frequencies
	return temp;
}

/*
• Exercise: Define a method
double[] rotate(int n, double[] list)
that rotates a list n places to the left, wrapping around at the start of the list, and 
assuming n is in the range zero to the length of the list. 
*/
public static double[] rotate(int n, double[] list){
	double[] temp = new double[alphaLen];
	// for all letters offset back into list
	for( int i=0; i<alphaLen; i++)
		temp[i] = list[(i+n)%alphaLen];
	return temp;
}

/*
• Exercise: Define a method
double chisqr(double[] os)
that calculates the chi square statistic for a list of observed frequencies os with 
respect to a list of expected frequencies es, which is given by
where n is the length of the two lists os and es, and xsi denotes the ith element of 
a list xs, counting from zero. 
*/
public static double chisqr(double[] os){
	double stat = 0;
	// summation given (osi-esi)^2/esi for all letters
	for( int i=0; i<alphaLen; i++)
		stat += ( (os[i] - es[i])*(os[i] -es[i]) / es[i]) ;
	return stat;
}

/*
• Exercise: Define a method
int position(double a, double[] list)
that returns the first position (counting from zero) at which a value occurs in a 
list, assuming that it occurs at least once. 
*/
public static int position(double a, double[] list){
	int i;
	// count till out of string or a is found
	for( i=0; a!=list[i] && i<alphaLen; i++) ;
	return i;
}

/*
• Exercise: Using the methods defined above, define a method
String crack(String str)
that attempts to decode a string by first calculating the letter frequencies in the 
string, then calculating the chi square value of each rotation (in the range zero 
to twenty-five) of this list with respect to the table of expected frequencies, and 
finally using the position of the minimum value in this list as the shift factor to 
decode the original string.
*/
public static String crack(String str){
	double shiftOptions[] = new double[alphaLen];
	// first calculate letter frequencies
	double os[] = rotate( 0, freqs( str )  );
	// holds the lowest chiValues as best guess
	int bestGuess=0;
	//calculate chi for each rotation
	for( int i=0; i<alphaLen; i++ ){
		shiftOptions[i] = chisqr( os );
		// better guess found 
		if( shiftOptions[i] < shiftOptions[bestGuess]) bestGuess = i;
		//rotate by 1
		os = rotate( 1, os );
	}
	//decode with best guess as shiftFactor
	return decode( bestGuess, str );
}
/*
Try out your crack method on the following example:
myxqbkdevkdsyxc yx mywzvodsxq dro ohkw!*/  /*
congratulations on completing the exam!*/
}
