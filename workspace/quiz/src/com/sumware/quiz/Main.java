/*
 * Created on Apr 5, 2005
 *
 */
package com.sumware.quiz;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author nanderson
 *
 */
public class Main {
	private static final Logger logger = Logger.getLogger(Main.class);
	private static final String log4jConf = "log4j.properties";
    
    public static void main(String[] args) {
    	PropertyConfigurator.configure(log4jConf);    	
        if (args.length<1) {
            logger.info("Usage: Main file.csv");
            return;
        }
        InputStream is = null;
        try {
            // open CSV file
            is = new FileInputStream(args[0]);       
            
            // parse CSV into a List of Vehicles         
            VehicleFactory vf = (VehicleFactory)ObjectFactorySelector.getObjectFactory();
            List<Vehicle> list = vf.getObject("Vehicle", is);
            //print title
            printVehicleTitle();
            // print out list of Vehicles in human readable format
            Iterator<Vehicle> iterator = list.iterator();
            while (iterator.hasNext()) {
            	System.out.println(iterator.next().toString());
            }
        } catch (Exception e) {
            logger.error("Error in creating objects from CSV:", e);
        } finally {
            try {
                is.close();
            } catch (Exception ex) {;}
        }
    }
    
    /**
     * Dealer  VIN                 Stock#  Year  Make  Model–Code  Trans  Color  Trim  Mileage  Price   Date
     * @return title
     */
    public static void printVehicleTitle(){
    	
    	StringBuffer sb = new StringBuffer();
		sb.append("Dealer").append("\t");
		sb.append("VIN").append("\t\t\t");
		sb.append("Stock#").append("\t");
		sb.append("Year").append("\t");
		sb.append("Make").append("\t");
		sb.append("Model–Code").append("\t");
		sb.append("Trans").append("\t");
		sb.append("Color").append("\t");
		sb.append("Trim").append("\t");
		sb.append("Mileage").append("\t");
		sb.append("Price").append("\t");
		sb.append("Date");
		System.out.println(sb.toString());
    }
}
