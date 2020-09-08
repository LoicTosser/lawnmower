package com.mowercompany.lawnmower.domain;

import java.util.Objects;

public class Coordinates {

    private final Integer x;
    private final Integer y;

    public Coordinates(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates incrementX() {
        return new Coordinates(x + 1, y);
    }

    public Coordinates decrementX() {
        return new Coordinates(x - 1, y);
    }

    public Coordinates incrementY() {
        return new Coordinates(x, y + 1);
    }

    public Coordinates decrementY() {
        return new Coordinates(x , y - 1);
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(x, that.x) &&
                Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
