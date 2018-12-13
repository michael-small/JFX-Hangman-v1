package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.PrintWriter;

//https://stackoverflow.com/a/29081822 (James_D)
// next look into this https://www.youtube.com/watch?v=XCgcQTQCfJQ

public class OpenScene {

    private final PrintWriter writer ;

    public OpenScene(PrintWriter writer) {
        this.writer = writer ;
    }

    // doesn't need to be called "start" any more...
    public void start(Stage window) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene =  new Scene(root, 200 ,200);
        window.setScene(scene);
        window.show();
    }
}
