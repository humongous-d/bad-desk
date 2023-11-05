package me.piguy.baddesk.pages.panes;

import atlantafx.base.theme.Styles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import me.piguy.baddesk.models.Ticket;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static me.piguy.baddesk.ConfigurationManager.ITEMS_PER_PAGE;

public class TicketsView implements TabPaneViewController {
    @FXML
    private Pagination pageList;
    @FXML
    private Button addIncident;
    @FXML
    private TableView<Ticket> ticketsTable;

    private final ObservableList<Ticket> ticketsList = FXCollections.observableArrayList(new ArrayList<>());

    private void loadTablePages() {
        int totalTickets = ticketsList.size();

        pageList.setPageCount((int) Math.ceil((double) totalTickets / ITEMS_PER_PAGE));
        pageList.setPageFactory(page -> {
            int start = page * ITEMS_PER_PAGE;
            int end = Math.min(start + ITEMS_PER_PAGE + 1, totalTickets);

            if (start < end && start >= 0) {
                System.out.println(end);
                ticketsTable.setItems(FXCollections.observableArrayList(ticketsList.subList(start, end)));
            }

            return new StackPane();
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int i = 0; i <= 25; i++) {
            ticketsList.add(new Ticket(i, "Ticket #" + i));
        }

        addIncident.getStyleClass().add(Styles.SUCCESS);

        loadTablePages();
    }
}
