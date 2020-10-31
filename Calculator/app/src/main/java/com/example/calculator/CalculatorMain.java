package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

import static com.example.calculator.R.id.button0;
import static com.example.calculator.R.id.button1;
import static com.example.calculator.R.id.button2;
import static com.example.calculator.R.id.button3;
import static com.example.calculator.R.id.button4;
import static com.example.calculator.R.id.button5;
import static com.example.calculator.R.id.button6;
import static com.example.calculator.R.id.button7;
import static com.example.calculator.R.id.button8;
import static com.example.calculator.R.id.button9;
import static com.example.calculator.R.id.buttonAdd;
import static com.example.calculator.R.id.buttonClear;
import static com.example.calculator.R.id.buttonClosing;
import static com.example.calculator.R.id.buttonDecimal;
import static com.example.calculator.R.id.buttonDivide;
import static com.example.calculator.R.id.buttonEquals;
import static com.example.calculator.R.id.buttonExponent;
import static com.example.calculator.R.id.buttonMultiply;
import static com.example.calculator.R.id.buttonOpening;
import static com.example.calculator.R.id.buttonSubtract;
import static com.example.calculator.R.id.display;
import static java.lang.Double.parseDouble;
import static java.lang.Math.pow;
import static java.lang.Math.round;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.util.stream.Stream.of;


public class CalculatorMain extends AppCompatActivity {
	/**
	 * {@code layout1} contains all of the {@linkplain Button}s from the first spread of buttons.
	 *//* All of these vanish while the second spread ({@link #layout2}) is visible.*/
	Button[] layout1;
	/**
	 * {@code layout2} contains all of the {@linkplain Button}s from the second spread of buttons. All of these vanish while the first spread ({@link #layout1}) is visible.
	 */
//	Button[] layout2;
	/**
	 * This is the {@link TextView} that displays the actual inputs in the app.
	 */
	TextView displayed;
	/**
	 * These are the encasing {@linkplain LinearLayout}s that contains both button spreads, and have been declared to make it possible to toggle between the spreads.
	 */
//	LinearLayout[] linearLayouts;
	/**
	 * This is the {@linkplain StringBuilder} that is used for actually parsing the operations (It is different because it will use a lot more shorthand for simpler parsing).
	 */
	private StringBuilder parse = new StringBuilder();

	// A NOTE: I HAVE DISABLED THE TRIG FUNCTIONS, BOTH DUE TO A LACK OF TIME AND TROUBLE IMPLEMENTING THE FUNCTIONS INTO THE ALGORITHM IN A TIMELY MANNER
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		layout1 = new Button[]{
				findViewById(buttonClear),
//				findViewById(button2nd),
				findViewById(button1),
				findViewById(button2),
				findViewById(button3),
				findViewById(buttonAdd),
				findViewById(button4),
				findViewById(button5),
				findViewById(button6),
				findViewById(buttonSubtract),
				findViewById(button7),
				findViewById(button8),
				findViewById(button9),
				findViewById(buttonMultiply),
				findViewById(buttonEquals),
				findViewById(button0),
				findViewById(buttonDecimal),
				findViewById(buttonDivide),
				findViewById(buttonOpening),        // Opening Parenthesis
				findViewById(buttonClosing),
				findViewById(buttonExponent)};       // Closing Parenthesis
		/*layout2 = new Button[]{
				findViewById(buttonClearv2),
				findViewById(button2ndv2),
				findViewById(buttonSine),
				findViewById(buttonCosine),
				findViewById(buttonTangent),
				findViewById(buttonInvSine),        // Inverse Sine
				findViewById(buttonInvCosine),      // Inverse Cosine
				findViewById(buttonInvTangent),     // Inverse Tangent
				findViewById(buttonCosecant),
				findViewById(buttonSecant),
				findViewById(buttonCotangent),
				findViewById(buttonClosingv2),      // Closing Parenthesis
				findViewById(buttonSqrt)};          // Square Root*/
		displayed = findViewById(display);
		/*linearLayouts = new LinearLayout[]{
				findViewById(layoutPrimary),
				findViewById(layoutSecondary)};*/
//		Clear button for spread one
		layout1[0].setOnClickListener(this::clear);
/*//		Clear button for spread two
		layout2[0].setOnClickListener(this::clear);*/
/*//		Second button for spread one
		layout1[1].setOnClickListener(v -> {
			linearLayouts[0].setVisibility(GONE);
			linearLayouts[1].setVisibility(VISIBLE);
		});*/
/*//		Second button for spread two
		layout2[1].setOnClickListener(v -> {
			linearLayouts[0].setVisibility(VISIBLE);
			linearLayouts[1].setVisibility(GONE);
		});*/
//		Opening parenthesis button for spread one
		layout1[17].setOnClickListener(this::openingParenthesis);
//		Closing parenthesis button for spread one
		layout1[18].setOnClickListener(this::closingParenthesis);
/*//		Closing parenthesis button for spread two
		layout2[11].setOnClickListener(this::closingParenthesis);*/
//		1 button
		layout1[1].setOnClickListener(v -> {
			displayed.setText(format("%s1", displayed.getText()));
			parse.append('1');
		});
//		2 button
		layout1[2].setOnClickListener(v -> {
			displayed.setText(format("%s2", displayed.getText()));
			parse.append('2');
		});
//		3 button
		layout1[3].setOnClickListener(v -> {
			displayed.setText(format("%s3", displayed.getText()));
			parse.append('3');
		});
//		+ button
		layout1[4].setOnClickListener(v -> {
			displayed.setText(format("%s + ", displayed.getText()));
			parse.append(" + ");
		});
//		4 button
		layout1[5].setOnClickListener(v -> {
			displayed.setText(format("%s4", displayed.getText()));
			parse.append('4');
		});
//		5 button
		layout1[6].setOnClickListener(v -> {
			displayed.setText(format("%s5", displayed.getText()));
			parse.append('5');
		});
//		6 button
		layout1[7].setOnClickListener(v -> {
			displayed.setText(format("%s6", displayed.getText()));
			parse.append('6');
		});
//		− button
		layout1[8].setOnClickListener(v -> {
			displayed.setText(format("%s − ", displayed.getText()));
			parse.append(" - ");
		});
//		7 button
		layout1[9].setOnClickListener(v -> {
			displayed.setText(format("%s7", displayed.getText()));
			parse.append('7');
		});
//		8 button
		layout1[10].setOnClickListener(v -> {
			displayed.setText(format("%s8", displayed.getText()));
			parse.append('8');
		});
//		9 button
		layout1[11].setOnClickListener(v -> {
			displayed.setText(format("%s9", displayed.getText()));
			parse.append('9');
		});
//		× button
		layout1[12].setOnClickListener(v -> {
			String x = displayed.getText() + " × ";
			displayed.setText(x);
			parse.append(" * ");
		});
//		= button
		layout1[13].setOnClickListener(this::equals);
//		0 button
		layout1[14].setOnClickListener(v -> {
			displayed.setText(format("%s0", displayed.getText()));
			parse.append('0');
		});
//		. button
		layout1[15].setOnClickListener(v -> {
			displayed.setText(format("%s\u002e", displayed.getText()));
			parse.append('.');
		});
//		÷ button
		layout1[16].setOnClickListener(v -> {
			String x = displayed.getText() + " ÷ ";
			displayed.setText(x);
			parse.append(" / ");
		});
//		^ button
		layout1[19].setOnClickListener(v -> {
			String x = displayed.getText() + "^";
			displayed.setText(x);
			parse.append(" ^ ");
		});
//		TODO: Modify both the infix-to-postfix algorithm and the postfix parser to both be able to accept trigonometric inputs
/*//		sin button
		layout2[2].setOnClickListener(v -> displayed.setText(parse.append("sin(")));
//		cos button
		layout2[3].setOnClickListener(v -> displayed.setText(parse.append("cos(")));
//		tan button
		layout2[4].setOnClickListener(v -> displayed.setText(parse.append("tan(")));
//		sin⁻¹ button
		layout2[5].setOnClickListener(v -> {
			String x = parse.toString() + R.string.inverseSine + '(';
			displayed.setText(x);
			parse.append("asi(");
		});
//		cos⁻¹ button
		layout2[6].setOnClickListener(v -> {
			String x = parse.toString() + R.string.inverseCosine + '(';
			displayed.setText(x);
			parse.append("aco(");
		});
//		tan⁻¹ button
		layout2[7].setOnClickListener(v -> {
			String x = parse.toString() + R.string.inverseTangent + ' ';
			displayed.setText(x);
			parse.append("ata(");
		});
//		csc button
		layout2[8].setOnClickListener(v -> displayed.setText(parse.append("csc(")));
//		sec button
		layout2[9].setOnClickListener(v -> displayed.setText(parse.append("sec(")));
//		cot button
		layout2[10].setOnClickListener(v -> displayed.setText(parse.append("cot(")));
//		√ button
		layout2[12].setOnClickListener(v -> displayed.setText(parse.append(R.string.u221a + '(')));*/
	}

	private void clear(View v) {
		displayed.setText("");
		parse = new StringBuilder();
	}

	private void openingParenthesis(View v) {
		String x = displayed.getText() + "(";
		displayed.setText(x);
		parse.append(" ( ");
	}

	private void closingParenthesis(View v) {
		String x = displayed.getText() + ")";
		displayed.setText(x);
		parse.append(" ) ");
	}

	private void equals(View v) {
//		While the code may be the same, these generate an (almost always) unique long ID, which is then used for error detection
		long[] randomHashID = new long[]{round(pow(this.hashCode(), 3)) - (Integer.hashCode(new Random(new Random().nextLong()).nextInt()) + (Double.hashCode(new Random(new Random().nextLong()).nextDouble()))),
				round(pow(this.hashCode(), 3)) - (Integer.hashCode(new Random(new Random().nextLong()).nextInt()) + (Double.hashCode(new Random(new Random().nextLong()).nextDouble()))),
				round(pow(this.hashCode(), 3)) - (Integer.hashCode(new Random(new Random().nextLong()).nextInt()) + (Double.hashCode(new Random(new Random().nextLong()).nextDouble()))),
				round(pow(this.hashCode(), 3)) - (Integer.hashCode(new Random(new Random().nextLong()).nextInt()) + (Double.hashCode(new Random(new Random().nextLong()).nextDouble()))),
				round(pow(this.hashCode(), 3)) - (Integer.hashCode(new Random(new Random().nextLong()).nextInt()) + (Double.hashCode(new Random(new Random().nextLong()).nextDouble()))),
				round(pow(this.hashCode(), 3)) - (Integer.hashCode(new Random(new Random().nextLong()).nextInt()) + (Double.hashCode(new Random(new Random().nextLong()).nextDouble())))};
		double parsed = new Parser(parse.toString(), randomHashID).parse();
		Object x = (parsed == randomHashID[0]) ? "Decimal Error" :
				((parsed == randomHashID[1]) ? "Error, Illegal Multiplication Attempt" :
						((parsed == randomHashID[2]) ? "Error, Illegal Operator Combination" :
								((parsed == randomHashID[3]) ? "Error, Illegal Amount of Parentheses" :
										((parsed == randomHashID[4]) ? Integer.toString(0) :
												((parsed == randomHashID[5]) ? "Error, Too Many Operands" :
														Double.valueOf(round(pow(10, 5) * new Parser(parse.toString(), randomHashID).parse()) / pow(10, 5)))))));
		displayed.setText(parse.replace(0, parse.length(), valueOf(x)));
	}


	static class Parser extends CalculatorMain {
		/**
		 * The inputted {@link String} that still has to be parsed
		 */
		private String toParse;

		private final long[] hashIDForErrorDetection;

		public Parser(@NotNull String toParse, long[] hashIDForDecimalErrorDetection) {
			this.toParse = toParse.trim();
			this.hashIDForErrorDetection = hashIDForDecimalErrorDetection;
		}

		/**
		 * This method parses the whole input. It first checks if there are a correct number of parentheses, then it determines which sub-method to hand parsing control to based on whether the expression is four-function or trigonometric, and then it finally calculates and returns the result.
		 *
		 * @return The {@code int result}, which is the final result of all calculations
		 */
		double parse() {
			if (toParse.endsWith("+ ") || toParse.endsWith("* ") || toParse.endsWith("/ ") || toParse.endsWith("- ") ||
					of("+  +",
							"+  -",
							"+  *",
							"+  /",
							"-  +",
							"-  -",
							"-  *",
							"-  /",
							"*  +",
							"*  -",
							"*  *",
							"*  /",
							"/  +",
							"/  -",
							"/  *",
							"/  /").anyMatch(toParse::contains))
				return hashIDForErrorDetection[2];
			if (!confirmParenthesis()) return hashIDForErrorDetection[3];
			if (toParse.startsWith("(") && toParse.endsWith(")"))
				toParse = toParse.substring(1, toParse.length() - 1).trim();
			return evaluatePostfix(infixToPostfix(toParse));
		}

//		TODO: Delete the following method if deemed entirely unnecessary
		/*private double parseBasic(String input) {
			while (input.contains("*") || input.contains("/"))
				if (input.indexOf("*") < input.indexOf("/")) input = parseMultiplication(input);
				else
					input = ""*//*input.substring(0, input.indexOf("/") - 2) +
							(parseDouble(Character.toString(input.charAt(input.indexOf("/") - 1))) / parseDouble(Character.toString(input.charAt(input.indexOf("/") + 1)))) +
							(input.indexOf("/") + 1 != input.length() - 1 ? input.substring(input.indexOf("/") + 2) : "")*//*;
			while (input.contains("+") || input.contains("-"))
				if (input.indexOf("+") < input.indexOf("-")) {

				} else {
				}
			return parseDouble(input);
		}

		private String parseMultiplication(String input) {
			return input.substring(0, input.substring(0, input.indexOf("*") - 1).lastIndexOf(" ")) +
					(parseDouble(input.substring(input.substring(0, input.indexOf("*") - 1).lastIndexOf(" ") + 1, input.indexOf("*") - 1)) * parseDouble(input.substring(input.indexOf()))) +
					((input.indexOf("*") + 1 != input.length() - 1) ? input.substring(input.indexOf("*") + 2) : "");
		}*/

		/**
		 * This method is for confirming there are the same number of opening and closing parentheses
		 */
		private boolean confirmParenthesis() {
			int[] count = {0, 0};
			for (char x : toParse.toCharArray())
				if (Character.toString(x).equals("(")) count[0]++;
				else if (Character.toString(x).equals(")")) count[1]++;
			return count[0] == count[1];
		}

		private @NotNull String infixToPostfix(@NotNull String infix) {
//			To find out the precedence, this method takes the index of the token in the ops string and divides by 2 (rounding down). This gives us: 0, 0, 1, 1, 2
			final String ops = "-+/*^";
			StringBuilder stringBuilder = new StringBuilder();
			Stack<Integer> stack = new Stack<>();
			String[] split = infix.split(" ");
			for (int i = 0; i < split.length; i++) {
				String token = split[i];
				if (token.isEmpty()) continue;
				int temp = 0;
				for (char x : token.toCharArray()) if (x == '.') temp++;
				if (temp >= 2) return "Error 1";
				if ((i >= 1) && split[i - 1].equals(")") || (i <= (split.length - 2)) && split[i + 1].equals("("))
					return "Error 2";
				int index = ops.indexOf(token);
//				check for operator
				if (index != -1) {
					while (!stack.isEmpty())
						if (((stack.peek() / 2) > (index / 2)) || (((stack.peek() / 2) == (index / 2)) && (!token.equals("^"))))
							stringBuilder.append(ops.charAt(stack.pop())).append(' ');
						else break;
					stack.push(index);
				} else if (token.equals("(")) stack.push(-2); // -2 stands for "("
				else if (token.equals(")")) {
//					until "(" on the stack, pop operators.
					while (stack.peek() != -2)
						stringBuilder.append(ops.charAt(stack.pop())).append(' ');
					stack.pop();
				} else stringBuilder.append(token).append(' ');
			}
			while (!stack.isEmpty()) stringBuilder.append(ops.charAt(stack.pop())).append(' ');
			return stringBuilder.toString();
		}

		private double evaluatePostfix(@NotNull String postfix) {
			switch (postfix) {
				case "Error 1":
					return (double) hashIDForErrorDetection[0];
				case "Error 2":
					return (double) hashIDForErrorDetection[1];
				default:
					LinkedList<Double> stack = new LinkedList<>();
					String[] split = postfix.split(" ");
					int[] countOpVsNum = {0, 0};
					for (String s : split)
						if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
							countOpVsNum[0]++;
						else countOpVsNum[1]++;
					if (countOpVsNum[1] - countOpVsNum[0] != 1) return hashIDForErrorDetection[2];
					for (String token : split)
						switch (token) {
							case "*": {
								double secondOperand = stack.pop();
								double firstOperand = stack.pop();
								stack.push(firstOperand * secondOperand);
								break;
							}
							case "/": {
								double secondOperand = stack.pop();
								double firstOperand = stack.pop();
								stack.push(firstOperand / secondOperand);
								break;
							}
							case "-": {
								double secondOperand = stack.pop();
								double firstOperand = stack.pop();
								stack.push(firstOperand - secondOperand);
								break;
							}
							case "+": {
								double secondOperand = stack.pop();
								double firstOperand = stack.pop();
								stack.push(firstOperand + secondOperand);
								break;
							}
							case "^": {
								double secondOperand = stack.pop();
								double firstOperand = stack.pop();
								stack.push(pow(firstOperand, secondOperand));
								break;
							}
							default:
								try {
									stack.push(parseDouble(token));
								} catch (NumberFormatException e) {
									return hashIDForErrorDetection[4];
								}
								break;
						}
					if (stack.size() == 0) return 0;
					if (stack.size() > 1) return hashIDForErrorDetection[5];
					return stack.pop();
			}
		}
	}
//	TODO: Uncomment this after the parser accepts trig inputs
		/*private boolean isFourFunction() {
			return of("sin", "cos", "tan", "asi", "aco", "ata", "csc", "sec", "cot").noneMatch(s -> toParse.contains(s));
		}*/
}