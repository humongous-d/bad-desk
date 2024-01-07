package me.piguy.baddesk.api;

import me.piguy.baddesk.models.Ticket;
import me.piguy.baddesk.models.User;
import me.piguy.baddesk.pages.panes.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface ApiAdapter {
    // Internals
    String getUrl();
    public void connect(String host, String port);
    public void disconnect();

    // User login
    public boolean login(String username, String password);
    boolean newTicket(Ticket ticket);

    boolean newTicket(String title, String description, Integer priority, Status status, String assignee, String attachment);

    public boolean resetPassword(String username);
    public String getToken();

    User currentUser();

    // ticket

    ArrayList<HashMap<String, Object>> getTickets();

    ArrayList<HashMap<String, Object>> getTickets(int page);

    Boolean deleteTicket(String id);

    Boolean editTicket(Ticket ticket);


    ArrayList<HashMap<String, Object>> getUsers();
}