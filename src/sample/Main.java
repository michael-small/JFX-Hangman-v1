//Michael Small, Nathan Foss, Melissa Kamrowski
//Files to start out with borrowed from class files

//TODO: Jar included in out/artifacts
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Jerma Hangman v1");
        primaryStage.getIcons().add(new Image("jermimg/jerma7.jpg"));

        primaryStage.setScene(new Scene(root, 500, 390));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
