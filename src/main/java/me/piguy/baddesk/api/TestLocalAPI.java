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
}
