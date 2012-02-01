package BFS_new;
import java.util.LinkedList;
import java.util.Iterator;
/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2003
 * Company:
 * @author
 * @version 1.0
 */

public class BinaryTreeMain {

  public BinaryTreeMain() {
  }
  public static void main(String[] args) {
    BinaryTree f1 = new BinaryTree("Jackie");
    BinaryTree f2 = new BinaryTree("Cynthia");
    BinaryTree f3 = new BinaryTree("Katherine", f1, f2);
    BinaryTree f4 = new BinaryTree("Barbara");
    BinaryTree f5 = new BinaryTree("Rita");
    BinaryTree f6 = new BinaryTree("Sylvia",f4, f5);
    BinaryTree f7 = new BinaryTree("Dolly",f3,f6);

    BinaryTree m1 = new BinaryTree("Gary");
    BinaryTree m2 = new BinaryTree("Raul");
    BinaryTree m3 = new BinaryTree("Rafael", m1, m2);
    BinaryTree m4 = new BinaryTree("Milton");
    BinaryTree m5 = new BinaryTree("Ron");
    BinaryTree m6 = new BinaryTree("Tony",m4,m5);
    BinaryTree m7 = new BinaryTree("Josh", m3, m6);

    BinaryTree t1 = new BinaryTree("Cathy", f7, m7);


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

    System.out.println("Testing f7 size() " + f7.size() );
    System.out.println("Testing m7 size() " + m7.size() );
    System.out.println("Testing t1 size() " + t1.size() );
    System.out.println("Testing size2() " + t1.size2() );

    System.out.println("Testing find  " + t1.find( "Cynthia" ));

    System.out.println("Testing height " + t1.height() );

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