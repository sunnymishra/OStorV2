package com.ostor.util;

import java.io.IOException;
import java.util.Properties;

public class Constants {
	
	public static Properties getGenericProperties() {
		if (genericProperties != null)
			return genericProperties;

		try {
			genericProperties = new Properties();
			genericProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("generic.properties"));
		} catch (IOException e) {
			return new Properties();
		}
		return genericProperties;
	}
	public enum OperationType{
		API, CONTENT
	}
	private static Properties genericProperties;
	
	public static final String TEST = "TEST";
}
