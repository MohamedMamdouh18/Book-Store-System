package db.bookstore.controllers;

import Database.DAO.CustomerDAO;
import Database.Models.Book;
import db.bookstore.UserInfo;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

public class CartController implements Initializable {

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private Button cancelButton;

    @FXML
    private TableColumn<Book, String> category;

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

        Map < Book , Integer > map = new HashMap<>();
        for (Book book : UserInfo.userCart){
            if (!map.containsKey(book)){
                map.put(book , 1);
            }else{
                map.put(book , map.get(book) + 1);
            }
        }
        List < Book > books = new ArrayList<>();

    }

    @FXML
    void cancelButtonOnAction(ActionEvent event) {

    }

    @FXML
    void creditConfirmButtonOnAction(ActionEvent event) {

    }

}
