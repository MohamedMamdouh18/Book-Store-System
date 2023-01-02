package db.bookstore.controllers;

import Database.Models.User;
import db.bookstore.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class RoutingHandler {
    public final static String MainView = "MainView.fxml";
    public final static String SignIn = "SignIn.fxml";
    public final static String SignUp = "SignUp.fxml";
    public final static String UserProfile = "UserProfile.fxml";
    public final static String ManagerProfile = "ManagerProfile.fxml";
    public final static String Home = "HomeView.fxml";
    private static AnchorPane mainView = null;
    private static MainController mainController = null;
    private static User currentUser;

    public static void changeView(String next) throws IOException {
        if (mainView.getChildren().size() > 0)
            mainView.getChildren().remove(0);
        Parent root;
        FXMLLoader loader = new FXMLLoader(Application.class.getResource(next));
        root = loader.load();
        mainView.getChildren().add(root);
    }

    public static void setMainView(AnchorPane mainView, MainController mainController) {
        if (RoutingHandler.mainView != null)
            return;
        RoutingHandler.mainView = mainView;
        RoutingHandler.mainController = mainController;
    }

    public static void setCurrentUser(User currentUser) {
        RoutingHandler.currentUser = currentUser;
    }

    public static void notifySigning() {
        RoutingHandler.mainController.notifyBar(currentUser);
    }
}