package com.airsafe.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Singleton storing shared airline configuration.
 */
public final class GlobalConfig {

    private static final GlobalConfig INSTANCE = new GlobalConfig();
    private final Map<String, String> settings = new ConcurrentHashMap<>();

    private GlobalConfig() {
        settings.put("airlineName", "AirSafe Airlines");
        settings.put("currency", "USD");
        settings.put("timezone", "UTC");
        settings.put("eticketPrefix", "ASF");
    }

    public static GlobalConfig getInstance() {
        return INSTANCE;
    }

    public String getSetting(String key) {
        return settings.get(key);
    }

    public void updateSetting(String key, String value) {
        settings.put(key, value);
    }
}
