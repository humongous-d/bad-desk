package me.piguy.baddesk.api;

import java.io.IOException;
import java.util.Map;

public class TestLocalAPI implements ApiAdapter {

    @Override
    public boolean resetPassword(String username) {
        return false;
    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public Map<String, Object> currentUser() {
        return null;
    }


    // Ignored
    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public void connect(String host, String port) {}

    @Override
    public void disconnect() {

    }

    @Override
    public boolean login(String username, String password) throws IOException {
        return false;
    }
}
