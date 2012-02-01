/**
* Mogees Test
* 
* usage:
* javac Prime.java
* java Prime 2000000
* @author Angelo Kaichen Huang
* 
*/
public class PrimeSum {
	public static void main(String argv[]) {
		int maxNum = 0, sum = 0;		
		try {
			maxNum = Integer.parseInt(argv[0]);
		} catch (Exception exp) {
			System.out.println("Please give an integer number...");	
			System.exit(1);
		}
		for (int i = 2; i <= maxNum; i++) {
			if (isPrime(i))
				sum += i;
		}
		System.out.println("The sum of primes below '" + maxNum + "' is '" + sum + "'");	    
	}
	
	/**
	 * This function is used to test if a given number is prime.
	 * @param num
           */
	private static boolean isPrime (int num) {
		boolean primeBool = true;
		//whether a number is prime is to divide by all primes less than or equal to the square root of that number
		int sr = (int)Math.round(Math.sqrt(num));		
		for (int j = 2; j <= sr; j ++) {
			if(num%j==0)
			{
				primeBool = false;           
				break;
			}  
		}
		return primeBool;
	}
	 
}

//References:
//http://en.wikipedia.org/wiki/Prime_number