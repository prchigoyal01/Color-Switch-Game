package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public final class Gameplay implements Serializable {
    private Ball ball;
    private ArrayList<GameObject> gameObjects;
    private final static long SerialVersionUID = 42L;
    public UserProfile user;
    private static Random rand = new Random();
    private transient Group root;

    Gameplay(UserProfile user, Group root){
        this.user = user;
        this.ball = new Ball(root);
        this.gameObjects = new ArrayList<>();
        this.root = root;
        createGame();
    }

    public Ball getBall() { return ball; }
    public ArrayList<GameObject> getGameObjects() { return gameObjects; }
    public Group getRoot() { return root; }

    public void createGame() {
        gameObjects.add(new RightCrossStar(root));
        gameObjects.add(new LeftCrossStar(root));
        gameObjects.add(new DiamondLineStar(root));
        gameObjects.add(new SquareLineStar(root));
        gameObjects.add(new ColorSwitch(root));
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

