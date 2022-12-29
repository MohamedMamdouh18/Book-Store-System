package db.bookstore.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserProfileController {

    @FXML
    private TextField emailAddress;

    @FXML
    private TextField firstName;

    @FXML
    private Label firstNameWelcomeLabel;

    @FXML
    private Button homeReturningButton;

    @FXML
    private TextField lastName;

    @FXML
    private TextField password;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Button saveChangesButton;

    @FXML
    private TextField shipping;

    @FXML
    private Button signOutButton;

    @FXML
    private TextField userName;

    @FXML
    void homeReturningOnAction(ActionEvent event) {

    }

    @FXML
    void saveChangesOnAction(ActionEvent event) {

    }

}
