package sample;

import javafx.scene.Group;

import java.io.Serializable;
import java.util.ArrayList;

public final class Gameplay implements Serializable {
    private Ball ball;
    private ArrayList<ObstacleCombination> allObstacleCombinations;
    private ArrayList<ObstacleCombination> currentObstacleCombinations;
    private ArrayList<ColorSwitch> currentColourSwitches;
    private final static long SerialVersionUID = 42L;
    public UserProfile user;

    Gameplay(UserProfile user, Group root){
        this.user = user;
        ball = new Ball(root);
        allObstacleCombinations = new ArrayList<>();
        currentObstacleCombinations = new ArrayList<>();
        currentColourSwitches = new ArrayList<>();

        createGame();
    }

    public Ball getBall() { return ball; }
    public ArrayList<ObstacleCombination> getAllObstacleCombinations() { return allObstacleCombinations; }
    public ArrayList<ObstacleCombination> getCurrentObstacleCombinations() { return currentObstacleCombinations; }
    public ArrayList<ColorSwitch> getCurrentColourSwitches() { return currentColourSwitches; }

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

