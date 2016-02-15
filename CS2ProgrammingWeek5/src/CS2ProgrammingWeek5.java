
///////////////////////////////////////////
//
// Test frame for CS2 programming assignments
//   Created 12-10-2014 by Rick Leinecker
//
///////////////////////////////////////////

public class CS2ProgrammingWeek5 
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
		return( "Ansag,Ashton,3244125");
	}

	/*	Problem #1
	//	Given an array of ints, is it possible to choose a group 
	//	of some of the ints, such that the group sums to the given 
	//	target? This is a classic backtracking recursion problem. 
	//	Once you understand the recursive backtracking strategy in 
	//	this problem, you can use the same pattern for many problems to
	//	search a space of choices. Rather than looking at the whole array, 
	//	our convention is to consider the part of the array starting at 
	//	index start and continuing to the end of the array. The caller 
	//	can specify the whole array simply by passing start as 0. No loops 
	//	are needed -- the recursive calls progress down the array. 

	//	groupSumsTarget(0, {2, 4, 8}, 10) true
	//	groupSumsTarget(0, {2, 4, 8}, 14) true
	//	groupSumsTarget(0, {2, 4, 8}, 9) false

	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTarget(int start, int[] nums, int target) 
	{
		// base cases
		// end of the array
		if(start >= nums.length) return false;
		// found target
		if(nums[start] == target) return true;

		// trace forward to find the target
		if(groupSumsTarget(start +1, nums, target)) return true;
		// then trace to find the sum that matches the target
		if(groupSumsTarget(start +1, nums, target -nums[start])) return true;

		// the target was not found
		return false;
	}

	/*	Problem #2
	//	Given an array of ints, is it possible to choose a group of 
	//	some of the ints, beginning at the start index, such that 
	//	the group sums to the given target? However, with the additional 
	//	constraint that all 6's must be chosen. (No loops needed.)

	//	groupSumsTarget6(0, {5, 6, 2}, 8) true
	//	groupSumsTarget6(0, {5, 6, 2}, 9) false
	//	groupSumsTarget6(0, {5, 6, 2}, 7) false

	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including 
	 *              all 6's in the group
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTarget6(int start, int[] nums, int target) 
	{
		// get number of 5s and use that offset and find if the sum can be made
		return groupSumsTarget( start,nums, (target-(lookForHelper6(start, nums) *6)) );
	}
	static int lookForHelper6(int start, int nums[]){
		
		// base case
		// nothing left to check
		if( start >= nums.length ) return 0;
		// do not count these again when searching
		if( nums[start]==6 ){
			nums[start]=0;
			return 1 + lookForHelper6(start +1, nums);
		}
		// do recursion
		return 0 + lookForHelper6(start +1, nums);		
	}

	/*	Problem #3
	//	Given an array of ints, is it possible to choose a group of some 
	//	of the ints, such that the group sums to the given target with this 
	//	additional constraint: If a value in the array is chosen to be in 
	//	the group, the value immediately following it in the array 
	//	must not be chosen. (No loops needed.)

	//	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 12) → true
	//	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 14) → false
	//	groupSumsTargetNoAdj(0, {2, 5, 10, 4}, 7) → false

	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including the 
	 *              specified constraints
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTargetNoAdj(int start, int[] nums, int target) 
	{
		// base cases
		// end of the array
		if(start >= nums.length) return false;
		// found target
		if(nums[start] == target) return true;
	
		// trace forward to find the target
		if(groupSumsTargetNoAdj(start +1, nums, target)) return true;
		// then trace to find the sum that matches the target
		if(groupSumsTargetNoAdj(start +2, nums, target -nums[start])) return true;
	
		// the target was not found
		return false;
	}	

	/*	Problem #4
	//	Given an array of ints, is it possible to choose a group of some 
	//	of the ints, such that the group sums to the given target with these 
	//	additional constraints: all multiples of 5 in the array must be 
	//	included in the group. If the value immediately following a multiple 
	//	of 5 is 1, it must not be chosen. (No loops needed.) 

	//	groupSumsTarget5(0, {2, 5, 10, 4}, 19) → true
	//	groupSumsTarget5(0, {2, 5, 10, 4}, 17) → true
	//	groupSumsTarget5(0, {2, 5, 10, 4}, 12) → false

	
	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including the specified constraints
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTarget5(int start, int[] nums, int target) 
	{
		// get number of 5s and use that offset and find if the sum can be made
		return groupSumsTarget( start,nums, (target-(lookForHelper5(start, nums) *5)) );
	}
	static int lookForHelper5(int start, int nums[]){
		
		// base case
		// nothing left to check
		if( start >= nums.length ) return 0;
		// do not count these again when searching
		if( nums[start]==5 ){
			nums[start]=0;
			// this is the 1 after a 5 condition
			if( start < nums.length -1 && nums[start]==1)
				nums[start +1]=0;
			return 1 + lookForHelper5(start +1, nums);
		}
		// do recursion
		return 0 + lookForHelper5(start +1, nums);		
	}
	
	/*	Problem #5
	//	Given an array of ints, is it possible to choose a group of some of 
	//	the ints, such that the group sums to the given target, with this 
	//	additional constraint: if there are numbers in the array that are adjacent 
	//	and the identical value, they must either all be chosen, or none of 
	//	them chosen. For example, with the array {1, 2, 2, 2, 5, 2}, either all 
	//	three 2's in the middle must be chosen or not, all as a group. (one loop 
	//	can be used to find the extent of the identical values). 

	//	groupSumsTargetClump(0, {2, 4, 8}, 10) true
	//	groupSumsTargetClump(0, {1, 2, 4, 8, 1}, 14) true
	//	groupSumsTargetClump(0, {2, 4, 4, 8}, 14) false	

	 * 
	 * @param start, nums, target
	 * 		int start tells you where to start in the array nums
	 * 		int[] nums is the given array
	 * 		int target is the value to which the group should sum to
	 * 
	 * @return
	 * 		returns true if there is a group that sums to target including the specified constraints
	 * 		returns false if there is no group that sums to target
	 */
	static boolean groupSumsTargetClump(int start, int[] nums, int target) 
	{
		// base cases
		// end of the array
		if(start >= nums.length) return false;
		// found target and check for a repeat
		if(nums[start] == target){ 
			if( start < nums.length -1 && nums[start +1] == nums[start] ) return false;
			return true;
		}
		
		// count adjacentecies
		int adjust=1;
		if( start < nums.length -1 && nums[start] == nums[start +1] ){
			for( int i=start +1; i < nums.length; i++){
				if( nums[i] == nums[start] ) adjust++;
			}
		}
		
		// trace forward to find the target
		if(groupSumsTargetClump(start +adjust, nums, target)) 
			return true;
		// then trace to find the sum that matches the target
		if(groupSumsTargetClump(start +adjust, nums, target - (nums[start] * adjust))) 
			return true;

		// the target was not found
		return false;
	}

	/*	Problem #6
	//	Given an array of ints, is it possible to divide the ints into two 
	//	groups, so that the sums of the two groups are the same. Every int must 
	//	be in one group or the other. **** Write a recursive helper method that takes 
	//	whatever arguments you like, and make the initial call to your recursive 
	//	helper from splitArray(). (No loops needed.) ****    

	//	divideArray({2, 2})  true
	//	divideArray({2, 3}) false
	//	divideArray({5, 2, 3}) true

	 * 
	 * @param nums
	 * 		int[] nums is the given array
	 * 
	 * @return 
	 * 		returns true if the array can be divided so that the constraints are met
	 * 		returns false if the array cannot be divided so that the constraints are met
	 */
	// why did I need this method?? it says use splitArray to call the helper so I called 
	//     the splitArray here and had it call the helper
	static boolean divideArray(int[] nums) 
	{
		return splitArray( nums );
	}
	static boolean splitArray(int array[]){
		
		// base case
		// 1 or less items cannot be split
		if( array.length <= 1) return false;
		
		// it said no loop so this is instead of a loop to the rightGroup
		int rightGroup[] = new int[array.length -1];
        System.arraycopy(array, 1, rightGroup, 0, rightGroup.length);
        
        // just for clarity the first element is always the whole left side
        int leftGroup = array[0];

        // if the helper found a working match return true
        if( helperSix(0, rightGroup, leftGroup) ) return true;
		
        // makes the first element of the right the entire left grouping
        rightGroup[0] += leftGroup;
        
        // keep trying with recursion
		return splitArray(rightGroup);
	}
	static boolean helperSix(int start, int nums[], int target){
		
		// base case
		// out of elements now return if it matches the required target
		if( start >= nums.length ) return ( target == 0 ) ? true : false;

		// keep adding till all elements added
		return helperSix( start +1, nums, target -nums[start] );
	}
	

	/*	Problem #7
	//	Given an array of ints, is it possible to divide the ints into two groups, 
	//	so that the sum of one group is a multiple of 10, and the sum of the 
	//	other group is odd. Every int must be in one group or the other. Write 
	//	a recursive helper method that takes whatever arguments you like, and 
	//	make the initial call to your recursive helper from 
	//	splitOdd10(). (No loops needed.)  

	//	oddDivide10({5, 5, 5}) → true
	//	oddDivide10({5, 5, 6}) → false
	//	oddDivide10({5, 5, 6, 1}) → true

	 * 
	 * @param nums
	 * 		int[] nums is the given array
	 * 
	 * @return 
	 * 		returns true if the array can be divided so that the constraints are met
	 * 		returns false if the array cannot be divided so that the constraints are met 
	 */
	// As with 6 I think the names were not updated in the comments however I will follow the comment
	
	
	static boolean oddDivide10(int[] nums) 
	{
		return splitOdd10( nums );
	}
	static boolean splitOdd10(int array[]){

		// base case
		// 1 or less items cannot be split
		if( array.length <= 1 ) return false;
		
		// it said no loop so this is instead of a loop to the rightGroup
		int rightGroup[] = new int[array.length -1];
        System.arraycopy( array, 1, rightGroup, 0, rightGroup.length );
        
        // just for clarity the first element is always the whole left side
        int leftGroup = array[0];

        // if the helper found a working match return true
        if( helperSeven(0, rightGroup, leftGroup, 0) ) return true;
		
        // makes the first element of the right the entire left grouping
        rightGroup[0] += leftGroup;
        
        // keep trying with recursion
		return splitOdd10(rightGroup);
	}
	static boolean helperSeven(int start, int nums[], int leftSum, int rightSum) {
		
		// base case
		// out of elements now test requirements
		if( start >= nums.length ){
			
			// test (multiple of 10)  &&  test (odd)
			if( (leftSum  % 10 == 0)  && (rightSum % 2 == 1) ) return true;
			if( (rightSum % 10 == 0)  && (leftSum  % 2 == 1) ) return true;
			
			// else the requirements not met
			return false;
		}

		// keep adding till rightSum is calculated
		return helperSeven( start +1, nums, leftSum, rightSum +nums[start]);
	}

	/* Problem #8
	//	Given an array of ints, is it possible to divide the ints into 
	//	two groups, so that the sum of the two groups is the same, with 
	//	these constraints: all the values that are multiple of 5 must 
	//	be in one group, and all the values that are a multiple of 3 
	//	(and not a multiple of 5) must be in the other. (No loops needed.)  

	//	divide53({1,1}) true
	//	divide53({1, 1, 1}) false
	//	divide53({2, 4, 2}) true

	 * 
	 * @param nums
	 * 		int[] nums is the given array
	 * 
	 * @return 
	 * 		returns true if the array can be divided so that the constraints are met
	 * 		returns false if the array cannot be divided so that the constraints are met
	 */
	static boolean divide53(int[] nums) 
	{
		// find the required numbers
		int larger = lookForHelper53(0, nums, 5);
		int smaller = lookForHelper53(0, nums, 3);
		
		// the helper needs to know which is smaller so switch if needed
		if( smaller > larger ){
			int temp = smaller;
			smaller = larger;
			larger = temp;
		}
		
		return helper53(0, nums, larger, smaller );
	}
	// finds if there is a way to make a matching half 
	static boolean helper53(int start, int nums[], int larger, int smaller){
		
		// base case
		// out of array
		if( start >= nums.length) return false;
		// adding to the larger cannot make the smaller so stop
		if( larger < smaller ) return false;
		
		// if a matching half can be found return true
		if( groupSumsTarget( start, nums, larger - smaller ) ) return true;
		
		// else do more recursion
		return helper53( start +1, nums, larger + nums[start], smaller );
	}
	// counts the required elements
	static int lookForHelper53(int start, int nums[], int look){
		
		// base case
		// nothing left to check
		if( start >= nums.length ) return 0;
		// do not count these again when searching
		if( nums[start]%look == 0 ){
			int temp = nums[start];
			nums[start]=0;
			return temp + lookForHelper53(start +1, nums, look);
		}
		// do recursion
		return 0 + lookForHelper53(start +1, nums, look);		
	}

	///////////////////////////////////////////
	//
	// End of assignment code.
	//
	///////////////////////////////////////////

	public static void main(String[] args)
	{
	}

}
