package test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sumware.quiz.ObjectFactorySelector;
import com.sumware.quiz.Vehicle;
import com.sumware.quiz.VehicleFactory;

public class ParseVehicleTest
{

    @Before
    public void setUp() throws Exception
    {    	

    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void testNumberOfObjectsCreated()
    {
    	InputStream is = null;
    	List<Vehicle> list = Collections.emptyList();
    	try {
    		is = new FileInputStream("test1.csv");       

    		// parse CSV into a List of Vehicles         
    		VehicleFactory vf = (VehicleFactory)ObjectFactorySelector.getObjectFactory();
    		list = vf.getObject("Vehicle", is);

    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			is.close();
    		} catch (Exception ex) {;}
    	}
    	Assert.assertEquals(5, list.size());
    }

    @Test
    public void testDateFormat()
    {
    	InputStream is = null;
    	List<Vehicle> list = Collections.emptyList();
    	try {
    		is = new FileInputStream("test1.csv");       

    		// parse CSV into a List of Vehicles         
    		VehicleFactory vf = (VehicleFactory)ObjectFactorySelector.getObjectFactory();
    		list = vf.getObject("Vehicle", is);

    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			is.close();
    		} catch (Exception ex) {;}
    	}
    	
        Iterator<Vehicle> iterator = list.iterator();
        while (iterator.hasNext()) {
        	Assert.assertNotNull(iterator.next().getTransmissionDate());
        }
    }
    
    @Test
    public void testOptionCodeFormat()
    {
    	InputStream is = null;
    	List<Vehicle> list = Collections.emptyList();
    	try {
    		is = new FileInputStream("test2.csv");       

    		// parse CSV into a List of Vehicles         
    		VehicleFactory vf = (VehicleFactory)ObjectFactorySelector.getObjectFactory();
    		list = vf.getObject("Vehicle", is);

    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			is.close();
    		} catch (Exception ex) {;}
    	}
    	//The format is correct.
    	Vehicle v1 = list.get(0);
    	Assert.assertEquals(6, v1.getOptions().size());
    	//The format is wrong.
    	Vehicle v2 = list.get(1);
    	Assert.assertEquals(0, v2.getOptions().size());
    }
}
