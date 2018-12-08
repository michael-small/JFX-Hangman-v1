package sample;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Random;

public class Controller {

//    TODO: delete this variable once debugging/testing is done. Rely on enterSecretWord functionality in full game.
    private String secretWord = "Jordan";
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

    private String referenceURL = "";

//    TODO: Implement permanent container of references
    private String[][] references = {
            {"coffee cheetos chicken", "https://www.youtube.com/watch?v=6x7ezLiR4rY&feature=youtu.be"},
            {"wot about der legs", "https://www.youtube.com/watch?v=SvYAPV4FEaQ&feature=youtu.be"},
            {"Somebody clip this", "https://www.youtube.com/watch?v=fw-M7y4DLko"}, //bad reference
            {"Is it like a currency thing?","https://www.youtube.com/watch?v=DKplyPPtAJ0&feature=youtu.be&t=4m58s"},
            {"You missed that one. Try another!","https://www.youtube.com/watch?v=Fv4mR6QpYws&feature=youtu.be&t=138"},
            {"That's somebody's asshole","https://www.youtube.com/watch?v=8_xW-SqyJAU"},
            {"I beat Dark Souls 3 on stream","https://www.youtube.com/watch?v=Vk0-kiRexMU&feature=youtu.be&t=4m46s"},
            {"This dude is fucked","https://www.youtube.com/watch?v=fCQi0W9DOaM&feature=youtu.be&t=39s"},
            {"We pray at night, we stalk at night","https://www.youtube.com/watch?v=jAXioRNYy4s"},
            {"Ladies and gentlemen of the jury many I please have the attention of the class?","https://youtu.be/jAXioRNYy4s?t=87"},
            {"You're running over a guy right now","https://youtu.be/jAXioRNYy4s?t=62"}
    };

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
            guessInputResponse.setText("IT'S OVAH!");
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
            if (secretWord.substring(i,i+1).equals(" ")) {
                dashedSecretWord.append(" ");
            } else {
                dashedSecretWord.append("_");
            }
        }
        return dashedSecretWord.toString();
    }

//    Checks guessed letter against secret word, letter for letter.
    private String checkGuess(String theGuess){
        String wordSoFar = guessedWord.getText();
        StringBuilder thingToReturn = new StringBuilder();
        if (secretWord.toLowerCase().contains(theGuess)) {
            for (int i = 0; i<secretWord.length(); i++){
                if (secretWord.substring(i, i+1).toLowerCase().equals(theGuess)) {
                    thingToReturn.append(secretWord.substring(i,i+1));
                } else {
                    thingToReturn.append(wordSoFar.substring(i, i+1));
                }
            }
            return thingToReturn.toString();
        } else {
            addHungPart();
        }
//        if word isn't updated by a right guess, wordSoFar is returned, keeping word the same
        return wordSoFar;
    }

//    image source: https://stackoverflow.com/questions/13880638/how-do-i-pick-up-the-enter-key-being-pressed-in-javafx2
//    guesses a letter entered in the text field upon hitting "Enter" key
    public void onEnter(){
        String theGuess = guessField.getText().toLowerCase();
        if(theGuess.length() > 1){
            guessInputResponse.setText("One letter at a time!");
            guessField.clear();
            guessField.setStyle(null);
        } else {
            if (secretWord.toLowerCase().contains(theGuess)) {
                guessField.setStyle("-fx-background-color: green");
                guessInputResponse.setText("+2");
            } else {
                guessField.setStyle("-fx-background-color: red");
                guessInputResponse.setText("-2");
//                alt responses: "You missed that one! Try another"
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

    public void randomReference() {
        resetGame();
//        Luchian Grigore @ https://stackoverflow.com/questions/8065532/how-to-randomly-pick-an-element-from-an-array/8065570#8065570
        Random generator = new Random();
        int randomIndex = generator.nextInt(references.length);
        secretWord = references[randomIndex][0];
        guessedWord.setText(dashifySecretWord());
        referenceURL = references[randomIndex][1];
    }

    public void resetGame(){
//        resets pic to start
        startingPic = 1;
        String path = "/jermimg/jerma1.jpg";
        Image img1 = new Image(getClass().getResource(path).toExternalForm());
        hangPic.setImage(img1);
//        clears text and styles from all text fields and labels
        secretWordtxtField.clear();
        guessedLetters.setText("");
        guessInputResponse.setText("");
        guessField.clear();
        guessField.setStyle(null);
        guessField.setDisable(false);
        guessField.setPromptText("Guess a letter!");
        guessedWord.setText(dashifySecretWord());
    }

//    enter new word to try from a text field
    public void enterSecretWord(){
        secretWord = secretWordtxtField.getText();
        guessedWord.setText(dashifySecretWord());
        guessField.clear();
        resetGame();
    }

    // Borrowed from: https://www.geeksforgeeks.org/sort-a-string-in-java-2-different-ways/
    // Method to sort a string alphabetically
    private static String sortString(String inputString) {
        // convert input string to char array
        char tempArray[] = inputString.toCharArray();
        // sort tempArray
        Arrays.sort(tempArray);
        // return new sorted string
        return new String(tempArray);
    }

//    checks win, displays win
    private void checkWinCondition() {
        if(secretWord.toLowerCase().equals(guessedWord.getText().toLowerCase())){
            String pathWin = "/jermimg/jerma win.jpg";
            Image gameOver = new Image(getClass().getResource(pathWin).toExternalForm());
            hangPic.setImage(gameOver);
            guessField.setDisable(true);
            guessInputResponse.setText("Loot Get!");
//          TODO:  winQuote();
        }
    }

    public void linkToReference() throws URISyntaxException, IOException {
        Desktop d = Desktop.getDesktop();
        d.browse(new URI(referenceURL));
    }

    @FXML
    private void initialize() {
        guessedWord.setText(dashifySecretWord());

        Image img1 = new Image(getClass().getResource("/jermimg/jerma1.jpg").toExternalForm());
        hangPic.setImage(img1);
    }



}
