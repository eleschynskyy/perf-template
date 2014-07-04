package com.br.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ConfigProperties {

	private static Properties SYSTEM_PROPERTIES;

	static {
		SYSTEM_PROPERTIES = new Properties();
		URL props = ClassLoader.getSystemResource("system.properties");
		try {
			SYSTEM_PROPERTIES.load(props.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getSystemProperties(String key) {
		return SYSTEM_PROPERTIES.getProperty(key);
	}

}

