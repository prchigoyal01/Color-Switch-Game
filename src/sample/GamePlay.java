package sample;

import java.io.Serializable;
import java.util.ArrayList;

public final class GamePlay implements Serializable {
    private Ball ball;
    private ArrayList<ObstacleCombination> allObstacleCombinations;
    private ArrayList<ObstacleCombination> currentObstacleCombinations;
    private ArrayList<ColorSwitch> currentColourSwitches;
    private final static long SerialVersionUID = 42L;

    public void createGame() {}
    public void saveGame() {}
    public void loadGame() {}
    public void endGame() {}

    private void serializable(){}
    private void deserializable(){}
}

