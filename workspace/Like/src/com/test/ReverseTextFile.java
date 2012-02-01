package com.test;

import java.io.*; 
import java.util.*;

/**
 * @author Angelo
 * Write a program that will read the content of a text file and save it in reverse order
 */
public class ReverseTextFile{
	
	public String reverseText(String fileName) {
		String str = readFile(fileName);
		String [] arr = str.split("\\s+");
		List<String> list = Arrays.asList(arr);
		Collections.reverse(list);
		arr = list.toArray(new String[0]);		
		return arrayToString(arr, " ");
	}
	
	private String arrayToString(String[] array, String delim) {
	    StringBuilder arTostr = new StringBuilder();
	    if (array.length > 0) {
	        arTostr.append(array[0]);
	        for (int i=1; i<array.length; i++) {	            
	            arTostr.append(delim);
	        	arTostr.append(array[i]);
	        }
	    }
	    return arTostr.toString();
	}
	
	private String readFile(String fileName) {
		if (fileName == null)
			return null;
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br  = new BufferedReader(new FileReader(fileName));
			String currLine;			
			while((currLine = br.readLine())!=null) {
				sb.append(currLine);
			}
			br.close();
		} catch (FileNotFoundException fnfe) {			 
			fnfe.printStackTrace();	 		
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	private void outToFile(String fileName, String content) {
		if (fileName == null)
			return;
		
		try {
			BufferedWriter bw  = new BufferedWriter(new FileWriter(fileName));
			bw.write(content);
			bw.close();
		} catch (FileNotFoundException fnfe) {			 
			fnfe.printStackTrace();	 		
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}	
	}
	
	/**
	 * @param args
	 * usage: java ReverseTextFile in out
	 */
	public static void main(String [] args) {
		if (args.length == 0) {
			System.out.println("Please provide a input file!!");
			return;
		}
		String inF = args[0];
		String outF;
		if (args.length == 1) {
			outF = "out";
		} else {
			outF = args[1];
		}
		
		ReverseTextFile reverseTextFile = new ReverseTextFile();
		String out = reverseTextFile.reverseText(inF);		
		reverseTextFile.outToFile(outF, out);
	}
}