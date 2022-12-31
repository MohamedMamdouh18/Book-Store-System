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
        this.changeMainView();
    }

    @FXML
    void homeReturningOnAction(ActionEvent event) {

    }

    public void changeMainView() throws IOException {
        if(mainPain.getChildren().size() > 0)
            mainPain.getChildren().remove(0);
        Parent root ;
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("SignIn.fxml"));
        root = loader.load();
        mainPain.getChildren().add(root);
    }
}