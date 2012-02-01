import java.util.*;

public class ThreadTest implements Runnable {
	
	
	public ThreadTest() {}
	
	public void run() {
		
	}
	
	public static void main (String [] argv) {
		new Thread(new ThreadTest()).start();	
	}
}