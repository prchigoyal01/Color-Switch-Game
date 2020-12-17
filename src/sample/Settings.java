package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Settings implements Initializable {
    @FXML Button goBack;
    @FXML CheckBox playMusicCheckbox;
    @FXML CheckBox playSoundsCheckbox;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        if(MainLobby.musicPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)){
            playMusicCheckbox.setSelected(true);
        }
        else{
            playMusicCheckbox.setSelected(false);
        }
    }

    public void goBackButtonPushed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainLobby.fxml" ));
        Scene mainLobby = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainLobby);
    }

    public void playMusicCheckboxClicked(){
        if(MainLobby.musicPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)){
            MainLobby.musicPlayer.pause();
        }
        else{
            MainLobby.musicPlayer.play();
        }
    }

    public void playSoundsCheckboxClicked(){

    }
}
