package com.mowercompany.lawnmower.domain;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Mower {

    private final String id = UUID.randomUUID().toString();

    private Position position;
    private Direction direction;

    private final List<Move> moves;

    public Mower(Position initialPosition,
                 Direction initialDirection,
                 List<Move> moves) {
        this.position = initialPosition;
        this.direction = initialDirection;
        this.moves = moves;
    }

    @Override
    public String toString() {
        return "Mow{" +
                "id='" + id + '\'' +
                ", position=" + position +
                ", direction=" + direction +
                '}';
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
}
