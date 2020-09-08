package com.mowercompany.lawnmower.domain;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

class MowerTest implements WithAssertions {


    @Test
    void shouldMoveForward() {
        Mower.MowerBuilder builder = new Mower.MowerBuilder();
        Mower mower = builder.withCoordinates(new Coordinates(1,2)).withDirection(Direction.E).withMoves(Stream.of(MoveType.F)).build();

        assertThat(mower.hasNextMove()).isTrue();
        mower.prepareNextMove();
        mower.confirmMove();

        assertThat(mower.hasNextMove()).isFalse();
        assertThat(mower.toString()).isEqualTo("2 2 E");
    }

    @Test
    void shouldTurnLeft() {
        Mower.MowerBuilder builder = new Mower.MowerBuilder();
        Mower mower = builder.withCoordinates(new Coordinates(1,2)).withDirection(Direction.E).withMoves(Stream.of(MoveType.L)).build();

        assertThat(mower.hasNextMove()).isTrue();
        mower.prepareNextMove();
        mower.confirmMove();

        assertThat(mower.hasNextMove()).isFalse();
        assertThat(mower.toString()).isEqualTo("1 2 N");
    }

    @Test
    void shouldTurnRight() {
        Mower.MowerBuilder builder = new Mower.MowerBuilder();
        Mower mower = builder.withCoordinates(new Coordinates(1,2)).withDirection(Direction.E).withMoves(Stream.of(MoveType.R)).build();

        assertThat(mower.hasNextMove()).isTrue();
        mower.prepareNextMove();
        mower.confirmMove();

        assertThat(mower.hasNextMove()).isFalse();
        assertThat(mower.toString()).isEqualTo("1 2 S");
    }
}