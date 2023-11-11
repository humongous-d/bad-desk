package me.piguy.baddesk.api;

import me.piguy.baddesk.models.Ticket;
import me.piguy.baddesk.pages.panes.Status;

import java.io.IOException;
import java.util.ArrayList;
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
        Request loginRequest;
        Ctx loginResponse;
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
        if (loginResponse.getReturnCode() == 200) {
            this.setToken(String.valueOf(loginResponse.toMap().get("access_token")));
            return true;
        } else {
            return false;
        }
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
    public ArrayList<HashMap<String, Object>> getTickets() {
        Request request;
        Ctx response;

        try {
            request = new Request(this, "/tickets");
            response = request.Get()
                    .withAuth()
                    .Do()
                    .Listen();
        } catch (IOException e) {
            System.out.println("Failed to update ticket");
            return new ArrayList<>();
        }
        return response.toList();
    }

    @Override
    public ArrayList<HashMap<String, Object>> getTickets(int page) {
        Request request;
        Ctx response;

        try {
            request = new Request(this, "/tickets/split/" + page);
            response = request.Get()
                    .withAuth()
                    .Do()
                    .Listen();
        } catch (IOException e) {
            System.out.println("Failed to update ticket");
            return new ArrayList<>();
        }
        return response.toList();
    }

    @Override
    public Boolean deleteTicket(String id) {
        Request request;
        Ctx response;

        try {
            request = new Request(this, "/tickets/delete");
            response = request.Post()
                    .withAuth()
                    .AddQuery("id", id)
                    .Do()
                    .Listen();
        } catch (IOException e) {
            System.out.println("Failed to get tickets");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean editTicket(Ticket ticket) {
        // Create a request to make a ticket
        Request ticketRequest;
        Ctx ticketResponse;
        try {
            ticketRequest = new Request(this, "/tickets/update");
            ticketResponse = ticketRequest.Post()
                    .withAuth()
                    .AddQuery("id", ticket.id())
                    .DoWithJSON(new HashMap<>() {
                        {
                            put("title", ticket.subject());
                            put("description", ticket.description());
                            put("attachment", ticket.attachment());
                            put("assignee", ticket.asignedTo());
                            put("priority", ticket.priority().ordinal());
                            put("status", ticket.status().name());
                        }
                    })
                    .Listen();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean newTicket(String title, String description, Integer priority, Status status, String assignee, String attachment) {
        // Create a request to make a ticket
        Request ticketRequest;
        Ctx ticketResponse;
        try {
            ticketRequest = new Request(this, "/tickets/new");
            ticketResponse = ticketRequest.Post()
                    .withAuth()
                    .DoWithJSON(new HashMap<>() {
                        {
                            put("title", title);
                            put("description", description);
                            put("attachment", attachment);
                            put("assignee", assignee);
                            put("priority", priority);
                            put("status", status.name());
                        }
                    })
                    .Listen();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean newTicket(Ticket ticket) {
        return newTicket(
                ticket.subject(),
                ticket.description(),
                ticket.priority().ordinal(), // Jesus...
                ticket.status(),
                ticket.asignedTo(),
                ticket.attachment()

        );
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

    @Override
    public ArrayList<HashMap<String, Object>> searchTicket(String query) {
        Request request;
        Ctx response;

        try {
            request = new Request(this, "/tickets/search");
            response = request.Get()
                    .withAuth()
                    .AddQuery("query", query)
                    .Do()
                    .Listen();
        } catch (IOException e) {
            System.out.println("Failed to search tickets");
            e.printStackTrace();
            return new ArrayList<>();
        }
        return response.toList();
    }


}
