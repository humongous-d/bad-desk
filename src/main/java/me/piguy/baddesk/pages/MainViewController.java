package me.piguy.baddesk.pages;

import atlantafx.base.theme.NordDark;
import atlantafx.base.theme.NordLight;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import me.piguy.baddesk.ConfigurationManager;
import me.piguy.baddesk.pages.theme.PageTheme;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainViewController implements ViewController {
    private ConfigurationManager config;

    @FXML
    private ChoiceBox<String> themeSelector;
    @FXML
    private CheckBox darkModeToggle;
    @FXML
    private AnchorPane dashboardPane;
    @FXML
    private AnchorPane ticketPane;
    @FXML
    private AnchorPane userPane;

    @FXML
    private void toggleDarkMode() {
        changeTheme();
    }

    private void attachPane(String paneFxml, AnchorPane destinationPane) throws IOException {
        AnchorPane dashboardFXML = FXMLLoader.load(Objects.requireNonNull(ViewController.class.getResource(paneFxml)));
        destinationPane.getChildren().setAll(dashboardFXML.getChildren());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.config = ConfigurationManager.getInstance();


        HashMap<String, PageTheme> pageThemes = config.getThemes();

        boolean firstTheme = true;
        for (PageTheme theme : pageThemes.values()) {
            String name = theme.getThemeName();
            themeSelector.getItems().add(name);
            if (firstTheme) {
                firstTheme = false;
            }
        }



        try {
            attachPane("dashboard-view.fxml", dashboardPane);
            attachPane("tickets-view.fxml", ticketPane);
            attachPane("user-view.fxml", userPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void changeTheme() {
        PageTheme theme = config.getTheme(themeSelector.getValue());
        themeSelector.getScene().setUserAgentStylesheet(theme.getThemeDark().getUserAgentStylesheet());

        if (darkModeToggle.isSelected()) {
            themeSelector.getScene().setUserAgentStylesheet(theme.getThemeDark().getUserAgentStylesheet());
        } else {
            darkModeToggle.getScene().setUserAgentStylesheet(theme.getThemeLight().getUserAgentStylesheet());
        }

        darkModeToggle.setDisable(!theme.hasVariation());
    }
}
