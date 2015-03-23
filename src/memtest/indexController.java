/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package memtest;

import Logic.GenerateRandomNumbers;
import Logic.MemoryMarker;
import Logic.MemoryStatistics;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author root
 */
public class indexController implements Initializable {
    @FXML
    private Pane answerPane;
    private Pane normalAnswerPane;
    private Pane easyAnswerPane;
    
    @FXML
    private Pane memValuesDisplayPane;
    
    
    /****************   CLASSES INSTANTIATION ********************/
    GenerateRandomNumbers randNum;
    MemoryStatistics statistics;
    MemoryMarker marker;
    
    Font fontSize;
    Label mem1, mem2, mem3, mem4, mem5, mem6, mem7;
    Label memArray[];
    Random rand;
    
    int lastPoints;
    int level;
    int stage;
    boolean isPlaying;/***********
     * @isPlaying.
     * when there is a game currently going on, this variable will be set to true.
     * This will be used to note if the player wants to change the game in the middle of another so
     * that a warning message be produced to him/her.
     */
   
    
    
    public final int EASY = 3, NORMAL = 5, DIFFICULT = 7;
    @FXML
    private TextField inputValue1;
    @FXML
    private TextField inputValue2;
    @FXML
    private TextField inputValue3;
    @FXML
    private TextField inputValue4;
    @FXML
    private TextField inputValue5;
    @FXML
    private TextField inputValue6;
    @FXML
    private TextField inputValue7;
    @FXML
    private Button submitAnswerButton;
    @FXML
    private Pane scoreDashBoard;
    @FXML
    private Label totalScore;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        level = 1;
        statistics = new MemoryStatistics();
        
        
        isPlaying = false;
        //makeAnswerPanesInvisible();
        
        
        //this will come after we get the stage that the user wants to play. 
        //For the time being, i will use the normal stage, taking 5 memory values
        memArray = new Label[DIFFICULT];
        stage = EASY;
    } 
    
    
    
    
    
    @FXML
    private void test(MouseEvent event) {
        displayMemValues();
        //makeInvisibleAndClearInputFields();
    }
    
    @SuppressWarnings("empty-statement")
    public void displayMemValues(){
        fontSize = new Font(20);
        
        double x, y; 
        rand = new Random();
        
        for(int i = 0; i < DIFFICULT; i++){
            try {
                    memArray[i].setText(null);
            } catch (Exception e) {
                System.out.println("not initialised yet");
            }
        }
        
        //testing some random positioning of labels

        System.out.println("stage = " + stage);
        for(int i = 0 ; i < stage; i++){
            memArray[i] = new Label("\n " + randNum.getArrayOfRandomNumbers()[i] + " \n");
        }
        
        for(int i = 0 ; i < stage ; i++){
            
            while( (x = rand.nextInt((int)memValuesDisplayPane.getWidth()) - 50 ) < 50 )     
                ;
            while( (y = rand.nextInt((int)memValuesDisplayPane.getHeight()) - 50) < 50 )
                ;
            memArray[i].setLayoutX(x); 
            memArray[i].setLayoutY(y);
            memArray[i].setFont(fontSize);
            System.out.println("(x, y) :: (" + x + ", " + y + ")");
        }
        
        for(int i = 0 ; i < stage ; i++){
            boolean ret = memValuesDisplayPane.getChildren().add(memArray[i]);
        }
    }
    

    @FXML
    private void EASYGame(ActionEvent event) throws InterruptedException {
        setGame(EASY, EASY * 10);
        displayInputFields(this.stage);
    }

    @FXML
    private void NORMALGame(ActionEvent event) throws InterruptedException {
        setGame(NORMAL, NORMAL * 100);
        displayInputFields(this.stage);
    }

    @FXML
    private void DIFFICULTGame(ActionEvent event) throws InterruptedException {
        setGame(DIFFICULT, DIFFICULT * 1000);
        displayInputFields(this.stage);
    }
    
    public void setGame(int EASY_NORMAL_DIFFICULT, int maxMemValue){
        randNum = new GenerateRandomNumbers(EASY_NORMAL_DIFFICULT, maxMemValue);
        if(isPlaying){
            Action confirmGameChange = Dialogs.create().masthead("Changing game in the middle of another Game").message("Do you want to change the game.\n All moves of the current game will be lost").title("change Game").showConfirm();
            if (confirmGameChange == Dialog.Actions.YES) {
                this.stage = EASY_NORMAL_DIFFICULT;
                isPlaying = true;
                displayMemValues();
            }else{
                Dialogs.create().message("Continue the game").showInformation();
            }
        }else{
            this.stage = EASY_NORMAL_DIFFICULT;
            isPlaying = true;
            displayMemValues();
        }
    }
    
    @FXML
    private void submitMemorisedValuesForChecking(MouseEvent event) throws InterruptedException {
        int[] userInput = new int[stage];
        int[] correctWrong = new int[2];
        marker = new MemoryMarker(stage);
        switch(stage){
            case 7:
                userInput[6] = Integer.parseInt(inputValue7.getText());
                userInput[5] = Integer.parseInt(inputValue6.getText());
                System.out.println("playing DIFFICULT");
            case 5:
                userInput[4] = Integer.parseInt(inputValue5.getText());
                userInput[3] = Integer.parseInt(inputValue4.getText());
                System.out.println("playing NORMAL");
            case 3: 
                userInput[2] = Integer.parseInt(inputValue3.getText());
                userInput[1] = Integer.parseInt(inputValue2.getText());
                userInput[0] = Integer.parseInt(inputValue1.getText());
                System.out.println("playing EASY");
                break;
            default:
                Dialogs.create().message("wrong level. Piss off").showInformation();
        }
        correctWrong = marker.markQuestions(randNum.getArrayOfRandomNumbers(), userInput);
        switch(stage){
            case 7:
                statistics.addQuestionsAndPoints(correctWrong, stage, level*3);
                break;
            case 5:
                statistics.addQuestionsAndPoints(correctWrong, stage, level*2);
                break;
            case 3:
                statistics.addQuestionsAndPoints(correctWrong, stage, level*1);
                break;
            default:
                Dialogs.create().message("Invalid stage").showError();
        }
        
        //flikerScoreDashBoard();
        System.out.println("pts " + statistics.getPoints());
        totalScore.setText(String.valueOf(statistics.getPoints()));
        
    }
    
    public void flikerScoreDashBoard() throws InterruptedException{
            scoreDashBoard.setVisible(false);
            System.out.println("flikering the dash board");
            Thread.sleep(1000);
            scoreDashBoard.setVisible(true);
            scoreDashBoard.setDisable(true);
            System.out.println("Disabling the dash board");
            Thread.sleep(1000);
            scoreDashBoard.setDisable(false);
            
            scoreDashBoard.setVisible(false);
            System.out.println("flikering the dash board");
            Thread.sleep(1000);
            scoreDashBoard.setVisible(true);
            scoreDashBoard.setDisable(true);
            System.out.println("Disabling the dash board");
            Thread.sleep(1000);
            scoreDashBoard.setDisable(false);
      
    }
    
    public void makeInvisibleAndClearInputFields(int stage){

                inputValue7.setVisible(false);
                inputValue7.setText(null);
                inputValue6.setVisible(false);
                inputValue6.setText(null);

                inputValue5.setVisible(false);
                inputValue5.setText(null);
                inputValue4.setVisible(false);
                inputValue4.setText(null);

                inputValue3.setVisible(false);
                inputValue3.setText(null);
                inputValue2.setVisible(false);
                inputValue2.setText(null);
                inputValue1.setVisible(false);
                inputValue1.setText(null);

    }
    
    public void displayInputFields(int stage) throws InterruptedException{
        makeInvisibleAndClearInputFields(this.stage);
        switch(stage){
            case 7:
                inputValue7.setVisible(true);
                Thread.sleep(1000);
                inputValue6.setVisible(true);
                Thread.sleep(1000);
            case 5:
                inputValue5.setVisible(true);
                Thread.sleep(1000);
                inputValue4.setVisible(true);
                Thread.sleep(1000);
                
            case 3:
                inputValue3.setVisible(true);
                Thread.sleep(1000);
                inputValue2.setVisible(true);
                Thread.sleep(1000);
                inputValue1.setVisible(true);
                break;
            default:
        }
        
        //position button
        System.out.println("positioning submit button.");
        switch(stage){
            case 3:
                submitAnswerButton.setVisible(false);
                submitAnswerButton.setLayoutX(inputValue1.getWidth() * 7);
                submitAnswerButton.setLayoutY(inputValue1.getHeight() / 2);
                submitAnswerButton.setVisible(true);
                break;
            case 5:
                submitAnswerButton.setVisible(false);
                submitAnswerButton.setLayoutX(inputValue1.getWidth() * 11);
                submitAnswerButton.setLayoutY(inputValue1.getHeight() / 2);
                submitAnswerButton.setVisible(true);
                break;
            case 7:
                submitAnswerButton.setVisible(false);
                submitAnswerButton.setLayoutX(inputValue1.getWidth() * 15);
                submitAnswerButton.setLayoutY(inputValue1.getHeight() / 2);
                submitAnswerButton.setVisible(true);
                break;
            default:
        }
    }
   
    
}
