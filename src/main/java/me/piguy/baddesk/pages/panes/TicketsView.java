package me.piguy.baddesk.pages.panes;

import atlantafx.base.theme.Styles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import me.piguy.baddesk.ConfigurationManager;
import me.piguy.baddesk.api.ApiAdapter;
import me.piguy.baddesk.models.Priority;
import me.piguy.baddesk.models.Ticket;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import me.piguy.baddesk.pages.panes.Status;

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
    @FXML
    private TextField searchField;

    private final ObservableList<Ticket> ticketsList = FXCollections.observableArrayList(new ArrayList<>());
    private ApiAdapter api;

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
        int end = Math.min(start + ITEMS_PER_PAGE, totalTickets);

        if (start < end && start >= 0) {
            ticketsTable.setItems(FXCollections.observableArrayList(ticketsList.subList(start, end)));
        }
    }

    private void loadTable() {
        int page = pageList.getCurrentPageIndex();
        this.loadTable(page);
    }

    private void loadData() {
        //loadExampleData();
        loadApiData();
    }

    private void loadApiData() {
        ArrayList<HashMap<String, Object>> tickets = api.getTickets();
        for (HashMap<String, Object> ticket : tickets) {
            Date date;
            if (!Objects.equals(ticket.get("due_date").toString(), "null")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSSSSS]");
                date = Date.from(
                        LocalDateTime.parse(ticket.get("due_date").toString(), formatter)
                                .atZone(ZoneId.of("Europe/Berlin")) // Complains without this
                                .toInstant()
                );
            } else {
                date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
            }
            ticketsList.add(new Ticket(
                    (String) ticket.get("id"),
                    (String) ticket.get("title"),
                    (String) ticket.get("description"),
                    Priority.values()[(Integer) ticket.get("priority")],
                    Status.valueOf((String) ticket.get("status")),
                    date,
                    (String) ticket.get("assignee"),
                    (String) ticket.get("attachment")
            ));
            pageListSetup();
            sortByPriority();
        }

    }

    private void loadExampleData() {
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

            ticketsList.add(new Ticket(
                    String.valueOf(i),
                    "Ticket #" + i,
                    "",
                    priority,
                    Status.Open,
                    Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    "Joe Dohn",
                    ""
            ));
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
        // Load api first
        api = ConfigurationManager.getInstance().api;

        loadCss();
        loadData();
        pageListSetup();
        sortByPriority();

        ContextMenu contextMenu = new ContextMenu();
        MenuItem editItem = new MenuItem("Edit");
        MenuItem deleteItem = new MenuItem("Delete");

        deleteItem.setOnAction(
                this::onDelete
        );
        editItem.setOnAction(
                this::onEdit
        );
        // Add product button
        addIncident.setOnAction(
                this::onAddIncident
        );

        contextMenu.getItems().addAll(editItem, deleteItem);


        ticketsTable.setRowFactory(t -> {
            TableRow<Ticket> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.SECONDARY) {
                    contextMenu.show(ticketsTable, event.getScreenX(), event.getScreenY());
                }
            });
            return row;
        });
    }

    private void onAddIncident(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("task-popup.fxml"));
        GridPane content;
        try {
            content = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PopupController controller = loader.getController();
        // Dont set ticket because thats for edit
//        controller.setTicket(ticketsTable.getFocusModel().getFocusedItem());
        controller.setApi(api);
        Stage popup = new Stage();
        Scene scene = new Scene(content, 400, 500);
        popup.setScene(scene);
        popup.setTitle("Help");
        popup.show();

        popup.setOnHidden(
                (e) -> {
                    refreshTable();
                }
        );
    }

    private void onEdit(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("task-popup.fxml"));
        GridPane content;
        try {
            content = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PopupController controller = loader.getController();
        // Dont set ticket because thats for edit
        controller.setTicket(ticketsTable.getFocusModel().getFocusedItem());
        controller.setApi(api);
        Stage popup = new Stage();
        Scene scene = new Scene(content, 400, 500);
        popup.setScene(scene);
        popup.setTitle("Help");
        popup.show();

        popup.setOnHidden(
                (e) -> {
                    refreshTable();
                }
        );
    }

    private void onDelete(ActionEvent e) {
        api.deleteTicket(ticketsTable.getFocusModel().getFocusedItem().id());
        refreshTable();
    }

    private void refreshTable() {
        ticketsList.clear();
        loadData();
        loadTable();
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

    public void searchTicket(KeyEvent keyEvent) {
        String key = searchField.getText();
        if (key.length() >= 3) {
            // Implement your ticket search logic here
            ObservableList<HashMap<String, Object>> searchResults = FXCollections.observableArrayList();

            for (HashMap<String, Object> ticket : api.getTickets()) {
                if (ticket.get("subject").toString().toLowerCase().contains(key.toLowerCase())) {
                    searchResults.add(ticket);
                }
            }

            // Update your table with search results
            ticketsTable.getItems().clear();
            ticketsTable.getItems().addAll(searchResults);
        }
    }
}
