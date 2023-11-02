package me.piguy.baddesk;

import me.piguy.baddesk.api.ApiAdapter;
import me.piguy.baddesk.api.TestLocalAPI;
import me.piguy.baddesk.pages.theme.CupertinoTheme;
import me.piguy.baddesk.pages.theme.DraculaTheme;
import me.piguy.baddesk.pages.theme.NordTheme;
import me.piguy.baddesk.pages.theme.PageTheme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConfigurationManager {
    private static ConfigurationManager config;
    private final HashMap<String, PageTheme> themes = new HashMap<>();
    public ApiAdapter api;

    public static ConfigurationManager getInstance() {
        if (config == null) {
            config = new ConfigurationManager();
        }
        return config;
    }

    private void addTheme(PageTheme theme) {
        themes.put(theme.getThemeName(), theme);
    }

    public HashMap<String, PageTheme> getThemes() {
        return new HashMap<>(themes);
    }

    public PageTheme getTheme(String name) {
        return themes.get(name);
    }

    // All the config goes here
    private ConfigurationManager() {
        api = new TestLocalAPI();

        addTheme(new NordTheme());
        addTheme(new DraculaTheme());
        addTheme(new CupertinoTheme());
    }

}
