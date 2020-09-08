package com.mowercompany.lawnmower.interfaces;

import com.mowercompany.lawnmower.application.MowTheLawn;
import com.mowercompany.lawnmower.domain.Coordinates;
import com.mowercompany.lawnmower.domain.Direction;
import com.mowercompany.lawnmower.domain.Lawn;
import com.mowercompany.lawnmower.domain.MoveType;
import com.mowercompany.lawnmower.domain.Mower;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.stream.Stream;

class InputFileParserTest implements WithAssertions {

    @Test
    void shouldThrowExceptionIfInputFileIsNull() {
        assertThatThrownBy(() ->InputFileParser.toLawn(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Input file can't be null");
    }

    @Test
    void shouldThrowExceptionIfInputFileDoesNotExist() {
        assertThatThrownBy(() ->InputFileParser.toLawn(new File("unexisting_file")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Input file unexisting_file does not exist");
    }

    @Test
    void shouldThrowExceptionWhenFileIsEmpty() {
        assertThatThrownBy(() ->InputFileParser.toLawn(new File("src/test/resources/empty.mow")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Input file is empty");
    }

    @Test
    void shouldThrowExceptionWhenFirstLineIsEmpty() {
        assertThatThrownBy(() ->InputFileParser.toLawn(new File("src/test/resources/first-line-empty.mow")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("First line of input file must not be null or empty");
    }

    @Test
    void shouldThrowExceptionWhenEmptyEntryInLawn() {
        assertThatThrownBy(() ->InputFileParser.toLawn(new File("src/test/resources/empty-entry-in-lawn.mow")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Error while parsing lawn");
    }

    @Test
    void shouldThrowExceptionWhenMoreThan2EntriesInLawn() {
        assertThatThrownBy(() ->InputFileParser.toLawn(new File("src/test/resources/3-entries-in-lawn.mow")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("First line is not valid:  1 2 3");
    }

    @Test
    void shouldThrowExceptionWhenInvalidEntryInLawn() {
        assertThatThrownBy(() ->InputFileParser.toLawn(new File("src/test/resources/invalid-entry-in-lawn.mow")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Error while parsing lawn");
    }

    @Test
    void shouldThrowExceptionWhenOneLineIsMissingForMower() {
        assertThatThrownBy(() ->InputFileParser.toLawn(new File("src/test/resources/only-one-line-for-mower.mow")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Missing second line of mower's input data");
    }

    @Test
    void shouldThrowExceptionWhenMissingEntryInMowerPosition() {
        assertThatThrownBy(() ->InputFileParser.toLawn(new File("src/test/resources/missing-entry-in-mower-position.mow")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid line: 1 2. Line for mower position must contain 3 entries");
    }

    @Test
    void shouldThrowExceptionWhenEmptyEntryInMowerPosition() {
        assertThatThrownBy(() ->InputFileParser.toLawn(new File("src/test/resources/empty-entry-in-mower-position.mow")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Error while parsing mower position");
    }

    @Test
    void shouldThrowExceptionWhenInvalidEntryInMowerPosition() {
        assertThatThrownBy(() ->InputFileParser.toLawn(new File("src/test/resources/invalid-mower-position.mow")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Error while parsing mower position");
    }

    @Test
    void shouldThrowExceptionWhenInvalidEntryInMowerMoves() {
        assertThatThrownBy(() ->InputFileParser.toLawn(new File("src/test/resources/invalid-mower-moves.mow")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("No enum constant com.mowercompany.lawnmower.domain.MoveType.X");
    }

    @Test
    void shouldParseValidInputFile() {
        Mower.MowerBuilder builder = new Mower.MowerBuilder();
        builder = builder.withDirection(Direction.N).withCoordinates(new Coordinates(1,2));
        builder.withMoves(Stream.of(MoveType.L, MoveType.F, MoveType.L, MoveType.F, MoveType.L,  MoveType.F, MoveType.L, MoveType.F, MoveType.F));
        Mower expectedMower1 = builder.build();

        builder = new Mower.MowerBuilder();
        builder = builder.withDirection(Direction.E).withCoordinates(new Coordinates(3,3));
        builder.withMoves(Stream.of(MoveType.F, MoveType.F, MoveType.R, MoveType.F, MoveType.F,  MoveType.R, MoveType.F, MoveType.R, MoveType.R, MoveType.F));
        Mower expectedMower2 = builder.build();
        Lawn expectedLawn = new Lawn(new Coordinates(5, 5));
        MowTheLawn.MowTheLawnRequest mowTheLawnRequest = InputFileParser.toLawn(new File("src/test/resources/simpleInput.mow"));

        assertThat(mowTheLawnRequest.getLawn()).isEqualTo(expectedLawn);
        assertThat(mowTheLawnRequest.getMowers().size()).isEqualTo(2);
        assertThat(mowTheLawnRequest.getMowers().get(0).toString()).isEqualTo(expectedMower1.toString());
        assertThat(mowTheLawnRequest.getMowers().get(1).toString()).isEqualTo(expectedMower2.toString());
    }

}