
import java.util.Hashtable;
import java.util.ArrayList;
import java.io.*;

/**
 * @author Angelo Kai-Chen Huang
 * This class is used to solve each rover's movement and print its final coordinates.
 */
class RoverTest{
	//the hash for rover orientation by using "N,W,S,E" for keys
	private Hashtable<String, Integer> orientation;
	//the array uses index to get rover orientation
	private char[] orientationReverse = {'N', 'W', 'S', 'E'};
	private int maxXCoord = 0;
	private int maxYCoord = 0;
	private ArrayList<RoverOld> roverList;
	
	public static void main(String[] args){
		RoverTest RT = new RoverTest();
		for (int arrSize = 0; arrSize < RT.roverList.size(); arrSize++) {
			int roverOri = ((RoverOld)RT.roverList.get(arrSize)).getRoverOri();
			System.out.println(((RoverOld)RT.roverList.get(arrSize)).getXCoord() + " "
					+ ((RoverOld)RT.roverList.get(arrSize)).getYCoord() + " "
					+ RT.orientationReverse[roverOri]);
		}
    }
	
	public RoverTest() {
		
		//put the orientation and its index into the hashtable.
		orientation = new Hashtable<String, Integer>();
		orientation.put("N", 0);
		orientation.put("W", 1);
		orientation.put("S", 2);
		orientation.put("E", 3);
		
		roverList = new ArrayList<RoverOld>();
		//the Rover input file
		String fileName = "rover_input";
		BufferedReader in = null;
		try {
			int numOfLines = count(fileName);
			FileReader fileReader = new FileReader(fileName);			
			in = new BufferedReader(fileReader);
			
			//read the Max X cord and Y cord.
			String coordsString = in.readLine();
			//System.out.println(coordsString);
			String [] maxCoords = null;
			maxCoords = coordsString.split(" ");
			maxXCoord = Integer.parseInt(maxCoords[0]);
			maxYCoord = Integer.parseInt(maxCoords[1]);
			
			// read two lines for each rover per iteration
			for (int lineCount = 1; lineCount < numOfLines; lineCount+=2) {
			    int errorMove = 0;
				//the first line for current coordinates of rover
			    String curCoordsString = in.readLine();
				String [] curCoords = null;
				curCoords = curCoordsString.split(" ");
				RoverOld rover = new RoverOld();
				rover.setXCoord(Integer.parseInt(curCoords[0]));
				rover.setYCoord(Integer.parseInt(curCoords[1]));
				rover.setRoverOri(orientation.get(curCoords[2]));
				
				//the second line for rover's movements
				String movesString = in.readLine();				
				String [] moves = null;
				moves = movesString.split("");
				
				// for each movement character, make the rover do the corresponded action.
				for (int i = 1; i < moves.length; i++) {
					//System.out.println(moves[i]);
					if (moves[i].equals("L")) {
						//System.out.println("Do L action");
				 		rover.setRoverOriAddOne();
				 		if (rover.getRoverOri() == 4) {	 		
				 			rover.setRoverOri(0);
				 		}	 		
				 	} else if (moves[i].equals("R")) {
				 		rover.setRoverOriMinusOne();
				 		if (rover.getRoverOri() == -1) {
				 			rover.setRoverOri(3);
				 		}
				 	} else if (moves[i].equals("M")) {
				 		// The rover heads North
				 		if (rover.getRoverOri() == 0){
				 			rover.setYCoordAddOne();
				 			if (rover.getYCoord() > maxYCoord) {
				 				rover.setYCoord(maxYCoord);
				 			}
				 		}
				 		// The rover heads West
				 		if (rover.getRoverOri() == 1) {
				 		    rover.setXCoordMinusOne();
				 		    if (rover.getXCoord() < 0) {
				 				rover.setXCoord(0);
				 			}	 		    
				 		}
				 	    // The rover heads South
				 	    if (rover.getRoverOri() == 2) {
				 			rover.setYCoordMinusOne();
				 			if (rover.getYCoord() < 0) {
				 				rover.setYCoord(0);
				 			}
				 	    }
				 	    // The rover heads East
				 	    if (rover.getRoverOri() == 3){
				 	    	rover.setXCoordAddOne();
				 			if (rover.getXCoord() > maxXCoord) {
				 				rover.setXCoord(maxXCoord);
				 			}
				 	    }
				 	} else {
				 		System.out.println(i);
				 		errorMove = 1;	// Otherwise, there is an error in the movement string 			 	
				 	}				 
				}			
				// The movements are all successfully completed, then add the rover to list.
				if (errorMove == 0){
					//System.out.println("no errors");
					roverList.add(rover);
				}
				// Otherwise, set the rover to its initial coordinate and orientation.
				else {
					rover.setXCoord(Integer.parseInt(curCoords[0]));
					rover.setYCoord(Integer.parseInt(curCoords[1]));
					rover.setRoverOri(orientation.get(curCoords[2]));
					roverList.add(rover);
				}
			}			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}		
	}
	
	// This function is used to count the number of lines in the file.
	public int count(String fileName) throws IOException {
		int numLines = 0;
		FileReader fileReader = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fileReader);
		String line;
		do {
			line = br.readLine();
			if (line != null)
			{
				numLines++;				
			}
		} while (line != null);
		
		return numLines;
	}
}