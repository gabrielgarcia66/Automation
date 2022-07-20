package com.aspiration.config;

import java.util.Properties;

public class PropertyManager {
	private static Properties properties = new Properties();

	public static Properties getConfigProperties() {
		return properties;
	}

	public static void setConfigProperties(Properties prop) {
		properties = prop;
	}
}
