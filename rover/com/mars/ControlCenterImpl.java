package com.mars;
import java.util.*;

/**
 * @author Angelo K. Huang
 * implementation class for ControlCenter Interface.
 */
public class ControlCenterImpl implements ControlCenter{
	List<RoverCommand> roverCmdList = new ArrayList<RoverCommand>();
	
	/* (non-Javadoc)
	 * @see com.mars.ControlCenter#addRover(com.mars.Rover, java.lang.String)
	 */
	public void addRover(Rover r, String command) {
		if (command == null){
			return;
		}
		char [] arr = command.toCharArray();
		RoverCommand rc = new RoverCommand(r, arr);
		roverCmdList.add(rc);
	}
	
	/* (non-Javadoc)
	 * @see com.mars.ControlCenter#runAllRovers()
	 */
	public void runAllRovers(){
		Iterator<RoverCommand> it = roverCmdList.iterator();
		while(it.hasNext()) {
			RoverCommand rc = it.next();
			rc.execute();			
			System.out.println(rc.getRover().toString());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.mars.ControlCenter#runRover(int)
	 */
	public String runRover(int i){
		RoverCommand rc = roverCmdList.get(i);
		if (rc != null) {
			rc.execute();
			return rc.getRover().toString();
		}
		return null;
	}
}