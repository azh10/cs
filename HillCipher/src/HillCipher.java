import java.util.*;
import java.io.*;

public class HillCipher
{
	static int n = 0;
	static final int wrapSize = 80;
	
	public void matrixMath(){
		
		
	}
	
	static void prnt(int[][] mat){
		
		int r=0;
		while( r<mat.length ){
			for( int i=0; i<mat.length; i++ ){
				System.out.print( mat[r][i] +" ");
			}
			System.out.println();
			r++;
		}
	}
	static void print(int[][] mat){

		int r=0;
		while( r<mat.length ){
			System.out.print( mat[r++][0] +" ");
		}
	}
	static String toReady(String str){
		
		String temp = new String("");
		int i=0;
		while( i<str.length() ){
			
			if( Character.isLetter( str.charAt(i++) ) )
				temp += str.charAt(i-1);
		
		}
		
		while( temp.length()%n != 0 ){
			temp += "x";
		}
		
		return temp;
	}
	static int[][] toMatr(String plain, int start){
		
		int mat[][] = new int[n][1];
		for( int i=0; i<n; i++ ){
			mat[i][0] = plain.charAt(start +i) - 'a';
			//System.out.print( mat[i][0] +" " );
		}
		//System.out.println();
		
		return mat;
	}
	
	static String matToStr(int mat[][]){
		
		String str = new String("");
		int temp;
		char letter = 'a';
		for( int i=0; i<mat.length; i++ ){
			temp = mat[i][0]%26;
			//letter += temp;
			str += (char)('a' + temp);
			//System.out.println( str );
			//letter = 'a';
		}
		
		return str;
	}
	static String ciph(int mat[][], int key[][]){
		
		String str = new String("");
		int temp[][] = new int[mat.length][1];
		
		//prnt( key );
		
		for( int i=0; i<mat.length; i++){
			//System.out.println( i );
			for( int j=0; j<mat.length; j++ ){
				//System.out.print( j +" " );
				temp[i][0] += key[i][j] * mat[j][0];
				//System.out.println( temp[i][0] );
			}
		}
		//print( temp );
		
		str += matToStr( temp );
		
		//System.out.println( str );
		
		return str;
	}
	

	// add a new line wrap at desired placement
	static String wordWrap(String str){
		
		for( int i=wrapSize; i<str.length(); i +=wrapSize +1 )
			str = str.substring(0,i) +"\n"+ str.substring(i);
		return str;
	}
	
	static int[][] readKey( String filename ){
		
		int size = 4;
		int[][] temp = new int[size][size];
		
		//
		//
		
		return temp;
	}
	
	static String readText( String fileName ){
		
		return "";
	}
	public static void main(String[] args)
	{
		// int keyMat[][] = readKey( args[0] );
		
		int[][] keyMat = {
			{9, 12, 13, 1},
			{7, 3, 6, 9 },
			{8, 4, 2, 1 },
			{10, 5, 15, 3}
		};
		n = keyMat.length;
		
		System.out.println( "Key File: \n"+ n );
		prnt( keyMat );

		String plaint;
		String readyt;
		String cipher = new String("");
		
		//plaint = new String( readText(args[1] ) );
		plaint = new String("Security professionals have said for years that the only way to make a computer truly secure is for it to not be connected to any other computers, a method called airgapping. Then, any attack would have to happen physically, with the attacker actually entering the room and accessing the computer that way, which is incredibly unlikely. In the case of computers containing highly sensitive information, additional, physical security can always be added in the form of security guards, cameras, and so on."+
		"Researchers at Georgia Institute of Technology have uncovered a vulnerability in all computers, however, which can be exploited regardless of an air gap. It’s a vulnerability which you’d never suspect, and it’s one that’s hard to fight against. All CPUs emit electromagnetic signals when they are performing tasks, and the first thing these researchers discovered was that binary ones and zeroes emit different levels. The second thing they discovered is that electromagnetic radiation is also emitted by the voltage fluctuations and that it can be read from up to six meters away. These signals, by the way, are known as side-channels, and they are well-documented in the cryptography field."
		);
		
		// wraps to fit screen
		plaint = wordWrap( plaint );
		System.out.println( "\nInput File: \n\n"+ plaint +"\n" );
		
		
		readyt = new String( toReady( plaint.toLowerCase()) );
		
		// make n sized groups and encrypt adding to cipher string as it goes along
		for( int i=0; i<readyt.length()-1; i += n )
			cipher += ""+ ciph( toMatr(readyt, i), keyMat );
		
		// wraps to fit screen
		cipher = wordWrap( cipher );
		
		System.out.println( "Corresponding Output File: \n\n"+ cipher +"\n" );
		
	
	}
}
