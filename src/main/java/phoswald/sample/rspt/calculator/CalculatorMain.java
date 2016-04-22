package phoswald.sample.rspt.calculator;

import phoswald.sample.rspt.calculator.parser.CalculatorParser;
import phoswald.sample.rspt.calculator.parser.CalculatorParser.Ref;
import phoswald.sample.rspt.calculator.parser.CalculatorParser.Ref_int;

public class CalculatorMain {

	public static void main(String[] args) {
		System.out.println("CalculatorConsole -- An expression evaluator to showcase RSPT (the Really Simple Parser Tool)");
		System.out.println("(Enter expressions to calculate or 'exit' to quit)");
		CalculatorParser parser = new CalculatorParser();
		while(true) {
			String      input  = System.console().readLine();
			Ref<String> result = new Ref<String>(null);
			Ref_int     error  = new Ref_int(0);
			if(input.equals("exit")) {
				break;
			}
			if(!parser.Parse_ROOT(input, result, error)) {
	            String next = input.length()-error.val <= 50 ? input.substring(error.val, input.length()) : input.substring(error.val, error.val + 50) + "...";
				String msg = String.format("Error at offset %d: Cannot handle '%s'.", error.val+1, next);
				System.out.println(msg);
			} else {
				String msg = "Result: " + result.val;
				System.out.println(msg);
			}
		}
	}
}
