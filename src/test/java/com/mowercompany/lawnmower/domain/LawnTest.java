package com.mowercompany.lawnmower.domain;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

class LawnTest implements WithAssertions {

    @Test
    void cantInstanciateLawnWithNullPosition() {
        assertThatThrownBy(() -> new Lawn(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Upper right corner position can't be null");
    }

    @Test
    void shouldBeIn() {
        Lawn lawn = new Lawn(new Position(5,5));
        assertThat(lawn.isIn(new Position(3,3))).isTrue();
    }

    @Test
    void shouldBeInWhenYIsOutOfUpperBound() {
        Lawn lawn = new Lawn(new Position(5,5));
        assertThat(lawn.isIn(new Position(3,6))).isFalse();
    }

    @Test
    void shouldBeInWhenXIsOutOfUpperBound() {
        Lawn lawn = new Lawn(new Position(5,5));
        assertThat(lawn.isIn(new Position(6,3))).isFalse();
    }

    @Test
    void shouldBeInWhenYIsNegative() {
        Lawn lawn = new Lawn(new Position(5,5));
        assertThat(lawn.isIn(new Position(3,-1))).isFalse();
    }

    @Test
    void shouldBeInWhenXIsNegative() {
        Lawn lawn = new Lawn(new Position(5,5));
        assertThat(lawn.isIn(new Position(-1,3))).isFalse();
    }

    @Test
    void shouldThrowExceptionWithNullPosition() {
        Lawn lawn = new Lawn(new Position(5,5));
        assertThatThrownBy(() -> lawn.isIn(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Position can't be null");
    }


}