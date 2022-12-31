package db.bookstore.controllers;

import Database.DAO.CustomerDAO;
import Database.Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignInController implements Initializable {

    @FXML
    private AnchorPane mainView;

    @FXML
    private Button signUpButton;

    @FXML
    private Button signInButton;

    @FXML
    private Label wrongCredentials;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wrongCredentials.setVisible(false);
    }

    @FXML
    void SignInOnAction(ActionEvent event) throws SQLException {
        CustomerDAO customerDAO = CustomerDAO.getCustomerDAO();
        User answer = customerDAO.signInUser(username.getText().trim());
        if (answer == null || !Objects.equals(answer.getPassword(), password.getText()))
            wrongCredentials.setVisible(true);

        else
            System.out.println("go in");
    }

    @FXML
    void SignUpOnAction(ActionEvent event) {

    }

}
