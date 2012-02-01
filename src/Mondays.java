import java.util.*;
/**
 * 
 * Mogees Test
 * usages:
 * javac Mondays.java
 * java Mondays 1901 2000
 * @author  Angelo Kaichen Huang
 */
public class Mondays {
   public static void main(String[] argv) {
      
	  int from = 0, to = 0, totalMondays = 0;  
	  try {
			from = Integer.parseInt(argv[0]);
			to = Integer.parseInt(argv[1]);
	  } catch (Exception exp) {
				System.out.println("Sample Usages: Java Months 1901 2000");	
				System.exit(1);
	  }
	  
      for (int i = from; i <= to; i++) {
		totalMondays += calcMondays(i);
	  }
	  System.out.println("Total number of Monday on the first of month from '" + from + "' to '" + to + "' is " + totalMondays);    
   }
   
   /**
          *  It is used to calculate how many Mondays on the first of month in the given year.
          *  @param year
          */
   private static int calcMondays (int year) {
      int dayOfWeek = 0, sumOfYear = 0;
	  Calendar c = Calendar.getInstance();     
      //JANUARY
	  c.set(year, Calendar.JANUARY, 1, 0, 0, 0);	  
      dayOfWeek = c.get(Calendar.DAY_OF_WEEK);// 2=Monday
	  if (dayOfWeek == 2) sumOfYear++;     
	  //FEBRUARY
	  c.set(year, Calendar.FEBRUARY, 1, 0, 0, 0);	 
      dayOfWeek = c.get(Calendar.DAY_OF_WEEK);// 2=Monday
	  if (dayOfWeek == 2) sumOfYear++;
	  //MARCH
	  c.set(year, Calendar.MARCH, 1, 0, 0, 0);	 
      dayOfWeek = c.get(Calendar.DAY_OF_WEEK);// 2=Monday
	  if (dayOfWeek == 2) sumOfYear++;
	  //APRIL
	  c.set(year, Calendar.APRIL, 1, 0, 0, 0);	 
      dayOfWeek = c.get(Calendar.DAY_OF_WEEK);// 2=Monday
	  if (dayOfWeek == 2) sumOfYear++;
	  //MAY
	  c.set(year, Calendar.MAY, 1, 0, 0, 0);	 
      dayOfWeek = c.get(Calendar.DAY_OF_WEEK);// 2=Monday
	  if (dayOfWeek == 2) sumOfYear++;
	  //JUNE
	  c.set(year, Calendar.JUNE, 1, 0, 0, 0);	 
      dayOfWeek = c.get(Calendar.DAY_OF_WEEK);// 2=Monday
	  if (dayOfWeek == 2) sumOfYear++;
	  //JULY
	  c.set(year, Calendar.JULY, 1, 0, 0, 0);	 
      dayOfWeek = c.get(Calendar.DAY_OF_WEEK);// 2=Monday
	  if (dayOfWeek == 2) sumOfYear++;
	  //AUGUST
	  c.set(year, Calendar.AUGUST, 1, 0, 0, 0);	 
      dayOfWeek = c.get(Calendar.DAY_OF_WEEK);// 2=Monday
	  if (dayOfWeek == 2) sumOfYear++;
	  //SEPTEMBER
	  c.set(year, Calendar.SEPTEMBER, 1, 0, 0, 0);	 
      dayOfWeek = c.get(Calendar.DAY_OF_WEEK);// 2=Monday
	  if (dayOfWeek == 2) sumOfYear++;
	  //OCTOBER
	  c.set(year, Calendar.OCTOBER, 1, 0, 0, 0);	 
      dayOfWeek = c.get(Calendar.DAY_OF_WEEK);// 2=Monday
	  if (dayOfWeek == 2) sumOfYear++;
	  //NOVEMBER
	  c.set(year, Calendar.NOVEMBER, 1, 0, 0, 0);	 
      dayOfWeek = c.get(Calendar.DAY_OF_WEEK);// 2=Monday
	  if (dayOfWeek == 2) sumOfYear++;
	  //DECEMBER
	  c.set(year, Calendar.DECEMBER, 1, 0, 0, 0);	 
      dayOfWeek = c.get(Calendar.DAY_OF_WEEK);// 2=Monday
	  if (dayOfWeek == 2) sumOfYear++;
	  
	  return sumOfYear;  
   }
}