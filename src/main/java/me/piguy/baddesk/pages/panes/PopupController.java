package me.piguy.baddesk.pages.panes;

import me.piguy.baddesk.api.ApiAdapter;
import me.piguy.baddesk.models.Ticket;

public interface PopupController {
    void setTicket(Ticket ticket);

    void setApi(ApiAdapter api);
}
