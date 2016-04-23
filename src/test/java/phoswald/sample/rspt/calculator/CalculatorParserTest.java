package phoswald.sample.rspt.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import phoswald.sample.rspt.calculator.parser.CalculatorParser;
import phoswald.sample.rspt.calculator.parser.CalculatorParser.Ref;
import phoswald.sample.rspt.calculator.parser.CalculatorParser.Ref_int;

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

    private void assertResult(double expectedResult, String expression) {
        assertResult(Double.toString(expectedResult), expression);
    }

    private void assertResult(String expectedResult, String expression) {
        Ref<String> actualResult = new Ref<>(null);
        Ref_int pos = new Ref_int(0);
        assertTrue(testee.Parse_ROOT(expression, actualResult, pos));
        assertEquals(expectedResult, actualResult.val);
    }

    private void assertError(int expectedOffset, String expression) {
        Ref<String> actualResult = new Ref<>(null);
        Ref_int pos = new Ref_int(0);
        assertFalse(testee.Parse_ROOT(expression, actualResult, pos));
        assertEquals(expectedOffset, pos.val);
    }
}
