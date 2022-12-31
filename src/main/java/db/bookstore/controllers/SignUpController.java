package db.bookstore.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignUpController {
    @FXML
    private TextField userName;

    @FXML
    private TextField password;

    @FXML
    private TextField emailAddress;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;


    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField shipping;

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    @FXML
    void signInOnAction(ActionEvent event) throws IOException {
        RoutingHandler.changeView(RoutingHandler.SignIn);
    }

    @FXML
    void signUpOnAction(ActionEvent event) throws IOException {
        RoutingHandler.changeView(RoutingHandler.SignIn);
    }

}
