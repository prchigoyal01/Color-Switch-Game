package sample;

import java.io.Serializable;
import java.util.ArrayList;

public final class Gameplay implements Serializable {
    private Ball ball;
    private ArrayList<ObstacleCombination> allObstacleCombinations;
    private ArrayList<ObstacleCombination> currentObstacleCombinations;
    private ArrayList<ColorSwitch> currentColourSwitches;
    private final static long SerialVersionUID = 42L;
    public UserProfile user;

    Gameplay(UserProfile user){
        this.user = user;
    }

    public static void createGame() {}
    public static void saveGame() {}
    public static void loadGame() {}
    public static void endGame() {}

    private void serializable(){}
    private void deserializable(){}

    public UserProfile getUser() {
        return user;
    }
}

