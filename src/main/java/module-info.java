module me.piguy.baddesk {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;


    opens me.piguy.baddesk to javafx.fxml;
    exports me.piguy.baddesk;
    exports me.piguy.baddesk.api;
}