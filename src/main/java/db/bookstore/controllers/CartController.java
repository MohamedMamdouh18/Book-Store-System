package db.bookstore.controllers;

import Database.DAO.AdminDAO;
import Database.DAO.CustomerDAO;
import Database.Models.Book;
import Database.Models.Sale;
import db.bookstore.UserInfo;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CartController implements Initializable {

    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> category;
    @FXML
    private TableColumn<Book, String> isbn;
    @FXML
    private TableColumn<Book, Float> price;
    @FXML
    private TableColumn<Book, Date> publicationYear;
    @FXML
    private TableColumn<Book, String> publisher;
    @FXML
    private TableColumn<Book, Integer> quantity;
    @FXML
    private TableColumn<Book, String> title;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField creditCardHolderTextField;
    @FXML
    private TextField cvvTextField;
    @FXML
    private DatePicker expiryDateTextField;
    @FXML
    private TextField idTextField;
    @FXML
    private Label bookAuthors;
    @FXML
    private Label bookCategory;
    @FXML
    private Label bookISBN;
    @FXML
    private Label bookPrice;
    @FXML
    private Label bookPublisher;
    @FXML
    private Label bookStock;
    @FXML
    private Label bookTitle;
    @FXML
    private Label bookYear;
    @FXML
    private AnchorPane bookDetails;
    @FXML
    private Label cartWarning;
    @FXML
    private Label cartAdding;
    @FXML
    private Label cartRemove;
    @FXML
    private Label totalPrice;
    @FXML
    private Label wrongCard;
    @FXML
    private Label successfulPurchase;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isbn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        publicationYear.setCellValueFactory(new PropertyValueFactory<Book, Date>("publication_year"));
        price.setCellValueFactory(new PropertyValueFactory<Book, Float>("price"));
        category.setCellValueFactory(new PropertyValueFactory<Book, String>("category"));
        quantity.setCellValueFactory(new PropertyValueFactory<Book, Integer>("stock"));
        publisher.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher_name"));

        bookTable.setItems(FXCollections.observableArrayList(UserInfo.userCart.getCartList()));
        bookDetails.setVisible(false);
        totalPrice.setText(UserInfo.userCart.getCartPrice().toString());
        hideLabels();
    }

    @FXML
    void confirmOrderOnAction(ActionEvent event) throws SQLException {
        hideLabels();
        if (Date.valueOf(LocalDate.now()).after(getDateFromDatePicker()))
            wrongCard.setVisible(true);
        else {
            var books = bookTable.getItems();
            for (int i = 0; i < books.size(); i++) {
                System.out.println(UserInfo.currentUser.getUsername());
                Sale sale = Sale.builder().username(UserInfo.currentUser.getUsername())
                        .sale_date(Date.valueOf(LocalDate.now())).book_isbn(books.get(i).getIsbn())
                        .count(books.get(i).getStock()).build();
                System.out.println(sale.toString());
                AdminDAO.getInstance().insertSale(sale);
            }

            successfulPurchase.setVisible(true);
            UserInfo.userCart.emptyCart();
            bookTable.setItems(FXCollections.observableArrayList(new ArrayList<>()));
            bookTable.refresh();
            bookDetails.setVisible(false);
            totalPrice.setText("0.0");
        }
    }

    @FXML
    void tableSelectBook(MouseEvent event) throws IOException, SQLException {
        Book currentBook = bookTable.getSelectionModel().getSelectedItem();
        if (currentBook != null) {
            bookDetails.setVisible(true);
            hideLabels();

            bookTitle.setText(currentBook.getTitle());
            bookISBN.setText(currentBook.getIsbn());
            bookCategory.setText(currentBook.getCategory());
            bookPrice.setText(currentBook.getPrice().toString());
            bookPublisher.setText(currentBook.getPublisher_name());
            bookYear.setText(currentBook.getPublication_year().toString());
            bookStock.setText(currentBook.getStock().toString());

            List<String> authors = CustomerDAO.getInstance().searchBookAuthors(currentBook.getIsbn());
            String authorsLabel = "";
            for (String author : authors)
                authorsLabel = authorsLabel + author + "\n";
            bookAuthors.setText(authorsLabel);
        }
    }

    java.sql.Date getDateFromDatePicker() {
        LocalDate localDate = expiryDateTextField.getValue();
        return Date.valueOf(localDate);
    }

    @FXML
    void addBook(ActionEvent event) {
        Book currentBook = bookTable.getSelectionModel().getSelectedItem();
        if (UserInfo.userCart.addBook(currentBook)) {
            hideLabels();
            cartAdding.setVisible(true);
            bookTable.setItems(FXCollections.observableArrayList(UserInfo.userCart.getCartList()));
            bookTable.refresh();
            totalPrice.setText(UserInfo.userCart.getCartPrice().toString());
        } else {
            hideLabels();
            cartWarning.setVisible(true);
        }
    }

    @FXML
    void removeBook(ActionEvent event) {
        hideLabels();
        cartRemove.setVisible(true);
        Book currentBook = bookTable.getSelectionModel().getSelectedItem();
        UserInfo.userCart.removeBook(currentBook);
        bookTable.setItems(FXCollections.observableArrayList(UserInfo.userCart.getCartList()));
        bookTable.refresh();
        totalPrice.setText(UserInfo.userCart.getCartPrice().toString());
    }

    void hideLabels() {
        cartRemove.setVisible(false);
        cartWarning.setVisible(false);
        cartAdding.setVisible(false);
        successfulPurchase.setVisible(false);
        wrongCard.setVisible(false);
    }
}
