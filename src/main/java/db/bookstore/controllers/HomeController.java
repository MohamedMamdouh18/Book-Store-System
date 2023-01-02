package db.bookstore.controllers;

import Database.DAO.CustomerDAO;
import Database.Models.Book;
import db.bookstore.UserInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    ObservableList<Book> books = FXCollections.observableArrayList(
            new Book("1", "1", Date.valueOf("2202-1-1"), (float) 10.00, "Horror", 10, 200, "khaled"),
            new Book("2", "2", Date.valueOf("2111-1-1"), (float) 30.00, "Comedy", 22, 444, "ali")
    );
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

        bookTable.setItems(books);
        bookDetails.setVisible(false);
    }

    @FXML
    void searchOnAction(ActionEvent event) throws IOException {
        Book book = bookTable.getSelectionModel().getSelectedItem();
        System.out.println(book.toString());
    }

    @FXML
    void tableSelectBook(MouseEvent event) throws IOException, SQLException {
        Book currentBook = bookTable.getSelectionModel().getSelectedItem();
        if (currentBook != null) {
            bookDetails.setVisible(true);
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
        UserInfo.userCart.add(currentBook);
    }

}
