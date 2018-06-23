
package validator;
import parser.MainParser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Tester {
	public static void main(String[] args){
		ArrayList<String> text = new ArrayList<String>();
		text.add("    		hello  ");
		text.add("");
		text.add(" ");
		text.add("   			");
		text.add("//");
		text.add("//hello");
		text.add("hi");
		MainParser.primaryParsing(text);

	}
}
