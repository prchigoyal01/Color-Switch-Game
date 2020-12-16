// Manas Gupta : 2019368
// Prachi Goyal: 2019186
package sample;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MainLobby extends Application implements Initializable, Serializable {

    @FXML Button settingsButton;
    @FXML Button statsButton;
    @FXML Button playButton;
    @FXML Button prizesButton;
    @FXML Button creditsButton;
    @FXML Arc pink1;    @FXML Arc cyan1;    @FXML Arc purple1;    @FXML Arc yellow1;
    @FXML Arc pink2;    @FXML Arc cyan2;    @FXML Arc purple2;    @FXML Arc yellow2;
    @FXML Arc pink3;    @FXML Arc cyan3;    @FXML Arc purple3;    @FXML Arc yellow3;



        //      The constructor is called first, then any @FXML annotated fields are populated, then initialize() is called.
        //      Therefore the constructor does not have access to @FXML fields referring to components defined in the .fxml file,
        //      while initialize() does have access to them.
//    MainLobby(){
//        return;
//    }

        //      helper function to initialize to rotate the individual shapes
    void rotateHelper(Shape shape, int x, int y, int duration){
        //      x is half the radius here
        shape.getTransforms().add(new Translate(-x,-y));
        shape.setTranslateX(x);
        shape.setTranslateY(y);

        //      set Axis of Rotation (already set in Scene Builder, so not setting it here)
        //      negative sign reverses direction of rotation
        //      shape.setRotationAxis(new Point3D(0,0,-1));
        RotateTransition rt = new RotateTransition();
        //      Linear Interpolator makes the rotation go smoothly i.e. no speed up during
        //      beginning and no slow down in the end
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setDuration(Duration.millis(duration));
        rt.setNode(shape);
        rt.setCycleCount(10000);
        rt.setByAngle(360);
        rt.setAutoReverse(false);
        rt.play();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources){
        rotateHelper(pink1,-35,35,3000);
        rotateHelper(cyan1,35,35,3000);
        rotateHelper(yellow1,35,-35,3000);
        rotateHelper(purple1,-35,-35,3000);

        rotateHelper(pink2,-50,50,4000);
        rotateHelper(cyan2,50,50,4000);
        rotateHelper(yellow2,50,-50,4000);
        rotateHelper(purple2,-50,-50,4000);

        rotateHelper(pink3,-70,70,4500);
        rotateHelper(cyan3,70,70,4500);
        rotateHelper(yellow3,70,-70,4500);
        rotateHelper(purple3,-70,-70,4500);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainLobby.fxml" ));
        primaryStage.setTitle("Color Switch");
        primaryStage.setScene(new Scene(root, 500, 700));
        primaryStage.show();
    }

    // Runs the code before closing the Stage
    @Override
    public void stop() throws IOException{
        System.out.println("Stage is closing");
        // Save file
        serialize();
    }

    public static void serialize() throws IOException{
        ObjectOutputStream out = null;

        try{
            out = new ObjectOutputStream( new FileOutputStream("savedData.txt"));
            out.writeObject(SelectPlayer.gameplays);

            if(SelectPlayer.numberToGamePlayMapping != null){
                for(Map.Entry<Integer,Gameplay> entry: SelectPlayer.numberToGamePlayMapping.entrySet()){
                    System.out.println("AAAA "+entry.getKey()+" " + entry.getValue());
                }
            }
            else{
                System.out.println("SelectPlayer.buttonToGameplayMapping: null");
            }
            out.writeObject(SelectPlayer.numberToGamePlayMapping);
        }
        finally{
            out.close();
        }
    }

    // When the app runs for the first time,'savedData.txt' is not present so it results in IOException.
    public static void deserialize() throws IOException, ClassNotFoundException{
        ObjectInputStream in = null;

        HashMap<Integer,Gameplay> savedMappings = new HashMap<>();
        ArrayList<Gameplay> savedGameplays = new ArrayList<>();
        try{
            in = new ObjectInputStream(new FileInputStream("savedData.txt"));
            System.out.println("in: "+in);
            savedGameplays = (ArrayList<Gameplay>) in.readObject();
            savedMappings = (HashMap<Integer,Gameplay>) in.readObject();
        } catch( IOException e){
            System.out.println("Cannot find the file.");
        }
        finally{
            if(in != null){
                in.close();
            }
            SelectPlayer.gameplays = savedGameplays;
            SelectPlayer.numberToGamePlayMapping = savedMappings;

            if(SelectPlayer.numberToGamePlayMapping != null){
                for(Map.Entry<Integer,Gameplay> entry: SelectPlayer.numberToGamePlayMapping.entrySet()){
                    System.out.println("BBBB "+entry.getKey()+" " + entry.getValue());
                }
            }
        }
    }


        public static void main(String[] args) {
        launch(args);
    }


    public void settingsButtonPushed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml" ));
        Scene settingsScene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(settingsScene);
    }

    public void statsButtonPushed(ActionEvent event) throws IOException {
        if(!SelectPlayer.isLoggedIn()){
            SelectPlayer.notLoggedInError();
            return;
        }
        Parent root = FXMLLoader.load(getClass().getResource("Stats.fxml"));
        Scene statsScene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(statsScene);
    }

    public void playButtonPushed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SelectPlayer.fxml" ));
        Scene selectPlayerScene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(selectPlayerScene);
    }

    public void prizesButtonPushed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Prizes.fxml" ));
        Scene prizeScene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(prizeScene);
    }

    public void creditsButtonPushed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Credits.fxml" ));
        Scene creditsScene = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(creditsScene);
    }
}