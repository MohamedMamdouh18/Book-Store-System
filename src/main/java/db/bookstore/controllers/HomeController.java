package db.bookstore.controllers;

import Database.DAO.CustomerDAO;
import Database.Models.Book;
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
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    private final HashMap<String, String> comboBoxSelection = new HashMap<>();
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> isbn;
    @FXML
    private TableColumn<Book, String> title;
    @FXML
    private TableColumn<Book, Date> publicationYear;
    @FXML
    private TableColumn<Book, Float> price;
    @FXML
    private TableColumn<Book, String> category;
    @FXML
    private TableColumn<Book, Integer> stock;
    @FXML
    private TableColumn<Book, String> publisher;
    @FXML
    private TextField searchValue;
    @FXML
    private ComboBox<String> searchAttribute;
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

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isbn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        publicationYear.setCellValueFactory(new PropertyValueFactory<Book, Date>("publication_year"));
        price.setCellValueFactory(new PropertyValueFactory<Book, Float>("price"));
        category.setCellValueFactory(new PropertyValueFactory<Book, String>("category"));
        stock.setCellValueFactory(new PropertyValueFactory<Book, Integer>("stock"));
        publisher.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher_name"));

        searchAttribute.setItems(FXCollections.observableArrayList("ISBN", "Title", "Publication Year", "Price", "Category", "Stock", "Publisher"));
        comboBoxSelection.put("ISBN", "isbn");
        comboBoxSelection.put("Title", "title");
        comboBoxSelection.put("Publication Year", "publication_year");
        comboBoxSelection.put("Price", "price");
        comboBoxSelection.put("Category", "category");
        comboBoxSelection.put("Stock", "stock");
        comboBoxSelection.put("Publisher", "publisher_name");

        List<Book> books = CustomerDAO.getInstance().getBooks();
        bookTable.setItems(FXCollections.observableArrayList(books));
        bookDetails.setVisible(false);
        cartWarning.setVisible(false);
        cartAdding.setVisible(false);
    }

    @FXML
    void searchOnAction(ActionEvent event) throws IOException, SQLException {
        bookDetails.setVisible(false);

        String attribute = comboBoxSelection.get(searchAttribute.getValue());
        String value = searchValue.getText();
        List<Book> ans = CustomerDAO.getInstance().searchBookByAttribute(attribute, value);
        bookTable.setItems(FXCollections.observableArrayList(ans));
    }

    @FXML
    void tableSelectBook(MouseEvent event) throws IOException, SQLException {
        Book currentBook = bookTable.getSelectionModel().getSelectedItem();
        if (currentBook != null) {
            bookDetails.setVisible(true);
            cartWarning.setVisible(false);
            cartAdding.setVisible(false);

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

    @FXML
    void addCartOnAction(ActionEvent event) {
        Book currentBook = bookTable.getSelectionModel().getSelectedItem();
        if (!UserInfo.userCart.addBook(currentBook)) {
            cartWarning.setVisible(true);
            cartAdding.setVisible(false);
        } else {
            cartWarning.setVisible(false);
            cartAdding.setVisible(true);
        }
    }

}
