package me.piguy.baddesk.models;

import javafx.fxml.FXML;

public record Ticket(int id, String subject) {
    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }
}
