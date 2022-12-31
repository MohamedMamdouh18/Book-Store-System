package db.bookstore.controllers;

import db.bookstore.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Label firstNameWelcomeLabel;

    @FXML
    private Button homeReturningButton;

    @FXML
    private AnchorPane mainPain;

    @FXML
    private Button signOutButton;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RoutingHandler.setMainView(this.mainPain);
        RoutingHandler.changeView(RoutingHandler.SignIn);
    }

    @FXML
    void homeReturningOnAction(ActionEvent event) {

    }

    public void changeMainView(String path) throws IOException {

    }
}