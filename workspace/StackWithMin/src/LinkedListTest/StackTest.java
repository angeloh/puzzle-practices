package LinkedListTest;

import LinkedListTest.MyStack;


public class StackTest {
	 /**
	   * This class is used to execute the stack push and pop test with
	   * another linked list to record the history of min value.
	   */
	public StackTest() {
		
	}
	
	public static void main(String[] args) {
		
		MyStack theStack = new MyStack(100); // make new stack
		int popup;
		System.out.println("*The first time of stack push*");
		theStack.setMin(20);	    
		theStack.push(20);		
	    theStack.push(40);	    
	    theStack.push(60);	    
	    theStack.push(80);	   
	    theStack.push(100);	    
	    theStack.push(120);	    
	    theStack.push(140);	    
	    theStack.push(160);	   
	    theStack.push(180);	    
	    theStack.push(200);
	    
	    System.out.println("Current Stack Min Value:");
	    System.out.println(theStack.min());
	    
	    System.out.println("Pop Up a element from Stack:");
	    popup = theStack.pop();
	    System.out.println(popup);
	    
	    System.out.println("Current Stack Min Value:");
	    System.out.println(theStack.min());
	    
	    System.out.println();
	    System.out.println("*The 2nd time of stack push*");	    
	    theStack.push(10);	    
	    theStack.push(20);	    
	    theStack.push(30);	   
	    theStack.push(5);   
	     
	    System.out.println("Current Stack Min Value:");
	    System.out.println(theStack.min());
	    
	    System.out.println("Pop Up a element from Stack:");
	    popup = theStack.pop();
	    System.out.println(popup);	    
	    
	    System.out.println("Current Stack Min Value:");
	    System.out.println(theStack.min());
	    
	  }
}