package com.jsf;

import java.net.InetSocketAddress;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpServer;

public class AppServer {
	
	public static HttpServer httpServer;
	
	private static int httpPort = 8080;
	
	public static ArrayList<Servlet> servlets = new ArrayList<Servlet>();
	
	/* Creates a servlet and adds it to the AppServer */
	public static void AddRoute(String route, ServletAction action)
	{
		PrefabServlet prefab = new PrefabServlet();
		prefab.setRoute(route);
		prefab.action = action;
		servlets.add(prefab);
	}
	
	public static void listen(int PORT)
	{
		try
		{
			int MIN_BACKLOG = 0;
			httpPort = PORT;
			httpServer = HttpServer.create(new InetSocketAddress(httpPort), MIN_BACKLOG);
			httpServer.setExecutor(null);
			
			int s_length = servlets.size();
			for(int i = 0; i < s_length; i++)
			{
				Servlet s = servlets.get(i);
				httpServer.createContext("/" + s.getRoute(), s);
			}
			
			httpServer.start();
			
		} catch (Exception e) { e.printStackTrace(); }
	}

}
