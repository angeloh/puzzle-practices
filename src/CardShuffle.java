
import java.util.*;

/* The example for shuffle problem:
 * [1, 2, 3, 4, 5, 6]
 * [4, 1, 5, 2, 6, 3]
 * [2, 4, 6, 1, 3, 5]
 * [1, 2, 3, 4, 5, 6]
 */

public class CardShuffle {
	
	public static void main(String[] args){
		long num = CardShuffle.shuffles(1002, 101);
		System.out.println(num);
		
    }
	public static long shuffles(int nCards,int iCut)
	{		
		int [] shuffledPos = new int[nCards]; //the position of the cards after one time shuffle
		Set set = new HashSet(); // the set used to record each different circle length, no duplicates allowed in set
		
		// when the iCut is smaller than the middle of nCards
		if(2*iCut < nCards){
			int middlePartNum = nCards - 2*iCut;
			for(int i = 0; i < nCards; i++){
				if (i < iCut){
					shuffledPos[i] = middlePartNum + i*2 + 1;
				} else if ( i < nCards - iCut) {
					shuffledPos[i] = i - iCut;
				} else {
				    int tmp = i - iCut - middlePartNum;
					shuffledPos[i] = middlePartNum + tmp*2;
				}
			}
        //when the iCut is bigger than the middle of nCards	
		} else {
			int leftCut = nCards - iCut;
			int topPartNum = nCards - 2*leftCut;		      
		    for (int i = 0; i < nCards; i++) {
		        if (i < topPartNum) // top
		        	shuffledPos[i] = i;
		        else if (i < iCut) // midportion
		        	shuffledPos[i] = topPartNum + (i - topPartNum)*2 + 1;
		        else // bottom
		        	shuffledPos[i] = topPartNum + (i - iCut)*2;
		    }   
		}
		
		//The number of circle for one card return to its original position.
		for (int j = 0; j < nCards; j++) {
			int curPos = shuffledPos[j];
			long length = 1;
			while(curPos != j) {
				curPos = shuffledPos[curPos];
				length++;
			}
			set.add(length);
		}
		
	    //The number of times to make each card to go back to its original position.
		//It is computed by the number of Least common multiple of all different circle lengths.
		long lcmNum;
		// Iterating over the elements in the set
	    Iterator it = set.iterator();
	    lcmNum = ((Long)it.next()).longValue();
	    while (it.hasNext()) {
	        // Get element
	    	long tmp = ((Long)it.next()).longValue();
	        lcmNum = lcm(lcmNum, tmp);
	    }		
		return lcmNum;
	}
	// this function is used to count the Least common multiple
	private static long lcm(long a, long b) {
		 return (a*b) / gcd(a, b);
    }
	
    //this function is used to count the Greatest common divisor
    private static long gcd(long a, long b) {
		 return (b == 0) ? a : gcd(b, a % b);
	}
}

