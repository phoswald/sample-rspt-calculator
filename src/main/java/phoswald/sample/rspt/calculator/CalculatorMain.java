package phoswald.sample.rspt.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import phoswald.sample.rspt.calculator.parser.CalculatorParser;

public class CalculatorMain {

	public static void main(String[] args) throws IOException {
		System.out.println("sample-rspt-calculator - A simple calculator using RSPT (the Really Simple Parser Tool)");
        System.out.println("See: https://github.com/phoswald/sample-rspt-calculator - (C) 2016 Philip Oswald");
		System.out.println("[Enter expressions to calculate or an empty line to quit]");
		runLoop(new BufferedReader(new InputStreamReader(System.in)), System.out);
	}

	public static void runLoop(BufferedReader input, PrintStream output) throws IOException {
        CalculatorParser parser = new CalculatorParser();
        while(true) {
            String line = input.readLine();
            if(line == null || line.isEmpty()) {
                break;
            }
            try {
                output.println("Result: " + parser.Parse_ROOT(line));
            } catch(CalculatorParser.ParserException e) {
                output.println("Syntax Error:");
                output.println("- input:   '" + e.getInput() + "'");
                output.println("- position: " + pad(' ', e.getPosition()) + "^");
            }
        }
	}

	private static String pad(char character, int length) {
	    return new String(new char[length]).replace('\u0000', character);
	}
}
