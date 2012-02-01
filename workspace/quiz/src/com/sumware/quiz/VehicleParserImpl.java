package com.sumware.quiz;

import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class VehicleParserImpl implements VehicleParser{
	private static final Logger logger = Logger.getLogger(VehicleParserImpl.class);
	private static final String log4jConf = "log4j.properties";    
	
	public VehicleParserImpl() {
		PropertyConfigurator.configure(log4jConf);		
	}
	
	public List<Vehicle> parseStream(InputStream in){
		List<Vehicle> list = Collections.emptyList();
		
		try {
			list = processLineByLine(in);
		} catch (Exception ex) {
			logger.error("Error Occurred in parseStream().", ex);
		}
		return list;
	}	

	protected List<Vehicle> processLineByLine(InputStream in) {				
		List<Vehicle> list = new ArrayList<Vehicle>();
		try {
			//use buffering, reading one line at a time	        
			BufferedReader bd = new BufferedReader(new InputStreamReader(in));	
			try {
				String line = null; //not declared within while loop	          
				Vehicle v = null;
				while (( line = bd.readLine()) != null){
					v = processLine(line);
					if (v != null)
						list.add(v);
				}
			}
			finally {
				bd.close();
			}
		}
		catch (IOException ioe){
			logger.error("Source File is not found.", ioe);
		}
		return list;
	}
	
	protected Vehicle processLine(String aLine){
		StringTokenizer tokens = new StringTokenizer(aLine, ",");
    	if (tokens.countTokens() != 15) {
    		//throw new IllegalArgumentException("Line format is wrong.");
    		logger.warn("Line format is wrong:");
    		logger.warn(aLine);
    		return null;
    	}    	
    	String [] args = new String [15];
    	int i = 0;
    	while (tokens.hasMoreTokens()) {
    		String tokenStr = tokens.nextToken();
    		tokenStr = tokenStr.replaceAll("\"", "");
    		args[i] = tokenStr;
    		i++;
    	}
    	VehicleParserImpl.VehcicleInitizer init = new VehicleParserImpl.VehcicleInitizer();
    	return init.initVehicle(args);
    	
	}
	
	static class VehcicleInitizer{	
		public Vehicle initVehicle(String [] args){
			if (args.length != 15) {	    		
	    		logger.warn("Line format is wrong:");	    		
	    		return null;
	    	}
			Vehicle v  = new Vehicle();
			if (args[0] != null)
				v.setDealerCode(args[0]);
			if (args[1] != null)
				v.setVinPrefix(args[1]);
			if (args[2] != null)
				v.setVinSuffix(args[2]);
			if (args[3] != null)
				v.setStockNumber(args[3]);
			if (args[4] != null)
				v.setModelYear(args[4]);
			if (args[5] != null)
				v.setMakeCode(args[5]);
			if (args[6] != null)
				v.setModelLine(args[6]);
			if (args[7] != null)
				v.setModelCode(args[7]);
			if (args[8] != null)
				v.setTransmissionType(args[8]);
			if (args[9] != null)
				v.setColorCode(args[9]);
			if (args[10] != null)
				v.setTrimCode(args[10]);
			if (args[11] != null) {
				List<Option> list = parseOptions(args[11]);				
				v.setOptions(list);
			}
			if (args[12] != null)				
				v.setMileage(atoi(args[12]));				 
			if (args[13] != null)
				v.setPrice(atoi(args[13]));
			if (args[14] != null) {
				Date date = parseDate(args[14]);
				if (date != null)
					v.setTransmissionDate(date);
			}
			return v;
			
			
		}
		
		public Integer atoi(String str) {
			Integer result = 0;
			try {
				result = Integer.parseInt(str);
			} catch (NumberFormatException nfe ){
				logger.error("Number Format is wrong: " + str, nfe);
			}
			return result;
		}
		
		public Date parseDate(String dateStr) {
			DateFormat df = new SimpleDateFormat("MMddyyyy");
			Date date = null;
			try
			{
				date = df.parse(dateStr);           
				
			} catch (ParseException e)
			{
				logger.error("Date Format is wrong!", e);
			}
			return date;

		}
		
		public List<Option> parseOptions(String dateStr) {
			char [] chars = dateStr.toCharArray();
			if ((chars.length % 3) != 0) {
				logger.error("Options '" + dateStr + "' is wrong format.");
				return Collections.emptyList();
			}
			List<Option> list = new ArrayList<Option>();
			for (int i = 0; i < chars.length ; i+=3) {
				char [] subChars = new char [3];
				subChars[0] = chars[i];
				subChars[1] = chars[i+1];
				subChars[2] = chars[i+2];
				Option o = new Option();
				o.setOptionCode(subChars);
				list.add(o);
			}
			return list;
		}
		
	}

}