package com.mowercompany.lawnmower.domain;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

class LawnTest implements WithAssertions {

    @Test
    void shouldMow() {
        Mower.MowerBuilder builder = new Mower.MowerBuilder();
        builder = builder.withDirection(Direction.N).withPosition(new Position(1,2));
        builder.withMoves(Stream.of(Move.L, Move.F, Move.L, Move.F, Move.L,  Move.F, Move.L, Move.F, Move.F));
        Mower mower1 = builder.build();

        builder = new Mower.MowerBuilder();
        builder = builder.withDirection(Direction.E).withPosition(new Position(3,3));
        builder.withMoves(Stream.of(Move.F, Move.F, Move.R, Move.F, Move.F,  Move.R, Move.F, Move.R, Move.R, Move.F));
        Mower mower2 = builder.build();
        Lawn lawn = new Lawn(new Position(5, 5), Arrays.asList(mower1, mower2));

        lawn.mow();

        assertThat(mower1.getDirection()).isEqualTo(Direction.N);
        assertThat(mower1.getPosition()).isEqualTo(new Position(1,3));

        assertThat(mower2.getDirection()).isEqualTo(Direction.E);
        assertThat(mower2.getPosition()).isEqualTo(new Position(5,1));
    }

//    void shouldNotMoveWhenAnotherMowerIsAtNextPosition() {
//
//    }
//
//    void shouldNotMoveWhenNextPositionXIsNegative() {
//
//    }
//
//    void shouldNotMoveWhenNextPositionYIsNegative() {
//
//    }
//
//    void shouldNotMoveWhenNextPositionXIsOutOfLawn1() {
//
//    }
//
//    void shouldNotMoveWhenNextPositionYIsOutOfLawn1() {
//
//    }

}