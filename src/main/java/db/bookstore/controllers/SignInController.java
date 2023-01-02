package db.bookstore.controllers;

import Database.DAO.AdminDAO;
import Database.Models.User;
import db.bookstore.UserInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignInController implements Initializable {

    @FXML
    private Label wrongCredentials;

    @FXML
    private TextField passwordVisible;

    @FXML
    private PasswordField passwordHidden;

    @FXML
    private TextField username;

    @FXML
    private CheckBox checkBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wrongCredentials.setVisible(false);
    }

    @FXML
    void SignInOnAction(ActionEvent event) throws SQLException, IOException {
        AdminDAO adminDAO = AdminDAO.getInstance();
        User answer = adminDAO.signInUser(username.getText().trim());
        if (answer == null || !Objects.equals(answer.getPassword(), (checkBox.isSelected()) ? passwordVisible.getText() : passwordHidden.getText()))
            wrongCredentials.setVisible(true);
        else {
            RoutingHandler.changeView(RoutingHandler.Home);
            UserInfo.currentUser = answer;
            UserInfo.userCart = new ArrayList<>();
            RoutingHandler.notifySigning();
        }
    }

    @FXML
    void SignUpOnAction(ActionEvent event) throws IOException {
        RoutingHandler.changeView(RoutingHandler.SignUp);
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
