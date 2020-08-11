package com.mowercompany.lawnmower;


import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
class LawnmowerApplicationTest implements WithAssertions {

    @Test
    void shouldThrowIllegalArgumentExceptionWhenNoArgs() {
        assertThatThrownBy(() -> LawnmowerApplication.main(new String[]{}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Only one argument (input file path) is permitted");
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenMoreThan1Args() {
        assertThatThrownBy(() -> LawnmowerApplication.main(new String[]{"john", "doe"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Only one argument (input file path) is permitted");
    }

    @Test
    void shouldWorkWithValidFilePathAsArgument() {
        LawnmowerApplication.main(new String[]{"src/test/resources/simpleInput.mow"});
    }
}
