package BFS_new;
import java.util.Iterator;
/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2003
 * Company:
 * @author
 * @version 1.0
 */

public class BinarySearchTreeMain {

  public BinarySearchTreeMain() {
  }
  public static void main(String[] args) {

     BinarySearchTree t1 = new BinarySearchTree();
     t1.addElement("Jackie");
     t1.addElement("Cynthia");
    t1.addElement("Katherine");
    t1.addElement("Barbara");
    t1.addElement("Rita");
    t1.addElement("Sylvia");
    t1.addElement("Dolly");
    t1.addElement("Gary");
    t1.addElement("Raul");
    t1.addElement("Rafael");
    t1.addElement("Milton");
    t1.addElement("Ron");
    t1.addElement("Tony");
    t1.addElement("Josh");
    t1.addElement("Cathy");

    System.out.println("Using toString \n" + t1);

    System.out.println("Using inorder Iterator \n" );
    Iterator itr = t1.iteratorInOrder();
    while(itr.hasNext()){
      System.out.println(itr.next());
    }

    System.out.println("Using preorder Iterator \n" );
    itr = t1.iteratorPreOrder();
    while(itr.hasNext()){
      System.out.println(itr.next());
    }

    System.out.println("Using postorder Iterator \n" );
    itr = t1.iteratorPostOrder();
    while(itr.hasNext()){
      System.out.println(itr.next());
    }

    System.out.println("Using levelorder Iterator \n" );
    itr = t1.iteratorLevelOrder();
    while(itr.hasNext()){
      System.out.println(itr.next());
    }


    System.out.println("Testing t1 size() " + t1.size() );
    System.out.println("Testing size2() " + t1.size2() );

    System.out.println("Testing find  " + t1.find( "Raul" ));

    System.out.println("Testing height " + t1.height() );
    String s = "Cathy";
    System.out.println("Testing remove " +
                                t1.removeElement(s));
    System.out.println("Using toString after removal of leaf\n" + t1);

    System.out.println("Testing removeMin " + t1.removeMin() );
    System.out.println("Testing removeMax " + t1.removeMax() );

    System.out.println("Testing findMin " + t1.findMin() );
    System.out.println("Testing findMax " + t1.findMax() );

    System.out.println("Testing more efficient find  " + t1.find( "Rafael" ));

    System.out.println("Testing removeLeftSubtree ");
    t1.removeLeftSubtree();
    System.out.println("Using toString \n" + t1);
    System.out.println("Testing t1 size() " + t1.size() );

    System.out.println("Testing removeRightSubtree ");
    t1.removeRightSubtree();
    System.out.println("Using toString \n" + t1);
    System.out.println("Testing t1 size() " + t1.size() );

  }

}