package BFS_new;
import java.util.NoSuchElementException;
/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2003
 * Company:
 * @author
 * @version 1.0
 */

public class BinarySearchTree extends BinaryTree implements BinarySearchTreeADT{

  public BinarySearchTree() {
     super();
  }

  public BinarySearchTree(Comparable element){
    super(element);
  }

  /*  Adds the specified object to the binary search tree in the
   *  appropriate position according to its key value.  Note that equal elements are added to the right.
   */
  public void addElement(Comparable element){
    TreeNode temp = new TreeNode(element);
    if(isEmpty())
      root = temp;
    else{
      TreeNode current = root;
      boolean added = false;
      while(!added){
        if(element.compareTo( current.getValue() ) <0 ){
          if(current.getLeft() == null){
            current.setLeft(temp);
            added = true;
          }
          else
            current = current.getLeft();
        }
        else{
          if(current.getRight() == null){
            current.setRight(temp);
            added = true;
          }
          else
            current = current.getRight();
        }
      }
    }
    count++;
  }

/* Removes the first element that matches the specified target
 * element from the binary search tree and returns a reference to
 * it.  Throws a NoSuchElementException if the specified target
 * element is not found in the binary search tree.
 */
  public Comparable removeElement(Comparable targetElement){
    Comparable result = null;
    if(!isEmpty()){
      if( targetElement.equals( root.getValue() ) ){
        result = (Comparable)root.getValue();
        root = replacement(root);
        count--;
      }
      else{
        TreeNode current;
        TreeNode parent = root;
        boolean found = false;

        if( targetElement.compareTo(root.getValue() )<0)
          current = root.getLeft();
        else
          current = root.getRight();

        while( current != null && !found){

          if( targetElement.equals(current.getValue() ) ){
            found = true;
            count--;
            result = (Comparable)current.getValue();

            if(current == parent.getLeft() )
              parent.setLeft(replacement(current));
            else
              parent.setRight(replacement(current));
          }
          else{
            parent = current;

            if( targetElement.compareTo(current.getValue() ) < 0 )
              current = current.getLeft();
            else
              current = current.getRight();
          }
        }
        if( !found)
          throw new NoSuchElementException();
      }
    }

     return result;
  }

  /*  Returns a reference to a node that will replace the one
   *  specified for removal.  In the case where the removed
   *  node has two children, the inorder predecessor is used
   *  as its replacement
   */
  private TreeNode replacement( TreeNode node){
    TreeNode result = null;

    if( (node.getLeft() == null) && (node.getRight() == null) )
      result = null;
    else
      if( (node.getLeft() != null) && node.getRight() == null )
        result = node.getLeft();
      else
      if( (node.getLeft() == null) && (node.getRight() != null ))
        result = node.getRight();
      else{
        TreeNode current = node.getLeft();
        TreeNode parent = node;

        while ( current.getRight() != null){
          parent = current;
          current = current.getRight();
        }

        if(parent != node){
          parent.setRight(current.getLeft());
        }
        else
           parent.setLeft( current.getLeft() );
        result = current;
        result.setLeft(node.getLeft() );
        result.setRight(node.getRight() );
      }

      return result;
    }

    public void removeAllOccurrences( Comparable targetElement){
    }

    /*  Removes the node with the least value from the binary search
     *  tree and returns a reference to its element.  Throws a
     *  NoSuchElementException if the binary search tree is empty
     */
    public Comparable removeMin(){
      Comparable result = null;
      if(isEmpty())
        throw new NoSuchElementException();
      else{
        if(root.getLeft() == null){
          result = (Comparable) root.getValue();
          root = root.getRight();
        }
        else{
          TreeNode parent = root;
          TreeNode current = root.getLeft();
          while(current.getLeft() != null){
            parent = current;
            current = current.getLeft();
          }
          result = (Comparable) current.getValue();
          parent.setLeft( current.getRight() );
        }
        count--;
      }
      return result;
    }

    /*  Removes the node with the greatest value from the binary search
     *  tree and returns a reference to its element.  Throws a
     *  NoSuchElementException if the binary search tree is empty
     */
    public Comparable removeMax(){
      Comparable result = null;
      if(isEmpty())
        throw new NoSuchElementException();
      else{
        if(root.getRight() == null){
          result = (Comparable) root.getValue();
          root = root.getLeft();
        }
        else{
          TreeNode parent = root;
          TreeNode current = root.getRight();
          while(current.getRight() != null){
            parent = current;
            current = current.getRight();
          }
          result = (Comparable) current.getValue();
          parent.setRight( current.getLeft() );
        }
        count--;
      }
      return result;
    }

    public Comparable findMin(){
      Comparable result = null;
      if(isEmpty())
        throw new NoSuchElementException();
      else{
        if(root.getLeft() == null){
          result = (Comparable) root.getValue();
        }
        else{
          TreeNode current = root.getLeft();
          while(current.getLeft() != null){
            current = current.getLeft();
          }
          result = (Comparable) current.getValue();
        }
      }
      return result;
    }

    public Comparable findMax(){
      Comparable result = null;
      if(isEmpty())
        throw new NoSuchElementException();
      else{
        if(root.getRight() == null){
          result = (Comparable) root.getValue();
        }
        else{
          TreeNode current = root.getRight();
          while(current.getRight() != null){
            current = current.getRight();
          }
          result = (Comparable) current.getValue();
        }
      }
      return result;
    }

    /*  This method overrides the one in BinaryTree.  This method is more
     *  efficient by making use of the ordering property of a binary
     *  search tree.
     */
    public Object find( Object targetElement){

      if(isEmpty())
        throw new NoSuchElementException();
      else{
        TreeNode current = root;
        Comparable testElement = (Comparable)targetElement;
        while(current != null && !current.getValue().equals(testElement) ){
          if( testElement.compareTo(current.getValue()) < 0)
            current = current.getLeft();
          else
            current = current.getRight();
        }
        if(current == null)
          return null;
        else
          return current.getValue();
      }
  }

}