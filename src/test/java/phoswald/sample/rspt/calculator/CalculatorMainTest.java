package phoswald.sample.rspt.calculator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class CalculatorMainTest {

    private final BufferedReader input = mock(BufferedReader.class);
    private final PrintStream output = mock(PrintStream.class);

    @Test
    void testRunLoopNoInput() throws IOException {
        // Act
        CalculatorMain.runLoop(input, output);

        // Assert
        verify(input).readLine();
        verifyNoMoreInteractions(input);
        verifyNoInteractions(output);
    }

    @Test
    void testRunLoopValidInput() throws IOException {
        // Arrange
        when(input.readLine()).thenReturn("1+2", "x=4+5", "x*x", "");

        // Act
        CalculatorMain.runLoop(input, output);

        // Assert
        verify(input, times(4)).readLine();
        verifyNoMoreInteractions(input);
        verify(output).println("Result: 3.0");
        verify(output).println("Result: 9.0");
        verify(output).println("Result: 81.0");
        verifyNoMoreInteractions(output);
    }

    @Test
    void testRunLoopInvalidInput() throws IOException {
        // Arrange
        when(input.readLine()).thenReturn("1+2++3+4", "");

        // Act
        CalculatorMain.runLoop(input, output);

        // Assert
        verify(input, times(2)).readLine();
        verifyNoMoreInteractions(input);
        verify(output).println("Syntax Error:");
        verify(output).println("- input:   '1+2++3+4'");
        verify(output).println("- position:    ^");
        verifyNoMoreInteractions(output);
    }
}
