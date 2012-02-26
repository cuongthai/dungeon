package com.rmit.sea.gameengine.behaviour;

import com.rmit.sea.gameengine.mapmodel.pixel.Coordinate;
import com.rmit.sea.gameengine.model.Direction;
import com.rmit.sea.gameengine.model.NavigationManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MoveRandomlyBehaviour implements MoveBehaviour {

    private Direction direction;
    private int steps;
    /*
     * The following code is taken from stackoverflow
     * http://stackoverflow.com/questions/1972392/java-pick-a-random-value-from-an-enum
     */
    private final List<Direction> VALUES =
            Collections.unmodifiableList(Arrays.asList(Direction.values()));
    private final int SIZE = VALUES.size()-2;
    private final Random RANDOM = new Random();

    private Direction getRandomDirection() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public MoveRandomlyBehaviour(int steps) {
        this.steps = steps;
        direction = getRandomDirection();
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public int getSteps() {
        return steps;
    }
}
