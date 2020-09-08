package com.mowercompany.lawnmower.domain;

import java.util.Objects;

public class Position {

    private final Coordinates coordinates;
    private final Direction direction;

    public Position(Coordinates coordinates, Direction direction) {
        this.coordinates = coordinates;
        this.direction = direction;
    }

    public Position turnLeft() {
        return new Position(this.coordinates, this.direction.turnLeft());
    }

    public Position turnRight() {
        return new Position(this.coordinates, this.direction.turnRight());
    }

    public Position moveForward() {
        switch (this.direction) {
            case E:
                return new Position(coordinates.incrementX(), this.direction);
            case W:
                return new Position(coordinates.decrementX(), this.direction);
            case N:
                return new Position(coordinates.incrementY(), this.direction);
            case S:
                return new Position(coordinates.decrementY(), this.direction);
            default:
                throw new IllegalStateException("Invalid direction");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return Objects.equals(coordinates, position.coordinates) &&
                Objects.equals(direction, position.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, direction);
    }

    @Override
    public String toString() {
        return this.coordinates + " " + this.direction;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }
}
