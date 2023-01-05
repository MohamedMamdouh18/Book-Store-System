package db.bookstore.controllers;

import Database.DAO.CustomerDAO;
import db.bookstore.UserInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {

    @FXML
    private TextField emailAddress;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField passwordVisible;

    @FXML
    private PasswordField passwordHidden;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField shipping;

    @FXML
    private Label savedChanges;

    @FXML
    private Label error;

    @FXML
    private CheckBox checkBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emailAddress.setText(UserInfo.currentUser.getEmail_address());
        firstName.setText(UserInfo.currentUser.getFirst_name());
        lastName.setText(UserInfo.currentUser.getLast_name());
        passwordHidden.setText(UserInfo.currentUser.getPassword());
        phoneNumber.setText(UserInfo.currentUser.getPhone_number());
        shipping.setText(UserInfo.currentUser.getAddress());
        savedChanges.setVisible(false);
        error.setVisible(false);
    }

    @FXML
    void saveChangesOnAction(ActionEvent event) throws SQLException {
        try {
            var DAO = CustomerDAO.getInstance();
            var user = UserInfo.currentUser;
            DAO.changeUserProfile(user.getUsername(), "email_address", emailAddress.getText());
            DAO.changeUserProfile(user.getUsername(), "first_name", firstName.getText());
            DAO.changeUserProfile(user.getUsername(), "last_name", lastName.getText());
            DAO.changeUserProfile(user.getUsername(), "password", (checkBox.isSelected()) ? passwordVisible.getText() : passwordHidden.getText());
            DAO.changeUserProfile(user.getUsername(), "phone_number", phoneNumber.getText());
            DAO.changeUserProfile(user.getUsername(), "address", shipping.getText());
            savedChanges.setVisible(true);
            error.setVisible(false);
        } catch (Exception e) {
            error.setVisible(true);
            savedChanges.setVisible(false);
        }

    }

    @FXML
    void checkBoxOnAction(ActionEvent event) {
        if (checkBox.isSelected()) {
            passwordVisible.setText(passwordHidden.getText());
            passwordVisible.setVisible(true);
            passwordHidden.setVisible(false);
            return;
        }
        passwordHidden.setText(passwordVisible.getText());
        passwordHidden.setVisible(true);
        passwordVisible.setVisible(false);
    }
}
