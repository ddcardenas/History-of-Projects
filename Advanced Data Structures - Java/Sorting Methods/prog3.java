package assignment3;  //for running with package in eclipse

//  Daniel Cardenas
//  COMP282 Monday/Wednesday Class
//  Assignment #3
//  Turn in: 12/5/18
//  Contained in this file are methods for Sorting using various quicksort
//		and heapsort algorithms, along with 3 methods for partitioning	

import java.math.*;
import java.util.Random;

//use this class so that you can return two pivots in your quicksorts
//partition method
class pair {
	public int left, right;
	
	public pair(int left, int right) {
		this.left = left;
		this.right = right;
	}
	
	public int getLeft() {
		return left;
	}
	
	public int getRight() {
		return right;
	}
}

class ArraySorts {
	
	//iterative Insertion Sort
	public static void insertionSort(int[] a, int n) {
		//n is number of elements in array
		//step through the array starting at second element
		for (int index = 1; index < n; index++) {
			//save value at index
			int saved_val = a[index];
			int next_index = index-1;
			//step through array in decreasing index
			// so long as elements of greater value are encountered
			while (next_index >= 0 && a[next_index] > saved_val) {
				a[next_index+1] = a[next_index];
				next_index--;
			}
			//when an element of lesser or equal value is found
			// swap that next_index+1 with saved value
			a[next_index + 1] = saved_val;
		}
			
	} //end of insertionSort 
	
	//book's partition method
	private static int partition(int[] a, int LF, int RT, int pivot_index) {	
		//swap pivot with left most index
		int pivot_value = a[pivot_index];
		a[pivot_index] = a[LF];
		a[LF] = pivot_value;
		pivot_index = LF;
		//set last element of partition of the lessers
		int lastS1 = LF;
		//set first unknown element
		int firstUnknown = LF + 1;
		boolean flag = true;
		
		//check all unknowns until there are none left
		//there will be no more unknowns 
		// when firstUnknown is greater than RT 
		while (firstUnknown <= RT) {
			//if less than pivot value, 
			// swap with lastS1+1 to become lastS1
			// and increment firstUnknown
			if (a[firstUnknown] < pivot_value) { 
				int tempS1value = a[lastS1+1];
				a[lastS1+1] = a[firstUnknown];
				a[firstUnknown] = tempS1value;
				lastS1++;
				firstUnknown++;
			}
			//if great than pivot value,
			// merely increment firstUnknown
			else if (a[firstUnknown] > pivot_value) {
				firstUnknown++;
			}
			else {
				if (flag) {
					//swap into small partition
					int tempS1value = a[lastS1+1];
					a[lastS1+1] = a[firstUnknown];
					a[firstUnknown] = tempS1value;
					lastS1++;
					firstUnknown++;
				}
				else {
					//don't swap - put in large partition
					firstUnknown++;
				}
				flag = !flag;
			}
			
		} //end while
		
		//place pivot in proper position
		a[LF] = a[lastS1];
		a[lastS1] = pivot_value;
		return lastS1;
		
	} //end partition method
	
	//2 pointer partition
	private static pair partition2(int[]a, int LF, int RT, int pivot_index) {
		//start with LF and RT as pointers
		//value of pivot_index is our pivot
		int pivot_value = a[pivot_index];
		int rtptr = RT;
		int lfptr = LF;
		
		//main while loop for partition, until LF crosses RT
		while (LF <= RT) {
			//NEW TESTING STRATEGY//
			if (a[LF] >= pivot_value) {
				if (a[RT] <= pivot_value) {
					int templf = a[LF];
					a[LF] = a[RT];
					a[RT] = templf;
					LF++;
					RT--;
				}
				else {
					RT--;
				}
				
			}
			else {
				LF++;
			}
			
		} //end main while loop
		
		//RT is end of left partition (S1)
		//LF is beginning of right partition (S2)
		return new pair(RT, LF);
	} //end of partition2 method
	
	
	//3 partition method from class notes
	private static pair partition3(int[]a, int LF, int RT, int pivot1, int pivot2) {
		
		int temp_value = a[pivot1];
		a[pivot1] = a[LF];
		a[LF] = temp_value;
		
        temp_value = a[RT];
		a[RT] = a[pivot2];
		a[pivot2] = temp_value;
        
		if (a[LF] > a[RT]) {
			//  swap LF and RT
			int templs = a[RT];
			a[RT] = a[LF];
			a[LF] = templs;
        }
		
		int ls = LF; //last smaller
		int fb = RT; //first bigger
		int fu = LF+1; //first unknown
		boolean flag = true;
		
		while (fu < fb) { 
			//place first unknown (a[fu]) into its proper position
			//if smaller than pivot1
			if (a[fu] < a[LF]) {
				int temp_val = a[ls+1];
				a[ls+1] = a[fu];
				a[fu] = temp_val;
				ls++;
				fu++;
			}
			//else if larger than pivot2
			else if (a[fu] > a[RT]) {
				//swap with a[fb-1]
				int temp_val = a[fb-1];
				a[fb-1] = a[fu];
				a[fu] = temp_val;
				fb--;
			}
			//otherwise it is between pivot1 and pivot2 or equal to either
			else if (a[LF] < a[fu] && a[fu] < a[RT]) {
				fu++;
			}
			else {
				if (flag) {
                    if (a[fu] == a[LF]) {
                		int tempvalu = a[ls+1];
                		a[ls+1] = a[fu];
                		a[fu] = tempvalu;
                        ls++;
                        fu++;
                    } else if (a[fu] == a[RT]) {
                		int tempfbval = a[fb-1];
                		a[fb-1] = a[fu];
                		a[fu] = tempfbval;
                        fb--;
                    }
                } else {
                    fu++;
                }
                flag = !flag;
			}
		} //end while
		
		//place pivot1 and pivot2 in correct position
		//  swap ls and LF
		int templs = a[ls];
		a[ls] = a[LF];
		a[LF] = templs;
		//  swap fb and RT
		int tempfb = a[fb];
		a[fb] = a[RT];
		a[RT] = tempfb;
		
		//return indexes for pivot1 and pivot2
		return new pair(ls, fb);
	} //end of partition3
	
	
	/*******************/
	//QuickSort1 method//
	public static void QuickSort1(int[] a, int n, int cutoff) {
		//n is number of elements in array, 
		//	thus n-1 is last index in array with an element
		int max_index = n - 1;
		QuickSort1(a, 0, max_index, cutoff);
		insertionSort(a, n);
		
	} //end QuickSort1 driver
	
	private static void QuickSort1(int[] a, int LF, int RT, int cutoff) {
		
		int pivot;
		// also tested while condition as (RT - LF + 1 >= cutoff+1)
		while (RT - LF + 1 >= cutoff && (RT-LF+1) != 0) { //allows for cutoff of 0
			Random random_num = new Random();
			int range = RT - LF;
			//I found out the bound, .nextInt(bound), cannot be 0
			// and cutoff must be greater than 0
			int random_value = 0;
			if (cutoff > 0)
				random_value = random_num.nextInt(range+1);
			pivot = LF + random_value; 
			
			pivot = partition(a, LF, RT, pivot);
			int LF_size = pivot - 1 - LF;
			int RT_size = RT - (pivot + 1);
			if (LF_size < RT_size) {
				QuickSort1(a, LF, pivot-1, cutoff);
				LF = pivot + 1;
			}
			else {
				QuickSort1(a, pivot+1, RT, cutoff);
				RT = pivot - 1;
			}
		}
	} //end QuickSort1

	
	/*******************/
	//Quicksort2 method//
	public static void QuickSort2(int[]a, int n, int cutoff) {
		
		int max_index = n-1;
		QuickSort2(a, 0, max_index, cutoff);
		insertionSort(a, n);	
	}
	
	public static void QuickSort2(int[]a, int LF, int RT, int cutoff) {
		int pivot;
		pair pointers = new pair(0,0); //to hold pointers after partitioning
		while (RT - LF + 1 >= cutoff) { // && (RT-LF+1) != 0) { 
			Random random_num = new Random();
			int range = RT - LF;
			//I found out the bound, .nextInt(bound), cannot be 0
			// and cutoff must be greater than 0
			int random_value = 0;
			if (cutoff > 0)
				random_value = random_num.nextInt(range+1);
			pivot = LF + random_value; 
			
			//need to hold the 2 pointers in pair object
			pointers = partition2(a, LF, RT, pivot);
			//pointers.getLeft() holds end of left partition
			//pointers.getRight() holds beginning of right partition
			int LF_size = pointers.getLeft() - LF + 1;
			int RT_size = RT - pointers.getRight() + 1;   //RT - (pivot + 1);
			if (LF_size < RT_size) {
				QuickSort2(a, LF, pointers.getLeft(), cutoff);
				LF = pointers.getRight();
			}
			else {
				QuickSort2(a, pointers.getRight(), RT, cutoff);
				RT = pointers.getLeft();
			}
		}	
		
	} //end of QuickSort2 method	
	
	
	/*******************/
	//QuickSort3 method//
	public static void QuickSort3(int[] a, int n, int cutoff) {
		//n is number of elements in array, 
		//	thus n-1 is last index in array with an element
		int max_index = n - 1;
		QuickSort3(a, 0, max_index, cutoff);
		insertionSort(a, n);
		
	} //end QuickSort3 driver
	
	private static void QuickSort3(int[] a, int LF, int RT, int cutoff) {
		
		int pivot;
		// also tested while condition as (RT - LF + 1 >= cutoff+1)
		while (RT - LF + 1 >= cutoff && (RT-LF+1) != 0) { //allows for cutoff of 0
			pivot = LF; 
			pivot = partition(a, LF, RT, pivot);
			int LF_size = pivot - 1 - LF;
			int RT_size = RT - (pivot + 1);
			if (LF_size < RT_size) {
				QuickSort3(a, LF, pivot-1, cutoff);
				LF = pivot + 1;
			}
			else {
				QuickSort3(a, pivot+1, RT, cutoff);
				RT = pivot - 1;
			}
		}
	} //end QuickSort3
	
	
	/*******************/
	//Quicksort4 method//
	public static void QuickSort4(int[]a, int n, int cutoff) {
		
		int max_index = n-1;
		QuickSort4(a, 0, max_index, cutoff);
		insertionSort(a, n);	
	}
	
	public static void QuickSort4(int[]a, int LF, int RT, int cutoff) {
		int pivot;
		pair pointers = new pair(0,0); //to hold pointers after partitioning
		while (RT - LF + 1 >= cutoff && (RT-LF+1) != 0) { 
			pivot = LF; 
			
			//need to hold the 2 pointers in pair object
			pointers = partition2(a, LF, RT, pivot);
			//pointers.getLeft() holds end of left partition
			//pointers.getRight() holds beginning of right partition
			int LF_size = pointers.getLeft() - LF + 1;
			int RT_size = RT - pointers.getRight() + 1;   //RT - (pivot + 1);
			if (LF_size < RT_size) {
				QuickSort4(a, LF, pointers.getLeft(), cutoff);
				LF = pointers.getRight();
			}
			else {
				QuickSort4(a, pointers.getRight(), RT, cutoff);
				RT = pointers.getLeft();
			}
		}	
		
	} //end of QuickSort4 method	
	
	/*******************/
	//QuickSort5 method//
	public static void QuickSort5(int a[], int n, int cutoff) {
		//n is number of elements in array, 
		//	thus n-1 is last index in array with an element
		int max_index = n - 1;
		QuickSort5(a, 0, max_index, cutoff);
		insertionSort(a, n);		
	} //end QuickSort5 driver
	
	private static void QuickSort5(int a[], int LF, int RT, int cutoff) {
		
		while (RT - LF + 1 >= cutoff) {	
			int pivot1;
			int pivot2;
			Random random_num = new Random();
			int range = RT - LF;
			int random_value1;
			
			random_value1 = random_num.nextInt(range+1);
			pivot1 = LF + random_value1; 
			
            do {
                pivot2 = LF + random_num.nextInt(range+1);
            } while (pivot1 == pivot2);
			
			pair both_pivots;
			both_pivots = partition3(a, LF, RT, pivot1, pivot2);
			
			//size of each of the 3 segments
			int S1_size = both_pivots.getLeft() - LF; 
			int S2_size = both_pivots.getRight() - both_pivots.getLeft() + 1;
			int S3_size = RT - both_pivots.getRight();
			
			//find biggest of 3 segments
			// and then quicksort smaller 2 segments
			// and then have while loop simulate quicksort on biggest segment	
			if (S1_size > S2_size) {
				//then S2 is not biggest, thus can be ignored
				if (S1_size > S3_size) {
					//then S1 is biggest
					//QS S2 and S3, simulate S1
					QuickSort5(a, both_pivots.getLeft()+1, both_pivots.getRight()-1, cutoff); //S2
					QuickSort5(a, both_pivots.getRight()+1, RT, cutoff); //S3
					RT = both_pivots.getLeft() - 1;
				}
				else {
					//otherwise S3 is biggest or equal to S1
					//QS S1 and S2, simulate S3
					QuickSort5(a, LF, both_pivots.getLeft()-1, cutoff); //S1
					QuickSort5(a, both_pivots.getLeft()+1, both_pivots.getRight()-1, cutoff); //S2
					LF = both_pivots.getRight() + 1;
				}
			}
			else {
				//then S1 is not biggest, thus can be ignored
				if (S2_size > S3_size) {
					//then S2 is biggest
					//QS S1 and S3, simulate S2
					QuickSort5(a, LF, both_pivots.getLeft()-1, cutoff); //S1
					QuickSort5(a, both_pivots.getRight()+1, RT, cutoff); //S3
					LF = both_pivots.getLeft() + 1;
					RT = both_pivots.getRight() - 1;
				}
				else {
					//otherwise S3 is biggest or equal to S1
					//QS S1 and S2, simulate S3
					QuickSort5(a, LF, both_pivots.getLeft()-1, cutoff); //S1
					QuickSort5(a, both_pivots.getLeft()+1, both_pivots.getRight()-1, cutoff); //S2
					LF = both_pivots.getRight() + 1;
				}
			} 
		} //end while loop
	} //end QuickSort5

	
	/******************/
	//AlmostQS1 method//
	public static void AlmostQS1(int[] a, int n, int cutoff) {
		//n is number of elements in array, 
		//	thus n-1 is last index in array with an element
		int max_index = n - 1;
		QuickSort1(a, 0, max_index, cutoff);
		//insertionSort(a, n);
		
	} //end AlmostQS1 driver
	
	/*******************/
	//AlmostQS2 method//
	public static void AlmostQS2(int[]a, int n, int cutoff) {
		
		int max_index = n-1;
		QuickSort2(a, 0, max_index, cutoff);
		//insertionSort(a, n);	
	}
	
	/*******************/
	//AlmostQS5 method//
	public static void AlmostQS5(int a[], int n, int cutoff) {
		//n is number of elements in array, 
		//	thus n-1 is last index in array with an element
		int max_index = n - 1;
		QuickSort5(a, 0, max_index, cutoff);
		//insertionSort(a, n);		
	} //end AlmostQS5 driver
	
	
	/*******************/
	
	public static void HeapSortTD(int[]a, int n) {
			
		if (n > 1) {
			int max_index = n-1;
			//change the array to a heap, using top down method
			for (int i = 1; i <= max_index; i++) {
				int saved_value = a[i];
				int saved_index = i;
				
				if (a[i] > a[(i-1)/2]) {
					boolean at_beginning = false;
					//while not at beginning of array 
					//	and our current element is greater than its parent
					while (!at_beginning && saved_value > a[(i-1)/2]) { 
						a[i] = a[(i-1)/2]; //parent shifts down to child
						i = (i-1)/2; //parent is now target
						if (i == 0)
							at_beginning = true;
					} //end while loop
					
					//current target is assigned original value of our focus
					a[i] = saved_value; 
				} //end if loop
				
				i = saved_index;
			} //end for loop
			
			//second thing to do is swap the first element (largest) 
			//	with the last element
			int tempval = a[max_index];
			a[max_index] = a[0];
			a[0] = tempval;
			
			//third thing to do is do same thing on a smaller array (n-1)
				//trickle first down
				//swap first/last
				//	if focus element is less than either child
			max_index--;
			while (max_index > 0) {
			int saved_value = a[0];
			int saved_index = 0;
			boolean focus_has_children = true;
			boolean heap_done = false;
			int i = 0;
			//if element is less than either child, 
			//	perform shifting up and tricking down
			//  MUST FIRST CHECK IF THERE IS A CHILD
			//	then, after verifying the element has a child we can compare
			//check if element is a heapsort
			// by checking children
			
			//TRICKLE FIRST DOWN
			while (focus_has_children && !heap_done) {
			//check if element has children
			//	if left child has index < n, than it exists	
			if ((i*2)+1 <= max_index) { 
				//if right child has index < n, than it exists
				//	and element has TWO children
				if ((i*2)+2 <= max_index) {
					//find if element is less than either child
					if (saved_value < a[(i*2)+1] || saved_value < a[(i*2)+2]) {
						if (a[(i*2)+1] > a[(i*2)+2]) { //left child is greater
							//shift up left child
							a[i] = a[(i*2)+1];
							//trickle down: change focus to left child
							i = (i*2)+1;
						}
						else { //otherwise right child is greater
							//shift up right child
							a[i] = a[(i*2)+2];
							//trickle down: change focus to right child
							i = (i*2)+2;
						} //end left/right greater than
					}
					//otherwise element is greater than or equal to both children
					else { 
						heap_done = true;	
					} //end 2 child compare
				}
				else { //otherwise element ONLY has left child: at end of array
					if (a[i] < a[(i*2)+1]) { //if element is less than child
						//shift up
						a[i] = a[(i*2)+1];
						//trickle down: change focus to left child
						i = (i*2)+1;
					}
					else { //otherwise element is greater than child
						heap_done = true;
					} //end only 1 child compare
					
				} //end 1 or 2 children check
			}
			else { //otherwise, element has no children
				focus_has_children = false;
			} //end check of atleast one or no children
				
			} //end while loop	
			//after all that
			a[i] = saved_value;
			//i = saved_index;
			//SWAP FIRST/LAST
			int temp_value = a[max_index];
			a[max_index] = a[0];
			a[0] = temp_value;
			//REPEAT, BUT NEXT TIME DECREASE MAX_INDEX BY ONE
			max_index--;
			}
		} //end check for array greater than 1 elements
	} //end HeapSortTD
	
	
	public static void HeapSortBU(int[] a, int n) {
		
		//BUILD HEAP BOTTOM UP
		for (int i = (n-2)/2; i >= 0; i--) {
			trickle_down(a, i, n-1);
		}
		//put first element (largest) to end
		//	then trickle down new first element
		for (int max_index = n-1; max_index > 0; max_index--) {
			swap(a, max_index, 0);
			trickle_down(a, 0, max_index-1);	
		}
		
	}
	
	private static void swap(int[] a, int max_index, int zero) {
		int savedval = a[max_index];
		a[max_index] = a[zero];
		a[zero] = savedval;
	}
	
	private static void trickle_down(int[] a, int i, int n) {
		int saved_value = a[i];
		//boolean heap_done = false;
		//int max_index = n-1;
		int child = (i*2)+1;
		//if element is less than either child, perform shifting up and tricking down
		//  MUST FIRST CHECK IF THERE IS A CHILD
		//	then, after verifying the element has a child we can compare
		//check if element is a heapsort
		// by checking children
		if (child+1 <= n && a[child] < a[child+1]) {
			child++;
        }
		
		if ((i * 2 + 1) <= n && saved_value < a[child]) {
			
            do {
                a[i] = a[child];
                i = child;
                child = i * 2 + 1;
                if (child + 1 <= n && a[child] < a[child + 1]) {
                    child++;
                }
            } while ((i * 2 + 1) <= n && saved_value < a[child]);
            a[i] = saved_value;
        }

	} //end trickle down method

	public static String myName() {
		return "Daniel D. Cardenas";
	}
	
} //end of ArraySorts
