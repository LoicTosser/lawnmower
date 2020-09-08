package com.mowercompany.lawnmower.domain;

import java.util.Objects;

public class Lawn {

    private final Coordinates upperRightCorner;

    public Lawn(Coordinates upperRightCorner) {
        if (upperRightCorner == null) {
            throw new IllegalArgumentException("Upper right corner position can't be null");
        }
        this.upperRightCorner = upperRightCorner;
    }

    boolean isIn(final Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Position can't be null");
        }
        return coordinates.getX() >= 0 && coordinates.getX() <= this.upperRightCorner.getX()
                && coordinates.getY() >= 0 && coordinates.getY() <= this.upperRightCorner.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lawn)) return false;
        Lawn lawn = (Lawn) o;
        return Objects.equals(upperRightCorner, lawn.upperRightCorner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(upperRightCorner);
    }
}
