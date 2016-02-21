
import javax.net.ssl.*;
import java.util.logging.*;

///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek6
{

	///////////////////////////////////////////
	//
	// Start of assignment code.
	//
	///////////////////////////////////////////

	/**
	 * Returns the last name, first name, and PID of the student.
	 * 
	 * This is required in order to get credit for the programming assignment.
	 */
	static String GetNameAndPID()
	{
		return("Ansag,Ashton,3244125");
	}

	//	Problem #1
	//	Given a string, count the number of words ending in 'y' 
	//	or 'z' -- so the 'y' in "heavy" and the 'z' in "fez" count, 
	//	but not the 'y' in "yellow" (not case sensitive). We'll say 
	//	that a y or z is at the end of a word if there is not an 
	//	alphabetic letter immediately following it. (Note: 
	//	Character.isLetter(char) tests if a char is an alphabetic letter.) 

	//	wordEndYZ("fez day") → 2
	//	wordEndYZ("day fez") → 2
	//	wordEndYZ("day fyyyz") → 2

	/**
	 * 
	 * @param str
	 * 		str containing the original string
	 * 
	 * @return int
	 * 		int containing the # of words that end in y or z
	 */
	 
	 // cut the string as it searches for y and z's
	static int wordEndYZ(String str) 
	{  
		// base case
		// empty string it does not count
		if( str.length()==0 ) return 0;
		// one item left retun if it is y or z
		if( str.length()==1 )
			return ( str.charAt(0)=='y' || str.charAt(0)=='z' )? 1:0;
		
		// if the next letter is not a letter check for y or z
		if( !Character.isLetter(str.charAt(1)) && (str.charAt(0)=='y' || str.charAt(0)=='z') )
			return 1 + wordEndYZ( str.substring(1) );
		
		// else it is not end of word with y or z
		return 0 + wordEndYZ( str.substring(1) );
	}

	//	Problem #2
	//	Given two strings, base and remove, return a version of the base 
	//	string where all instances of the remove string have been removed 
	//	(not case sensitive). You may assume that the remove string is length 
	//	1 or more. Remove only non-overlapping instances, so with "xxx" 
	//	removing "xx" leaves "x".

	//	removeFromBase("Hello there", "llo") → "He there"
	//	removeFromBase("Hello there", "e") → "Hllo thr"
	//	removeFromBase("Hello there", "x") → "Hello there"

	/**
	 * 
	 * @param base, remove
	 * 		base contains original string of characters
	 * 		remove contains original string that is to be removed from base
	 * 
	 * @return
	 * 		String containing the base with all instances of remove taken out
	 */
	static String removeFromBase(String base, String remove) 
	{
		// well this is built in
		return base.replaceAll(remove, "");
		
		/* // here is how I would hard cold it though
		// if out of string return what is legt
		if( base.length()<remove.length() ) return base;
		// if it starts with the remove string add nothing add move on
		if( base.substring(0, remove.length()).equals(remove) )
			return ""+ removeFromBase( base.substring( remove.length() ) ,remove );
		// else add the first letter and continue recursion 
		return base.charAt(0) + removeFromBase( base.substring(1), remove );
		*/
	}	

	//	Problem #3
	//	Given a string, return true if the number of appearances of 
	//	"is" anywhere in the string is equal to the number of appearances 
	//	of "not" anywhere in the string (case sensitive). 

	//	equalAppearance("This is not") → false
	//	equalAppearance("This is notnot") → true
	//	equalAppearance("noisxxnotyynotxisi") → true

	/**
	 * 
	 * @param str
	 * 		str contains the original string of characters
	 * 
	 * @return
	 * 		returns true if "is" appears as many times as "not"
	 * 		returns false if "is" does not appear as many times as "not"
	 */
	static boolean equalAppearance(String str) 
	{
		int isOccurs, notOccurs;
		// store full str
		String temp = new String(str);
		// compare length of str with "is" to str without "is" to find times "is" is in str
		isOccurs = ( str.length() - str.replaceAll("is","").length() ) /2;
		// refill str
		str = temp;
		// compare length of str with "not" to str without "not" to find times "not" is in str
		notOccurs = ( str.length() - str.replaceAll("not","").length() ) /3;
		
		// return if isOccurs equals notOccurs return true else false
		return (isOccurs==notOccurs)? true:false;
	}	

	//	Problem #4
	//	We'll say that a lowercase 'g' in a string is "happy" if there 
	//	is another 'g' immediately to its left or right. Return true if 
	//	all the g's in the given string are happy. 

	//	gisHappy("xxggxx") → true
	//	gisHappy("xxgxx") → false
	//	gisHappy("xxggyygxx") → false

	/**
	 * 
	 * @param str
	 * 		String containing original string of characters
	 * 
	 * @return
	 * 		returns true if 'g' appears with another 'g' to it's right or left
	 * 		returns false if this is not the case
	 */
	static boolean gisHappy(String str) 
	{
		// base case
		// out of string to check
		if( str.length() == 0 ) return true; 
		// only one char left in the string
		if( str.length() == 1 ) return (str.charAt(0)!='g')? true:false;
		// only two left add another char and continue alg
		if( str.length() == 2 ) str = str +"x";
		
		// if the 2nd letter is a g look right then left
		if( str.charAt(1) == 'g' ){
			
			// check for match right, if so carry on past right char 
			if( str.charAt(2) == 'g' ) return gisHappy( str.substring(3) );
			
			// check for match left, if so carry on past left char 
			if( str.charAt(0) == 'g' ) return gisHappy( str.substring(2) );
		}
		// the first letter is a lone g 
		if( str.charAt(0) == 'g' ) return false;
		
		// the 1st and 2nd letter are not g's carry on past  
		return gisHappy( str.substring(2) );
	}

	//	Problem #5
	//	We'll say that a "triple" in a string is a char appearing three times in a row. 
	//	Return the number of triples in the given string. The triples may overlap. 

	//	numberOfTriples("abcXXXabc") → 1
	//	numberOfTriples("xxxabyyyycd") → 3
	//	numberOfTriples("a") → 0	

	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return
	 * 		Integer containing the # of "triples" in str
	 */
	static int numberOfTriples(String str) 
	{
		// base case
		// not enough string
		if( str.length() < 3 ) return 0;
		
		// triplet is true if 1st and 2nd char are equal
		boolean triplet = str.charAt(0) == str.charAt(1);
		// true if prior is true and 2nd 3rd are true;
		triplet = triplet && str.charAt(2) == str.charAt(1);
		
		// if a triplet, add 1 and recurse
		return (triplet) ? 1:0 + numberOfTriples( str.substring(1) );
	}

	//	Problem #6
	//	Given a string, return the sum of the digits 0-9 that appear in the 
	//	string, ignoring all other characters. Return 0 if there are no digits 
	//	in the string. (Note: Character.isDigit(char) tests if a char is one 
	//	of the chars '0', '1', .. '9'. Integer.parseInt(string) converts a string to an int.) 

	//	addUpDigits("aa1bc2d3") → 6
	//	addUpDigits("aa11b33") → 8
	//	addUpDigits("Chocolate") → 0

	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		Integer containing the # sum of all digits that appear in str
	 */
	static int addUpDigits(String str) 
	{
		// base case
		// out of string
		if( str.length() == 0 ) return 0;
		
		// there is a number add that number to the sum
		if( Character.isDigit( str.charAt(0) ) ) 
			return Integer.parseInt( str.substring(0,1) ) + addUpDigits( str.substring(1) );
		
		// not a number carry on with recursion
		return 0 + addUpDigits( str.substring(1) );
	}

	//	Problem #7
	//	Given a string, return the longest substring that appears at 
	//	both the beginning and end of the string without overlapping. 
	//	For example, beginningAndEndOfString("abXab") is "ab". 

	//	beginningAndEndOfString("abXYab") → "ab"
	//	beginningAndEndOfString("xx") → "x"
	//	beginningAndEndOfString("xxx") → "x"

	/**
	 * 
	 * @param string
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		String containing the beginning and ending substrings that are the same
	 */
	static String beginningAndEndOfString(String string) 
	{
		// base case 
		// out of string
		if( string.length() <= 1 ) return "";
		
		// if the string is odd this alg could have errors make offset
		int offset = ( string.length() % 2 == 1 )? 1:0;
		// the middle partition of the two halves
		int middle = string.length() /2 + offset;
		// left is the equal or largeg half that gets reduced
		String left  = string.substring(0,middle);
		String right = string.substring(middle);
		
		// was the largest begin/end found
		if( left.equals(right) ) return left;
		
		// they did not equal remove one from end of left
		left = ""+ left.substring(0,left.length() -1);
		// recurse
		return beginningAndEndOfString( left +""+ right );
	}

	//	Problem #8
	//	Given a string, look for a mirror image (backwards) string at both 
	//	the beginning and end of the given string. In other words, zero or more 
	//	characters at the very beginning of the given string, and at the very 
	//	end of the string in reverse order (possibly overlapping). For example, 
	//	the string "abXYZba" has the mirror end "ab". 

	//	beginningMirrorEnd("abXYZba") → "ab"
	//	beginningMirrorEnd("abca") → "a"
	//	beginningMirrorEnd("aba") → "aba"

	/**
	 * 
	 * @param string
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		String containing the beginning of the string that is mirrored at the end
	 */
	static String beginningMirrorEnd(String string) 
	{
		// base case 
		// out of string
		if( string.length() == 0 ) return "";
		// if there is only one char then that is the begin/end
		if( string.length() == 1 ) return string;
		
		// hold what will be added to return ( for clarity and one return statement)
		String addOn = new String("");
		
		// the first and last match up
		if( string.charAt(0) == string.charAt(string.length() -1) )
			addOn = string.substring(0,1);
		return addOn + beginningMirrorEnd( string.substring(1,string.length() -1) );
	}

	//	Problem #9
	//	Given a string, return the length of the largest "block" in the string. 
	//	A block is a run of adjacent chars that are the same. 

	//	largestBlock("hoopla") → 2
	//	largestBlock("abbCCCddBBBxx") → 3
	//	largestBlock("") → 0

	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		Integer containing the # of chars in the largest "block" in str
	 */
	static int largestBlock(String str) 
	{
		// non recursive base case
		// if the string is less then 2 the whole str is a run
		if( str.length() <= 1 ) return str.length();
		
		// hold current largest and current counter
		int largest = 0, counter = 1;
		
		// check all the numbers for runs
		for( int i=0; i<str.length() -1; i++ ){
			
			// run length +1 add to counter, else run ended restart counter
			if( str.charAt(i) == str.charAt(i +1) ) counter++;
			else counter = 1;
			// check if a new larger run was found
			if( counter > largest) largest = counter;
		}
		// done looking return the largest run counter
		return largest;
	}

	//	Problem #10
	//	Given a string, return the sum of the numbers appearing in the string, 
	//	ignoring all other characters. A number is a series of 1 or more digit 
	//	chars in a row. (Note: Character.isDigit(char) tests if a char is one 
	//	of the chars '0', '1', .. '9'. Integer.parseInt(string) converts a string to an int.)

	//	addUpNumbers("abc123xyz") → 123
	//	addUpNumbers("aa11b33") → 44
	//	addUpNumbers("7 11") → 18

	/**
	 * 
	 * @param str
	 * 		String containing the original string of characters
	 * 
	 * @return 
	 * 		Integer containing the sum of all the numbers that appear in str
	 */
	static int addUpNumbers(String str) 
	{
		// base case
		// out of string
		if( str.length() == 0 ) return 0;

		// number of digits in a row
		int i = 0;
		// while not out of bounds and still looking at digits
		while( i < str.length() && Character.isDigit( str.charAt(i) ) ){
			// count
			i++;
		}
		// if a number use it in recursion
		if( i > 0 )
			return Integer.parseInt( str.substring(0,i) ) + addUpNumbers( str.substring(i) );
		
		// not a number carry on with recursion
		return 0 + addUpNumbers( str.substring(1) );
	}

	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////

	public static void main(String[] args)
	{
		String str = new String("164");
		int temp = 0;
		
		
		System.out.println( wordEndYZ( "i'am a happy happy man." ));
		System.out.println( removeFromBase( "appple", "pp") );
		System.out.println( equalAppearance( "it is not a" ) );
		System.out.println( gisHappy( "The doggie is good" ) );
		System.out.println( numberOfTriples( "the AAA ruined my life" ) );
		System.out.println( addUpDigits( "I am 400 years old." ) );
		System.out.println( beginningAndEndOfString( "people suck other people" ) );
		System.out.println( beginningMirrorEnd( "racecar" ) );
		System.out.println( largestBlock( "furry furrrries aaaaaare furry...." ) );
		System.out.println( addUpNumbers( "I am 400 years old -2016" ) );
	}

}
