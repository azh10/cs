import java.util.*;
import java.io.*;

public class HillCipher
{

	static int n = 0;
	static final int wrapSize = 80;
	
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
		}	
		return mat;
	}
	
	// encrypt with the cipher 
	static String ciph(int mat[][], int key[][]){
		
		String str = new String("");
		int temp[][] = new int[mat.length][1];
		for( int i=0; i<mat.length; i++){
			for( int j=0; j<mat.length; j++ )
				temp[i][0] += key[i][j] * mat[j][0];
			str += (char)('a'+ temp[i][0]%26);
		}
		return str;
	}
	

	// add a new line wrap at desired placement
	static String wordWrap(String str){
		
		for( int i=wrapSize; i<str.length(); i +=wrapSize +1 )
			str = str.substring(0,i) +"\n"+ str.substring(i);
		return str;
	}
	
	static int[][] readKey( String fileName ){
		
		int size, temp[][];
		try {
			Scanner file = new Scanner( new File(fileName) );
			size = file.nextInt();
			temp = new int[size][size];

			for( int i=0; i<size; i++ )
				for( int j=0; j<size; j++ )
					temp[i][j] = file.nextInt();
			
			file.close();
			return temp;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return new int[1][1];
	}
	
	static String readText( String fileName ){
		
		String temp = new String("");
		try {
			Scanner file = new Scanner( new File(fileName) );
			while( file.hasNextLine() ){
				temp += file.nextLine();
			}
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	public static void main(String[] args)
	{
		// fill the key array
		int[][] keyMat = readKey( args[0] ) ;
		n = keyMat.length;
		
		System.out.println( "Key File: \n"+ n );
		prnt( keyMat );

		// wraps to fit screen
		String plaint = new String( wordWrap( readText(args[1]) ) );
		String readyt = new String( toReady( plaint.toLowerCase()) );
		String cipher = new String("");
		
		System.out.println( "\nInput File: \n\n"+ plaint +"\n" );
		
		// make n sized groups and encrypt adding to cipher string as it goes along
		for( int i=0; i<readyt.length()-1; i += n )
			cipher += ""+ ciph( toMatr(readyt, i), keyMat );
		
		cipher = wordWrap( cipher );	
		System.out.println( "Corresponding Output File: \n\n"+ cipher +"\n" );
	
	}
}
