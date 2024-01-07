package me.piguy.baddesk.pages.panes;

import atlantafx.base.theme.Styles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import me.piguy.baddesk.ConfigurationManager;
import me.piguy.baddesk.api.ApiAdapter;
import me.piguy.baddesk.models.Priority;
import me.piguy.baddesk.models.Ticket;
import me.piguy.baddesk.models.User;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserView implements TabPaneViewController {
    @FXML
    private Button addUser;
    @FXML
    private TableView usersTable;

    private final ObservableList<User> usersList = FXCollections.observableArrayList(new ArrayList<>());

    private ApiAdapter api;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        api = ConfigurationManager.getInstance().api;
        addUser.getStyleClass().add(Styles.SUCCESS);

        loadApiData();

    }
    private void loadApiData() {
        ArrayList<HashMap<String, Object>> users = api.getUsers();
        for (HashMap<String, Object> user : users) {
            usersList.add(new User(
                    "N/A",
                    (String) user.get("username"),
                    (String) user.get("name"),
                    (String) user.get("role")
            ));
        }
        usersTable.setItems(usersList);
    }
}
