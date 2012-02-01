package BFS_new;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2003
 * Company:
 * @author
 * @version 1.0
 */

public interface BinarySearchTreeADT extends BinaryTreeADT {

  public void addElement( Comparable element);
  public Comparable removeElement( Comparable targetElement);
  public void removeAllOccurrences( Comparable targetElement);
  public Comparable removeMin();
  public Comparable removeMax();
  public Comparable findMin();
  public Comparable findMax();
}