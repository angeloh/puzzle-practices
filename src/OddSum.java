/**
* usage:
* javac OddSum.java
* java OddSum 2 100
* @author Angelo Kaichen Huang
*/
public class OddSum {
	public static void main(String argv[]) {
		int first = 0, sec = 0, sum = 0;		
		try {
			first = Integer.parseInt(argv[0]);
			sec = Integer.parseInt(argv[1]);
		} catch (Exception exp) {
			System.out.println("Please give an integer number...");	
			System.exit(1);
		}
		for (int i = first; i <= sec ; i++) {
			if (i % 2 == 1)
				sum += i;
		}
		System.out.println("The sum of odds is '" + sum + "'");	    
	} 
}

//References:
//http://en.wikipedia.org/wiki/Prime_number