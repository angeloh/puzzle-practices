package com.mars;

/**
 * @author Angelo K. Huang
 * Rover class includes the coordinates and orientation data, 
 * and getter & setter to manipulate the data. 
 */
public class Rover {
	private int x;
	private int y;
	private short ori; //0 = 'N', 1 = 'E', 2 = 'S', 3 = 'W'
	private int xMax;
	private int yMax;
		
	public Rover(int x, int y, short o, int xMax, int yMax) {
		this.x = x;
		this.y = y;
		this.ori = o;
		this.xMax = xMax;
		this.yMax = yMax;
	}
	
	public void execute(Character c){
		char sc = Character.toLowerCase(c);
		switch(sc){		
			case 'r':
				setOri(++ori);
				break;
			case 'l':
				setOri(--ori);
				break;
			default:
				move();
		}
	}
	
	public void move(){
		switch (ori){
			case 0:
				setY(y+1);
				break;
			case 1:
				setX(x+1);
				break;
			case 2:
				setY(y-1);
				break;
			case 3:
				setX(x-1);
				break;			
		}			
	}
	
	public int getX() {
		return x;		
	}
	
	public int getY() {
		return y;		
	}
	
	public short getOri() {
		return ori;	
	}	
	
	public void setX(int x) {
		if (x >= 0 && x <= xMax)
			this.x = x;		
	}
	
	public void setY(int y) {
		if (y >= 0 && y <= yMax)
			this.y = y;
	}
	
	public void setOri(short ori) {
		this.ori = (ori>3)?0:(ori<0)?3:ori;		
	}
	
	public String toString(){
		return x + " " + y + " " + RoverFactory.getOriChar(ori);
	}
}