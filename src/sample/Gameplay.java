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
    private transient Scene scene;

    Gameplay(UserProfile user, Group root, Scene scene){
        this.user = user;
        this.ball = new Ball(root);
        this.gameObjects = new ArrayList<>();
        this.root = root;
        this.scene = scene;
        createGame();
    }

    public Ball getBall() { return ball; }
    public ArrayList<GameObject> getGameObjects() { return gameObjects; }
    public Group getRoot() { return root; }
    public Scene getScene() { return scene; }

    public void createGame() {
        gameObjects.add(new RightCrossStar(root));
        gameObjects.add(new LeftCrossStar(root));
        gameObjects.add(new DiamondLineStar(root));
        //gameObjects.add(new SquareLineStar(root));
        gameObjects.add(new ColorSwitch(root, 250, -80));
    }

    public void updatePositions() {
        for(GameObject obj : gameObjects) {
            if(obj instanceof ObstacleCombination) {
                ObstacleCombination obstacle = (ObstacleCombination) obj;
                obstacle.X = (int) obstacle.star.getShape().getTranslateX();
                obstacle.Y = (int) obstacle.star.getShape().getTranslateY();
            }
        }
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

