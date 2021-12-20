module com.example.financial {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com.financial.controller to javafx.fxml;
    exports com.financial.controller;
    exports com.financial.controller.gui;
    opens com.financial.controller.gui to javafx.fxml;
    exports com.financial.controller.login;
    opens com.financial.controller.login to javafx.fxml;
}