package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OptionsController {
    public void returnToMainMenu(ActionEvent event) throws IOException
    {
        Parent loginMenu = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene loginMenuScene = new Scene(loginMenu);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(loginMenuScene);
        window.show();
    }

    private void jerma985Quotes() {

    }

    private void secondJermaQuotes() {

    }
}
