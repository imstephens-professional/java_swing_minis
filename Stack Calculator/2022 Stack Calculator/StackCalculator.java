package lookethhere;

/*	Made by: 
 * 		Isabella Stephens
 * 	Made on: 
 * 		7 September 2022
 * 	Updated on: 
 * 		21 October 2022
 * 
 * this is a guide on how to write code that works, but works in a very dubious manner
 * i apologize in advance
 */

import java.util.ArrayList;
/* Progress:
 * Summary: Takes a String from the driver and categorizes them into an ArrayList. It distinguishes between the 
 * char's '(', '[', ')', ']'; the operators '+', '-', '/', '%', '*', and '**'; positive and negative numbers; 
 * positive and negative variables; and the '=' sign. Any whitespace and tab markers are ignored by the program.
 */

public class StackCalculator {
	public void processInput(String s) {
		int problems = 0; // will count how many problems are present
		calculations cc = new calculations(); // for the calculations class

		s = spaceDelete(s); // Deletes excess space

		// checks to see if all the symbols can be utilized by Stack Calculator
		// increases problems by 1 if there is even a single symbol that doesn't match
		boolean symbolsAllowed = symbolCheck(s);
		if (symbolsAllowed == false) {
			System.out.println("ERROR: INVALID SYMBOL(S)");
			problems += 1;
		}

		int stringLength = s.length(); // give length of string
		ArrayList<String> processedString = new ArrayList<String>(); // final place to put String pieces
		
		int eqFlag = 0; // flag for equality symbol
		int delCounter; // counts how many characters must be deleted from the string

		// loops until everything in the string is gone
		while (stringLength > 0) {
			delCounter = 1; // base case for deletion- will always delete 1 char from string
			char temp = s.charAt(0); // stores the first char in the string

			// check for equality symbol present
			if (temp == '=') {
				eqFlag = 1;
			}

			// FOR THE * AND ** SYMBOLS
			if (temp == '*' && stringLength == 1) { // catches if length is only 1
				processedString.add(Character.toString(temp));

			} else if (temp == '*' && s.charAt(1) == '*') { // checks for **
				delCounter = 2;
				String temp2 = "";
				temp2 += Character.toString(temp);
				temp2 += Character.toString(s.charAt(1));
				processedString.add(temp2);

			} else if (temp == '*') { // otherwise single *
				processedString.add(Character.toString(temp));

				// FOR THE - AND NEGATIVE NUMS/VARIABLES
				// the trickiest worst part of this code
				// this makes everything else look neat
			} else if (temp == '-') {

				if (stringLength == 1) { // NO SYMBOL AFTER -; AN ERROR
					processedString.add(Character.toString(temp));
					problems++;
					break;
				}

				int minusCounter = minusCount(s); // counts how many - are present adjacent to each other
				int ALSize = processedString.size(); // to see if there's anything in the AL currently
				int flagShip = 0;
				String prevString = "";

				if (ALSize == 0) { // no other symbol in AL
					prevString = "ZEBA";
				} else { // get previous
					prevString = processedString.get(ALSize - 1);
				}

				boolean isOperator = cc.isOperator(prevString.charAt(0));
				
				// SINGLE MINUS WITH A NUM AFTER IT
				if (minusCounter == 1 && isNum(s.charAt(1)) && ((isOperator == true && prevString.length() < 2) || prevString.equals("ZEBA")
						|| prevString.equals("{") || prevString.equals("(") || prevString.equals("["))) { 
					String tempS = s.substring(1);
					delCounter = numCount(tempS); // counts how many nums are adjacent
					String extra = numLump(tempS, delCounter);
					String temp2 = "";
					temp2 += s.charAt(0);
					temp2 += extra;
					delCounter += 1;
					processedString.add(temp2);

				} else if (minusCounter == 1 && isLetter(s.charAt(1))
						&& ((isOperator == true && prevString.length() < 2) || prevString.equals("ZEBA") || prevString.equals("{")
								|| prevString.equals("(") || prevString.equals("["))) { 
					delCounter = 2;
					String temp2 = "";
					temp2 += s.charAt(0);
					temp2 += s.charAt(1);

					processedString.add(temp2);

				} else if (minusCounter == 1) { // breaks otherwise
					processedString.add(Character.toString(temp));

				} else { // there is more than one -

					if (stringLength == minusCounter) { // the rest of the spots are just -
						delCounter = minusCounter;
						for (int i = 0; i < delCounter; i++) {
							processedString.add(Character.toString(temp));
						}

					} else { // a bunch of - with no purpose. add em into the ArrayList bc why not
						delCounter = minusCounter - 1; // counts how many numbers there are in a row

					}

				}

				// if char IS A NUM WITHOUT ANY - IN FRONT
			} else if (isNum(temp)) {
				delCounter = numCount(s); // counts how many numbers there are in a row
				String temp2 = numLump(s, delCounter); // lumps individual ints together

				processedString.add(temp2);

				// if nothing is caught, just add to arraylist anyway
			} else {
				processedString.add(Character.toString(temp));
			}

			// MOST IMPORTANT: REMOVES SELECTED CHARS AND REEVALUATES STRING LENGTH
			s = removeCharacter(s, delCounter);
			stringLength = s.length();
		}

		// checks to see if the parentheses are balances
		boolean isBalanced = isParBalanced(processedString);
		if (isBalanced == false) {
			problems += 1;
			System.out.println("ERROR: UNBALANCED PARENTHESES");
		}
		Variable v = new Variable();

		// if there is an eq sign, set var; otherwise, proceed as normal
		if (eqFlag > 0) { 
			v.varSettingUp(processedString);
			

		} else {
			cc.beginCalc(processedString, problems, v);
		}

		System.out.println(" ");

	}

	// Pre: String with whitespace
	// Post: String without whitespace
	// Method: Takes a String and removes the whitespace/tabs in it
	public String spaceDelete(String s) {
		String processedOne = "";

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ') {
				continue;
			} else if (s.charAt(i) == '\t') { // this took way too long to figure out it counted a single character
				continue;
			} else { // only adds to String if not whitespace or tab
				processedOne += s.charAt(i);
			}
		}

		return processedOne;
	}

	// Pre: String without whitespace, int
	// Post: String with certain amount of char removed from the front
	// Purpose: Takes the String and removes a certain amount of chars from the
	// front based on the int parameter
	public String removeCharacter(String s, int count) {
		// if String is empty or count wanted removed is longer than String itself
		if (s == null) {
			return s;
		}

		int stringLength = s.length();
		if (count > stringLength) { // if how many you want deleted is bigger than the length, then set count to
									// length
			count = stringLength;
		}

		String newS = s.substring(count);

		return newS;
	}

	// Pre: Char array containing single chars of unprocessed string, length of og
	// array
	// Post: String array
	// Purpose: Converts chars to Strings to make it easier to work with
	public String[] charToString(char[] p, int length) {
		String[] tempArray = new String[length];

		for (int i = 0; i < length; i++) {
			char temp = p[i];
			String tempTwo = Character.toString(temp);
			tempArray[i] = tempTwo;
		}

		return tempArray;
	}

	// Pre: Selected char from array
	// Post: boolean value
	// Purpose: Evaluates whether the char in the array is a number.
	public boolean isNum(char temp) {
		switch (temp) {
		case '0':
			return true;
		case '1':
			return true;
		case '2':
			return true;
		case '3':
			return true;
		case '4':
			return true;
		case '5':
			return true;
		case '6':
			return true;
		case '7':
			return true;
		case '8':
			return true;
		case '9':
			return true;
		}

		return false;
	}

	// Pre: char from String input
	// Post: true or false whether a letter is detected
	// Purpose: Goes through 2 strings checking to see if the character matches any
	// of the chars
	public boolean isLetter(char p) {
		String capitalized = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowercase = "abcdefghijklmnopqrstuvwxyz";
		int length = lowercase.length();

		for (int i = 0; i < length; i++) {
			if (capitalized.charAt(i) == p) {
				return true;
			}
		}

		for (int i = 0; i < length; i++) {
			if (lowercase.charAt(i) == p) {
				return true;
			}
		}

		return false;
	}

	// Pre: og string
	// Post: true or false
	// Purpose: checks to see if all symbols are "allowed"
	public boolean symbolCheck(String s) {
		int stringLength = s.length();
		int totalCount = 0;
		String allowedOperators = "*()[]{}-/+=%";

		for (int j = 0; j < stringLength; j++) {
			for (int i = 0; i < allowedOperators.length(); i++) {
				if (allowedOperators.charAt(i) == s.charAt(j)) {
					totalCount += 1;
				}
			}
		}

		for (int j = 0; j < stringLength; j++) {
			if (isNum(s.charAt(j))) {
				totalCount += 1;
			}
		}

		for (int j = 0; j < stringLength; j++) {
			if (isLetter(s.charAt(j))) {
				totalCount += 1;
			}
		}

		if (totalCount == stringLength) {
			return true;
		}

		return false;
	}

	// Pre: String without whitespace
	// Post: int
	// Purpose: Checks to see the length of the numbers and returns their length.
	// Detects when to stop counting by itself.
	public int numCount(String s) {
		int counter = 0;

		if (s == null) { // there is no num??
			return 0;
		} else if (s.length() == 1) { // there is only 1 num
			counter += 1;
			return counter;
		}

		int stringCounter = s.length();
		int index = 0;
		while (isNum(s.charAt(index))) { // while this char IS a number...
			counter++;
			stringCounter--;

			if (stringCounter <= 0) {
				break;
			}

			index++;
		}

		return counter;
	}

	// Pre: string containing the nums, amount of nums found in string currently
	// Post: the nums adjacent to each other in a single string
	// Purpose: Groups the numbers together
	public String numLump(String s, int delCounter) {
		String temp2 = "";
		for (int i = 0; i < delCounter; i++) {
			temp2 += Character.toString(s.charAt(i));
		}
		return temp2;
	}

	// Pre: string
	// Post: how many - symbols present
	// Purpose: counts how many - are adjacent to each other
	public int minusCount(String s) {
		int counter = 0;

		if (s == null) { // there is no num??
			return 0;
		} else if (s.length() == 1) { // there is only 1 num
			counter += 1;
			return counter;
		}

		int stringCounter = s.length();
		int index = 0;
		while (s.charAt(index) == '-') { // while this char IS a number...
			counter++;
			stringCounter--;

			if (stringCounter <= 0) {
				break;
			}

			index++;
		}

		return counter;
	}

	// Pre: Character Arraylist of parsed-through String
	// Post: true false based on whether the parenthesis are balanced or not
	// Purpose: To detect whether the parenthesis are balanced or not
	public boolean isParBalanced(ArrayList<String> p) {
		Stack sk = new Stack();
		int counter = 0;

		// pushes left parentheses into stack and pops them out if the next element in
		// the ArrayList is the matching set
		for (int i = 0; i < p.size(); i++) {
			Node here = sk.peek();
			if (p.get(i).equals("(") || p.get(i).equals("[") || p.get(i).equals("{")) {
				String temp = p.get(i);
				Node temp2 = new Node(temp);
				sk.push(temp2);

			} else if (p.get(i).equals(")") && sk.head == null || p.get(i).equals("]") && sk.head == null
					|| p.get(i).equals("}") && sk.head == null) {
				counter++;
				break;

			} else if (p.get(i).equals(")") && here.getName().equals("(")) {
				sk.pop();

			} else if (p.get(i).equals("]") && here.getName().equals("[")) {
				sk.pop();

			} else if (p.get(i).equals("}") && here.getName().equals("{")) {
				sk.pop();

			}
		}

		// if there is a }, ), or ] in the array when the stack is empty, returns
		// unbalanced
		if (counter > 0) {
			System.out.println("ERROR: TOO MANY RIGHT PARENTHESES");
			return false;
		}

		if (sk.head == null) {
			return true;
		}

		return false;

	}

}
