package com.mowercompany.lawnmower.interfaces;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

class FileLawnmowerTest implements WithAssertions {

    @Test
    void shouldThrowExceptionWhenNullInputFilePath() {
        FileLawnmower fileLawnmower = new FileLawnmower();
        assertThatThrownBy(() -> fileLawnmower.mowLawn(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Input file path can't be null or empty");
    }

    @Test
    void shouldThrowExceptionWhenEmptyInputFilePath() {
        FileLawnmower fileLawnmower = new FileLawnmower();
        assertThatThrownBy(() -> fileLawnmower.mowLawn(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Input file path can't be null or empty");
    }

    @Test
    void shouldThrowExceptionWhenInvalidFilePath() {
        FileLawnmower fileLawnmower = new FileLawnmower();
        assertThatThrownBy(() -> fileLawnmower.mowLawn("unexisting_path"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Input file invalid: unexisting_path");
    }

    @Test
    void shouldMowLawnWhenValidInputFilePath() {
        FileLawnmower fileLawnmower = new FileLawnmower();
        fileLawnmower.mowLawn("src/test/resources/simpleInput.mow");
    }

}