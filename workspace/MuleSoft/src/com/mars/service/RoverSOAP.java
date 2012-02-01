package com.mars.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import com.mars.Rover;
import com.mars.RoverCommand;
import com.mars.RoverFactory;

@WebService
public class RoverSOAP {
    @WebMethod
    public String execute(int xmax, int ymax, int x, int y, char ori, String cmd) {
    	RoverFactory rf = new RoverFactory(xmax, ymax);
    	Rover r = rf.getObject(x, y, ori);
    	char [] arr = cmd.toCharArray();
		RoverCommand rc = new RoverCommand(r, arr);
    	rc.execute();
    	return rc.getRover().toString();
    }
    
    public static void main(String[] args){
        // create and publish an endpoint
    	RoverSOAP soap = new RoverSOAP();
        Endpoint.publish("http://localhost:8080/RoverSOAP", soap);        
    }
}