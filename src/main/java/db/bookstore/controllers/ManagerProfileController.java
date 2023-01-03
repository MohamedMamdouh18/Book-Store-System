package db.bookstore.controllers;

import Database.DAO.ManagerDAO;
import Database.Models.Book;
import db.bookstore.Utils.Helpers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ManagerProfileController {

    @FXML
    private Button PromoteButton;

    @FXML
    private Button PromotionConfirmButton;

    @FXML
    private Pane PromotionPane;

    @FXML
    private Pane PlaceOrderPane;
    @FXML
    private Pane AddBookPane;
    @FXML
    private Pane ModifyBookPane;


    @FXML
    private Label firstNameWelcomeLabel;

    @FXML
    private Button homeReturningButton;

    @FXML
    private TextField promotionUserNameTextField;

    @FXML
    private Button signOutButton;

    @FXML
    private TextField quantityField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField publisherField;
    @FXML
    private TextField ISBNField;

    @FXML
    private TextField titleField;
    @FXML
    private TextField AuthorField;

    @FXML
    private Label warning;
    @FXML
    private Label warningPrice;
    @FXML
    private Label warningQuantity;

    @FXML
    private Label warningAuthor;

    @FXML
    private DatePicker PublicationDatePicker;

    List<String> authors = new ArrayList<>();
    int min_stock = 2;

    void setAllInvisible() {
        PromotionPane.setVisible(false);
        PlaceOrderPane.setVisible(false);
        AddBookPane.setVisible(false);
        ModifyBookPane.setVisible(false);
    }

    void setAllWarningsInvisible() {
        warningAuthor.setVisible(false);
        warningPrice.setVisible(false);
        warningQuantity.setVisible(false);
        warning.setVisible(false);
    }

    @FXML
    void PromoteButtonOnAction(ActionEvent event) {
        setAllInvisible();
        PromotionPane.setVisible(true);
    }

    @FXML
    void PlaceOrderButtonOnAction(ActionEvent event) {
        setAllInvisible();
        PlaceOrderPane.setVisible(true);
    }

    @FXML
    void AddBookButtonOnAction(ActionEvent event) {
        setAllInvisible();
        AddBookPane.setVisible(true);
    }

    @FXML
    void ModifyBookButtonOnAction(ActionEvent event) {
        setAllInvisible();
        ModifyBookPane.setVisible(true);
    }


    @FXML
    void confirmAddBook(ActionEvent event) throws SQLException {
        if (titleField.getText().isEmpty() ||
                ISBNField.getText().isEmpty() || categoryField.getText().isEmpty()
                || publisherField.getText().isEmpty() || titleField.getText().isEmpty()
                || priceField.getText().isEmpty() || quantityField.getText().isEmpty() || authors.isEmpty()
                || PublicationDatePicker.getValue() == null) {
            setAllWarningsInvisible();
            warning.setVisible(true);
        } else if (!Helpers.isNumeric(quantityField.getText())) {
            setAllWarningsInvisible();
            warningQuantity.setVisible(true);
        } else if (!Helpers.isNumeric(priceField.getText())) {
            setAllWarningsInvisible();
            warningPrice.setVisible(true);
        }else if(PublicationDatePicker.getValue() == null){
            warning.setText("Publication Date should be specified.");
            warning.setVisible(true);
        }else {
            warning.setText("Please make sure all fields are filled.");
            ManagerDAO managerDAO = ManagerDAO.getInstance();
            setAllWarningsInvisible();

            Book book = Book.builder().title(titleField.getText())
                    .isbn(ISBNField.getText())
                    .publisher_name(publisherField.getText())
                    .category(categoryField.getText())
                    .publication_year(getDateFromDatePicker())
                    .stock(Integer.parseInt(quantityField.getText()))
                    .price(Float.parseFloat(priceField.getText()))
                    .minimum_stock(min_stock).build();
            managerDAO.addBook(book);
            managerDAO.addAuthors(book.getIsbn(), authors);

            clearAddBookFields();
        }
    }

    @FXML
    void PromotionConfirmOnAction(ActionEvent event) {

    }

    @FXML
    void homeReturningOnAction(ActionEvent event) {

    }

    @FXML
    void addAuthor(ActionEvent event) {
        if (AuthorField.getText().isEmpty()) {
            setAllWarningsInvisible();
            warningAuthor.setVisible(true);
        } else {
            setAllWarningsInvisible();
            warningAuthor.setVisible(false);
            authors.add(AuthorField.getText());
            AuthorField.clear();
        }
    }

    void clearAddBookFields(){
        titleField.clear();
        ISBNField.clear();
        publisherField.clear();
        categoryField.clear();
        priceField.clear();
        quantityField.clear();
        authors.clear();
    }

    java.sql.Date getDateFromDatePicker(){
        LocalDate localDate = PublicationDatePicker.getValue();
        return Date.valueOf(localDate);
    }


}