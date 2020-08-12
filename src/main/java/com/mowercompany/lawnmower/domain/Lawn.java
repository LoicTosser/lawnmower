package com.mowercompany.lawnmower.domain;

import java.util.Objects;

public class Lawn {

    private final Position upperRightCorner;

    public Lawn(Position upperRightCorner) {
        if (upperRightCorner == null) {
            throw new IllegalArgumentException("Upper right corner position can't be null");
        }
        this.upperRightCorner = upperRightCorner;
    }

    boolean isIn(final Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Position can't be null");
        }
        return position.getX() >= 0 && position.getX() <= this.upperRightCorner.getX()
                && position.getY() >= 0 && position.getY() <= this.upperRightCorner.getY();
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
