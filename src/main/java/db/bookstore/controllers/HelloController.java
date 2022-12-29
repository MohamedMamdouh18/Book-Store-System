package db.bookstore.controllers;

import Database.DAO.CustomerDAO;
import Database.DBConnector;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws SQLException {
        welcomeText.setText("Welcome to JavaFX Application!");
        CustomerDAO dao = new CustomerDAO(DBConnector.getCustomerConnection());
        dao.changeUserPassword("mamdouh", "111111");
    }
}