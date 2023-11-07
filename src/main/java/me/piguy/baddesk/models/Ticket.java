package me.piguy.baddesk.models;

import java.util.Date;

public record Ticket(int id, String subject, Priority priority, Date dueDate, String asignedTo) {
}
