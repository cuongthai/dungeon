package com.rmit.sea.gameengine.behaviour;

import com.rmit.sea.gameengine.model.Direction;

class MoveIntentlyBehaviour implements MoveBehaviour {

    private Direction direction;
    private int steps;

    public MoveIntentlyBehaviour(Direction direction, int steps) {
        this.direction = direction;
        this.steps = steps;
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
