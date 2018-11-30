package sample;

import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.xml.soap.Text;

public class Controller {


    public String secretWord = "redrum";
    public String resetWord = "Reset Game";
    @FXML
    private TextField textfield;
    @FXML
    public Label guessedWord;
    @FXML
    private Label guessedLetters;

    // these are used for images
    public ImageView hangPic = new ImageView();
    public Integer startingPic = 4;

//  Cycles through hangman images
    public void setImageView() {
        startingPic++;
        if (startingPic > 10) {
            String pathEND = new String("/img/gameover.jpg");
            Image gameOver = new Image(getClass().getResource(pathEND).toExternalForm());
//            https://i.ebayimg.com/images/g/w~YAAOSw8HBZHJUZ/s-l300.jpg (source of image)
            hangPic.setImage(gameOver);
        }
        else {
//            https://www.oligalma.com/en/downloads/images/hangman (hangman image set)
            String path = new String("/img/" + Integer.toString(startingPic) + ".jpg");
            Image img1 = new Image(getClass().getResource(path).toExternalForm());
            hangPic.setImage(img1);
        }
    }


//    Makes one dash per character in secret word
    public String lines() {
        StringBuilder size = new StringBuilder();
        for (int i = 0; i<secretWord.length(); i++){
            size.append("_");
        }
        return size.toString();
}

    public String checkGuess(String theGuess){
        String wordSoFar = guessedWord.getText();
        String thingToReturn = "";
        if (secretWord.contains(theGuess)) {
            for (int i = 0; i<secretWord.length(); i++){
                if (secretWord.substring(i, i+1).equals(theGuess)) {
                    thingToReturn += theGuess;
                } else {
                    thingToReturn += wordSoFar.substring(i, i+1);
                }
            }
            return thingToReturn;
        } else{
            setImageView();
        }
        return wordSoFar;
    }

//    image source: https://stackoverflow.com/questions/13880638/how-do-i-pick-up-the-enter-key-being-pressed-in-javafx2
//    guesses a letter entered in the text field upon hitting "Enter" key
    public void onEnter(ActionEvent ae){
        TextField textfield = (TextField) ae.getSource();
        String theGuess = textfield.getText().toLowerCase();
        if (secretWord.contains(theGuess)){
            textfield.setStyle("-fx-background-color: green");
        } else {
            textfield.setStyle("-fx-background-color: red");
        }
        guessedWord.setText(checkGuess(theGuess));
        textfield.clear();

//        guessedLetters.getText();
        guessedLetters.setText(theGuess + guessedLetters.getText());
    }

    public void resetGame(){
//    sets to first pic
        textfield.clear();
        startingPic = 4;
        String path = new String("/img/" + Integer.toString(startingPic) + ".jpg");
        Image img1 = new Image(getClass().getResource(path).toExternalForm());
        hangPic.setImage(img1);
//    resets color of text field
        textfield.setStyle(null);
//    removes all valid guessed letters from guessedWord
        guessedWord.setText(lines());
//    removes all guessed letters from guessedLetters
        guessedLetters.setText("");
        }


    //    //TODO: Changes button color to red if guess is wrong, green if it is correct (Option 2)
//    public void guess(ActionEvent actionEvent) {
//        Button theButtonPressed = (Button) actionEvent.getSource();
//        String theGuess = theButtonPressed.getText().toLowerCase();
//        theButtonPressed.setDisable(true);
//        if (secretWord.contains(theGuess)){
//            theButtonPressed.setStyle("-fx-background-color: green");
//        } else {
//            theButtonPressed.setStyle("-fx-background-color: red");
//        }
//        guessedWord.setText(checkGuess(theGuess));
//    }


//    https://stackoverflow.com/questions/32806068/how-to-change-fxml-lable-text-by-id
//    thanks to the top answer
    @FXML
    private void initialize() {
        guessedWord.setText(lines());

        Image img1 = new Image(getClass().getResource("/img/4.jpg").toExternalForm());
        hangPic.setImage(img1);
    }



}
