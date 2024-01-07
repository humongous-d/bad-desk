package me.piguy.baddesk.api;

import me.piguy.baddesk.models.Ticket;
import me.piguy.baddesk.models.User;
import me.piguy.baddesk.pages.panes.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestLocalAPI {

    public boolean resetPassword(String username) {
        return false;
    }

    public String getToken() {
        return null;
    }

    public User currentUser() {
        return null;
    }

    public ArrayList<HashMap<String, Object>> getTickets() {
        return null;
    }

    public ArrayList<HashMap<String, Object>> getTickets(int page) {
        return null;
    }

    public Boolean deleteTicket(String id) {
        return null;
    }

    public Boolean editTicket(Ticket ticket) {
        return null;
    }


    // Ignored
    public String getUrl() {
        return null;
    }

    public void connect(String host, String port) {}

    public void disconnect() {}

    public boolean newTicket(String title, String description, Integer priority, Status status, String assignee, String attachment) {
        return false;
    }

    public boolean login(String username, String password) {
        return false;
    }

    public boolean newTicket(Ticket ticket) {
        return false;
    }

}
