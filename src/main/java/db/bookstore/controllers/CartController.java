package db.bookstore.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class CartController {

    @FXML
    private TableView<?> bookTable;

    @FXML
    private Button cancelButton;

    @FXML
    private TableColumn<?, ?> category;

    @FXML
    private TextField creditCardHolderTextField;

    @FXML
    private Button creditConfirmButton;

    @FXML
    private Pane creditPane;

    @FXML
    private TextField cvvTextField;

    @FXML
    private TextField expiryDateTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private TableColumn<?, ?> isbn;

    @FXML
    private TableColumn<?, ?> price;

    @FXML
    private TableColumn<?, ?> publicationYear;

    @FXML
    private TableColumn<?, ?> publisher;

    @FXML
    private TableColumn<?, ?> quantity;

    @FXML
    private TableColumn<?, ?> title;

    @FXML
    void cancelButtonOnAction(ActionEvent event) {

    }

    @FXML
    void creditConfirmButtonOnAction(ActionEvent event) {

    }

}
