package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    public ImageView menuPic = new ImageView();

    public void goToHangman(ActionEvent event) throws IOException
    {
        Parent loginMenu = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene loginMenuScene = new Scene(loginMenu);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(loginMenuScene);
        window.show();
    }

    @FXML
    private void initialize() {
        Image img1 = new Image(getClass().getResource("/jermimg/jermamenu.png").toExternalForm());
        menuPic.setImage(img1);
    }
}
