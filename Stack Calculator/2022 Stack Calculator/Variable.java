/* 
 * Imports
 */ 
import java.util.ArrayList;

public class Variable {
	static String var = null;
	static String value = "-9999";

	public void varSettingUp(ArrayList<String> z) {
		calculations cc = new calculations();

		String newVar = z.get(0);
		String test = z.get(1);

		if (!test.equals("=")) {
			System.out.println("ERROR: INVALID VARIABLE NAME");
		} else {
			// remove first 2 elements
			z.remove(0);
			z.remove(0);
			z = cc.ShuntingYardAlg(z);

			if (var != null) {
				z = replaceVars(z);
			}
			setVar(newVar);
			String result = cc.actualCalculation(z);
			value = result;

			// tells user that the variable is set
			System.out.println(var + " is set to " + value);
		}
	}

	public ArrayList<String> replaceVars(ArrayList<String> z) {
		ArrayList<String> temp = new ArrayList<String>();
		int length = z.size();
		int index = 0;

		while (length > 0) {
			String temporary = z.get(index);
			if (temporary.equals(var)) {
				temp.add(value);

			} else {
				temp.add(temporary);
			}

			index++;
			length--;
		}

		return temp;
	}

	public void setVar(String here) {
		var = here;
	}

	public String getVar() {
		return var;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String here) {
		value = here;
	}

}
