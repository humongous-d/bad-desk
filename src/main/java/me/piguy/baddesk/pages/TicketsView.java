package me.piguy.baddesk.pages;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import me.piguy.baddesk.models.Ticket;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TicketsView implements TabPaneViewController {

    @FXML
    private TableView<Ticket> ticketsTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i <= 25; i++) {
            tickets.add(new Ticket(i, "Ticket #" + i));
        }
        ticketsTable.setItems(FXCollections.observableArrayList(tickets));
    }
}
