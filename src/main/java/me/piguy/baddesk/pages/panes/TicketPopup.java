package me.piguy.baddesk.pages.panes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import me.piguy.baddesk.api.ApiAdapter;
import me.piguy.baddesk.models.Priority;
import me.piguy.baddesk.models.Ticket;

import java.time.ZoneId;
import java.util.Date;

public class TicketPopup implements PopupController {
    @FXML
    public ChoiceBox<Status> statusChoiceBox;
    @FXML
    public ObservableList<Status> status = FXCollections.observableArrayList(Status.class.getEnumConstants());
    @FXML
    private TextField subjectField;
    @FXML
    private TextField descField;

    @FXML
    private ChoiceBox<Priority> priorityChoiceBox;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;
    private ObservableList<Priority> priorities = FXCollections.observableArrayList(Priority.class.getEnumConstants());

    private Ticket ticket;
    private ApiAdapter api;

    public void initialize() {
        // Initialize the ChoiceBoxes with enum values
        priorityChoiceBox.getItems().setAll(priorities);
        statusChoiceBox.getItems().setAll(status);

        // Check if editing an existing task or creating a new one
        if (ticket == null) {
            // Set default values for a new task
            priorityChoiceBox.setValue(Priority.Low);
            statusChoiceBox.setValue(Status.Open);
        } else {
            // Populate fields for editing an existing task
            populateFields();
        }
    }
    @Override
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
        populateFields();
    }

    @Override
    public void setApi(ApiAdapter api) {
        this.api = api;
    }

    private void populateFields() {
        subjectField.setText(ticket.subject());
        descField.setText(ticket.description());
        priorityChoiceBox.setValue(ticket.priority());
        statusChoiceBox.setValue(ticket.status());

        dueDatePicker.setValue(ticket.dueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    @FXML
    private void saveChanges() {
        if (ticket == null) {
            // Creating a new task
            ticket = new Ticket(
                    "null",
                    subjectField.getText(),
                    descField.getText(),
                    priorityChoiceBox.getValue(),
                    statusChoiceBox.getValue(),
                    Date.from(dueDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    "Assignee",
                    ""
            );

            api.newTicket(ticket);
        } else {
            // Editing an existing task
            ticket = new Ticket(
                    ticket.id(),
                    subjectField.getText(),
                    descField.getText(),
                    priorityChoiceBox.getValue(),
                    statusChoiceBox.getValue(),
                    Date.from(dueDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    ticket.asignedTo(),
                    ""
            );

            api.editTicket(ticket);
        }

        closeStage();
    }

    @FXML
    private void cancelEdit() {
        closeStage();
    }

    private void closeStage() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

}
