package BFS_new;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2003
 * Company:
 * @author
 * @version 1.0
 */

public class TreeNode {
  private Object value;
  private TreeNode left;
  private TreeNode right;

  public TreeNode( Object initValue){
    value = initValue;
    left = null;
    right = null;
  }
    public TreeNode( Object initValue, TreeNode initLeft,
                                       TreeNode initRight){
    value = initValue;
    left = initLeft;
    right = initRight;
  }

  public Object getValue() { return value; }

  public TreeNode getLeft() { return left; }

  public TreeNode getRight() { return right; }

  public void setValue( Object theNewValue){
    value = theNewValue;
  }

  public void setLeft( TreeNode theNewLeft){
    left = theNewLeft;
  }

  public void setRight(TreeNode theNewRight){
    right = theNewRight;
  }

  //Possible assignment for students
  public int numChildren(){
    int children = 0;
    if(left != null)
      children = 1 + left.numChildren();
    if(right != null)
      children = children + 1 + right.numChildren();
    return children;
  }

  public boolean isLeaf(){
    if(left == null && right == null)
      return true;
    return false;
  }



}