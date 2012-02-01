import java.util.*;

/**
 * 
 * Mogees Test
* usage:
* javac Triangle_Divisors.java
* java Triangle_Divisors 500
* @author Angelo Kaichen Huang
*/
public class Triangle_Divisors {

	public static void main(String argv[]) {
		
		int n = 2;//start from 2		
		int max = 0;
		int target = 0;
		try {
			target = Integer.parseInt(argv[0]);
		} catch (Exception exp) {
			System.out.println("Please give an integer number...");	
			System.exit(1);
		}		
        
        while(true) {
            
			if (isPrime(n*(n+1)/2)) {
				if (max < 2)  {
					max = 2;
					System.out.println("New max total divisors is '" + max + "' from the '" + n + "th' triangle number(" + (n*(n+1)/2) + ")");
				}
			} else {
				//To save time, we divide the accumulation formula into two parts to send smaller numbers to calcTotalDivisors() .
				int div1  = (n%2==1) ? calcTotalDivisors(n) : calcTotalDivisors(n/2);
	            int div2  = (n%2==1) ? calcTotalDivisors((n+1)/2) : calcTotalDivisors(n+1);
	            if (div1 * div2 > max) {
	                max = div1 * div2;
	                System.out.println("New max total divisors is '" + max + "' from the '" + n + "th' triangle number(" + (n*(n+1)/2) + ")");
	            }
			}
            if (max >= target) {
				System.out.println("The first triangle number to have over '" + target + "' divisors:");
				System.out.println("It is '" + max + "' from the '" + n + "th' triangle number(" + (n*(n+1)/2) + ")");
				break;
			}			
            n++;
        }		
	}
	/**
	* In order to obtain total divisors for a number,
	*we have to obtain the prime factor decomposition of the number and then to combine those factors in all possible forms.
           *For example:
           *756 = (3^3) * (2^2) * 7
	*Any factor you may have will be a combination (product) of these factors. You have available 3 "3"s, 2 "2"s and 1 "7", so 
	*	# of ways factor "3" may appear = 3 + 1 = 4 (3^0, 3^1, 3^2, 3^3)
	*	# of ways factor "2" may appear = 2 + 1 = 3 (2^0, 2^1, 2^2)
	*	# of ways factor "7" may appear = 1 + 1 = 2 (7^0, 7^1)
	*	thus: # of ways these factors may be combined = 4*3*2 = 24 
	*	=> there are 24 factors
	*/
	private static int calcTotalDivisors(int num) {
		int total = 1;
		if (isPrime(num)) {
			total = 2;
		} else {
			Map<Integer, Integer> factorResult = factorize(num);
			Iterator iterator = factorResult.values().iterator();
			while( iterator.hasNext() ){
				Integer iint = (Integer)iterator.next();
				total = total * (iint.intValue() + 1);
			}
		}
		return total;
	}
	
	/**
	 * This function is used to find all prime factors.
	 * @param num
           */
	private static Map<Integer, Integer> factorize(int num)
	{
        Map<Integer, Integer> primeFactors = new HashMap<Integer, Integer>();
        
        int div = 2; //initialize to 2
        while (num > 1)  //While num is more than one
        {
            int newSum = 0;
			if (isPrime(div) && (num % div == 0)) //If 'div' is a prime number AND 'num' modulus 'div' equals zero
            {
				if (primeFactors.get(new Integer(div)) != null) {
					Integer sumInt = (Integer)primeFactors.get(new Integer(div));
					newSum = sumInt.intValue() + 1;
				} else {
					newSum++;
				}
				
				primeFactors.put(new Integer(div), new Integer(newSum));
                num = num/div; //quotient
            } 
            else                
                div++; //increase 'div' by one 
               
        } // end while        
        return primeFactors;
    } // end method factorize
	
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
//http://en.wikipedia.org/wiki/Triangular_number
//http://www.urch.com/forums/gre-math/15597-find-all-positive-divisors-number.html