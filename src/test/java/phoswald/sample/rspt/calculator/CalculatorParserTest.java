package phoswald.sample.rspt.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Test;

import phoswald.sample.rspt.calculator.parser.CalculatorParser;

public class CalculatorParserTest {

    private CalculatorParser testee = new CalculatorParser();

    @Test
    public void testPredefinedSymbols() {
        assertResult(Math.PI, "pi");
        assertResult(Math.E, "e");
        assertResult(0.0, "x");
        assertResult("Copyright (C) 2013 Philip Oswald", "About");
        assertResult("Version 1.11 for Java", "Version");
    }

    @Test
    public void testNumbers() {
        assertResult("42.0", "42");
        assertResult("-42.0", "0-42");
    }

    @Test
    public void testSimpleMath() {
        assertResult("3.0", "1+2");
        assertResult("2.0", "1*2");
        assertResult("3.0", "10-3-4");
        assertResult("11.0", "10-(3-4)");
        assertResult("42.1", "42+(1/10)");
        assertResult("42.1", "42+1/10");
    }

    @Test
    public void testStatefulVariables() {
        assertResult("3.0", "x=1+2");
        assertResult("6.0", "y=x+x");
        assertResult("36.0", "y=y*y");
        assertResult("36.0", "y");
    }

    @Test
    public void testSyntaxErrors() {
        assertError(1, "1+");
        assertError(7, "1+1+1+1+(1+)+3");
    }

    private void assertResult(double expectedResult, String input) {
        assertResult(Double.toString(expectedResult), input);
    }

    private void assertResult(String expectedResult, String input) {
        assertEquals(expectedResult, testee.parseROOT(input));
    }

    private void assertError(int expectedOffset, String input) {
        try {
            testee.parseROOT(input);
            fail();
        } catch(CalculatorParser.ParserException e) {
            assertEquals(expectedOffset, e.getPosition());
            assertSame(input, e.getInput());
        }
    }
}
