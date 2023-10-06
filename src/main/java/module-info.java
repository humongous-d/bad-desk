module me.piguy.baddesk {
    requires javafx.controls;
    requires javafx.fxml;


    opens me.piguy.baddesk to javafx.fxml;
    exports me.piguy.baddesk;
}