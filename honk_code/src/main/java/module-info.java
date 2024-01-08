module honk.honk_code {
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
	requires json.simple;
	requires junit;
	requires org.testng;
	
	opens honk.honk_code to javafx.fxml;
    exports honk.honk_code;
}