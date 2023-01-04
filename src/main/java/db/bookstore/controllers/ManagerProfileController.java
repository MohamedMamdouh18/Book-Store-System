package db.bookstore.controllers;

import Database.DAO.ManagerDAO;
import Database.Models.Book;
import Database.Models.Order;
import db.bookstore.UserInfo;
import db.bookstore.Utils.Helpers;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
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

    private String[] CategoryArray = { "History", "Science", "Geography", "Religion", "Art" };
    private Book modifyBook = null;

    private Order confirmOrder = null;

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
    private Pane modifyBookPane;

    @FXML
    private Pane findBookPane;

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
    private Spinner<Integer> quantitySpinner;
    @FXML
    private Spinner<Integer> minimumQuantitySpinner;

    @FXML
    private TextField priceField;
    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private ListView<String> authorsListView;
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

    @FXML
    private TextField findBookISBNField;

    @FXML
    private Label findBookPrompt;

    @FXML
    private Button findBookButton;

    @FXML
    private ToggleButton modifyBookTitleEditButton;

    @FXML
    private Button modifyBookTitleResetButton;

    @FXML
    private TextField modifyBookTitleField;

    @FXML
    private ToggleButton modifyBookPubEditButton;

    @FXML
    private Button modifyBookPubResetButton;

    @FXML
    private TextField modifyBookPubField;

    @FXML
    private ToggleButton modifyBookPriceEditButton;

    @FXML
    private Button modifyBookPriceResetButton;

    @FXML
    private TextField modifyBookPriceField;

    @FXML
    private ToggleButton modifyBookCategoryEditButton;

    @FXML
    private Button modifyBookCategoryResetButton;

    @FXML
    private ComboBox<String> modifyBookCategoryComboBox;

    @FXML
    private ToggleButton modifyBookStockEditButton;
    

    @FXML
    private ToggleButton modifyBookMinimumStockEditButton;
    
    @FXML
    private Button modifyBookStockResetButton;

    @FXML
    private Button modifyBookMinimumStockResetButton;


    @FXML
    private Spinner<Integer> modifyBookStockSpinner;


    @FXML
    private Spinner<Integer> modifyBookMinimumStockSpinner;

    @FXML
    private ToggleButton modifyBookYearEditButton;

    @FXML
    private Button modifyBookYearResetButton;

    @FXML
    private DatePicker modifyBookYearDatePicker;

    @FXML
    private Label modifyBookPrompt;

    @FXML
    private Pane findOrderPane;

    @FXML
    private Label findOrderPrompt;

    @FXML
    private TextField findOrderIDField;

    @FXML 
    private Label confirmOrderIDLabel; 
    

    @FXML 
    private Label confirmOrderUserLabel; 
    

    @FXML 
    private Label confirmOrderISBNLabel; 
    

    @FXML 
    private Label confirmOrderCountLabel; 
    

    @FXML 
    private Label confirmOrderDateLabel; 
    
    @FXML 
    private Pane confirmOrderPane;

    @FXML
    private Label confirmOrderPrompt;

    @FXML 
    private Button confirmOrderButton2;

    List<String> authors = new ArrayList<>();
    int min_stock = 2;

    public ManagerProfileController() {

    }

    void setAllInvisible() {
        PromotionPane.setVisible(false);
        PlaceOrderPane.setVisible(false);
        AddBookPane.setVisible(false);
        modifyBookPane.setVisible(false);
        findBookPane.setVisible(false);
        findOrderPane.setVisible(false);
        confirmOrderPane.setVisible(false);
    }

    void setAllWarningsInvisible() {
        warningAuthor.setVisible(false);
        warningPrice.setVisible(false);
        warningQuantity.setVisible(false);
        warning.setVisible(false);
        promotePrompt.setVisible(false);
        placeOrderPrompt.setVisible(false);
        findBookPrompt.setVisible(false);
        modifyBookPrompt.setVisible(false);
        findOrderPrompt.setVisible(false);
        confirmOrderPrompt.setVisible(false);
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
        categoryComboBox.getItems().setAll(CategoryArray);
        categoryComboBox.getSelectionModel().selectFirst();
        quantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1));
        minimumQuantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1));
        setAllWarningsInvisible();
        setAllInvisible();
        AddBookPane.setVisible(true);
    }

    @FXML
    void modifyBookButtonOnAction(ActionEvent event) {
        setAllWarningsInvisible();
        setAllInvisible();
        findBookPane.setVisible(true);
    }

    @FXML
    void confirmOrderButtonOnAction(ActionEvent event) {
        setAllWarningsInvisible();
        setAllInvisible();
        findOrderPane.setVisible(true);
    }

    @FXML
    void confirmAddBook(ActionEvent event) {
        if (titleField.getText().isEmpty() ||
                ISBNField.getText().isEmpty()
                || publisherField.getText().isEmpty() || titleField.getText().isEmpty()
                || priceField.getText().isEmpty() || authors.isEmpty()
                || PublicationDatePicker.getValue() == null) {
            setAllWarningsInvisible();
            warning.setText("Please make sure all fields are filled");
            warning.setTextFill(Color.RED);
            warning.setVisible(true);

        } else if (!Helpers.isFloat(priceField.getText())) {
            setAllWarningsInvisible();
            warningPrice.setVisible(true);
        } else if (PublicationDatePicker.getValue() == null) {
            warning.setText("Publication Date should be specified");
            warning.setTextFill(Color.RED);

            warning.setVisible(true);
        }
        else if(minimumQuantitySpinner.getValue() > quantitySpinner.getValue()) {
            warning.setText("Current stock should be greater than the minimum stock");
            warning.setTextFill(Color.RED);
            warning.setVisible(true);
        } else {
            try {
                ManagerDAO managerDAO = ManagerDAO.getInstance();
                setAllWarningsInvisible();

                Book book = Book.builder().title(titleField.getText())
                        .isbn(ISBNField.getText())
                        .publisher_name(publisherField.getText())
                        .category(categoryComboBox.getValue())
                        .publication_year(getDateFromDatePicker())
                        .stock(quantitySpinner.getValue())
                        .price(Float.parseFloat(priceField.getText()))
                        .minimum_stock(min_stock).build();
                managerDAO.addBook(book);
                managerDAO.addAuthors(book.getIsbn(), authors);
                warning.setText("Book added successfully");
                warning.setTextFill(Color.GREEN);
                warning.setVisible(true);
                clearAddBookFields();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
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
                System.out.println(managerDAO.getBookByISBN("7"));
                String username = promotionUserNameTextField.getText();
                managerDAO.promoteCutomer(username);
                promotePrompt.setText(username + " is promoted");
                promotePrompt.setTextFill(Color.GREEN);
                promotePrompt.setVisible(true);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                promotePrompt.setTextFill(Color.RED);
                promotePrompt.setText("An error has occured");
                promotePrompt.setVisible(true);

            }
        }
    }

    @FXML
    void placeOrderConfirmOnAction(ActionEvent event) {
        if (placeOrderISBNField.getText().isEmpty()) {
            placeOrderPrompt.setText("Enter Book ISBN to place an order");
            placeOrderPrompt.setTextFill(Color.RED);
            placeOrderPrompt.setVisible(true);
        } else {
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
    void findBookButtonOnAction(ActionEvent event) {
        ManagerDAO managerDAO = ManagerDAO.getInstance();

        try {
            modifyBook = managerDAO.getBookByISBN(findBookISBNField.getText());
            if (modifyBook == null) {
                findBookPrompt.setText("No such Book");
                findBookPrompt.setTextFill(Color.RED);
                findBookPrompt.setVisible(true);
            } else {
                findBookPane.setVisible(false);
                setAllWarningsInvisible();
                modifyBookPane.setVisible(true);
                disableModifyFields();
                showBookDetails(modifyBook);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    void disableModifyFields() {
        modifyBookTitleField.setDisable(true);
        modifyBookPubField.setDisable(true);
        modifyBookPriceField.setDisable(true);
        modifyBookCategoryComboBox.setDisable(true);
        modifyBookStockSpinner.setDisable(true);
        modifyBookYearDatePicker.setDisable(true);
        modifyBookMinimumStockSpinner.setDisable(true);

        modifyBookTitleField.setEditable(false);
        modifyBookPubField.setEditable(false);
        modifyBookPriceField.setEditable(false);
        modifyBookCategoryComboBox.setEditable(false);
        modifyBookStockSpinner.setEditable(false);
        modifyBookYearDatePicker.setEditable(false);
        modifyBookMinimumStockSpinner.setEditable(false);

    }

    void showBookDetails(Book book) {
        modifyBookTitleField.setText(book.getTitle());
        modifyBookPubField.setText(book.getPublisher_name());
        modifyBookPriceField.setText(String.valueOf(book.getPrice()));
        modifyBookCategoryComboBox.getItems().setAll(CategoryArray);
        modifyBookCategoryComboBox.getSelectionModel().select(book.getCategory());
        modifyBookStockSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(book.getMinimum_stock(), 1000, book.getStock()));
        modifyBookYearDatePicker.setValue(book.getPublication_year().toLocalDate());
        modifyBookMinimumStockSpinner.setValueFactory(
            new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, book.getMinimum_stock()));
    }

    void clearBookDetails() {
        modifyBookTitleField.clear();
        modifyBookPubField.clear();
        modifyBookPriceField.clear();
        modifyBookCategoryComboBox.getItems().clear();
        modifyBookStockSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1));
        modifyBookYearDatePicker.setValue(LocalDate.now());
        modifyBookMinimumStockSpinner.setValueFactory(
            new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1));
        modifyBook = null;
    }

    @FXML
    void homeReturningOnAction(ActionEvent event) {
    }

    @FXML
    void modifyBookConfirmButtonOnAction(ActionEvent event) {
        if (!Helpers.isFloat(modifyBookPriceField.getText())) {
            modifyBookPrompt.setText("Price should be a number");
            modifyBookPrompt.setTextFill(Color.RED);
            modifyBookPrompt.setVisible(true);
        } else if (modifyBookPriceField.getText().isEmpty()) {
            modifyBookPrompt.setText("Please enter price or reset");
            modifyBookPrompt.setTextFill(Color.RED);
            modifyBookPrompt.setVisible(true);
        } else if (modifyBookPubField.getText().isEmpty()) {
            modifyBookPrompt.setText("please enter publisher or reset");
            modifyBookPrompt.setTextFill(Color.RED);
            modifyBookPrompt.setVisible(true);
        } else if (modifyBookTitleField.getText().isEmpty()) {
            modifyBookPrompt.setText("Please enter title or reset");
            modifyBookPrompt.setTextFill(Color.RED);
            modifyBookPrompt.setVisible(true);
        } 
        else if (modifyBookMinimumStockSpinner.getValue() > modifyBookStockSpinner.getValue()) {
            modifyBookPrompt.setText("Current stock must be greater than the minimum stock");
            modifyBookPrompt.setTextFill(Color.RED);
            modifyBookPrompt.setVisible(true);
        }
        else {
            try {
                ManagerDAO managerDAO = ManagerDAO.getInstance();
                managerDAO.modifyBook("title", modifyBookTitleField.getText(), modifyBook.getIsbn());
                managerDAO.modifyBook("publisher_name", modifyBookPubField.getText(), modifyBook.getIsbn());
                managerDAO.modifyBook("publication_year", Date.valueOf(modifyBookYearDatePicker.getValue()).toString(),
                        modifyBook.getIsbn());
                managerDAO.modifyBook("price", modifyBookPriceField.getText(), modifyBook.getIsbn());
                managerDAO.modifyBook("stock", String.valueOf(modifyBookStockSpinner.getValue()), modifyBook.getIsbn());
                managerDAO.modifyBook("category", modifyBookCategoryComboBox.getValue(), modifyBook.getIsbn());

                modifyBookPrompt.setText("Book modified successfully");
                modifyBookPrompt.setTextFill(Color.GREEN);
                modifyBookPrompt.setVisible(true);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @FXML
    void modifyBookTitleEditButtonOnAction(ActionEvent event) {
        modifyBookTitleField.setDisable(!modifyBookTitleField.isDisable());
        if (modifyBookTitleField.isDisable()) {
            modifyBookTitleField.setEditable(false);
        } else {
            modifyBookTitleField.setEditable(true);
        }
    }

    @FXML
    void modifyBookTitleResetButtonOnAction(ActionEvent event) {
        modifyBookTitleField.setText(modifyBook.getTitle());
    }

    @FXML
    void modifyBookPubEditButtonOnAction(ActionEvent event) {
        modifyBookPubField.setDisable(!modifyBookPubField.isDisable());
        if (modifyBookPubField.isDisable()) {
            modifyBookPubField.setEditable(false);
        } else {
            modifyBookPubField.setEditable(true);
        }
    }

    @FXML
    void modifyBookPubResetButtonOnAction(ActionEvent event) {
        modifyBookPubField.setText(modifyBook.getPublisher_name());
    }

    @FXML
    void modifyBookPriceEditButtonOnAction(ActionEvent event) {
        modifyBookPriceField.setDisable(!modifyBookPriceField.isDisable());
        if (modifyBookPriceField.isDisable()) {
            modifyBookPriceField.setEditable(false);
        } else {
            modifyBookPriceField.setEditable(true);

        }
    }

    @FXML
    void modifyBookPriceResetButtonOnAction(ActionEvent event) {
        modifyBookPriceField.setText(String.valueOf(modifyBook.getPrice()));
    }

    @FXML
    void modifyBookCategoryEditButtonOnAction(ActionEvent event) {
        modifyBookCategoryComboBox.setDisable(!modifyBookCategoryComboBox.isDisable());
        if (modifyBookCategoryComboBox.isDisable()) {
            modifyBookCategoryComboBox.setEditable(false);
        } else {
            modifyBookCategoryComboBox.setEditable(true);
        }
    }

    @FXML
    void modifyBookCategoryResetButtonOnAction(ActionEvent event) {
        modifyBookCategoryComboBox.getSelectionModel().select(modifyBook.getCategory());
    }

    @FXML
    void modifyBookStockEditButtonOnAction(ActionEvent event) {
        modifyBookStockSpinner.setDisable(!modifyBookStockSpinner.isDisable());
        if (modifyBookStockSpinner.isDisable()) {
            modifyBookStockSpinner.setEditable(false);
        } else {
            modifyBookStockSpinner.setEditable(true);
        }
    }

    @FXML
    void modifyBookStockResetButtonOnAction(ActionEvent event) {
        modifyBookStockSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
                modifyBook.getMinimum_stock(), 1000, modifyBook.getStock()));
    }

    @FXML
    void modifyBookMinimumStockEditButtonOnAction(ActionEvent event) {
        modifyBookMinimumStockSpinner.setDisable(!modifyBookMinimumStockSpinner.isDisable());
        if (modifyBookMinimumStockSpinner.isDisable()) {
            modifyBookMinimumStockSpinner.setEditable(false);
        } else {
            modifyBookMinimumStockSpinner.setEditable(true);
        }
    }

    @FXML
    void modifyBookMinimumStockResetButtonOnAction(ActionEvent event) {
        modifyBookMinimumStockSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
                1, 1000, modifyBook.getMinimum_stock()));
    }

    @FXML
    void modifyBookYearEditButtonOnAction(ActionEvent event) {
        modifyBookYearDatePicker.setDisable(!modifyBookYearDatePicker.isDisable());
        if (modifyBookYearDatePicker.isDisable()) {
            modifyBookYearDatePicker.setEditable(false);
        } else {
            modifyBookYearDatePicker.setEditable(true);
        }
    }

    @FXML
    void modifyBookYearResetButtonOnAction(ActionEvent event) {
        modifyBookYearDatePicker.setValue(modifyBook.getPublication_year().toLocalDate());
    }

    @FXML
    void modifyBookfindAnotherButtonOnAction(ActionEvent event) {
        clearBookDetails();
        setAllWarningsInvisible();
        setAllInvisible();
        disableModifyFields();
        findBookPane.setVisible(true);
    }

    void showOrderDetails(Order order) {
        confirmOrderIDLabel.setText(String.valueOf(order.getBook_id()));
        confirmOrderUserLabel.setText(order.getUsername());
        confirmOrderISBNLabel.setText(order.getBook_isbn());
        confirmOrderCountLabel.setText(String.valueOf(order.getCount()));
        confirmOrderDateLabel.setText(order.getOrder_date().toString());
    } 

   
    @FXML
    void findOrderButtonOnAction(ActionEvent event) {
        if (!Helpers.isInt(findOrderIDField.getText())) {
            findOrderPrompt.setText("ID should be an integer");
            findOrderPrompt.setTextFill(Color.RED);
            findOrderPrompt.setVisible(true);
        } else {
            try {
                ManagerDAO managerDAO = ManagerDAO.getInstance();
                confirmOrder = managerDAO.getOrderByID(findOrderIDField.getText());
                if (confirmOrder == null) {
                    findOrderPrompt.setText("No such Order");
                    findOrderPrompt.setTextFill(Color.RED);
                    findOrderPrompt.setVisible(true);
                } else {
                    System.out.println(confirmOrder);
                    findOrderPane.setVisible(false);
                    setAllWarningsInvisible();
                    confirmOrderPane.setVisible(true);
                    confirmOrderButton2.setDisable(false);
                    showOrderDetails(confirmOrder);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML 
    void confirmOrderButtonOnAction2(ActionEvent event) {
        try {
            ManagerDAO managerDAO = ManagerDAO.getInstance();
            managerDAO.confirmOrder(confirmOrder);
            confirmOrderPrompt.setText("Order confirmed successfully");
            confirmOrderPrompt.setTextFill(Color.GREEN);
            confirmOrderPrompt.setVisible(true);
            confirmOrderButton2.setDisable(true);

        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML 
    void findAnotherOrderButtonOnAction(ActionEvent event) {
        setAllWarningsInvisible();
        setAllInvisible();
        disableModifyFields();
        findOrderPane.setVisible(true);
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
            authorsListView.getItems().add(AuthorField.getText());
            AuthorField.clear();
        }
    }

    void clearAddBookFields() {
        titleField.clear();
        ISBNField.clear();
        publisherField.clear();
        priceField.clear();
        authors.clear();
    }

    java.sql.Date getDateFromDatePicker() {
        LocalDate localDate = PublicationDatePicker.getValue();
        return Date.valueOf(localDate);
    }

}