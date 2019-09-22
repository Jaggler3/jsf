package com.jsf;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class Servlet implements HttpHandler {

	/* The server location */
	private String route;
	
	public Servlet() { }
	
	public ServletAction action;
	
	@Override
	public void handle(HttpExchange req) throws IOException
	{
		String rawQuery = req.getRequestURI().getRawQuery();
		Json response = action.get(rawQuery == null ? new HashMap<String, String>() : ParseQuery(rawQuery), req);
		String responseData = response.format();
		
		req.sendResponseHeaders(200, responseData.length());
        OutputStream os = req.getResponseBody();
        os.write(responseData.getBytes());
        os.close();
	}
	
	public Map<String, String> ParseQuery(String query) {
		Map<String, String> result = new HashMap<String, String>();
		for (String param : query.split("&")) {
			String pair[] = param.split("=");
			if (pair.length > 1) {
				result.put(pair[0], pair[1]);
			} else {
				result.put(pair[0], "");
			}
		}
		return result;
	}
	
	public String getRoute()
	{
		return route;
	}
	public void setRoute(String route)
	{
		this.route = route;
	}
	
}
