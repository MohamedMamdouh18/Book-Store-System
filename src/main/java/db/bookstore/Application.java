package db.bookstore;

import db.bookstore.controllers.RoutingHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Application extends javafx.application.Application {
    double x = 0, y = 0;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
       
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(RoutingHandler.MainView));
        Parent root = fxmlLoader.load();
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        //to make the app move on dragging by mouse
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
        Scene scene = new Scene(root, 900, 600);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Wezza w Do7a w Mo2");
        stage.setScene(scene);
        stage.show();
    }
}