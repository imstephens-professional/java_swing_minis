public class DriverClass {
	public static void main(String[] args) {
		StackCalculator test = new StackCalculator();
		
		// Operations test
		test.processInput("8 +      6");
		test.processInput("1+\t -18");
		test.processInput("2  * 4 * 3");
		test.processInput("25 / 5 - 6");
		test.processInput("31 % 4 * 200000000");
    System.out.println("");

		// Variable test
		test.processInput("x = 4");
		test.processInput("x * 5");
		test.processInput("x = 2**5 + 1");
		test.processInput("2 * x + 4");
		test.processInput("y=  101 - 1 - x *2");
		test.processInput("-3 * y");
		System.out.println("");
		
		// Right associative test
		test.processInput("3 ** 2 *   * 2 ** 2");
		System.out.println("");
		
		// Parentheses test; Variable continuity test
		test.processInput("([4 + 6] * x + 2 - {3 + 2})");
		System.out.println("");

		// Unary operators
		test.processInput("--40");
		test.processInput("---12");
		test.processInput("10");
		test.processInput("10 + ---5");

		// Some additional tests
		test.processInput("");
		test.processInput("((()()())())");
		test.processInput("4");
		test.processInput("((4))");
		System.out.println("");

		// Unbalanced parentheses
		test.processInput("((())())(((()))");
		test.processInput(")(");
		test.processInput("([)]");
		System.out.println("");

		// Undefined variable
		test.processInput("Y + 6 * 12 ** 3");
		System.out.println("");

		// Invalid symbol
		test.processInput("6 * 14 + 324 * 2 ~ 1 ");
		System.out.println("");

		// Invalid variable name
		test.processInput("AB = 32 + x");
		System.out.println("");

		// Nonsensical input
		test.processInput("18 *%+ 5");
		System.out.println("");
	}
} // end of DriverClass()
