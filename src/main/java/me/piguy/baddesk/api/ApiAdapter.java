package me.piguy.baddesk.api;

import java.io.IOException;
import java.util.Map;

public interface ApiAdapter {
    // Internals
    String getUrl();
    public void connect(String host, String port);
    public void disconnect();

    // User login
    public boolean login(String username, String password);
    public boolean resetPassword(String username);
    public String getToken();

    Map<String, Object> currentUser();

    // ticket
}