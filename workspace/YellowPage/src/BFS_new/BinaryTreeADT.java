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

public interface BinaryTreeADT {

  void removeLeftSubtree();
  void removeRightSubtree();
  void removeAllElements();
  boolean isEmpty();
  int size();
  boolean contains ( Object targetElement);
  Object find( Object targetElement);
  String toString();
  Iterator iteratorInOrder();
  Iterator iteratorPreOrder();
  Iterator iteratorPostOrder();
  Iterator iteratorLevelOrder();
}