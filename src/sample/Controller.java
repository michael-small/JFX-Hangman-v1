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
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Controller {

//    TODO: delete this varible once debugging/testing is done. Rely on enterSecretWord functionality in full game.
    public String secretWord = "wwwwwww";
    @FXML
    private TextField guessField;
    @FXML
    public Label guessedWord;
    @FXML
    private Label guessedLetters;
    @FXML
    private TextField secretWordtxtField;
    @FXML
    private Label guessInputResponse;

    // these are used for images
    public ImageView hangPic = new ImageView();
    private Integer startingPic = 1;

    //  Cycles through hangman images when wrong letter is added
    private void addHungPart() {
        startingPic++;
        if (startingPic > 7) {
            String pathLose = "/jermimg/jerma lose.jpg";
            Image gameOver = new Image(getClass().getResource(pathLose).toExternalForm());
//            https://i.ebayimg.com/images/g/w~YAAOSw8HBZHJUZ/s-l300.jpg (source of image)
            hangPic.setImage(gameOver);
            guessField.setDisable(true);
            guessField.setPromptText("LIFE IS PAIN!");
        }
        else {
//            https://www.oligalma.com/en/downloads/images/hangman (hangman image set)
            String path = "/jermimg/jerma" + Integer.toString(startingPic) + ".jpg";
            Image img1 = new Image(getClass().getResource(path).toExternalForm());
            hangPic.setImage(img1);
        }
    }

//    Converts secretWord to dashes
    private String dashifySecretWord() {
        StringBuilder dashedSecretWord = new StringBuilder();
        for (int i = 0; i<secretWord.length(); i++){
            if(secretWord.substring(i,i+1).equals(" ")) {
                dashedSecretWord.append("  ");
            } else {
                dashedSecretWord.append("_");}
        }
        return dashedSecretWord.toString();
    }

//    Checks guessed letter against secret word, letter for letter.
    private String checkGuess(String theGuess){
        String wordSoFar = guessedWord.getText();
        StringBuilder thingToReturn = new StringBuilder();
        if (secretWord.contains(theGuess)) {
            for (int i = 0; i<secretWord.length(); i++){
                if (secretWord.substring(i, i+1).equals(theGuess)) {
                    thingToReturn.append(theGuess);
                } else {
                    thingToReturn.append(wordSoFar.substring(i, i+1));
                }
            }
            return thingToReturn.toString();
        } else {
            addHungPart();
        }
        return wordSoFar;
    }

//    image source: https://stackoverflow.com/questions/13880638/how-do-i-pick-up-the-enter-key-being-pressed-in-javafx2
//    guesses a letter entered in the text field upon hitting "Enter" key
    public void onEnter() {
        String theGuess = guessField.getText().toLowerCase();
        if(theGuess.length() > 1){
            guessInputResponse.setText("One letter at a time!");
            guessField.clear();
            guessField.setStyle(null);
        } else {
            if (secretWord.contains(theGuess)) {
                guessField.setStyle("-fx-background-color: green");
                guessInputResponse.setText("Good guess!");
            } else {
                guessField.setStyle("-fx-background-color: red");
                guessInputResponse.setText("Try something else.");
            }
//        Possible to trigger a loss at this step
            if (guessedLetters.getText().toLowerCase().contains(theGuess)){
                guessInputResponse.setText("Already guessed that.");
                guessField.clear();
            } else {
                guessedWord.setText(checkGuess(theGuess));
                guessField.clear();

                String temp = theGuess + guessedLetters.getText();
                guessedLetters.setText(sortString(temp).toUpperCase());
                checkWinCondition();
            }
        }
    }

    public void resetGame(){
        guessField.clear();
        secretWordtxtField.clear();
        //    sets to first pic
        startingPic = 1;
        String path = "/jermimg/jerma1.jpg";
        Image img1 = new Image(getClass().getResource(path).toExternalForm());
        hangPic.setImage(img1);
        guessInputResponse.setText("");
        //    resets color of text field
        guessField.setStyle(null);
        //    removes all valid guessed letters from guessedWord
        guessedWord.setText(dashifySecretWord());
        //    removes all guessed letters from guessedLetters
        guessedLetters.setText("");
        guessField.setDisable(false);
    }

    public void enterSecretWord(){
        String theGuess = secretWordtxtField.getText().toLowerCase();
        secretWord = theGuess;
        guessedWord.setText(dashifySecretWord());
        guessField.clear();
        resetGame();
    }

//    Borrowed from: https://www.geeksforgeeks.org/sort-a-string-in-java-2-different-ways/
    // Method to sort a string alphabetically
    private static String sortString(String inputString) {
        // convert input string to char array
        char tempArray[] = inputString.toCharArray();
        // sort tempArray
        Arrays.sort(tempArray);
        // return new sorted string
        return new String(tempArray);
    }

    private void checkWinCondition() {
        if(secretWord.equals(guessedWord.getText())){
            String pathWin = "/jermimg/jerma win.jpg";
            Image gameOver = new Image(getClass().getResource(pathWin).toExternalForm());
            hangPic.setImage(gameOver);
            guessField.setDisable(true);
        }
    }

    @FXML
    private void initialize() {
        guessedWord.setText(dashifySecretWord());

        Image img1 = new Image(getClass().getResource("/jermimg/jerma1.jpg").toExternalForm());
        hangPic.setImage(img1);
    }



}
