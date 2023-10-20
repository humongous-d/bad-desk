package me.piguy.baddesk;

import me.piguy.baddesk.api.ApiAdapter;
import me.piguy.baddesk.api.TestLocalAPI;

public class ConfigurationManager {
    private static ConfigurationManager config;

    public ApiAdapter api;

    public static ConfigurationManager getInstance() {
        if (config == null) {
            config = new ConfigurationManager();
        }
        return config;
    }

    // All the config goes here
    private ConfigurationManager() {
        api = new TestLocalAPI();
    }

}
