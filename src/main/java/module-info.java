module me.piguy.baddesk {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;
    requires org.json;

    // UI Imports
    requires atlantafx.base;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.material2;
    requires org.kordamp.ikonli.feather;

    opens me.piguy.baddesk to javafx.fxml;
    opens me.piguy.baddesk.pages to javafx.fxml;
    opens me.piguy.baddesk.pages.panes to javafx.fxml;
    opens me.piguy.baddesk.models to javafx.base;
    opens me.piguy.baddesk.utils to javafx.base, javafx.fxml;

    exports me.piguy.baddesk;
    exports me.piguy.baddesk.api;
    exports me.piguy.baddesk.pages;
    exports me.piguy.baddesk.pages.theme;
    exports me.piguy.baddesk.pages.panes;
}