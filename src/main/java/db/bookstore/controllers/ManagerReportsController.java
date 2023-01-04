package db.bookstore.controllers;

import Database.DAO.ManagerDAO;
import Database.Models.Book;
import Database.Models.Sale;
import Database.Models.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ManagerReportsController implements Initializable {
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
    private TableView<User> customerTabel;
    @FXML
    private TableColumn<User, String> address;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> firstName;
    @FXML
    private TableColumn<User, String> lastName;
    @FXML
    private TableColumn<User, String> phone;
    @FXML
    private TableColumn<User, String> username;


    @FXML
    private TableView<Sale> saleTable;
    @FXML
    private TableColumn<Sale, Integer> saleCount;
    @FXML
    private TableColumn<Sale, Date> saleDate;
    @FXML
    private TableColumn<Sale, Integer> saleID;
    @FXML
    private TableColumn<Sale, String> saleISBN;
    @FXML
    private TableColumn<Sale, String> saleUsername;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hideAllTables();
        setBookTable();
        setCustomerTable();
        setSaleTable();
    }

    void hideAllTables() {
        bookTable.setVisible(false);
        customerTabel.setVisible(false);
        saleTable.setVisible(false);
    }

    void setBookTable() {
        isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        publicationYear.setCellValueFactory(new PropertyValueFactory<>("publication_year"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        publisher.setCellValueFactory(new PropertyValueFactory<>("publisher_name"));
    }

    void setCustomerTable() {
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        email.setCellValueFactory(new PropertyValueFactory<>("email_address"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
    }

    void setSaleTable() {
        saleCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        saleDate.setCellValueFactory(new PropertyValueFactory<>("sale_date"));
        saleID.setCellValueFactory(new PropertyValueFactory<>("sale_id"));
        saleISBN.setCellValueFactory(new PropertyValueFactory<>("book_isbn"));
        saleUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
    }

    @FXML
    void topBooksOnAction(ActionEvent event) throws SQLException {
        hideAllTables();
        bookTable.setVisible(true);
        List<Book> ans = ManagerDAO.getInstance().reportTopTenBooks();
        bookTable.setItems(FXCollections.observableArrayList(ans));
    }

    @FXML
    void topCustomersOnAction(ActionEvent event) throws SQLException {
        hideAllTables();
        customerTabel.setVisible(true);
        List<User> ans = ManagerDAO.getInstance().reportTopFiveCustomers();
        customerTabel.setItems(FXCollections.observableArrayList(ans));
    }

    @FXML
    void totalSalesOnAction(ActionEvent event) throws SQLException {
        hideAllTables();
        saleTable.setVisible(true);
        List<Sale> ans = ManagerDAO.getInstance().reportLastMonthSales();
        saleTable.setItems(FXCollections.observableArrayList(ans));
    }

}
