module com.javafx.HONK {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens honk to javafx.fxml;
    exports honk;

    exports honk.view;
    opens honk.view to javafx.fxml;

    exports honk.controller;
    opens honk.controller to javafx.fxml;

    exports honk.model;
    opens honk.model to javafx.fxml;

}
