package me.piguy.baddesk.router_me;

import me.piguy.baddesk.Main;

import java.io.IOException;

/**
 * Navigatable pages for <a href=#{@link}>{@link MeRouter}</a>
 */
public enum Page {

    // ENUM MEMBERS
    // EDIT THIS SECTION WHEN ADDING NEW PAGES

    Login("hello-view.fxml"),
    Dashboard("hello-new.fxml", "Dashboard");  // title will be "BadDesk | Dashboard"

    // ENUM LOGIC
    // NO NEED TO TOUCH THIS PART
    public final String fxml;
    public final String title;

    Page(String fxml) {
        this.fxml = fxml;
        this.title = "BadDesk";
        validateFxmlPath();
    }

    Page(String fxml, String title) {
        this.fxml = fxml;
        this.title = "BadDesk | " + title;
        validateFxmlPath();
    }

    private void validateFxmlPath() {
        if (Main.class.getResource(fxml) == null) {
            throw new IllegalArgumentException("File " + fxml + " does not exist in resources folder");
        }
    }

    public void navigate() throws IOException {
        MeRouter.router.load(this);
    }
}
