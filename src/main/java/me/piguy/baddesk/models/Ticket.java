package me.piguy.baddesk.models;

import me.piguy.baddesk.pages.panes.Status;

import java.util.Date;

public record Ticket(String id, String subject, String description, Priority priority, Status status, Date dueDate, String asignedTo, String attachment) {}