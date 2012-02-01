package edu.lmu.cs.ksutton.cmsi282.hw2;

import java.util.ArrayList;


/**
 * Problem Set #2
 * Problem #5
 * 
 * The BucketSort class reads doubles from a file into
 * an ArrayList object then uses the BucketSorting 
 * algorithm to quickly sort the list.
 * 
 * The program should be invoked as follows:
 * 
 *     java BucketSort FILENAME
 *     
 * Where FILENAME is a file full of doubles
 * 
 * Caveat: The assignment asks to use the "<" to redirect the
 * file's contents into stdin. After some research, I couldn't
 * figure out how to do that exactly, so I created a class,
 * NumberReader.java that reads the data from the file.
 * 
 * Caveat: I'm getting a lot of warning concerning type safety,
 * parameterization, and the ArrayList object. It's probably
 * unsafe to be casting stuff back and forth all over the place,
 * but it should not be a problem in this program.
 * 
 * @author Kelly Sutton
 *
 */
public class BucketSort {

	public static void main(String args[]) {
		
		ArrayList<Double> masterlist = NumberReader.readDoublesFromFile(args[0]);
		

		// all of the doubles will be 0.0 <= x < 100.0
		double rangeOfBucket = 100.0 / masterlist.size();

		// creating a new ArrayList of n buckets
		ArrayList[] buckets = new ArrayList[masterlist.size()];
		buckets = fillBuckets(buckets);

		double d;
		for (int i = 0; i < masterlist.size(); i++) {
			d = (Double) masterlist.get(i);
			// this mess discovers the appropriate bucket and dumps d in it
			buckets[(int) (d / rangeOfBucket)].add(d); 
		}

		
		masterlist = new ArrayList<Double>();
		
		for (int m = 0; m < buckets.length; m++){
			buckets[m] = insertionSort(buckets[m]); //sort the bucket
			for (int n = 0; n < buckets[m].size(); n++){
				masterlist.add( (Double) buckets[m].get(n));//reading the numbers back into the original list in order
			}
		}
		
		System.out.println(masterlist.toString());

	}

	/**
	 * Modified version of InsertionSort found at
	 * http://www.samspublishing.com/articles/article.asp?p=31526&seqNum=4&rl=1
	 * 
	 * @param a The ArrayList to be sorted
	 * @return A sorted ArrayList
	 */
	public static ArrayList insertionSort(ArrayList a) {

		int in, out;

		for (out = 1; out < a.size(); out++) 
		{
			double temp = (Double) a.get(out); // remove marked item
			
			in = out; // start shifts at out
			
			// until one is smaller,
			while (in > 0 && (Double) a.get(in - 1) >= temp) 
			{
				a.set(in, a.get(in - 1));// shift item right
				in--; // go left one position
			}
			
			a.set(in, temp); // insert marked item
		} 

		return a;
	} 
	
	
	/**
	 * Helper method to fill an array of ArrayLists with _unique_ ArrayLists,
	 * something that Arrays.fill() does not do. 
	 * @param b The array of ArrayLists to be filled
	 * @return The original array filled with empty ArrayLists
	 */
	private static ArrayList[] fillBuckets(ArrayList[] b){
		for (int i = 0; i < b.length; i++){
			b[i] = new ArrayList();
		}
		return b;
	}

}
