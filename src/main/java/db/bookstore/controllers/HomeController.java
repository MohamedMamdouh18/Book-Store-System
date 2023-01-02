package db.bookstore.controllers;

import Database.Models.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    ObservableList<Book> books = FXCollections.observableArrayList(
            new Book("1", "1", Date.valueOf("2202-1-1"), (float) 10.00, "Horror", 10, 200, "khaled")
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
    }

    @FXML
    void searchOnAction(ActionEvent event) throws IOException {
        Book book = bookTable.getSelectionModel().getSelectedItem();
        System.out.println(book.toString());
    }

}
