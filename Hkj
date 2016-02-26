import java.io.File;
import java.util.*;
//Ashton Ansag as895644
public class TopologicalSort
{
  // dependence matrix
	boolean bMatrix[][];
	// dependence counts
	int chilDependencies[];
	int pageDependencies[];
	// return value
	int result;
	
	// takes dependence file and sorts
	public TopologicalSort(String filename) throws Exception
	{
		Scanner in = new Scanner(new File(filename));
		
		// while more in file
		while( in.hasNext() ){
		  
			int pages = in.nextInt();
			int numOfEdges = in.nextInt();
			
			// use the larger one for array allocation
			int N = Math.max( pages+1, numOfEdges );
			bMatrix = new boolean[N][N];
			chilDependencies = new int[N];
			pageDependencies = new int[N];
			
			// test case of 0,0 ending
			if( pages == 0 && numOfEdges == 0 ) ;
			else{
			  // get the next two numbers
  			for( int i=0; i < numOfEdges; i++ )
  				bMatrix[in.nextInt()][in.nextInt()] = true;
  			
  			// loop and count how the dependencies overlap
  			for( int i=1; i<N; i++ )
  			  for( int j=1; j<N; j++ ){
  			    if( bMatrix[j][i] ){
  			      pageDependencies[i]++; chilDependencies[i]++;
  			      pageDependencies[0]++; chilDependencies[0]++;
  			    }
  			    
  			  // this shows more than one arrangement mark it as non unique
  			  if( pageDependencies[i] > 1 || chilDependencies[i] > 1 ) bMatrix[0][0] = true;
  			}
  			
  			// check for cycles mostly
  			result = sourceRemoval();
  			System.out.println( result +" "+ bMatrix[0][0] );
			}
		}
		in.close();
	}
	
	// recitaiton idea for finding cycles
	public int sourceRemoval( ){
	  
	  // if this counter gets too large a cycle must have been found
	  int cycleloop = 0;
	  // whenever a page is removed from the queue list
	  int temp = bMatrix.length -1;
	  
	  // all pages placed
	  while( temp > 0 ){
  	  
  	  // cycle has been found return 0
  	  if( ++cycleloop > 3 * bMatrix.length ) return 0;
  	  
  	  // check through all the pages
  	  for( int i=1; i<bMatrix.length; i++ )
  	    // if the page has no dependence place and remove from other pages
  	    if( pageDependencies[i] == 0 ) {
  	      
  	      // one less page      and count the while temp down
    	    pageDependencies[i]--;    temp--;
    	    // it is not cycling so reset counter
    	    cycleloop = 0;
  	      
  	      // look for the pages that depended on the removed page count down
  	      for( int j=0; j<bMatrix.length; j++ )
  	        // something did have a depedence
  	        if( bMatrix[i][j] ){
  	          // count down and mark no dependence
  	          pageDependencies[j]--;
  	          bMatrix[i][j] = false;
  	        }
  	    }
	  }
	  // as a cycle was not found just return the stored non unique or unique return value
	  return ( bMatrix[0][0] ) ? 2 : 1 ;
	}
	
	public static void main(String [] args) throws Exception
	{
		TopologicalSort g = new TopologicalSort("input.txt");
	}
}
