package validator;
import parser.*;
import sun.applet.Main;
import validator.*;
import parser.MainParser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Tester {
	public static void main(String[] args){
//		ArrayList<String> text = new ArrayList<String>();
//		text.add("    		hello  ");
//		text.add("");
//		text.add(" ");
//		text.add("   			");
//		text.add("//");
//		text.add("//hello");
//		text.add("hi");
//		MainParser.primaryParsing(text);
//		RawLine line = MainParser.treatOpen(" if (a || 4 || 4.1 && b");

		VarObj a = new VarObj("a", MainParser.varType.DOUBLE,true,false,true);
		VarObj b = new VarObj("b", MainParser.varType.DOUBLE,true,false,true);

		ScopeObj s= new ScopeObj();
//		s.update(a);
		s.update(b);

		VarInstance dest = new VarInstance("c", MainParser.varType.UNKNOWN);
		VarInstance src = new VarInstance("b", MainParser.varType.UNKNOWN);
//		src=null;
//		VarInstance[] arr = new VarInstance[2];
//		arr[0]=at;
//		arr[1]=bt

		VarOperation varOperation = new VarOperation(src,dest);
		VarOperation[] arr = new VarOperation[1];
		arr[0]=varOperation;

		VariableLine vl=new VariableLine(false,arr);
		System.out.println(vl.isValid(s));
		System.out.println("gil");


	}
}
