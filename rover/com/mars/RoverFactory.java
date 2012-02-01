package com.mars;

/**
 * @author Angelo K. Huang
 * Factory pattern to create a Rover 
 * and provide static methods to transform orientation character to a number
 * or a number back to character.
 */
public class RoverFactory {	
	int xMax, yMax;
	public RoverFactory(int xMax, int yMax) {
		this.xMax = xMax;
		this.yMax = yMax;
	}	
	
	public Rover getObject(int x, int y, char c) {		
		return new Rover(x, y, getOriNum(c), xMax, yMax); 
	}
	
	/**
	 * @param c
	 * @return short
	 * 0 = 'N', 1 = 'E', 2 = 'S', 3 = 'W'
	 */
	public static short getOriNum(char c){		
		char cc = Character.toLowerCase(c);
		switch(cc){		
			case 'n':
				return 0;	
			case 'e':
				return 1;
			case 's':
				return 2;
			case 'w':
				return 3;			
		}
		throw new IllegalArgumentException();
	}
	
	/**
	 * @param s
	 * @return char
	 * 0 = 'N', 1 = 'E', 2 = 'S', 3 = 'W'
	 */
	public static char getOriChar(short s){		
		switch(s){
			case 0:
				return 'N';
			case 1:
				return 'E';
			case 2:
				return 'S';
			case 3:
				return 'W';
			
		}
		throw new IllegalArgumentException();
	}
}