package me.piguy.baddesk.api;

public class TestLocalAPI implements ApiAdapter {
    @Override
    public boolean checkPassword(String username, String password) {
        return false;
    }

    @Override
    public boolean resetPassword(String username) {
        return false;
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
}
