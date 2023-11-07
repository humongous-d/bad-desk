package me.piguy.baddesk.pages.panes;

import atlantafx.base.theme.Styles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import me.piguy.baddesk.models.Priority;
import me.piguy.baddesk.models.Ticket;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import static me.piguy.baddesk.ConfigurationManager.ITEMS_PER_PAGE;

public class TicketsView implements TabPaneViewController {
    @FXML
    private Button sortDate;
    @FXML
    private Button sortPriority;
    @FXML
    private Pagination pageList;
    @FXML
    private Button addIncident;
    @FXML
    private TableView<Ticket> ticketsTable;

    private final ObservableList<Ticket> ticketsList = FXCollections.observableArrayList(new ArrayList<>());

    private int priorityComparator(Ticket oldValue, Ticket newValue) {
        return Priority.compare(oldValue.priority(), newValue.priority());
    }

    private int dateComparator(Ticket oldValue, Ticket newValue) {
        return oldValue.dueDate().compareTo(newValue.dueDate());
    }

    private void pageListSetup() {
        int totalTickets = ticketsList.size();

        pageList.setPageCount((int) Math.ceil((double) totalTickets / ITEMS_PER_PAGE));
        pageList.setPageFactory(page -> {
            if (ticketsList.isEmpty()) return new StackPane();
            loadTable(page);
            return new StackPane();
        });
    }

    private void loadTable(int page) {
        int totalTickets = ticketsList.size();

        int start = page * ITEMS_PER_PAGE;
        int end = Math.min(start + ITEMS_PER_PAGE + /* index offset */ 1, totalTickets);

        if (start < end && start >= 0) {
            ticketsTable.setItems(FXCollections.observableArrayList(ticketsList.subList(start, end)));
        }
    }

    private void loadTable() {
        int page = pageList.getCurrentPageIndex();
        this.loadTable(page);
    }

    private void loadSampleData() {
        LocalDate date = LocalDate.now();
        for (int i = 0; i <= 65; i++) {
            Priority priority;

            if (i % 10 == 0) {
                priority = Priority.Low;
            } else if (i % 2 == 0) {
                priority = Priority.Medium;
            } else if (i % 3 == 0) {
                priority = Priority.High;
            } else if (i % 13 == 0) {
                priority = Priority.Critical;
            } else {
                priority = Priority.Low;
            }

            ticketsList.add(new Ticket(i, "Ticket #" + i, priority,
                    Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()), "Joe Dohn"));
            date = date.plusDays(1);
        }
    }

    private void loadCss() {
        addIncident.getStyleClass().add(Styles.SUCCESS);
        sortDate.getStyleClass().addAll(Styles.SMALL, Styles.ROUNDED, Styles.ACCENT, Styles.BUTTON_OUTLINED);
        sortPriority.getStyleClass().addAll(Styles.SMALL, Styles.ROUNDED, Styles.ACCENT, Styles.BUTTON_OUTLINED);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCss();
        loadSampleData();
        pageListSetup();
    }

    @FXML
    private void sortByPriority() {
        ticketsList.sort(this::priorityComparator);
        loadTable();
    }

    @FXML
    private void sortByDueDate() {
        ticketsList.sort(this::dateComparator);
        loadTable();
    }
}
