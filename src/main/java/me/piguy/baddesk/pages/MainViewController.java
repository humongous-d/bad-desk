package me.piguy.baddesk.pages;

import atlantafx.base.theme.NordDark;
import atlantafx.base.theme.NordLight;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class MainViewController implements ViewController {
    @FXML
    CheckBox darkModeToggle;

    public void toggleDarkMode() {
        if (darkModeToggle.isSelected()) {
            darkModeToggle.getScene().setUserAgentStylesheet(new NordDark().getUserAgentStylesheet());
        } else {
            darkModeToggle.getScene().setUserAgentStylesheet(new NordLight().getUserAgentStylesheet());
        }
    }
}
