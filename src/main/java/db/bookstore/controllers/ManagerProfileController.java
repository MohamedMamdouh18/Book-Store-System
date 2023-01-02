package db.bookstore.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ManagerProfileController {

    @FXML
    private Button PromoteButton;

    @FXML
    private Button PromotionConfirmButton;

    @FXML
    private Pane PromotionPane;

    @FXML
    private Label firstNameWelcomeLabel;

    @FXML
    private Button homeReturningButton;

    @FXML
    private TextField promotionUserNameTextField;

    @FXML
    private Button signOutButton;

    @FXML
    void PromoteButtonOnAction(ActionEvent event) {
        PromotionPane.setVisible(true);
    }

    @FXML
    void PromotionConfirmOnAction(ActionEvent event) {

    }

    @FXML
    void homeReturningOnAction(ActionEvent event) {

    }

}
