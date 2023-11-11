package me.piguy.baddesk.api;

import me.piguy.baddesk.models.Ticket;
import me.piguy.baddesk.pages.panes.Status;

import java.util.ArrayList;
import java.util.HashMap;
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

    @Override
    public ArrayList<HashMap<String, Object>> getTickets() {
        return null;
    }

    @Override
    public ArrayList<HashMap<String, Object>> getTickets(int page) {
        return null;
    }

    @Override
    public Boolean deleteTicket(String id) {
        return null;
    }

    @Override
    public Boolean editTicket(Ticket ticket) {
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
    public void disconnect() {}

    @Override
    public boolean newTicket(String title, String description, Integer priority, Status status, String assignee, String attachment) {
        return false;
    }

    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public boolean newTicket(Ticket ticket) {
        return false;
    }

}
