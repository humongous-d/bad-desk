package me.piguy.baddesk.pages;

import atlantafx.base.theme.Styles;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import me.piguy.baddesk.models.Ticket;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TicketsView implements TabPaneViewController {

    @FXML
    private Button addIncident;
    @FXML
    private TableView<Ticket> ticketsTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i <= 25; i++) {
            tickets.add(new Ticket(i, "Ticket #" + i));
        }
        ticketsTable.setItems(FXCollections.observableArrayList(tickets));

        addIncident.getStyleClass().add(Styles.SUCCESS);
    }
}
