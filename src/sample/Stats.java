package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Stats implements Initializable {
    @FXML Button goBack;
    @FXML Label starsBalanceLabel;
    @FXML Label levelsPlayedLabel;
    @FXML Label starsSpentLabel;
    @FXML Label endlessScoreLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        starsBalanceLabel.setText(Integer.toString(SelectPlayer.currentUser.getStarsBalance()));
        levelsPlayedLabel.setText(Integer.toString(SelectPlayer.currentUser.getLevelsPlayed()));
        starsSpentLabel.setText(Integer.toString(SelectPlayer.currentUser.getStarsSpent()));
        endlessScoreLabel.setText(Integer.toString(SelectPlayer.currentUser.getEndlessScore()));
    }

    public void goBackButtonPushed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainLobby.fxml" ));
        Scene mainLobby = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainLobby);
    }
}

