package com.mowercompany.lawnmower.domain;

import java.util.Objects;

public class Lawn {

    private final Integer height;
    private final Integer width;

    public Lawn(Integer height, Integer width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lawn)) return false;
        Lawn lawn = (Lawn) o;
        return Objects.equals(height, lawn.height) &&
                Objects.equals(width, lawn.width);
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width);
    }
}
