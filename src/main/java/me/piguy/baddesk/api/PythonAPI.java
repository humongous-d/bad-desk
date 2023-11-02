package me.piguy.baddesk.api;

import java.io.IOException;

public class PythonAPI implements ApiAdapter{

    private String host;
    private String port;

    @Override
    public String getUrl() {
        return "http://" + host + ":" + port + "/";
    }

    @Override
    public void connect(String host, String port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void disconnect() {

    }
    @Override
    public boolean checkPassword(String username, String password) {
        return false;
    }

    @Override
    public boolean resetPassword(String username) {
        return false;
    }
}
