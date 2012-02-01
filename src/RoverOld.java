
/**
 * @author Angelo Kai-Chen Huang
 * Rover class includs the coordinates and orientation data, and functions to manipulate the data. 
 */
public class RoverOld {
	private int xCoord;
	private int yCoord;
	private int roverOri;	
		
	public RoverOld() {
		xCoord = 0;
		yCoord = 0;
		roverOri = 0;			
	}
	
	public int getXCoord() {
		return xCoord;		
	}
	
	public int getYCoord() {
		return yCoord;		
	}
	
	public int getRoverOri() {
		return roverOri;	
	}	
	
	public void setXCoord(int x) {
		xCoord = x;		
	}
	
	public void setXCoordAddOne() {
		xCoord++;
	}
	
	public void setXCoordMinusOne() {
		xCoord--;
	}
	
	public void setYCoord(int y) {
		yCoord = y;
	}
	
	public void setYCoordAddOne() {
		yCoord++;
	}
	
	public void setYCoordMinusOne() {
		yCoord--;
	}
	public void setRoverOri(int ori) {
		roverOri = ori;
	}
	
	public void setRoverOriAddOne() {
		roverOri++;
	}
	
	public void setRoverOriMinusOne() {
		roverOri--;
	}
	
}