package db.bookstore.controllers;

import Database.DAO.ManagerDAO;
import Database.Models.Book;
import Database.Models.Order;
import db.bookstore.UserInfo;
import db.bookstore.Utils.Helpers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
    private Label placeOrderPrompt;

    @FXML
    private Button homeReturningButton;

    @FXML
    private TextField promotionUserNameTextField;

    @FXML
    private TextField placeOrderISBNField;

    @FXML
    private Spinner<Integer> placeOrderCountSpinner;

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
    private Label promotePrompt;

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


    public ManagerProfileController() {
       
    }

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
        promotePrompt.setVisible(false);
        placeOrderPrompt.setVisible(false);

    }

    @FXML
    void PromoteButtonOnAction(ActionEvent event) {
        setAllWarningsInvisible();
        setAllInvisible();
        PromotionPane.setVisible(true);
    }

    @FXML
    void PlaceOrderButtonOnAction(ActionEvent event) {
        placeOrderCountSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1));
        setAllWarningsInvisible();
        setAllInvisible();
        PlaceOrderPane.setVisible(true);
    }

    @FXML
    void AddBookButtonOnAction(ActionEvent event) {
        setAllWarningsInvisible();
        setAllInvisible();
        AddBookPane.setVisible(true);
    }

    @FXML
    void ModifyBookButtonOnAction(ActionEvent event) {
        setAllWarningsInvisible();
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
        } else if (PublicationDatePicker.getValue() == null) {
            warning.setText("Publication Date should be specified.");
            warning.setVisible(true);
        } else {
            warning.setText("Please make sure all fields are filled.");
            ManagerDAO managerDAO = ManagerDAO.getInstance();
            setAllWarningsInvisible();

            Book book = Book.builder().title(titleField.getText())
                    .isbn(ISBNField.getText())
                    .publisher_name(publisherField  .getText())
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
        if (promotionUserNameTextField.getText().isEmpty()) {
            promotePrompt.setText("Enter username to promote");
            promotePrompt.setTextFill(Color.RED);
            promotePrompt.setVisible(true);
        } else {
            ManagerDAO managerDAO = ManagerDAO.getInstance();
            try {
                String username = promotionUserNameTextField.getText();
                managerDAO.promoteCutomer(username);
                promotePrompt.setText(username + " is promoted");
                promotePrompt.setTextFill(Color.GREEN);
                promotePrompt.setVisible(true);
            } catch (Exception e) {
                promotePrompt.setTextFill(Color.RED);
                promotePrompt.setText("An error has occured");
                promotePrompt.setVisible(true);

            }
        }
    }

    @FXML
    void placeOrderConfirmOnAction(ActionEvent event) {
        if(placeOrderISBNField.getText().isEmpty()) {
            placeOrderPrompt.setText("Enter Book ISBN to place an order");
            placeOrderPrompt.setTextFill(Color.RED);
            placeOrderPrompt.setVisible(true);
        }
        else {
            ManagerDAO managerDAO = ManagerDAO.getInstance();
            try {
                managerDAO.placeOrder(Order.builder()
                        .username(UserInfo.currentUser.getUsername())
                        .book_isbn(placeOrderISBNField.getText())
                        .count(placeOrderCountSpinner.getValue())
                        .order_date(Date.valueOf(LocalDate.now())).build());

                placeOrderPrompt.setText("The order is placed successfully");
                placeOrderPrompt.setTextFill(Color.GREEN);
                placeOrderPrompt.setVisible(true);
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
                placeOrderPrompt.setText("An error has occured");
                placeOrderPrompt.setTextFill(Color.RED);
                placeOrderPrompt.setVisible(true);
            }
        }
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

    void clearAddBookFields() {
        titleField.clear();
        ISBNField.clear();
        publisherField.clear();
        categoryField.clear();
        priceField.clear();
        quantityField.clear();
        authors.clear();
    }

    java.sql.Date getDateFromDatePicker() {
        LocalDate localDate = PublicationDatePicker.getValue();
        return Date.valueOf(localDate);
    }

}