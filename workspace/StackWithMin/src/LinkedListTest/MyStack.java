package LinkedListTest;

import LinkedListTest.LinkedList.LinkableInteger;

public class MyStack implements Stack{
	
  private int maxSize;
  private int[] stackArray;
  private int top;
  private LinkedList historyOfMin;
  private int minValue;

  public MyStack(int s) {
    maxSize = s;
    stackArray = new int[maxSize];
    top = -1;
    historyOfMin = new LinkedList(); // Create a list

    minValue = 0;
  }

  public void push(int j) {
    stackArray[++top] = j;
    isMin(j);   
  }

  public int pop() {
	
	if (historyOfMin.getHead().equals(new LinkableInteger(stackArray[top]))){
	    	historyOfMin.removeFromHead();
	    	setMin(Integer.parseInt(historyOfMin.getHead().toString()));
	}	  
    return stackArray[top--];
  }

  public int top() {
    return stackArray[top];
  }
  
  public int size() {
	  return top;
  }
  
  public void setMin(int value){
		minValue = value;
  }

  public int min(){
		return minValue;
  }
	
  public void isMin(int value){
		if (value <= this.minValue) {
			minValue = value;
			historyOfMin.insertAtHead(new LinkableInteger(minValue));			
		}
  }

  public boolean isEmpty() {
    return (top == -1);
  }

  public boolean isFull() {
    return (top == maxSize - 1);
  }  
}