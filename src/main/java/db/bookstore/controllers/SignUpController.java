package db.bookstore.controllers;

import Database.DAO.AdminDAO;
import Database.Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private TextField userName;

    @FXML
    private PasswordField passwordHidden;

    @FXML
    private TextField passwordVisible;

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
    private Label userExists;

    @FXML
    private CheckBox checkBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userExists.setVisible(false);
    }

    @FXML
    void signInOnAction(ActionEvent event) throws IOException {
        RoutingHandler.changeView(RoutingHandler.SignIn);
    }

    @FXML
    void signUpOnAction(ActionEvent event) throws IOException, SQLException {
        AdminDAO adminDAO = AdminDAO.getInstance();

        User user = User.builder().username(userName.getText().trim())
                .address(shipping.getText().trim())
                .email_address(emailAddress.getText().trim())
                .first_name(firstName.getText().trim())
                .last_name(lastName.getText().trim())
                .password((checkBox.isSelected()) ? passwordVisible.getText() : passwordHidden.getText())
                .phone_number(phoneNumber.getText().trim())
                .role("customer").build();

        if (adminDAO.signInUser(user.getUsername()) != null)
            userExists.setVisible(true);
        else {
            adminDAO.signUpUser(user);
            RoutingHandler.changeView(RoutingHandler.SignIn);
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
