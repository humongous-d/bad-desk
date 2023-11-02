package me.piguy.baddesk.api;

public interface ApiAdapter {
    // Internals
    String getUrl();
    public void connect(String host, String port);
    public void disconnect();

    // User login
    public boolean checkPassword(String username, String password);
    public boolean resetPassword(String username);


    // ticket
}