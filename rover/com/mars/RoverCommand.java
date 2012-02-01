package com.mars;
import java.util.*;

/**
 * @author Angelo K. Huang
 * Command Pattern to execute commands for a rover.
 */
public class RoverCommand implements Command {
	private List<Character> commands = null;
	private Rover r = null;
	
	public RoverCommand (Rover r, char [] arr){
		this.r = r;
		this.commands = asList(arr);
	}
	
	/* (non-Javadoc)
	 * @see com.mars.Command#execute()
	 */
	public void execute(){
		Iterator<Character> it = commands.iterator();
		while(it.hasNext()) {
			Character c = it.next();
			r.execute(c);
		}
	}
	
	private List<Character> asList(char [] arr) {
		List<Character> list = new ArrayList<Character>();
		for (char c: arr){
			list.add(c);
		}
		return list;
	}
	
	public Rover getRover() {
		return r;
	}
}