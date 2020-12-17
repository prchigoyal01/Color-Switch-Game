package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

public class SelectPlayer implements Initializable {
    @FXML
    Button goBack;
    // RadioButton is not serializable, so cannot use it in HashMaps and then serialize the mapping
    @FXML
    RadioButton userRadioButton1;
    @FXML
    RadioButton userRadioButton2;
    @FXML
    RadioButton userRadioButton3;
    @FXML
    RadioButton userRadioButton4;
    @FXML
    RadioButton userRadioButton5;
    @FXML
    ToggleGroup chooseGameGroup;

    // map integers to buttons and store which integer maps to which gameplay.
    public static HashMap<Integer, Gameplay> numberToGamePlayMapping;// this mapping is saved in 'saveData.txt'
    public static HashMap<Integer, RadioButton> numberToButtonMapping;// this mapping is created everytime we change scene to 'SelectPlayer.fxml'
    private static boolean loadFromFile = true;

    public static UserProfile currentUser;
    public static Gameplay currentGameplay;
    public static ArrayList<Gameplay> gameplays;
    public static boolean pauseBoolean;
    public static boolean saveExitBoolean;
    public static boolean firstTime = true;
    public static boolean pauseBooleanBanana;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        numberToButtonMapping = new HashMap<>();
        numberToButtonMapping.put(1, userRadioButton1);
        numberToButtonMapping.put(2, userRadioButton2);
        numberToButtonMapping.put(3, userRadioButton3);
        numberToButtonMapping.put(4, userRadioButton4);
        numberToButtonMapping.put(5, userRadioButton5);

        gameplays = new ArrayList<>();

        // set Loaded Games names
//        System.out.println("loadfromFile: " + loadFromFile);
        if (loadFromFile) {
            try {
                MainLobby.deserialize();
            } catch (IOException e) {

            } catch (ClassNotFoundException e) {

            } finally {
                loadFromFile = false;
            }
//            System.out.println(gameplays.toString());
        }

        if (numberToGamePlayMapping != null) {
//            System.out.println("numberToGamePlayMapping: NOT NULL");
//            System.out.println(numberToGamePlayMapping.toString());
            for (Map.Entry<Integer, Gameplay> entry : numberToGamePlayMapping.entrySet()) {
                String username = entry.getValue().getUser().getUsername();
                numberToButtonMapping.get(entry.getKey()).setText(username);
            }
        } else {
//            System.out.println("numberToGamePlayMapping: null");
        }
    }

    Button pauseButtonFunction() {
        Button button = new Button("||");
        button.setTranslateX(10);
        button.setTranslateY(10);
        button.setFont(new Font("Broadway", 10));
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                {
                    pauseBoolean = true;
                    Stage window = new Stage();
                    window.setWidth(400);
                    window.setHeight(350);
                    window.initModality(Modality.APPLICATION_MODAL);
                    window.setTitle("Pause game");

                    Label welcomeText = new Label();
                    welcomeText.setFont(new Font("Broadway", 30));
                    welcomeText.setAlignment(Pos.CENTER);
                    welcomeText.setText("Pause Menu");
                    welcomeText.setTranslateX(120);
                    welcomeText.setTranslateY(10);

                    Button closeButton = new Button("Close Menu");
                    closeButton.setTranslateX(150);
                    closeButton.setTranslateY(300);
                    closeButton.setFont(new Font("Broadway", 10));
                    closeButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            pauseBoolean = false;
                            window.close();
                        }
                    });

                    Button saveExitButton = new Button("Save and Exit");
                    saveExitButton.setTranslateX(150);
                    saveExitButton.setTranslateY(200);
                    saveExitButton.setFont(new Font("Broadway", 10));
                    saveExitButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            // Save the game

                            // Exit the Scene
                            saveExitBoolean = true;
                            window.close();
                        }
                    });

                    Group root = new Group();
                    root.getChildren().add(welcomeText);
                    root.getChildren().add(closeButton);
                    root.getChildren().add(saveExitButton);

                    Scene scene = new Scene(root, 300, 300, Color.DARKGRAY);
                    window.initStyle(StageStyle.UNDECORATED);
                    window.setScene(scene);
                    window.showAndWait();
                    // after exiting the new Stage
                }
            }
        });

        return button;
    }

    // Start playing game from here, will be called after loading the game and setting the
    // required attributes (like objects,stars, position of ball, angle of rotation of obstacles already present).
    public Scene startGame() {
        //CREATE ROOT NODE

        Gameplay game;
        Group root = currentGameplay.getRoot();
        Scene scene;
        if (root == null) {
            root = new Group();
            scene = new Scene(root, 500, 700, Color.GREY);
            currentGameplay = new Gameplay(currentUser, root, scene);
            game = currentGameplay;
        } else {
            game = currentGameplay;
            scene = game.getScene();
        }
        Ball ball = game.getBall();

        pauseBoolean = false;
        saveExitBoolean = false;
        pauseBooleanBanana = false;

//        System.out.println("CURRENT GAMEPLAY: " + currentGameplay);

        Button pauseButton = pauseButtonFunction();
        Label label = new Label();
        label.setTranslateX(10);
        label.setTranslateY(650);
        label.setFont(new Font("Broadway", 50));

        TranslateTransition moveUp = new TranslateTransition(Duration.millis(160));
        TranslateTransition moveDown = new TranslateTransition(Duration.millis(160));

        if (firstTime) {
//            System.out.println("HERE: " + root + " " + ball);
            root.getChildren().add(ball.getShape());
            root.getChildren().add(pauseButton);
            root.getChildren().add(label);
            for (GameObject obj : game.getGameObjects()) {
                if (obj instanceof ObstacleCombination) {
                    ObstacleCombination obstacle = (ObstacleCombination) obj;
                    root.getChildren().addAll(obstacle.components);
                } else if (obj instanceof ColorSwitch) {
                    ColorSwitch c = (ColorSwitch) obj;
                    root.getChildren().addAll(c.getComponents());
                }
            }
            firstTime = false;
        }

        new AnimationTimer() {
            @Override
            public void handle(long l) {
                label.setText("Score: " + ball.getScore());
                ball.motion();
                if (ball.getShouldIEndGame()) {
                    endTheGame(ball);
                }
                for (GameObject obj : game.getGameObjects()) {
                    if (obj instanceof ObstacleCombination) {
                        ObstacleCombination obstacle = (ObstacleCombination) obj;
                        obstacle.destroy();
                    } else if (obj instanceof ColorSwitch) {
                        ColorSwitch c = (ColorSwitch) obj;
                        c.destroy();
                    }
                }
                if(pauseBooleanBanana){
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("SelectPlayer.fxml"));
                        Scene selectPlayerScene = new Scene(root);
                        Stage window = (Stage) scene.getWindow();
                        window.setScene(selectPlayerScene);
                        stop();
                    } catch (IOException e) {
                        System.out.println("ERROR: Closing the program due to IOException");
                        System.exit(1);
                    }
                }


                //MouseEvent
                scene.setOnMouseClicked(e -> {
                    ball.mini_move_up();
                    for (GameObject obj : game.getGameObjects()) {
                        if (obj instanceof ObstacleCombination) {
                            ObstacleCombination obstacle = (ObstacleCombination) obj;
                            obstacle.motion();
                        } else if (obj instanceof ColorSwitch) {
                            ColorSwitch c = (ColorSwitch) obj;
                            c.motion();
                        }
                    }
                });

                if (saveExitBoolean) {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("SelectPlayer.fxml"));
                        Scene selectPlayerScene = new Scene(root);
                        Stage window = (Stage) scene.getWindow();
                        window.setScene(selectPlayerScene);
                        stop();
                    } catch (IOException e) {
                        System.out.println("ERROR: Closing the program due to IOException");
                        System.exit(1);
                    }
                }
            }
        }.start();

        return scene;
    }

    void endTheGame(Ball ball) {
        ball.setShouldIEndGame(false);
        Stage window = new Stage();
        window.setWidth(400);
        window.setHeight(350);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("You DIED....");

        Label welcomeText = new Label();
        welcomeText.setFont(new Font("Broadway", 25));
        welcomeText.setAlignment(Pos.CENTER);
        welcomeText.setText("OOPS, YOU DIED...");
        welcomeText.setTranslateX(120);
        welcomeText.setTranslateY(10);

        Button saveExitButton = new Button("I'll go away...");
        saveExitButton.setTranslateX(150);
        saveExitButton.setTranslateY(200);
        saveExitButton.setFont(new Font("Broadway", 10));
        saveExitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Exit the Scene
                pauseBooleanBanana = true;
                window.close();
            }
        });

        Group root = new Group();
        root.getChildren().add(welcomeText);
        root.getChildren().add(saveExitButton);

        Scene scene = new Scene(root, 300, 300, Color.DARKGRAY);
        window.initStyle(StageStyle.UNDECORATED);
        window.setScene(scene);
        window.show();
        // after exiting the new Stage
    }

    public void loadGameButtonPushed(ActionEvent event) throws IOException {
        RadioButton selectedRadioButton = (RadioButton) chooseGameGroup.getSelectedToggle();
        if(selectedRadioButton == null){
            return;
        }
        if(selectedRadioButton.getText().equals("EMPTY")){
            return;
        }
        // check which number maps to selected RadioButton
        int num=0;
        for(num=1;num<=5;num++){
            if(numberToButtonMapping.get(num).equals(selectedRadioButton)){
                break;
            }
        }
        SelectPlayer.setCurrentGameplay(numberToGamePlayMapping.get(num));
        currentUser = currentGameplay.getUser();
    }

    public void startGamePlayButtonPushed(ActionEvent event) throws IOException {
        if(!isLoggedIn()){
            notLoggedInError();
            return;
        }
        Scene gameplayScene = startGame();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(gameplayScene);
    }

    public void newGameButtonPushed(ActionEvent event) throws IOException {
        //check if new account can be make
        Integer radioButtonNumber;
        if((radioButtonNumber = emptyRadioButton()) == -1){
            return;
        }
        // Enter details to make a new account
        {
            Stage window = new Stage();
            window.setWidth(400);
            window.setHeight(350);
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Not Logged in...");

            Label welcomeText = new Label();
            welcomeText.setFont(new Font("Broadway", 30));
            welcomeText.setAlignment(Pos.CENTER);
            welcomeText.setText("WELCOME !!");
            welcomeText.setTranslateX(110);
            welcomeText.setTranslateY(10);

            Label messageLabel = new Label();
            messageLabel.setAlignment(Pos.CENTER);
            messageLabel.setText("We just need to ask you the following!");
            messageLabel.setFont(new Font("Broadway", 18));
            messageLabel.setAlignment(Pos.CENTER);
            messageLabel.setTranslateX(20);
            messageLabel.setTranslateY(50);

            TextField enterName = new TextField();
            // setFocusTraversable prevents the TextField from being selected by default
            enterName.setFocusTraversable(false);
            enterName.setTranslateX(130);
            enterName.setTranslateY(150);
            enterName.setPromptText("What's your name?");

            TextField enterUsername = new TextField();
            enterUsername.setFocusTraversable(false);
            enterUsername.setTranslateX(130);
            enterUsername.setTranslateY(200);
            enterUsername.setPromptText("Your nickname, sire?");

            PasswordField enterPassword = new PasswordField();
            enterPassword.setFocusTraversable(false);
            enterPassword.setTranslateX(130);
            enterPassword.setTranslateY(250);
            enterPassword.setPromptText("What's your secret code?");

            Button closeButton = new Button("Here are my details");
            closeButton.setTranslateX(145);
            closeButton.setTranslateY(300);
            closeButton.setFont(new Font("Broadway", 10));
            closeButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (enterName.getText().equals("") || enterUsername.getText().equals("") || enterPassword.getText().equals("")) {
                        return;
                    }
                    newGameHelper(enterName.getText(), enterUsername.getText(), enterPassword.getText());
                    window.close();
                }
            });

            Group root = new Group();
            root.getChildren().add(welcomeText);
            root.getChildren().add(messageLabel);
            root.getChildren().add(enterName);
            root.getChildren().add(enterUsername);
            root.getChildren().add(enterPassword);
            root.getChildren().add(closeButton);

            Scene scene = new Scene(root, 300, 300, Color.DARKGRAY);
            window.initStyle(StageStyle.UNDECORATED);
            window.setScene(scene);
            window.showAndWait();
            // after exiting the new Stage
        }

        UserProfile newUser = currentUser;

//        System.out.println("BEFORE NEW GAME BUTTON PUSHED");
//        System.out.println(numberToGamePlayMapping.toString());
        Group root = new Group();
        Gameplay gameplay = new Gameplay(newUser, root, new Scene(root, 500, 700, Color.GREY));
        // Register the new account to the database
//        System.out.println("gameplays: "+gameplays);
//        System.out.println("gameplays.size(): "+gameplays.size());
        gameplays.add(gameplay);
        numberToGamePlayMapping.put(radioButtonNumber,gameplay);
        numberToButtonMapping.get(radioButtonNumber).setText(currentUser.getUsername());
        currentGameplay = gameplay;
//        System.out.println("AFTER NEW GAME BUTTON PUSHED");
//        System.out.println(numberToGamePlayMapping.toString());
    }

    void newGameHelper(String name, String username, String password){
        currentUser = new UserProfile(name,username,password);
    }

    public void goBackButtonPushed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainLobby.fxml" ));
        Scene mainLobby = new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainLobby);
    }

    //checks which RadioButton is free
    Integer emptyRadioButton(){
        if(userRadioButton1.getText().equals("EMPTY")){
            return 1;
        }
        else if(userRadioButton2.getText().equals("EMPTY")){
            return 2;
        }
        else if(userRadioButton3.getText().equals("EMPTY")){
            return 3;
        }
        else if(userRadioButton4.getText().equals("EMPTY")){
            return 4;
        }
        else if(userRadioButton5.getText().equals("EMPTY")){
            return 5;
        }
        return -1;
    }

    public static boolean isLoggedIn(){
        return currentUser != null;
    }

    public static void notLoggedInError(){
        Stage window = new Stage();
        window.setWidth(300);
        window.setHeight(300);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Not Logged in...");

        Button closeButton = new Button("Oh, I'll Login");
        closeButton.setTranslateX(100);
        closeButton.setTranslateY(300);
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
            }
        });

        Label messageLabel = new Label();
        messageLabel.setFont(Font.font("Broadway",13));
        messageLabel.setAlignment(Pos.CENTER);
        messageLabel.setText("Ours spies detected you are not logged in.");
        messageLabel.setTranslateX(10);
        messageLabel.setTranslateY(100);

        Label messageLabel2 = new Label();
        messageLabel2.setFont(Font.font("Broadway",14));
        messageLabel2.setAlignment(Pos.CENTER);
        messageLabel2.setText("How do we know you can be trusted...");
        messageLabel2.setTranslateX(10);
        messageLabel2.setTranslateY(150);

        Group root = new Group();
        root.getChildren().add(closeButton);
        root.getChildren().add(messageLabel);
        root.getChildren().add(messageLabel2);

        Scene scene = new Scene(root,300,300, Color.GREY);
        window.setScene(scene);
        window.showAndWait();
        return;
    }

    public static void setCurrentUser(UserProfile currentUser) {
        SelectPlayer.currentUser = currentUser;
    }
    public static void setCurrentGameplay(Gameplay currentGameplay) {
        SelectPlayer.currentGameplay = currentGameplay;
    }

}

class UserProfile implements Serializable {
    final private String name;
    private String username;
    private String password;
    private int levelsPlayed;
    private int starsBalance;
    private int starsSpent;
    private int endlessScore;
    private LocalDateTime latestSpinWheelTime;

    UserProfile(String name, String username, String password){
        this.name = name;
        this.username = username;
        this.password = password;
        levelsPlayed = 0;
        starsBalance = 0;
        starsSpent = 0;
        endlessScore = 0;
        latestSpinWheelTime = LocalDateTime.now().minusHours(10);
    }

    // getters from here
    public int getEndlessScore() {
        return endlessScore;
    }
    public int getLevelsPlayed() {
        return levelsPlayed;
    }
    public int getStarsBalance() {
        return starsBalance;
    }
    public int getStarsSpent() {
        return starsSpent;
    }

    public String getName() {
        return name;
    }
    public String getUsername() {
        return username;
    }
    public LocalDateTime getLatestSpinWheelTime() {
        return latestSpinWheelTime;
    }

    // setters from here
    public void setEndlessScore(int endlessScore) {
        this.endlessScore = endlessScore;
    }
    public void setLevelsPlayed(int levelsPlayed) {
        this.levelsPlayed = levelsPlayed;
    }
    public void setStarsBalance(int starsBalance) {
        this.starsBalance = starsBalance;
    }
    public void setStarsSpent(int starsSpent) {
        this.starsSpent = starsSpent;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setLatestSpinWheelTime(LocalDateTime latestSpinWheelTime) {
        this.latestSpinWheelTime = latestSpinWheelTime;
    }
}