package sample;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public abstract class ObstacleCombination extends GameObject{
    protected Star star;
    protected int YMove;
    ObstacleCombination() {
        this.X = 250;
        this.Y = 220;
        this.YMove = 0;
        star = new Star(this.X, this.Y);
    }
    //Combinations inherit ObstacleCombination and are made with union of various obstacle objects and a star
}

final class ringSmallStar extends ObstacleCombination {
    private ringSmall ringSmall;

    ringSmallStar() {
        draw();
        motion();
    }
    @Override
    public void draw() {
        ringSmall = new ringSmall();
    }

    @Override
    public void motion() {
        for(Shape x : ringSmall.components) {
            TranslateTransition tt = new TranslateTransition(Duration.millis(5000), x);
            tt.setByY(YMove);
            tt.setCycleCount(1);
            tt.play();
        }
        TranslateTransition tt = new TranslateTransition(Duration.millis(5000), star.getShape());
        tt.setByY(YMove);
        tt.setCycleCount(1);
        tt.play();
    }
}

final class ringMediumStar extends ObstacleCombination {
    @Override
    public void draw() {}

    @Override
    public void motion() {}
}

final class triangleBallStar extends ObstacleCombination {
    @Override
    public void draw() {}

    @Override
    public void motion() {}
}

final class circleBallStar extends ObstacleCombination {
    @Override
    public void draw() {}

    @Override
    public void motion() {}
}

final class diamondBallStar extends ObstacleCombination {
    @Override
    public void draw() {}

    @Override
    public void motion() {}
}

final class squareBallStar extends ObstacleCombination {
    @Override
    public void draw() {}

    @Override
    public void motion() {}
}

final class verticallyStackedRings extends ObstacleCombination {
    @Override
    public void draw() {}

    @Override
    public void motion() {}
}

final class coincidingRings extends ObstacleCombination {
    @Override
    public void draw() {}

    @Override
    public void motion() {}
}

final class horizontallyStackedRings extends ObstacleCombination {
    @Override
    public void draw() {}

    @Override
    public void motion() {}
}
