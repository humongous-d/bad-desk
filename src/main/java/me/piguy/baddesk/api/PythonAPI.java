package me.piguy.baddesk.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PythonAPI implements ApiAdapter {

    private String host;
    private String port;
    private String token;

    @Override
    public String getUrl() {
        return "http://" + host + ":" + port;
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
    public boolean login(String username, String password) {
        // Create a request to login
        Request loginRequest = null;
        Ctx loginResponse = null;
        try {
            loginRequest = new Request(this, "/token");
            loginResponse = loginRequest.Post()
                .DoWithMultipartForm(new HashMap<>() {
                    {
                        put("username", username);
                        put("password", password);
                    }
                })
                .Listen();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        // Set our API token for authentication
        this.setToken(String.valueOf(loginResponse.toMap().get("access_token")));
        return true;
    }

    @Override
    public Map<String, Object> currentUser() {
        // Check who I am
        Request meRequest;
        Ctx meResponse;
        try {
            meRequest = new Request(this, "/users/me");
            meResponse = meRequest.Get()
                    .withAuth()
                    .Do()
                    .Listen();
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }

        return meResponse.toMap();
    }

    @Override
    public boolean resetPassword(String username) {
        return false;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

}
