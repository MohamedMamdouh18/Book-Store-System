package db.bookstore.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ProfileController {

    @FXML
    private TextField emailAddressTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private Button saveChangesButton;

    @FXML
    private TextField shippingAddressTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    void saveChangesOnAction(ActionEvent event) {

    }

}
