package com.mars;

/**
 * @author Angelo K. Huang
 * interface for ControlCenter
 */
public interface ControlCenter{
	/**
	 * @param Rover r
	 * @param command
	 * Add Rover and command to control center
	 */
	public void addRover(Rover r, String command);
	/**
	 * Execute all rovers that are saved in the list of control center
	 */
	public void runAllRovers();
	/**
	 * @param int r
	 * Execute specific rover
	 */
	public String runRover(int r);
}