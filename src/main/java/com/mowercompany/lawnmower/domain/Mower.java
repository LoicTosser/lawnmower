package com.mowercompany.lawnmower.domain;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.UUID;
import java.util.stream.Stream;

public class Mower {

    private final String id = UUID.randomUUID().toString();

    private Position position;
    private Direction direction;

    private Position nextPosition;
    private Direction nextDirection;

    private final Queue<MoveType> moveTypes;

    private Mower(Position initialPosition,
                  Direction initialDirection,
                  Queue<MoveType> moveTypes) {
        this.position = initialPosition;
        this.direction = initialDirection;
        this.moveTypes = moveTypes;
    }

    boolean hasNextMove() {
        return !moveTypes.isEmpty();
    }

    void prepareNextMove() {
        if (!hasNextMove()) {
            return;
        }
        MoveType moveType = moveTypes.poll();
        if (moveType == null) {
            throw new IllegalStateException("Next move type can't be null");
        }
        switch (moveType) {
            case F:
                positionForward();
                break;
            case L:
                turnLeft();
                break;
            case R:
                turnRight();
                break;
            default:
                throw new IllegalStateException("Invalid move");
        }
    }

    void confirmMove() {
        this.direction = nextDirection;
        this.position = nextPosition;
        this.nextDirection = null;
        this.nextPosition = null;
    }

    private void turnRight() {
        switch (this.direction) {
            case E:
                this.nextDirection = Direction.S;
                break;
            case W:
                this.nextDirection = Direction.N;
                break;
            case N:
                this.nextDirection = Direction.E;
                break;
            case S:
                this.nextDirection = Direction.W;
                break;
            default:
                throw new IllegalStateException("Invalid Direction");
        }
        this.nextPosition = this.position;
    }

    private void turnLeft() {
        switch (this.direction) {
            case E:
                this.nextDirection = Direction.N;
                break;
            case W:
                this.nextDirection = Direction.S;
                break;
            case N:
                this.nextDirection = Direction.W;
                break;
            case S:
                this.nextDirection = Direction.E;
                break;
            default:
                throw new IllegalStateException("Invalid Direction");
        }
        this.nextPosition = this.position;
    }

    private void positionForward() {
        switch (this.direction) {
            case E:
                nextPosition = new Position(this.position.getX() + 1, this.position.getY());
                break;
            case W:
                nextPosition = new Position(this.position.getX() - 1, this.position.getY());
                break;
            case N:
                nextPosition = new Position(this.position.getX(), this.position.getY() + 1);
                break;
            case S:
                nextPosition = new Position(this.position.getX(), this.position.getY() - 1);
                break;
            default:
                throw new IllegalStateException("Invalid direction");
        }
        nextDirection = direction;
    }

    boolean isAtSamePosition(Position anotherPosition) {
        return this.position.equals(anotherPosition);
    }

    @Override
    public String toString() {
        return position.getX() + " " + position.getY() + " " + direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mower)) return false;
        Mower mower = (Mower) o;
        return Objects.equals(id, mower.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    Position getNextPosition() {
        return nextPosition;
    }

    public static class MowerBuilder {

        private Position initialPosition;
        private Direction initialDirection;
        private Queue<MoveType> moveTypes;

        public MowerBuilder withPosition(Position position) {
            this.initialPosition = position;
            return this;
        }

        public MowerBuilder withDirection(Direction direction) {
            this.initialDirection = direction;
            return this;
        }

        public MowerBuilder withMoves(Stream<MoveType> moveStream) {
            moveTypes = new ArrayDeque<>();
            moveStream.forEach(moveTypes::add);
            return this;
        }

        public Mower build() {
            return new Mower(initialPosition, initialDirection, moveTypes);
        }

    }
}
