package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    public ImageView menuPic = new ImageView();

    public void goToHangman(ActionEvent event) throws IOException
    {
        Parent loginMenu = FXMLLoader.load(getClass().getResource("Game.fxml"));
        Scene loginMenuScene = new Scene(loginMenu);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(loginMenuScene);
        window.show();
    }

    public void goToOptions(ActionEvent event) throws IOException
    {
        Parent optionsMenu = FXMLLoader.load(getClass().getResource("Options.fxml"));
        Scene optionsMenuScene = new Scene(optionsMenu);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(optionsMenuScene);
        window.show();
    }

    @FXML
    private void initialize() {
        Image img1 = new Image(getClass().getResource("/jermimg/jermamenu.png").toExternalForm());
        menuPic.setImage(img1);
    }
}
