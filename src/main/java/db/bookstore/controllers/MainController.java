package db.bookstore.controllers;

import db.bookstore.UserInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Label firstNameWelcomeLabel;

    @FXML
    private AnchorPane mainPain;

    @FXML
    private Pane pagesPane;

    @FXML
    private Button managerButton;

    @FXML
    private Button managerReports;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pagesPane.setVisible(false);
        RoutingHandler.setMainView(this.mainPain, this);
        RoutingHandler.changeView(RoutingHandler.SignIn);
    }

    void notifyBar() {
        pagesPane.setVisible(!pagesPane.isVisible());
        firstNameWelcomeLabel.setText("Hello, " + UserInfo.currentUser.getFirst_name());
        managerButton.setVisible(UserInfo.currentUser.getRole().equalsIgnoreCase("manager"));
        managerReports.setVisible(UserInfo.currentUser.getRole().equalsIgnoreCase("manager"));
    }

    @FXML
    void homeReturningOnAction(ActionEvent event) throws IOException {
        RoutingHandler.changeView(RoutingHandler.Home);
    }

    @FXML
    void signOutOnAction(ActionEvent event) throws IOException {
        RoutingHandler.notifySigning();
        RoutingHandler.changeView(RoutingHandler.SignIn);
    }

    @FXML
    void profileOnAction(ActionEvent event) throws IOException {
        RoutingHandler.changeView(RoutingHandler.UserProfile);
    }

    @FXML
    void managerActionOnAction(ActionEvent event) throws IOException {
        RoutingHandler.changeView(RoutingHandler.ManagerProfile);
    }

    @FXML
    void cartOnAction(ActionEvent event) throws IOException {
        RoutingHandler.changeView(RoutingHandler.Cart);
    }

    @FXML
    void managerReportsOnAction(ActionEvent event) throws IOException {
        RoutingHandler.changeView(RoutingHandler.ManagerReports);
    }
}