package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.PrintWriter;

public class LoginController {

    private OpenScene openScene ;

    @FXML
    private Button connectButton ; // needs fx:id in fxml file...

    public void initialize() throws Exception {
        PrintWriter writer = null;
        openScene = new OpenScene(writer);
    }

    @FXML // handler for connect button:
    private void btnConnect() throws Exception {
        Stage stage = (Stage) connectButton.getScene().getWindow();
        openScene.start(stage);
    }
}
