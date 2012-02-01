package BFS;
import java.io.*;
import BFS.pInteger;
import BFS.pBinarySearchTree;

class pBreadthFirstTraversalTest{
    public static void main(String[] args){
        pBreadthFirstTraversal tree = new pBreadthFirstTraversal();
        pInteger n;
        int i;
        System.out.println("Numbers inserted:");
        for(i=0;i<10;i++){
            tree.insert(n=new pInteger((int)(Math.random()*1000)));
            System.out.print(n+" ");
        }
        System.out.println("\nBreadth-First:");
        tree.breadth_first();
    }
    
    //prints out the names of nodes of a tree in breadth-first order.
    public static void printBreadthFirst(pBreadthFirstTraversal tree) {
    	pInteger n;
        int i;
        System.out.println("Name inserted:");
        for(i=0;i<10;i++){
            tree.insert(n=new pInteger((int)(Math.random()*1000)));
            System.out.print(n+" ");
        }
        System.out.println("\nBreadth-First(names of nodes):");
        tree.breadth_first();
    }
}