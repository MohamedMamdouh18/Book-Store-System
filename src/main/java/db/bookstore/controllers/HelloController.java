package db.bookstore.controllers;

import Database.DAO.ManagerDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws SQLException {
        welcomeText.setText("Welcome to JavaFX Application!");
        ManagerDAO dao = new ManagerDAO();
        dao.changeUserProfile("mamdouh", "first_name", "mamdouh");
    }
}