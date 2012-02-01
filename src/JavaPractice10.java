import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.*;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Collections;

public class JavaPractice10 {
	public static void main(String [] args) {		
		
		int n = Integer.parseInt("1111111111111111", 2);
		System.out.println(Integer.toBinaryString(n));
		int rn = reverseBit(n);
		System.out.println(Integer.toBinaryString(rn));	
		replaceStr();
		int [] arr = {0, 1, 2, 2, 1, 0};
		dutchSort(arr);
		System.out.println(Arrays.toString(arr));
		String str = rotateStr("abcdefg", 2);
		System.out.println(str);
		str = rotateStr("abcdefg", 1);
		System.out.println(str);
		str = rotateStr("abcdefg", 5);
		System.out.println(str);
		str = rotateStr("abcdef", 2);
		System.out.println(str);
		str = rotateStr("abcdef", 3);
		System.out.println(str);
		str = rotateStr("abcdef", 5);
		System.out.println(str);
		String str2 = rotateStr2("abcdef", 5);
		System.out.println(str2);
	}
	
	public static int reverseBit(int n) {
		int result = 0;
		for (int i = 0; i < 16; i++) {
			int r = n & (1 << i);
			int l = n & (1 << (31-i));			
			int diff  = 31-i-i;
			result |= (l>>>diff);
			result |= (r<<diff);
		}
		return result;
	}
	
	/**
	 * Write a program to replace string 'us' with 'them' from the following String . Do not replace 'Us' as well as any string containing us
	 * $_="Us? It usually rains when bus comes to us";
	 */
	public static void replaceStr() {
		String str = "Us? It usually rains when bus comes to us";
		String str2 = str.replaceAll(" us ", " them ");
		str2 = str2.replaceAll(" us$", " them");
		System.out.println(str2);
	}
	
	
	/**
	 * @param arr
	 * Dutch National Flag http://www.csse.monash.edu.au/~lloyd/tildeAlgDS/Sort/Flag/
	 * How to sort an array with only {0, 1, 2} possible values in O(n) without 
	 * extra space?
	 * Ex: an array {0, 1, 2, 2, 1, 0}
	 */
	public static void dutchSort(int [] arr) {
		int low = 0;
		int mid = 0;
		int hi = arr.length-1;
		
		while(mid<=hi) {
			switch(arr[mid]) {
			case 0:
				swap(arr, low, mid);
				low++;
				mid++;
				break;
			case 1:				
				mid++;				
				break;
			case 2:
				swap(arr, mid, hi);
				hi--;
				break;
			default:
				throw new InvalidParameterException();
			}
		}
		
	}
	
	public static void swap(int [] arr, int l, int r) {
		int tmp = arr[l];
		arr[l] = arr[r];
		arr[r] = tmp;
	}
	
	/**
	 * @param str
	 * @param k
	 * @return String
	 * using one variable to rotate string
	 * 
	 * this only support right rotate
	 * the reason to use gcd is that we only need to do the gcd's times of circular swap
	 */
	public static String rotateStr(String str, int k) {
		if (str == null)
			return null;
		if (k == 0 || str.length() == k)
			return str;
		if (k < 0)
			throw new InvalidParameterException();
		char [] arr = str.toCharArray();
		int gcd = findGCD(str.length(), k);
		for (int i = 0; i < gcd; i++) {
			int curi = i;			
			char cc = arr[curi];
			int prei = (i-k)<0?arr.length+(i-k):i-k;
			while(prei != i) {				
				arr[curi] = arr[prei];
				curi = prei;
				prei = (prei-k)<0?arr.length+(prei-k):prei-k;
			}
			arr[curi] = cc;			
		}
		return new String(arr);
	}
	
	public static int findGCD(int a, int b) {
		if (b == 0)
			return a;
		return findGCD(b, a%b);
	}
	
	public static String rotateStr2(String str, int k) {
		if (str == null)
			return null;
		if (k == 0)
			return str;
		if (k < 0)
			throw new InvalidParameterException();
		char [] arr = str.toCharArray();
		int l = 0, r = arr.length-1;
		while(l<=r){
			swap(arr, l, r);
			l++;
			r--;
		}
		l = 0;
		r = k-1;
		while(l<=r){
			swap(arr, l, r);
			l++;
			r--;
		}
		l = k;
		r = arr.length-1;
		while(l<=r){
			swap(arr, l, r);
			l++;
			r--;
		}
		return new String(arr);
	}
	
	public static void swap(char [] arr, int l, int r) {
		char tmp = arr[l];
		arr[l] = arr[r];
		arr[r] = tmp;
	}
	
}


/**
 * @author Angelo K. Huang
 * public interface ScheduledExecutorService extends ExecutorService
 * An ExecutorService that can schedule commands to run after a given delay, or to execute periodically.
 */
class MyScheduledExecutorService
{
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
 
    public void beepForAnHour()
    {
        final Runnable beeper = new Runnable()
        {
            public void run()
            {
                System.out.println("beep");
            }
        };
        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 1, 5, SECONDS);
 
         scheduler.schedule(
	         new Runnable() {
	        	 public void run() { beeperHandle.cancel(true); }
	         }
         , 60 * 60, SECONDS);
    }
 
    public static void main(String args[])
    {
        MyScheduledExecutorService mses = new MyScheduledExecutorService();
        mses.beepForAnHour();
    }
}