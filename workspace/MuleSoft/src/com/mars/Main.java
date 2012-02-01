package com.mars;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.*;

/**
 * @author Angelo K. Huang
 * Main class to execute Rover program
 */
public class Main{
	/**
	 * @param args
	 * Main function to execute rover program from an input file.
	 */
	public static void main(String [] args){
		if (args.length == 0) {
			System.out.println("Please provide a input file!!");
			return;
		}
		String fileName = args[0];
		Main main = new Main();
		List<String> input = main.readFile(fileName);
//		List<String> input = main.readFile("bin/in");
		ControlCenter cc = main.getCtlCenter(input);
		cc.runAllRovers();
	}
	private List<String> readFile(String fileName) {
		if (fileName == null)
			return null;
		List<String> list = new ArrayList<String>();
		try {
			BufferedReader br  = new BufferedReader(new FileReader(fileName));
			String currLine;			
			while((currLine = br.readLine())!=null) {
				list.add(currLine);
			}
		
		} catch (FileNotFoundException fnfe) {			 
			fnfe.printStackTrace();	 		
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return list;
	}
	
	protected ControlCenter getCtlCenter(List<String> input) {
		if (input == null)
			throw new IllegalArgumentException();
		RoverFactory factory = getRoverFactory(input.get(0));
		ControlCenter cc = new ControlCenterImpl();
		for(int i = 1; i < input.size(); i+=2){
			Rover r = getRover(factory, input.get(i));
			cc.addRover(r, input.get(i+1));
		}
		return cc;
	}
	
	private RoverFactory getRoverFactory(String line){
		if (line == null)
			throw new IllegalArgumentException();
		String [] arr = line.split("\\s+");
		if (arr.length < 2)
			throw new InvalidParameterException("Coordinates of the plateau are wrong!!");
		int xMax = Integer.parseInt(arr[0]);
		int yMax = Integer.parseInt(arr[1]);	
		 
		return new RoverFactory(xMax, yMax);
	}
	
	private Rover getRover(RoverFactory factory, String line){
		if (line == null)
			throw new IllegalArgumentException();
		String [] arr = line.split("\\s+");
		if (arr.length < 3)
			throw new InvalidParameterException("Coordinates of the rover are wrong!!");
		int x = Integer.parseInt(arr[0]);
		int y = Integer.parseInt(arr[1]);
		char c = arr[2].charAt(0);
		return factory.getObject(x, y, c);
	}
}