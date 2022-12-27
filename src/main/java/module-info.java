module db.bookstore {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens db.bookstore to javafx.fxml;
    exports db.bookstore;
    exports db.bookstore.controllers;
    opens db.bookstore.controllers to javafx.fxml;
}