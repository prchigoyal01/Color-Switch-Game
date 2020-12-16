package sample;

import javafx.scene.Group;
import javafx.scene.Scene;

import java.io.Serializable;
import java.util.ArrayList;

public final class Gameplay implements Serializable {
    private Ball ball;
    private ArrayList<ObstacleCombination> allObstacleCombinations;
    private ArrayList<ObstacleCombination> currentObstacleCombinations;
    private ArrayList<ColorSwitch> currentColourSwitches;
    private final static long SerialVersionUID = 42L;
    public UserProfile user;
    private transient Group root;
    private transient Scene scene;

    Gameplay(UserProfile user, Group root, Scene scene){
        this.user = user;
        this.ball = new Ball(root, scene);
        this.allObstacleCombinations = new ArrayList<>();
        this.currentObstacleCombinations = new ArrayList<>();
        this.currentColourSwitches = new ArrayList<>();
        this.root = root;
        this.scene = scene;
        createGame();
    }

    public Ball getBall() { return ball; }
    public ArrayList<ObstacleCombination> getAllObstacleCombinations() { return allObstacleCombinations; }
    public ArrayList<ObstacleCombination> getCurrentObstacleCombinations() { return currentObstacleCombinations; }
    public ArrayList<ColorSwitch> getCurrentColourSwitches() { return currentColourSwitches; }
    public Group getRoot() { return root; }
    public Scene getScene() { return scene; }

    public void createGame() {
        allObstacleCombinations.add(new ringSmallStar());
    }
    public static void saveGame() {}
    public static void loadGame() {}
    public static void endGame() {}

    private void serializable(){}
    private void deserializable(){}

    public UserProfile getUser() {
        return user;
    }
}

