package com.jsf;

import java.util.ArrayList;
import java.util.HashMap;

public class Json {	

	public String name;
	public HashMap<String, String> data;
	public HashMap<String, Json> children;
	
	public Json()
	{
		data = new HashMap<String, String>();
		children = new HashMap<String, Json>();
	}
 
	/* Add a property and return the parent Json object */
	public Json addProperty(String property, String value)
	{
		data.put(property, value);
		return this;
	}

	/* Add a child and return the parent Json object */
	public Json addChild(String name, Json child)
	{
		children.put(name, child);
		return this;
	}
	
	/* Format the Json to a String */
	public String format(boolean pretty, int indent)
	{
		String tabSpace = "\t".repeat(indent);
		String a = pretty ? "{\n" : "{";
		
		int data_length = data.size();
		ArrayList<String> keyList = new ArrayList<String>(data.keySet());
		for(int i = 0; i < data_length; i++)
		{
			String key = keyList.get(i);
			a += (pretty ? tabSpace + "\t\"": "\"") + key + "\":\"" + data.get(key) + "\"" + (i == data_length - 1 ? "\n" : ",\n");
		}
		
		int child_length = children.size();
		ArrayList<String> childList = new ArrayList<String>(children.keySet());
		for(int i = 0; i < child_length; i++)
		{
			String key = childList.get(i);
			a += (pretty ? tabSpace + "\t\"": "\"") + key + "\":" + children.get(key).format(pretty, pretty ? indent + 1 : 0) + (i == child_length - 1 ? "\n" : ",\n");
		}
		
		a += tabSpace + "}";
		
		return a;
	}
	
	/* Format the Json to a String (pretty = true) */
	public String format()
	{
		return format(true, 0);
	}
}