
import java.io.*;
import java.util.*;

public class JavaPractice2 {
	static int dividend = 7, divisor = 3, remainder = 0;
	
	public static void main (String [] args){
//		char [] chars = {'C', 'M', 'I', 'S', 'D', 'P', 'E', 'G'};
//		char [] out = new char [8];
//		boolean [] used = new boolean[8];
//		for (int k = 0; k < out.length; k++){
//			out[k] = '\u0000';
//			used[k] = false;
//		}
//
//		permuteString(chars, out, used, 0);
		
		System.out.println(Arrays.toString(divWithoutDivision(7, 3)));
		System.out.println(Arrays.toString(divWithoutDivision(-7, 3)));
		System.out.println(Arrays.toString(divWithoutDivision(7, -3)));
		System.out.println(Arrays.toString(divWithoutDivision(-7, -3)));
		System.out.println(Arrays.toString(divWithoutDivision(6, 3)));
		System.out.println(Arrays.toString(divWithoutDivision(-6, 3)));
		System.out.println(Arrays.toString(divWithoutDivision(6, -3)));
		System.out.println(Arrays.toString(divWithoutDivision(-6, -3)));
		System.out.println(Arrays.toString(divWithoutDivision(1, 3)));
		System.out.println(Arrays.toString(divWithoutDivision(-1, 3)));
		System.out.println(Arrays.toString(divWithoutDivision(1, -3)));
		System.out.println(Arrays.toString(divWithoutDivision(-1, -3)));
		System.out.println(Arrays.toString(divWithoutDivision(0, 3)));
		
		System.out.printf("\n%d / %d: quotient = %d", dividend, divisor, division(dividend, divisor));
		System.out.printf("\n%d / %d: remainder = %d", dividend, divisor, remainder);
		
		System.out.printf("\n%d / %d: quotient = %d", dividend, divisor, div(dividend, divisor));
		
				
		char [] charArr = {'a','b','e','h','j','a','e','c','d','d'};
		System.out.println("Before Change");
		System.out.println(Arrays.toString(charArr));
		removeDuplicate2(charArr);
		System.out.println("After Change");
		System.out.println(Arrays.toString(charArr));
		
		System.out.println("Compare Number Without Comparison operator");
		System.out.println(max(1, 8));
		
		int [] a = {2,4,6,8,10,0,0,0,0,0};
		int [] b = {1,3,5,7,9};
		a = sortedMerge(a, b);
		System.out.println("Merge Two Array");
		System.out.println(Arrays.toString(a));
		
		System.out.println("swap result: " + Integer.toBinaryString(swapOddEvenBits(Integer.parseInt("00001010", 2))));
		System.out.println("need instructions: " + countSwapOddEvenBits(Integer.parseInt("00001010", 2)));		
	
	}

	public static void permuteString(char [] chars, char [] out, boolean [] used, int curLevel) {
		if (curLevel == chars.length) {
			//System.out.println(out);
			BufferedWriter bof = null;
			try {
				bof = new BufferedWriter(new FileWriter("c:\\anagram.txt", true));
				bof.append(Arrays.toString(out));
				bof.newLine();
			} catch (FileNotFoundException fnfe) {
				fnfe.printStackTrace();
			} catch(IOException ioe) {
				ioe.printStackTrace();
			} finally{
				if (bof != null) {
					try {
						bof.flush();
						bof.close();
					} catch(IOException ioe) {
						ioe.printStackTrace(); 
					}
				}
			}
		}

		for (int i = 0; i < chars.length; i++) {
			if (used[i] == false) {
				out[curLevel] = chars[i];
				used[i] = true;
				permuteString(chars, out, used, curLevel+1);
				used[i] = false;
			}
		}
	}
	
	public static List<String> formatString(String str, int maxLength, int maxLines){
		if (str == null || str.equals("") || maxLength < 1 || maxLines < 1)
			return null;
		List<String> list = new ArrayList<String>();
		int curPos = 0;
		int totalLines = 0;
		int strLength = str.length();
		while(totalLines < maxLines && curPos < strLength){
			if ((strLength-curPos) < maxLength) {
				String tmp = str.substring(curPos, strLength);
				list.add(tmp);
				break;
			} else {
				String tmp = str.substring(curPos, curPos+maxLength);
				list.add(tmp);
				curPos = curPos+maxLength;
			}
		}
		return list;
	    
	}
	
	/**
	 * @param dividend
	 * @param divisor
	 * @return quotient and remainder
	 * this function can handle positive and negative numbers
	 * 
	 */
	public static int [] divWithoutDivision(int dividend, int divisor) {
		int [] result = new int[2];
		boolean oneNeg = false;
		boolean twoNeg = false;		
		int dd = dividend;
		int dr = divisor;
		if (dd == dr) {
			result[0] = 1;
			result[1] = 0;
			return result;
		}		
		if (dd == 0) {
			result[0] = 0;
			result[1] = 0;
			return result;
		}
		if (dd < 0 || dr < 0) {
			oneNeg = true;
			if (dd < 0 && dr < 0) { 
				oneNeg = false;
				twoNeg = true;
			}
			dd = Math.abs(dd);
			dr = Math.abs(dr);			
		}
		if (!oneNeg && dd < dr) {
			result[0] = 0;
			result[1] = dd;
			if (twoNeg)
				result[1]*=-1;
			return result;
		}		
		if (oneNeg) {
			result[0] = 1;
			int tmpDr = dr;
			while(tmpDr < dd) {
				tmpDr += dr;
				result[0]++;
			}
			result[1] = dd - tmpDr;
			result[0]*=-1;
			if (dividend < 0)
				result[1]*=-1;
			return result;      
		
		} else {
			result[0] = 1;
			int tmpDr = dr;
			while(tmpDr <= dd) {
				tmpDr += dr;
				result[0]++;
			}
			result[0]--;
			result[1] = dd - tmpDr + dr;
			if (twoNeg)
				result[1]*=-1;		
			return result;
		}
	}
	
	/**
	 * only support positive integers
	 * @param tempdividend
	 * @param tempdivisor
	 * @return quotient
	 */
	public static int division(int tempdividend, int tempdivisor) {

		int quotient = 1;
		if (tempdivisor == tempdividend) {
			remainder = 0;
			return 1;
		} else if (tempdividend < tempdivisor) {
			remainder = tempdividend;
			return 0;
		}   

		while (tempdivisor <= tempdividend) {
			/* Here divisor <>
		           divisor and quotient */
			tempdivisor = tempdivisor << 1;
			quotient = quotient << 1;      
		}

		/* We have reached the point where divisor > dividend, 
		     therefore divide divisor and quotient by two */
		tempdivisor = tempdivisor >> 1;
		quotient = quotient >> 1;

		/* Call division recursively for the difference to get the
	     exact quotient */
		quotient = quotient + division(tempdividend - tempdivisor, divisor);

		return quotient;
	}
	
	/**
	 * @param dividiend
	 * @param divisor
	 * @return quotient and remainder
	 * @throws Exception
	 * only support positive numbers
	 */
	public static int [] division2(int dividiend, int divisor) throws Exception {
		if (divisor <= 0 || dividiend < 0)
			throw new Exception();
		if (dividend < divisor) {
			return new int [] {0, dividend};
		}
		int quo = 0;
		int re = 0;
		int tmpDivisor = divisor;
		while(tmpDivisor<=dividiend) {
			quo++;
			tmpDivisor+=divisor;
		}
		re = dividiend - tmpDivisor + divisor;
		return new int [] {quo, re};
	}
	
	public static int div(int dividend, int divisor) {		
	    if (divisor == 0) {	        
	        return -1;
	    }
	    double invertedNum = Math.pow(divisor, -1.0);
	    return (int)(dividend*invertedNum);
	}
	
	
	/**
	 * Design an algorithm and write code to remove the duplicate characters in a string without using
	 * any additional buffer.
	 * @param chars
	 * @return
	 */
	public static char[] removeDuplicate(char [] chars) {
		Arrays.sort(chars);
		int p = 0;
		
		for (int i = 0; i < chars.length; i++) {
			chars[p] = chars[i];			
			while(i < chars.length && chars[p] == chars[i]) {
				i++;
			}
			i--;
			p++;
		}
		while(p < chars.length){
			chars[p++] = '\0';
		}
		return chars;
	}
	
	public static char[] removeDuplicate2(char [] chars) {
		int a1 = 0;
		int a2 = 0;
		int a3 = 0;
		for (a1=0; a1 < chars.length; a1++) {
			for (a2=a1+1, a3=a2; a3 < chars.length;){
				if (chars[a1] == chars[a2]) {
					if (a3 <= chars.length)
						a3++;
				} else {
					a2++;
					a3++;
					if (a2==a3)
						continue;
				}
				if (a3 == chars.length) {
					chars[a2] = '\u0000';
				} else {
					chars[a2] = chars[a3];
					chars[a3] = '\u0000';
				}
				
			}
		}
		return chars;
	}	
		
	/**
	 * Write a function int BitSwapReqd(int A, int B) to determine the
	 * number of bits required to convert integer A to integer B.
	 * @param a
	 * @param b
	 * @return int
	 */
	public static int bitSwapReqd(int a, int b) {
		int count = 0;
		int c = a ^ b;
		while(c != 0) {
			c = c & (c-1);
			count++;
		}
		return count;
	}
	
	/**
	 * If you were to write a program to swap odd and even bits in integer,
	 * what is the minimum number of instructions required? (eg,
	 * bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, etc)
	 * EXAMPLE:
	 * Input: 10001010
	 * Output: 01000101
	 * @param iint
	 * @return result
	 * 
	 * *** AAAAAAAA will be over java int limit 2^31 - 1
	 */
	public static int swapOddEvenBits(int iint) {
		int OxAA = Integer.parseInt("2AAAAAAA", 16);
		int Ox55 = Integer.parseInt("55555555", 16);
		int result = (((iint & OxAA) >> 1) | ((iint & Ox55) << 1));
		return result;
	}
	
	/**
	 * @param iint
	 * @return number of instructions
	 * this will return number of instructions required
	 */
	public static int countSwapOddEvenBits(int iint) {
		int OxAA = Integer.parseInt("2AAAAAAA", 16);
		int Ox55 = Integer.parseInt("55555555", 16);
		int result = (iint ^ ((iint & OxAA) >> 1) ^ ((iint & Ox55) << 1));
		int count = 0;
		while(result > 0) {
			if ((result & 1) == 1) {
				count++;
			}
			result = result >> 1;
		}
		
		return count;
	}
	
	/**
	 * Write a method which finds the maximum of two numbers. You should not use if-else or any
	 * other comparison operator.
	 * Example
	 * Input: 5, 10
	 * Output: 10
	 * @param a
	 * @param b
	 * @return
	 */
	public static int max(int a, int b) {
		//int Ox80000000 = Integer.parseInt("80000000", 16);
		//int c = (a-b) & Ox80000000;
		int c = (a-b);
		c = c >>> 31;
		return (1-c)*a + c*b;
	}
	/**
	 * a and b are both sorted arrays of integers. Each contains n sorted integers.
	 * a has length 2*n, with the last n slots empty, and b has just n elements.
	 * This method merges the elements from b into a, maintaining sorted order.
	 * @param a
	 * @param b
	 * @return int []
	 */
	public static int [] sortedMerge(int [] a, int [] b) {
		if (a.length != (b.length*2))
			return null;
		int aIndex = a.length/2 - 1;
		int bIndex = b.length - 1;
		int cIndex = a.length - 1;
		while(aIndex >= 0 && bIndex >= 0) {
			if (a[aIndex] <= b[bIndex]) {
				a[cIndex] = b[bIndex];
				bIndex--;				
			} else {
				a[cIndex] = a[aIndex];
				aIndex--;				
			}
			cIndex--;
		}
		if(aIndex <
				0) {
			while(bIndex >= 0) {
				a[cIndex] = b[bIndex];
				bIndex--;
				cIndex--;
			}
		} else {
			while(aIndex >= 0) {
				a[cIndex] = a[aIndex];
				aIndex--;
				cIndex--;
			}
		}
		return a;
		
	}	
	
}  