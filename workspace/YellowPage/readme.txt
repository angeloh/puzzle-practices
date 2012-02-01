Q1.  Define a node class that can be used for a tree structure.  
Write a function that prints out the names of nodes of a tree in breadth-first order.  

All the data structure file are in the BFS package. Please use pBreadthFirstTraversalTest.java
to run the function "public static void printBreadthFirst(T tree)"


Q2: Define a node class that can be used a (possibly cyclic) graph.  Write a function that prints out all the nodes in a graph.  

n/a

Q3: Write a function that divides a collection of m objects into n evenly-sized subsets.  (It is OK if some of the subsets contain 1 less object than the others.)

n/a

Q4: Write a DTD or XML schema for the following: 
An XML fragment may contain multiple <list> elements, each of which may hold (a) 0 or more <entry> elements that may contain CDATA, and/or (b) 0 or more additional nested <list> elements.  

    E.g.:
      <list name="animals">
        <entry>ghosts</entry>
        <list name="imaginary">
          <entry>unicorns</entry>
        </list>
        <list name="bugs">
          <entry>spider</entry>
          <entry>gnat</entry>
        </list>
      </list>
      <list name="recipes">
        <entry>ghost soup</entry>
      </list>
      ...
      
      
Please check yp.xsd file for the XML schema.

Q5: Using the above schema, write a function to print out the CDATA content of last <entry> element in any nested (i.e., not top-level) <list>.

n/a

Q6: Let S be a sequence such that S[k+1] = 1 / ((k * S[k]) / 2).  Let S[1] equal 1.  Write a program to compute the sum of the values of S[k] for k = 1 .. n.

Please check recursiveSum.java fro details.

Q7: Bonus: Write a function to sum the numbers from 1 to n without using a loop. 

Please check computeSum.java fro details.
