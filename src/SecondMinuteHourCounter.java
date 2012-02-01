import java.util.*;


/**
 * This class contains three arrays for three different intervals, second, minute and hour.
 * When user invoke count(), it will increment the value in the array for current
 * second, minute, and hour. Three public methods are available for retrieving the
 * counter values for current second, minute, and hour.
 * 
 * @author Angelo Huang
 * 
 * usage:
 * javac SecondMinuteHourCounter.java
 * java SecondMinuteHourCounter
 *
 */
public final class SecondMinuteHourCounter{	

	private int [] countInSecond =  new int [61];
	private int [] countInMinute = new int [61];
	private int [] countInHour = new int [25];
	private static int curSecond = -1;
	private static int curMinute = -1;
	private static int curHour = -1;	
	
	void count(){
		Calendar calendar = new GregorianCalendar();	    
	    int hour = calendar.get(Calendar.HOUR_OF_DAY);
	    int minute = calendar.get(Calendar.MINUTE);
	    int second = calendar.get(Calendar.SECOND);	    
	    
	    if (curSecond == second && curMinute == minute && curHour == hour) {
	    	countInSecond[curSecond]++;
	    } else {
	    	resetArray(countInSecond);
	    	curSecond = second;	    	
	    	countInSecond[curSecond] = 1;
	    }
	    
	    if (curMinute == minute && curHour == hour) {
	    	countInMinute[curMinute]++;
	    } else {
	    	resetArray(countInMinute);
	    	curMinute = minute;	    	
	    	countInMinute[curMinute] = 1;
	    }
	    
	    if (curHour == hour) {
	    	countInHour[curHour]++;
	    } else {
	    	resetArray(countInHour);
	    	curHour = hour;	    	
	    	countInHour[curHour] = 1;
	    }
	    
	}
	
	void resetArray(int [] arr) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = 0;
		}
	}
	
	int getSecond() {
		Calendar calendar = new GregorianCalendar();   
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
	    int minute = calendar.get(Calendar.MINUTE);
	    int second = calendar.get(Calendar.SECOND);
	    if (curSecond == second && curMinute == minute && curHour == hour)
	    	return countInSecond[second];
	    else
	    	return 0;
	}
	
	int getMinute() {
		Calendar calendar = new GregorianCalendar();    
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
	    int minute = calendar.get(Calendar.MINUTE);
	    if (curMinute == minute && curHour == hour)
	    	return countInMinute[minute];
	    else
	    	return 0;
	}
	
	int getHour() {
		Calendar calendar = new GregorianCalendar();	    
	    int hour = calendar.get(Calendar.HOUR_OF_DAY);
	    if (curHour == hour)
	    	return countInHour[hour];
	    else
	    	return 0;
	}

	public static void  main(String arg[]) {
		
		SecondMinuteHourCounter counter = new SecondMinuteHourCounter();
		counter.count();
		System.out.println("counter in a second : " + counter.getSecond());
		System.out.println("counter in a minute : " + counter.getMinute());
		System.out.println("counter in a hour : " + counter.getHour());		
		try{		  
		  Thread.sleep(1000);//sleep for 1000 ms		 
		} catch(InterruptedException ie){			
		}
		//counter.count();
		System.out.println("counter in a second : " + counter.getSecond());
		System.out.println("counter in a minute : " + counter.getMinute());
		System.out.println("counter in a hour : " + counter.getHour());
//		try{
//			Thread.sleep(60000);//sleep for 60000 ms
//		} catch(InterruptedException ie){			
//		}
//		counter.count();
//		System.out.println("counter in a second : " + counter.getSecond());
//		System.out.println("counter in a minute : " + counter.getMinute());
//		System.out.println("counter in a hour : " + counter.getHour());
		
	}
}