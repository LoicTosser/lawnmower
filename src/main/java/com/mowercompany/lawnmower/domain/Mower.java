package com.mowercompany.lawnmower.domain;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.UUID;
import java.util.stream.Stream;

public class Mower {

    private final String id = UUID.randomUUID().toString();

    private Position position;

    private Position nextPosition;

    private final Queue<MoveType> moveTypes;

    private Mower(Position initialPosition,
                  Queue<MoveType> moveTypes) {
        this.position = initialPosition;
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
        this.position = nextPosition;
        this.nextPosition = null;
    }

    private void turnRight() {
        this.nextPosition = this.position.turnRight();
    }

    private void turnLeft() {
        this.nextPosition = this.position.turnLeft();
    }

    private void positionForward() {
        this.nextPosition = this.position.moveForward();
    }

    boolean isAtSamePosition(Position anotherPosition) {
        return this.position.equals(anotherPosition);
    }

    @Override
    public String toString() {
        return this.position.toString();
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

        private Direction direction;
        private Coordinates coordinates;
        private Queue<MoveType> moveTypes;

        public MowerBuilder withDirection(Direction direction) {
            this.direction = direction;
            return this;
        }

        public MowerBuilder withCoordinates(Coordinates coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public MowerBuilder withMoves(Stream<MoveType> moveStream) {
            moveTypes = new ArrayDeque<>();
            moveStream.forEach(moveTypes::add);
            return this;
        }

        public Mower build() {
            return new Mower(new Position(this.coordinates, this.direction), moveTypes);
        }

    }
}
