package com.mowercompany.lawnmower.domain;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

class MowerControllerTest implements WithAssertions {

    @Test
    void shouldMowTheLawn() {
        Mower.MowerBuilder builder = new Mower.MowerBuilder();
        builder = builder.withDirection(Direction.N).withPosition(new Position(1,2));
        builder.withMoves(Stream.of(MoveType.L, MoveType.F, MoveType.L, MoveType.F, MoveType.L,  MoveType.F, MoveType.L, MoveType.F, MoveType.F));
        Mower mower1 = builder.build();

        builder = new Mower.MowerBuilder();
        builder = builder.withDirection(Direction.E).withPosition(new Position(3,3));
        builder.withMoves(Stream.of(MoveType.F, MoveType.F, MoveType.R, MoveType.F, MoveType.F,  MoveType.R, MoveType.F, MoveType.R, MoveType.R, MoveType.F));
        Mower mower2 = builder.build();
        Lawn lawn = new Lawn(new Position(5, 5));

        MowerController mowerController = new MowerController(lawn, Arrays.asList(mower1, mower2));
        mowerController.mowLawn();


        assertThat(mower1.toString()).isEqualTo("1 3 N");
        assertThat(mower2.toString()).isEqualTo("5 1 E");

    }

}