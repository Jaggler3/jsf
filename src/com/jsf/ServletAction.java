package com.jsf;

import java.util.Map;

import com.sun.net.httpserver.HttpExchange;

@FunctionalInterface
public interface ServletAction {
	public abstract Json get(Map<String, String> query, HttpExchange req);
}
