package com.sumware.quiz;

import java.io.InputStream;
import java.util.List;

/**
 * VehicleFactory responsible for creating Vehicle object. Later, it can be extended to other sub types.
 * @author Angelo Huang
 *
 */
public class VehicleFactory extends ObjectFactory{
	
	/**
	 * Only classes in same package or subclasses in other packages (super()) can call the constructor
	 */
	protected VehicleFactory() {
		
	}
	
	@Override
	public List<Vehicle> getObject(String objectType, InputStream in) {		
		List<Vehicle> list = null;
		if (objectType.equalsIgnoreCase("Vehicle")){
			VehicleParser vpi = new VehicleParserImpl();
			list = vpi.parseStream(in);
		}
		// later we can implement other types here..
		return list;
	}
}
