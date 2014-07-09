package com.br.core;

import java.util.HashMap;
import java.util.Map;

public enum PlatformType {
	WINDOWS("WINDOWS"),
	LINUX("LINUX"),
	MAC("MAC");
	
	private String platformKey;
	private static Map<String, PlatformType> platformMap = new HashMap<String, PlatformType>();
	static {
		for (PlatformType pt: PlatformType.values()) {
			platformMap.put(pt.key(), pt);
		}
	}
	
	private PlatformType(String key) {
		this.platformKey = key;
	}
	
	private String key() {
		return this.platformKey;
	}
	
	public static PlatformType get(String key) {
		if (platformMap.containsKey(key)) {
			return platformMap.get(key);
		}
		return WINDOWS;
	}
}
