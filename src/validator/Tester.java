package validator;
import parser.*;
import validator.*;
import parser.MainParser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Tester {
	public static void main(String[] args){
		//ArrayList<String> text = new ArrayList<String>();
		//text.add("    		hello  ");
		//text.add("");
		//text.add(" ");
		//text.add("   			");
		//text.add("//");
		//text.add("//hello");
		//text.add("hi");
		//MainParser.primaryParsing(text);
		//RawLine line = MainParser.treatOpen(" while (abc || true|| 4.1 && b){");
		//Pattern pattern = Pattern.compile(MainParser.CHECK_ASSIGNMENT);
		//System.out.println();

		MainParser.treatSingle("final int a = b;");
		System.out.println();


	}
}
