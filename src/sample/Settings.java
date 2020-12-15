package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Settings {
    @FXML Button goBack;

    public void goBackButtonPushed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainLobby.fxml" ));
        Scene mainLobby = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainLobby);
    }
}
