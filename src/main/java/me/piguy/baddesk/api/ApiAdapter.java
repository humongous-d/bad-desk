package me.piguy.baddesk.api;

public interface ApiAdapter {
    // User login
    public boolean checkPassword(String username, String password);
    public boolean resetPassword(String username);

    // ticket
}