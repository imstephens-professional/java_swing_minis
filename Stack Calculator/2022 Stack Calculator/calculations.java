/* 
 * Imports
 */ 
import java.util.ArrayList;

/* Portion that does the Shunting Yard Algorithm + actual calculations
 * ANY EQUAL SIGNS DO NOT MAKE IT HERE
 */

public class calculations {
	public void beginCalc(ArrayList<String> p, int problems, Variable v) {
		StackCalculator sk = new StackCalculator();

		// What's listed as current problems the previous class automatically flags:
		// Symbols are matched incorrectly; parentheses are unbalanced
		if (problems > 0) {
			System.out.println("INPUT INVALID");
			// makes sure the input is long enough to calculate to begin with

		} else if (p.size() == 1) {
			System.out.println(p.get(0));
		} else if (p.size() < 3) {
			System.out.println("INPUT TOO SMALL");

		} else {
			// check to see if any operators are directly next to each other OR at the very
			// beginning/end
			if (operatorMashing(p)) {
				System.out.println("INCORRECT SYNTAX");

			} else {
				// set infix to postfix
				p = ShuntingYardAlg(p); // gets rid of parentheses

				// checks to see if there is a variable present
				int flag = 0;
				for (int i = 0; i < p.size(); i++) {
					String tempOR = p.get(i);
					int size = tempOR.length();
					if (sk.isLetter(tempOR.charAt(0))) {
						flag++;
						// charAt(0) = -, size is bigger than 1, and charAt(1) is a letter
						// the size prevents the error of the next check
					} else if (tempOR.charAt(0) == '-' && size > 1 && sk.isLetter(tempOR.charAt(1))) {
						flag++;
					}
				}

				// if either the value or variable is default and there is a var present in the
				// input
				// submit error
				if ((v.getValue().equals("-9999") || v.getVar().equals(null)) && flag > 0) {
					System.out.println("ERROR: VARIABLE NOT INSTANTIATED");

				} else {
					// replaces all of the variables in the expression with the needed value
					p = v.replaceVars(p);

					// if there is a variable left, then it's not supposed to be there
					int flagVars = 0;
					for (int i = 0; i < p.size(); i++) {
						String tempCheck = p.get(i);
						boolean thisOne = sk.isLetter(tempCheck.charAt(0));
						if (thisOne == true) {
							flagVars++;
						}
					}

					if (flagVars > 0) {
						System.out.println("ERROR: VARIABLE NOT INSTANTIATED");
					} else {

						// finally, evaluate expression
						String finalResult = actualCalculation(p);

						if (finalResult.equals("ERROR")) {
							System.out.println("ERROR: CANNOT DIVIDE BY ZERO");
						} else if (finalResult.equals("#")) {
							System.out.println(0);
						} else {
							System.out.println(finalResult);
						}
					}
				}

			}
		}

		// end of beginCalc
	}

	// Pre: ArrayList
	// Post: boolean
	// Purpose: Checks to see if there are an improper amount of operators next to
	// each other
	public boolean operatorMashing(ArrayList<String> p) {
		int ALLength = p.size();
		int index = 0;
		int operatorTog = 0;

		String tempThis = p.get(0);
		if (isOperator(tempThis.charAt(0)) && tempThis.length() == 1) {
			return true;
		}

		while (ALLength > 0) {
			String temp = p.get(index);
			if (isOperator(temp.charAt(0)) && temp.length() == 1) {
				operatorTog += 1;
				if (operatorTog > 1) {
					return true;
				}
			} else {
				operatorTog = 0;
			}

			index++;
			ALLength--;
		}

		if (operatorTog > 0) {
			return true;
		}
		return false;
	}

	// Pre: ArrayList containing properly parsed symbols
	// Post: ArrayList
	// Purpose: Goes through the infix ArrayList and rearranges so everything is in
	// postfix
	public ArrayList<String> ShuntingYardAlg(ArrayList<String> p) {
		ArrayList<String> temp = new ArrayList<String>();
		StackCalculator sc = new StackCalculator();

		Stack sp = new Stack();
		String hashTic = "#";
		Node bottomStack = new Node(hashTic);
		sp.push(bottomStack); // BOTTOM OF STACK WILL ALWAYS HAVE # so we don't run into null problem

		int length = p.size(); // will go through the AL one symbol at a time
		int index = 0;

		// goes through AL until everything is inside temp
		while (length > 0) {
			String fullString = p.get(index);
			char charString = fullString.charAt(0); // this can make things simpler
			int stringLength = fullString.length(); // can help in first instance

			// if num or char, immediately add to temp
			if ((stringLength > 1 && charString != '*') || sc.isLetter(charString) || sc.isNum(charString)) {
				temp.add(fullString); // immediately add to ArrayList as-is

				// if string is a left parentheses, push onto stack
			} else if (fullString.equals("(") || fullString.equals("{") || fullString.equals("[")) {
				Node leftParenth = new Node(fullString);
				sp.push(leftParenth);

			} else if (isOperator(charString)) { // string is established as an operator
				// precedence and associativity of the operator
				int currPrec = opPrecedence(fullString);
				boolean currAssoc = opLeftAssoc(fullString);
				// System.out.println("Op: " + fullString + ", Prec: " + currPrec + ", Assoc: "
				// + currAssoc);

				Node headNow = sp.peek();
				String dataHead = headNow.getName();

				// if the stack is empty or a left parentheses, push operator onto stack
				if (dataHead.equals("#") || dataHead.equals("{") || dataHead.equals("(") || dataHead.equals("{")) {
					Node autoPush = new Node(fullString);
					sp.push(autoPush);

				} else {
					// pop off any node with higher or equal precedence to the
					while (!sp.peek().getName().equals("#") && currAssoc == true
							&& (currPrec <= opPrecedence(sp.peek().getName()))) {
						Node Op2 = sp.pop();
						String Op2Info = Op2.getName();
						temp.add(Op2Info);
					}
					// always push the current operator into the stack
					Node Op1 = new Node(fullString);
					sp.push(Op1);

				}

			} else if (charString == ')') {
				while (!sp.peek().getName().equals("(")) {
					Node item = sp.pop();
					String itemData = item.getName();
					temp.add(itemData);
				}
				sp.pop();

			} else if (charString == '}') {
				while (!sp.peek().getName().equals("{")) {
					Node item = sp.pop();
					String itemData = item.getName();
					temp.add(itemData);
				}
				sp.pop();

			} else if (charString == ']') {
				while (!sp.peek().getName().equals("[")) {
					Node item = sp.pop();
					String itemData = item.getName();
					temp.add(itemData);
				}
				sp.pop();

			}
			// pushes through AL
			index++;
			length--;
		}

		// if anything is left in the stack, add it to the AL
		while (!sp.peek().getName().equals("#")) {
			Node item = sp.pop();
			String itemData = item.getName();
			temp.add(itemData);
		}

		return temp;

	}

	// Pre:
	// Post:
	// Purpose: SHOULD NOT HAVE ANYTHING EXCEPT NUMBERS AND OPERATORS AT THIS POINT
	public String actualCalculation(ArrayList<String> p) {
		int length = p.size();
		int index = 0;
		StackCalculator sl = new StackCalculator();
		// creates stack and bottom of stack
		Stack finalSt = new Stack();
		String hashTic = "#";
		Node bottomStack = new Node(hashTic);
		finalSt.push(bottomStack);

		// if num, push in stack
		// when you reach operator, pop out two nums and evaluate
		// push result back onto stack
		// repeat
		// nothing should be left in the stack when you're done
		while (length > 0) {
			String str = p.get(index);
			int strLength = str.length();
			char strChar = str.charAt(0);

			// if current string is a num or var, push into stack
			if ((strLength > 1 && strChar != '*') || sl.isNum(strChar)) {
				Node calc = new Node(str);
				finalSt.push(calc);

			} else if (isOperator(strChar)) {
				Node b = finalSt.pop();
				Node a = finalSt.pop();
				String B = b.getName();
				String A = a.getName();
				int apple = Integer.parseInt(A);
				int banana = Integer.parseInt(B);

				int c = officialCalc(str, apple, banana);
				if (c == -9999) {
					return "ERROR";
				}
				String tempc = Integer.toString(c);
				Node here = new Node(tempc);

				finalSt.push(here);

			}

			index++;
			length--;
		}

		// pop off final result of stack calc
		Node finalOne = finalSt.pop();

		return finalOne.getName();

	}

	// Pre: String with the operation, 2 nums from nodes
	// Post: int result
	// Purpose: calculates the two popped off nodes and returns the result
	public int officialCalc(String z, int a, int b) {
		int c = 0;

		switch (z) {
		case "**":
			c = (int) Math.pow(a, b);
			return c;

		case "*":
			c = a * b;
			return c;

		case "/":
			if (b == 0) {
				return -9999;
			}
			c = a / b;
			return c;

		case "%":
			c = a % b;
			return c;

		case "+":
			c = a + b;
			return c;

		case "-":
			c = a - b;
			return c;

		}

		return c;
	}

	// Pre: accepts char
	// Post: returns true
	// Purpose: returns true if there is an operator present
	public boolean isOperator(char temp) {
		String allowedOperators = "*-/+%";

		for (int i = 0; i < allowedOperators.length(); i++) {
			if (allowedOperators.charAt(i) == temp) {
				return true;
			}
		}

		return false;
	}

	// Pre: String
	// Post: int precedence
	// Purpose: Checks the precedence of the operator
	public int opPrecedence(String elementZ) {
		if (elementZ.equals("**")) {
			return 3;
		} else if (elementZ.equals("*") || elementZ.equals("/") || elementZ.equals("%")) {
			return 2;
		} else if (elementZ.equals("+") || elementZ.equals("-")) {
			return 1;
		}

		// base case
		return -1;
	}

	// Pre: String
	// Post: boolean left associative
	// Purpose: says whether the operator is left associative
	public boolean opLeftAssoc(String elementZ) {
		if (elementZ.equals("+") || elementZ.equals("-") || elementZ.equals("/") || elementZ.equals("%")
				|| elementZ.equals("*")) {
			return true;
		}

		return false;
	}

}
