package sample;

public abstract class ObstacleCombination extends GameObject{
    protected Star star;
    //Combinations inherit ObstacleCombination and are made with union of various obstacle objects and a star
}

final class ringSmallStar extends ObstacleCombination {
    @Override
    public void draw() {}

    @Override
    public void motion() {}
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
