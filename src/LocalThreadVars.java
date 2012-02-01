public class LocalThreadVars implements Runnable {
  static private class MyThreadLocal extends ThreadLocal<Double> {
    protected Double initialValue() {
      return new Double (Math.random() * 1000.0);
    }
  }
  static ThreadLocal<Double> threadLocal = new MyThreadLocal();
  static int counter = 0;
  private LocalThreadVars() {
    counter++;
  }
  public void run() {
    LocalThreadVars myLTV = new LocalThreadVars();
    displayValues();
    try {
      Thread.sleep (
        LocalThreadVars.threadLocal.get().longValue());
      myLTV.displayValues();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  private void displayValues() {
    System.out.println (threadLocal.get() + "\t" + counter + 
      "\t" + Thread.currentThread().getName());
  }
  public static void main (String args[]) {
    LocalThreadVars ltv = new LocalThreadVars();
    ltv.displayValues();
    for (int i=0;i<5;i++) {
      Thread t = new Thread (ltv);
      t.start();
    }
  }
}