package Easy_Tree;

public class EasyNode {
  private int value;
  private EasyNode next;
  
  public EasyNode( int initValue){
    value = initValue;
    next = null;    
  }
  public EasyNode(int initValue, EasyNode initNext){
    value = initValue;
    next = initNext;    
  }

  public int getValue() { return value; }

  public EasyNode getNext() { return next; }

  public void setValue( int theNewValue){
    value = theNewValue;
  }

  public void setNext( EasyNode theNewNext){
	  next = theNewNext;
  }  

  //Possible assignment for students
  public int numChildren(){
    int children = 0;
    if(next != null)
      children = children + 1 + next.numChildren();
    return children;
  }

  public boolean isLeaf(){
    if(next == null)
      return true;
    return false;
  }



}