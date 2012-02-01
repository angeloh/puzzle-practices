
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
public class DivideSubset {
	public DivideSubset() {
		
	}
	
	public static List partition(Object[] objects, int n) {
		int length = objects.length;
		List finalList = new ArrayList();
		int numOfOnePart = length/n;
		int remainder =length%numOfOnePart;
		int numOfLessOne = 0;
		int i;
		if (remainder > 0) {
			numOfOnePart++;
		    remainder = length%numOfOnePart;
		    numOfLessOne = (numOfOnePart*n)-length;
		}
		if (numOfLessOne > 0) {
			
			int numOfNormal = n - numOfLessOne;
			for (i = 0; i < numOfNormal*numOfOnePart; i+=numOfOnePart) {
				List tmp = new ArrayList();
				for (int j = 0; j< numOfOnePart; j++){					
					tmp.add(objects[i+j]);
				}				
				finalList.add(tmp);
			}
			int lessOne = numOfOnePart - 1;
			for (int w = i; w < length; w+=lessOne) {
				List tmp = new ArrayList();
				for (int j = 0; j< lessOne; j++){					
					tmp.add(objects[w+j]);
				}				
				finalList.add(tmp);
			}
		
		}else {
			for (i = 0; i < n; i+=numOfOnePart) {
				List tmp = new ArrayList();
				for (int j = 0; j< numOfOnePart; j++){					
					tmp.add(objects[i+j]);
				}				
				finalList.add(tmp);
			}		
		}
		
		return finalList;		
	}
	
	public static void main(String[] args) {
		Integer[] numbers = {3,4,5,6,7,8,12,45,67,89,56};
		List result = DivideSubset.partition(numbers, 2);
		
		List first = (List)result.get(0);
		Iterator it = first.iterator();
		
		while ( it.hasNext() ) {
			Integer num = (Integer)it.next();
			System.out.println(num);
		}
		
	}
	
}