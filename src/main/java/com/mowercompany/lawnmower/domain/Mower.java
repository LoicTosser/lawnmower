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
    private final Queue<Move> moves;

    private Mower(Position initialPosition,
                 Direction initialDirection,
                 Queue<Move> moves) {
        this.position = initialPosition;
        this.direction = initialDirection;
        this.moves = moves;
    }

    public void mow(Lawn lawn) {
        while(!moves.isEmpty()) {
            moveAs(lawn, moves.poll());
        }
    }

    private void moveAs(Lawn lawn, Move move) {
        switch (move) {
            case F:
                Position nextPosition = moveForward();
                if (lawn.canMowerMoveTo(this, nextPosition)) {
                    this.position = nextPosition;
                }
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

    private void turnRight() {
        switch (this.direction) {
            case E:
                this.direction = Direction.S;
                break;
            case W:
                this.direction = Direction.N;
                break;
            case N:
                this.direction = Direction.E;
                break;
            case S:
                this.direction = Direction.W;
                break;
            default:
                throw new IllegalStateException("Invalid Direction");
        }
    }

    private void turnLeft() {
        switch (this.direction) {
            case E:
                this.direction = Direction.N;
                break;
            case W:
                this.direction = Direction.S;
                break;
            case N:
                this.direction = Direction.W;
                break;
            case S:
                this.direction = Direction.E;
                break;
            default:
                throw new IllegalStateException("Invalid Direction");
        }
    }

    private Position moveForward() {
        switch (this.direction) {
            case E:
                return new Position(this.position.getX() + 1, this.position.getY());
            case W:
                return new Position(this.position.getX() - 1, this.position.getY());
            case N:
                return new Position(this.position.getX(), this.position.getY() + 1);
            case S:
                return new Position(this.position.getX(), this.position.getY() - 1);
            default:
                throw new IllegalStateException("Invalid direction");
        }
    }

    public boolean isAtSamePosition(Mower anotherMower) {
        return this.position.getX().equals(anotherMower.position.getX()) && this.position.getY().equals(anotherMower.position.getY());
    }

    public Position getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
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

    public static class MowerBuilder {

        private Position initialPosition;
        private Direction initialDirection;
        private Queue<Move> moves;

        public MowerBuilder withPosition(Position position) {
            this.initialPosition = position;
            return this;
        }

        public MowerBuilder withDirection(Direction direction) {
            this.initialDirection = direction;
            return this;
        }

        public MowerBuilder withMoves(Stream<Move> moveStream) {
            moves = new ArrayDeque<>();
            moveStream.forEach(moves::add);
            return this;
        }

        public Mower build() {
            return new Mower(initialPosition, initialDirection, moves);
        }

    }
}
