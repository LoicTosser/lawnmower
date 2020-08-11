package com.mowercompany.lawnmower.interfaces;

import com.mowercompany.lawnmower.application.MowTheLawn;
import com.mowercompany.lawnmower.domain.Lawn;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.io.File;

class InputFileParserTest implements WithAssertions {

    @Test
    void shouldThrowExceptionIfInputFileIsNull() {
        assertThatThrownBy(() ->InputFileParser.parseFileToRequest(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Input file can't be null");
    }

    @Test
    void shouldThrowExceptionIfInputFileDoesNotExist() {
        assertThatThrownBy(() ->InputFileParser.parseFileToRequest(new File("unexisting_file")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Input file unexisting_file does not exist");
    }

    @Test
    void shouldThrowExceptionWhenFirstLineIsEmpty() {
        assertThatThrownBy(() ->InputFileParser.parseFileToRequest(new File("src/test/resources/empty.mow")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Input file is empty");
    }

    @Test
    void shouldParseValidInputFile() {
        MowTheLawn.MowTheLawnRequest mowTheLawnRequest = InputFileParser.parseFileToRequest(new File("src/test/resources/simpleInput.mow"));
        assertThat(mowTheLawnRequest.getLawn()).isEqualTo(new Lawn(5,5));
        assertThat(mowTheLawnRequest.getMowers()).isEmpty();
    }

}