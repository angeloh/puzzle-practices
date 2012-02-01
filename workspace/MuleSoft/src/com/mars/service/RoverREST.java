package com.mars.service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.util.StringTokenizer;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.BindingType;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Provider;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPBinding;
import javax.xml.ws.http.HTTPException;

import com.mars.*;

@WebServiceProvider
@BindingType(value=HTTPBinding.HTTP_BINDING)
public class RoverREST implements Provider<Source> {

	@Resource(type=Object.class)
	protected WebServiceContext context;

	public Source invoke(Source source) {
		try {
			MessageContext mc = context.getMessageContext();
			String q = (String)mc.get(MessageContext.QUERY_STRING);
			//String p = (String)mc.get(MessageContext.PATH_INFO);

			if (q != null && q.contains("xmax=") && q.contains("ymax=") && q.contains("x=") && q.contains("y=") &&
					q.contains("ori=") && q.contains("cmd=")) {
				 
				return invokeRover(q);
			} 
		} catch(Exception e) {
			e.printStackTrace();
			throw new HTTPException(500);
		}
		return createFailedResult();
	}

	private Source invokeRover(String str) {
		StringTokenizer st = new StringTokenizer(str, "=&/");
		st.nextToken();
		int xmax = Integer.parseInt(st.nextToken());
		st.nextToken();
		int ymax = Integer.parseInt(st.nextToken());
		st.nextToken();
		int x = Integer.parseInt(st.nextToken());
		st.nextToken();
		int y = Integer.parseInt(st.nextToken());
		st.nextToken();
		char ori = st.nextToken().charAt(0);
		st.nextToken();
		String cmd = st.nextToken();
		return execute(xmax, ymax, x, y, ori, cmd);
	}
	
	public Source execute(int xmax, int ymax, int x, int y, char ori, String cmd) {
    	RoverFactory rf = new RoverFactory(xmax, ymax);
    	Rover r = rf.getObject(x, y, ori);
    	char [] arr = cmd.toCharArray();
		RoverCommand rc = new RoverCommand(r, arr);
    	rc.execute();
    	return createResult(rc.getRover().toString());
    }

	private Source createResult(String str) {
		Source source = new StreamSource(
		new ByteArrayInputStream((new String("<result>"+str+"</result>")).getBytes()));
		return source;
	}
	
	private Source createFailedResult() {
		Source source = new StreamSource(
		new ByteArrayInputStream((new String("<result>Invalid Arguments</result>")).getBytes()));
		return source;
	}
	
	public static void main(String[] args){
		// create and publish an endpoint
		RoverREST rest = new RoverREST();
		Endpoint.publish("http://localhost:8080/RoverREST", rest);        
	}
}