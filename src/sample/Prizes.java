package sample;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.text.Font;
import javafx.scene.transform.Translate;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

public class Prizes {

    @FXML Button goBack;
    @FXML Arc spinPink1; @FXML Arc spinPink2; @FXML Arc spinPurple1; @FXML Arc spinPurple2;
    @FXML Label spinPink1Points;    @FXML Label spinPink2Points;    @FXML Label spinPurple1Points;    @FXML Label spinPurple2Points;


    public void goBackButtonPushed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainLobby.fxml" ));
        Scene mainLobby = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainLobby);
    }

    public void spinButtonClicked(ActionEvent event){
        // check if user can spin the spineWheel

        // oldDateTime is the date and time for the last spin (stored as information of a user)
        LocalDateTime oldDateTime = LocalDateTime.now();
//        if(!SelectPlayer.isLoggedIn()){
//            // Display a new dialog box and tell the user to Log-in
//            SelectPlayer.notLoggedInError();
//            return;
//        }
//        oldDateTime = SelectPlayer.currentUser.getLatestSpinWheelTime();

//        LocalDateTime currentDateTime = LocalDateTime.now();

//        if(currentDateTime.getYear() == oldDateTime.getYear()){
//            if(currentDateTime.getMonthValue() == oldDateTime.getMonthValue()){
//                if(currentDateTime.getDayOfMonth() == oldDateTime.getDayOfMonth()){
//                    if(currentDateTime.getHour() - oldDateTime.getHour() < 6){
//
//                        // Display a new dialog box and tell the user to wait
//                        Stage window = new Stage();
//                        window.setHeight(450);
//                        window.setWidth(300);
//                        window.initModality(Modality.APPLICATION_MODAL);
//                        window.setTitle("Unable to spin...");
//
//                        Button closeButton = new Button("Oh, I'll wait");
//                        closeButton.setTranslateX(100);
//                        closeButton.setTranslateY(300);
//                        closeButton.setOnAction(new EventHandler<ActionEvent>() {
//                            @Override
//                            public void handle(ActionEvent event) {
//                                window.close();
//                            }
//                        });
//
//                        Label messageLabel = new Label();
//                        messageLabel.setFont(Font.font("Broadway"));
//                        messageLabel.setAlignment(Pos.CENTER);
//                        messageLabel.setText("Hold up! Our treasure hunters are out there finding the best thing for you! Return back in "+ (6 - currentDateTime.getHour()+oldDateTime.getHour())+" hours.");
//                        messageLabel.setTranslateX(100);
//                        messageLabel.setTranslateX(100);
//
//                        Group root = new Group();
//                        root.getChildren().add(closeButton);
//                        root.getChildren().add(messageLabel);
//
//                        Scene scene = new Scene(root,450,300, Color.GREY);
//                        window.setScene(scene);
//                        window.showAndWait();
//                        return;
//                    }
//                }
//            }
//        }
        // set User's spin date and time to currentDateTime
//        SelectPlayer.currentUser.setLatestSpinWheelTime(currentDateTime);

        // Get some random angle to spin the wheel
        Random random = new Random();
        int angle = 720 + random.nextInt(360);

        // Use parallel to transition to add simultaneous animations (example- different colors of the wheel)
        // Use sequential transition to add animations in one after other (example - a slow spin then fast spin and another slow spin)
        ParallelTransition par = new ParallelTransition();
        SequentialTransition seq = new SequentialTransition();


        //      set Axis of Rotation (already set in Scene Builder, so not setting it here), negative sign reverses direction of rotation. Example - shape.setRotationAxis(new Point3D(0,0,-1));
        //      Linear Interpolator makes the rotation go smoothly i.e. no speed up during beginning and no slow down in the end

        // SpinWheel animation
        float x = 0f;
        float y = 62.5f;

        // SpinWheel rotates for longer time if 'angle' is large. On average it is 1000ms per 360 degrees.
        int duration = (3000/360)*angle;
        RotateTransition rt = new RotateTransition();


        spinPurple1.getTransforms().add(new Translate(-x,-y));
        spinPurple1.setTranslateX(x);
        spinPurple1.setTranslateY(y);


        rt.setInterpolator(Interpolator.LINEAR);
        rt.setDuration(Duration.millis(duration));
        rt.setNode(spinPurple1);
        rt.setCycleCount(1);
        rt.setByAngle(angle);
        rt.setAutoReverse(false);
        par.getChildren().add(rt);



        x = -62.5f;
        y = 0f;
        spinPink1.getTransforms().add(new Translate(-x,-y));
        spinPink1.setTranslateX(x);
        spinPink1.setTranslateY(y);

        rt = new RotateTransition();

        rt.setInterpolator(Interpolator.LINEAR);
        rt.setDuration(Duration.millis(duration));
        rt.setNode(spinPink1);
        rt.setCycleCount(1);
        rt.setByAngle(angle);
        rt.setAutoReverse(false);
        par.getChildren().add(rt);



        x = 62.5f;
        y = 0f;
        spinPink2.getTransforms().add(new Translate(-x,-y));
        spinPink2.setTranslateX(x);
        spinPink2.setTranslateY(y);

        rt = new RotateTransition();

        rt.setInterpolator(Interpolator.LINEAR);
        rt.setDuration(Duration.millis(duration));
        rt.setNode(spinPink2);
        rt.setCycleCount(1);
        rt.setByAngle(angle);
        rt.setAutoReverse(false);
        par.getChildren().add(rt);


        x = 0f;
        y = -62.5f;
        spinPurple2.getTransforms().add(new Translate(-x,-y));

        spinPurple2.setTranslateX(x);
        spinPurple2.setTranslateY(y);

        rt = new RotateTransition();

        rt.setInterpolator(Interpolator.LINEAR);
        rt.setDuration(Duration.millis(duration));
        rt.setNode(spinPurple2);
        rt.setCycleCount(1);
        rt.setByAngle(angle);
        rt.setAutoReverse(false);
        par.getChildren().add(rt);

        //spinPink1Points
        x = -62.5f;
        y = 0;
        spinPink1Points.getTransforms().add(new Translate(-x,-y));
        spinPink1Points.setTranslateX(x);
        spinPink1Points.setTranslateY(y);

        rt = new RotateTransition();

        rt.setInterpolator(Interpolator.LINEAR);
        rt.setDuration(Duration.millis(duration));
        rt.setNode(spinPink1Points);
        rt.setCycleCount(1);
        rt.setByAngle(angle);
        rt.setAutoReverse(false);
//        par.getChildren().add(rt);

        // spinPink2Points
        x = 62.5f;
        y = 0f;
        spinPink2Points.getTransforms().add(new Translate(-x,-y));
        spinPink2Points.setTranslateX(x);
        spinPink2Points.setTranslateY(y);

        rt = new RotateTransition();

        rt.setInterpolator(Interpolator.LINEAR);
        rt.setDuration(Duration.millis(duration));
        rt.setNode(spinPink2Points);
        rt.setCycleCount(1);
        rt.setByAngle(angle);
        rt.setAutoReverse(false);
//        par.getChildren().add(rt);

        // spinPurple1Points
        x = 0f;
        y = 62.5f;
        spinPurple1Points.getTransforms().add(new Translate(-x,-y));
        spinPurple1Points.setTranslateX(x);
        spinPurple1Points.setTranslateY(y);

        rt = new RotateTransition();

        rt.setInterpolator(Interpolator.LINEAR);
        rt.setDuration(Duration.millis(duration));
        rt.setNode(spinPurple1Points);
        rt.setCycleCount(1);
        rt.setByAngle(angle);
        rt.setAutoReverse(false);
        par.getChildren().add(rt);

        // spinPurple2Points
        x = 0f;
        y = -62.5f;
        spinPurple2Points.getTransforms().add(new Translate(-x,-y));
        spinPurple2Points.setTranslateX(x);
        spinPurple2Points.setTranslateY(y);

        rt = new RotateTransition();

        rt.setInterpolator(Interpolator.LINEAR);
        rt.setDuration(Duration.millis(duration));
        rt.setNode(spinPurple2Points);
        rt.setCycleCount(1);
        rt.setByAngle(angle);
        rt.setAutoReverse(false);
        par.getChildren().add(rt);

        seq.getChildren().add(par);
        seq.play();
    }
}
