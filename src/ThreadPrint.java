
public class ThreadPrint{
	
	public static void main(String [] argv) {
		final Object lock = new Object();
		Thread t1 = new Thread() {
			public void run() {
				System.out.print("a");
				synchronized(lock) { 
					try {
						lock.wait();
					} catch (InterruptedException ie) {

					}
				}
				System.out.print("b");
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				System.out.print("c");
				synchronized(lock) { lock.notify(); }
			}
		};
		t1.start();
		t2.start();
	}
}
