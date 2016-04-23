package phoswald.sample.rspt.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import phoswald.sample.rspt.calculator.parser.CalculatorParser;
import phoswald.sample.rspt.calculator.parser.CalculatorParser.Ref;
import phoswald.sample.rspt.calculator.parser.CalculatorParser.Ref_int;

public class CalculatorMain {

	public static void main(String[] args) throws IOException {
		System.out.println("sample-rspt-calculator -- A simple calculator using RSPT (the Really Simple Parser Tool)");
		System.out.println("[Enter expressions to calculate or an empty line to quit]");
		BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));
		CalculatorParser parser = new CalculatorParser();
		while(true) {
		    String input = systemIn.readLine();
		    if(input == null || input.isEmpty()) {
		        break;
		    }
			Ref<String> result = new Ref<String>(null);
			Ref_int error  = new Ref_int(0);
			if(!parser.Parse_ROOT(input, result, error)) {
                System.out.println("Syntax Error:");
			    System.out.println("- input:   '" + input + "'");
			    System.out.println("- position: " + pad(' ', error.val) + "^");
			} else {
				System.out.println("Result: " + result.val);
			}
		}
	}

	private static String pad(char character, int length) {
	    return new String(new char[length]).replace('\u0000', character);
	}
}
