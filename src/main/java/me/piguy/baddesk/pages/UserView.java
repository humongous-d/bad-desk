package me.piguy.baddesk.pages;

import atlantafx.base.theme.Styles;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class UserView implements TabPaneViewController {
    @FXML
    private Button addUser;
    @FXML
    private TableView ticketsTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addUser.getStyleClass().add(Styles.SUCCESS);
    }
}
