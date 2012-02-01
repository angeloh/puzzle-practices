package BFS_new;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2003
 * Company:
 * @author
 * @version 1.0
 */

public class BinaryTree implements BinaryTreeADT {
   protected TreeNode root;
   protected int count;

  public BinaryTree() {
    root = null;
    count = 0;
  }

  public BinaryTree(Object element){
    count = 1;
    root = new TreeNode(element);
  }

    public BinaryTree(Object element, BinaryTree leftSubtree,
                                      BinaryTree rightSubtree){
    count = 1;
    root = new TreeNode(element);
    if(leftSubtree != null){
      count = count + leftSubtree.size();
      root.setLeft(leftSubtree.root);
    }
    else
      root.setLeft(null);

    if(rightSubtree != null){
      count = count + rightSubtree.size();
      root.setRight(rightSubtree.root);
    }
    else
      root.setRight(null);

  }

    public void removeLeftSubtree(){
    /*  possible assignment to add numChildren to TreeNode*/
      if(root.getLeft()!= null)
        count = count - root.getLeft().numChildren() - 1;

      root.setLeft(null);
    }
    public void removeRightSubtree(){
    /*  possible assignment to add numChildren to TreeNode */
      if(root.getRight() != null)
        count = count - root.getRight().numChildren() - 1;

      root.setRight(null);
    }

    public void removeAllElements(){
      root = null;
      count = 0;
    }
    public boolean isEmpty(){
      return root == null;
    }
    public int size(){
      return count;
    }

    // if count not used
    //===========================================
    public int size2(){
      return recursiveSize(root);
    }

    private int recursiveSize(TreeNode root){
      if(root == null)
        return 0;
      else
      if(root.isLeaf() )
        return 1;
      else
        return 1 + recursiveSize( root.getLeft()) +
                   recursiveSize( root.getRight());
    }
    //===============================================
    public int height(){
      return height(root);
    }

    private int height(TreeNode t){
      if(t==null)
        return -1;
      else
        return 1 + Math.max( height(t.getLeft()), height(t.getRight()) );
    }
    //=====================================================

    public boolean contains ( Object targetElement){
      if(find(targetElement) == null)
        return false;
      return true;
    }

    public Object find( Object targetElement){
      TreeNode current = root;
      TreeNode temp = current;

      if( !(current.getValue().equals(targetElement)) &&
          (current.getLeft() != null)  )
          current = findAgain(targetElement, current.getLeft());

      if( !(current.getValue().equals(targetElement)))
        current = temp;

      if( !(current.getValue().equals(targetElement)) &&
          (current.getRight() != null)  )
          current = findAgain(targetElement, current.getRight());

      if( !(current.getValue().equals(targetElement)))
        return null;

      return current.getValue();
    }

    // private helper to find
    private TreeNode findAgain(Object targetElement, TreeNode next){

      TreeNode current = next;
      if( !(next.getValue().equals(targetElement)) && ( next.getLeft() != null))
        next = findAgain(targetElement, next.getLeft() );

      if ( !(next.getValue().equals(targetElement)) )
        next = current;

      if (!(next.getValue().equals(targetElement)) && (next.getRight() != null))
        next = findAgain(targetElement, next.getRight());

      return next;
  }

    public String toString(){
     String s = "";
     Iterator itr = iteratorInOrder();  // LinkedList iterator
     while( itr.hasNext() ){
      s += itr.next() + "\n";
     }
      return s;
    }

    //========================================================
    public Iterator iteratorInOrder(){
      LinkedList list = new LinkedList();
      inorder(root,list);
      return list.iterator();
    }

    private void inorder( TreeNode node, LinkedList list){
      if( node != null){
        inorder(node.getLeft(), list);
        list.add(node.getValue());
        inorder(node.getRight(),list);
      }
    }
    //==========================================================

    public Iterator iteratorPreOrder(){
      LinkedList list = new LinkedList();
      preorder(root,list);
      return list.iterator();
    }

    private void preorder( TreeNode node, LinkedList list){
      if( node != null){
        list.add(node.getValue());
        preorder(node.getLeft(), list);
        preorder(node.getRight(),list);
      }
  }
  //===================================================
    public Iterator iteratorPostOrder(){
       LinkedList list = new LinkedList();
      postorder(root,list);
      return list.iterator();
    }

    private void postorder( TreeNode node, LinkedList list){
      if( node != null){
        postorder(node.getLeft(), list);
        postorder(node.getRight(),list);
        list.add(node.getValue());
      }
    }
    //===================================================
    public Iterator iteratorLevelOrder(){
      LinkedList list = new LinkedList();
      levelorder(root,list);
      return list.iterator();
    }

    private void levelorder(TreeNode node, LinkedList list){
      LinkedList queue = new LinkedList();
      queue.add(node);
      while(queue.size() != 0){
        TreeNode temp = (TreeNode)queue.removeFirst();
        list.add(temp.getValue() );
        if( temp.getLeft() != null)
          queue.add( temp.getLeft() );
        if(temp.getRight() != null)
          queue.add(temp.getRight() );
      }
    }
}
