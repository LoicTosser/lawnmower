package com.mowercompany.lawnmower.domain;

public enum Direction {

    N(Direction.W,Direction.E),S(Direction.E, Direction.W),E(Direction.N, Direction.S),W(Direction.N, Direction.E);

    private final Direction left;

    private final Direction right;

    Direction(Direction left, Direction right) {
        this.left = left;
        this.right = right;
    }

    public Direction turnLeft() {
        return left;
    }

    public Direction turnRight() {
        return right;
    }



}
