package parser;

import validator.*;
import parser.RawLine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainParser {
//needs to be singelton
	private static final String space = "\\s*";
	private static final String FINAL = "(?:final\\s+)";
	private static final String STRING_CONTENT = "\"\\S*\"";
	private static final String BOOLEAN_CONTENT = "(?:true|false)";
	private static final String IF_WHILE = "(?:if|while)";
	private static final String OPEN_FINISH = ".*(?:\\{\\s*)$";
	private static final String CLOSE = "\\s*}\\s*";
	private static final String SINGLE_LINE = ".*(?:;\\s*)$";
	private static final String VALID_NUMBER = "(?:\\d+(?:.\\d+)?)";
	private static final String INT = "\\d+";
	private static final String STRING = "\".+\"";
	private static final String CHAR = "\".\"";
	private static final String DOUBLE = "\\d+.\\d+";
	private static final String NAME_VAR_VALDIATION = "(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)";
	public static final String CONTENT =  "(?:" + VALID_NUMBER + "|" + BOOLEAN_CONTENT + "|" + STRING_CONTENT + "|" + NAME_VAR_VALDIATION + ")"; // (?:(?:\d+(?:.\d+)?)|(?:true|false)|"\S*"|(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*))
	private static final String CHECK_TYPE ="(?:int|String|char|double|boolean)";
	private static final String CONDITION = "(?:" + VALID_NUMBER + "|" + BOOLEAN_CONTENT + "|" + NAME_VAR_VALDIATION + ")"; // (?:(?:\d+(?:.\d+)?)|(?:true|false)|(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*))
	private static final String CONDITION_LINE = "(?:" + space + IF_WHILE + space + "\\((?:"+ space + CONDITION + space + "(?:\\|\\||&&))*" + space + CONDITION + space + "\\)" + space + "\\{" + space + ")"; // (?:\s*(?:if|while)\s*\((?:\s*(?:(?:\d+(?:.\d+)?)|(?:true|false)|(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*))\s*(?:\|\||&&))*\s*(?:(?:\d+(?:.\d+)?)|(?:true|false)|(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*))\s*\)\s*{\s*)
	private static final String NAME_METHOD_VALDIATION = "[a-zA-Z]+[a-zA-Z0-9_]*";
	private static final String CHECK_CONDITION = "(?:(?:\\s)*(?:true|false|"+NAME_VAR_VALDIATION+"|" + VALID_NUMBER+"))";
	private static final String PARAMS = "(?:" +space+ FINAL + "?"  + space+ CHECK_TYPE + "\\s+" + NAME_VAR_VALDIATION + space + ")"; // (?:\s*(?:int|String|char|double|boolean)\s*(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*)
	public static final String CHECK_PARAM ="(?:[(](?:" + PARAMS + ",)*" + PARAMS + "[)]"+space+"\\{\\s*)"; // (?:[(](?:(?:\s*(?:final\s+)?\s*(?:int|String|char|double|boolean)\s+(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*),)*(?:\s*(?:final\s+)?\s*(?:int|String|char|double|boolean)\s+(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*)[)]\s*\{\s*)
	public static final String CHECK_FUNC_LINE = "(?:" +space + "void"  + "\\s+" + NAME_METHOD_VALDIATION + space + CHECK_PARAM + ")"; // (?:\s*(?:void|(?:int|String|char|double|boolean))\s+[a-zA-Z]+[a-zA-Z0-9_]*\s*(?:[(](?:(?:\s*(?:int|String|char|double|boolean)\s+(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*),)*(?:\s*(?:int|String|char|double|boolean)\s+(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*)[)]\s*\{\s*))
	public static final String ASSIGNMENT = "(?:" + space + "(" + NAME_VAR_VALDIATION + ")" + space + "=" + space + "(" + CONTENT + ")" + space + ")"; //(?:\s*(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*=\s*(?:(?:\d+(?:.\d+)?)|(?:true|false)|"\S*"|(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*))\s*)
	public static final String CHECK_ASSIGNMENT = FINAL + "?" + space + CHECK_TYPE + "\\s+" + "(?:(?:(?:\\s*" + ASSIGNMENT + "\\s*|" + NAME_VAR_VALDIATION + "\\s*),)*\\s*" + "(?:\\s*" + ASSIGNMENT + "\\s*|\\s*" + NAME_VAR_VALDIATION + "\\s*);\\s*)"; // (?:final\s+)?\s*(?:int|String|char|double|boolean)\s+(?:(?:(?:\s*(?:\s*(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*=\s*(?:(?:\d+(?:.\d+)?)|(?:true|false)|"\S*"|(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*))\s*)\s*|(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*),)*\s*(?:\s*(?:\s*(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*=\s*(?:(?:\d+(?:.\d+)?)|(?:true|false)|"\S*"|(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*))\s*)\s*|\s*(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*);\s*)
	public static final String ASSIGN_WITHOUTH_DEC = "(?:,|" + CHECK_PARAM + ")" + space +"(" + NAME_VAR_VALDIATION + ")" +space + "(?:,|;)";
	private static final String RETURN = "\\s*return\\s*;\\s*";
	public static final String FUNC_CALL = space  + NAME_METHOD_VALDIATION + space +"\\((?:" + space + NAME_VAR_VALDIATION + space + ",)*" + space + NAME_VAR_VALDIATION + space + "\\)" +space +";" + space;


	/**
	 * this function receive ArrayList of Strings and delete extra spaces, empty lines and comment lines
	 * @param text the text you want to parse
	 * @return the new cleaner text
	 */
	public static ArrayList<String> primaryParsing(ArrayList<String> text){
		ArrayList<String> newText = new ArrayList<String>();
		Pattern pattern = Pattern.compile("[\\s]+");
		Matcher matcher;
		for (String row :text) {
			matcher = pattern.matcher(row);
			row = matcher.replaceAll(" ");
			if (row.equals(" ") || row.equals("") || (row.length()>=2 && row.substring(0,2).equals("//"))){
				continue;
			}
			newText.add(row);

		}
		return newText;
	}


    public enum varType {DOUBLE, INT, STRING, CHAR, BOOLEAN,UNKNOWN}
    



    public static GlobalSegment parse(ArrayList<String> lines) throws TypeOneException{
        RawLine[] parsedlines = readlines(lines);
        GlobalSegment globalSegment = new GlobalSegment();
        int len=parsedlines.length;
        CodeSegment currentParent=globalSegment;
        for (int i=0; i<len; i++){
            if(parsedlines[i].isOpen()){
                //if a new codesegment is being created
                CodeSegment newparent =(CodeSegment) parsedlines[i].transfom();
                newparent.setParent(currentParent);
                currentParent.addLine(newparent);
                currentParent = newparent;
            }else if(parsedlines[i].isClosed()){
                //a codesegment end and we now we go back to adding stuff to its parent
                if(currentParent == null)
                    //we are already in the globalsegment
                    //todo exception
                    System.out.println("a closing without an opening");
                else
                    currentParent = currentParent.getParent();
            }else{
                //adding a singleline to the current codesegment
                currentParent.addLine(parsedlines[i].transfom());
            }
        }

        //check if a segment is left open
        if(currentParent.getParent()!=null)
            //todo exception
            System.out.println("an opening without a closing");
        if(globalSegment==currentParent)
            //should apply, not a neccessary check
            return globalSegment;
        System.out.println("currentparent is not globalsegment");
        return null;

    }


    private static RawLine[] readlines(ArrayList<String> parsed_text) throws BadLineException {
        RawLine[] parsedlines=new RawLine[parsed_text.size()];
        int counter = 0;
        for (String row:parsed_text){
            try {
				RawLine line = readline(row);
				parsedlines[counter] = line;
				counter ++;
			}catch (BadLineException exception){//todo make exception for this
				throw exception;
			}

        }
        return parsedlines;

    }

    public static RawLine readline (String line) throws BadLineException {
		Pattern pattern = Pattern.compile(OPEN_FINISH);
		Matcher matcher = pattern.matcher(line);
		if (matcher.matches()){
			return treatOpen(line);

		}
		pattern = Pattern.compile(CLOSE);
		matcher = pattern.matcher(line);
		if (matcher.matches()){
			return (new CloseLine());
		}
		pattern = Pattern.compile(SINGLE_LINE);
		matcher = pattern.matcher(line);
		if (matcher.matches()){
			return treatSingle(line);
		}
		throw new BadLineException();
    }

    public static RawLine treatSingle(String line)throws BadLineException {
		Pattern pattern = Pattern.compile(CHECK_ASSIGNMENT);
		Matcher matcher = pattern.matcher(line);
		if (matcher.matches()){ // this is assignment line (with deceleration)
			ArrayList<VarOperation>  vars = new ArrayList<>();
			System.out.println("this is declaration line");
			boolean isFinal = false;
			varType type;
			pattern = Pattern.compile("(" + NAME_METHOD_VALDIATION + ")");
			matcher = pattern.matcher(line);
			matcher.find();
			if(matcher.group(0).equals("final")){
				isFinal = true;
				matcher.find();
				System.out.println("the vars are final");
			}
			type = checkType(matcher.group(0));
			pattern = Pattern.compile(ASSIGNMENT);
			matcher = pattern.matcher(line);
			String name, content;
			varType variableType;
			while (matcher.find()){
				name = matcher.group(1);
				content = matcher.group(2);
				variableType = recognizeType(content);
				System.out.println("assign " + content + "(type " + variableType + ") to " + name + "(type " + type + ")");
				if (type != varType.UNKNOWN){
					content = null;
				}
				VarOperation operation= new VarOperation(variableType, type,content,name);
				vars.add(operation);
			}
			pattern = Pattern.compile(ASSIGN_WITHOUTH_DEC);
			matcher = pattern.matcher(line);
			while (matcher.find()){
				name = matcher.group(1);
				content = null;
				variableType = varType.UNKNOWN;
				System.out.println("declare " + name + "(type " + type + ")");
				VarOperation operation= new VarOperation(variableType, type,content,name);
				vars.add(operation);
			}
			return new VarDecelaration(isFinal,vars);
		}
		pattern = Pattern.compile(ASSIGNMENT + space + ";" + space);
		matcher = pattern.matcher(line);
		if(matcher.matches()){
			ArrayList<VarOperation> vars = new ArrayList<>();
			System.out.println("this is assignment line");
			boolean isFinal = false;
			String name = matcher.group(1);
			String content = matcher.group(2);
			varType variableType = recognizeType(content);
			if (variableType != varType.UNKNOWN){
				content = null;
			}
			System.out.println("assign " + content + "(type " + variableType + ") to " + name + "(type UNKNOWN");
			VarOperation operation= new VarOperation(variableType,varType.UNKNOWN,content,name);
			vars.add(operation);
			return new VarDecelaration(isFinal,vars);
		}
		pattern = Pattern.compile(RETURN);
		matcher = pattern.matcher(line);
		if (matcher.matches()){
			System.out.println("this is return line");
			return new RawReturn();
		}
		pattern = Pattern.compile(FUNC_CALL);
		matcher = pattern.matcher(line);
		if (matcher.matches()) { // this is function call
			ArrayList<VarInstance> vars = new ArrayList<>();
			System.out.println("this is call to function");
			pattern = Pattern.compile(NAME_VAR_VALDIATION);
			matcher = pattern.matcher(line);
			matcher.find();
			String methodName = matcher.group(0);
			System.out.println("function name is " + methodName);
			System.out.println("the vars you got: ");
			while (matcher.find()){
				String varName = matcher.group(0);
				System.out.println(varName);
				vars.add(new VarInstance(varName,varType.UNKNOWN));
			}
		}
		throw new BadLineException();
	}

	private static varType recognizeType(String content) {
		Pattern pattern = Pattern.compile(INT);
		Matcher matcher = pattern.matcher(content);
		varType type = varType.UNKNOWN;
		if (matcher.matches()){
			type = varType.INT;
		}
		pattern = Pattern.compile(STRING);
		matcher = pattern.matcher(content);
		if (matcher.matches()){
			type = varType.STRING;
		}
		pattern = Pattern.compile(CHAR);
		matcher = pattern.matcher(content);
		if (matcher.matches()){
			type = varType.CHAR;
		}
		pattern = Pattern.compile(BOOLEAN_CONTENT);
		matcher = pattern.matcher(content);
		if (matcher.matches()){
			type = varType.BOOLEAN;
		}
		pattern = Pattern.compile(DOUBLE);
		matcher = pattern.matcher(content);
		if (matcher.matches()){
			type = varType.DOUBLE;
		}
		return type;
	}

	public static RawLine treatOpen (String line) throws BadLineException {
		Pattern pattern = Pattern.compile(CONDITION_LINE);
		Matcher matcher = pattern.matcher(line);
		if (matcher.matches()){ // this is condition
			return treatCondition(line);
		}
		pattern = Pattern.compile(CHECK_FUNC_LINE);
		matcher = pattern.matcher(line);
		if (matcher.matches()) { //this is function deceleration
			return treatFunction(line);
		}
		throw new BadLineException();
	}

	private static OpenFunction treatFunction(String line) {
		System.out.println("this is a function");
		String name;
		Pattern pattern = Pattern.compile(NAME_METHOD_VALDIATION);
		Matcher matcher = pattern.matcher(line);
		matcher.find(); //find void
		matcher.find();// find the name of the function
		name = line.substring(matcher.start(),matcher.end());
		System.out.println("the name of the function is: " + name);
		FunctionObj functionObj = new FunctionObj(name);
		String [] params = line.split("\\(")[1].split(",");
		System.out.println("the vars you got:");
		for (String param: params) {
			boolean isFinal = false;
			String varName;
			MainParser.varType type;
			matcher = pattern.matcher(param);
			matcher.find(); // find the first name
			String first = param.substring(matcher.start(),matcher.end());
			if (first.equals("final")){
				isFinal = true;
				matcher.find(); // if it's final the type is the next instancce
				first = param.substring(matcher.start(),matcher.end());
			}
			type = checkType(first);
			matcher.find(); // the name is the next match
			varName = param.substring(matcher.start(),matcher.end());
			functionObj.addVar(varName,type,isFinal);
			System.out.print("var name: "  + varName + "   var type: " + type + "   is it final? : ");
			System.out.println(isFinal);

		}
		return new OpenFunction(functionObj);
	}

	private static OpenCondition treatCondition(String line) {
		System.out.println("this is condition");
		OpenCondition rawLine = new OpenCondition();
		String splited_line = line.split("\\(")[1];
		Pattern pattern = Pattern.compile(NAME_VAR_VALDIATION);
		Matcher matcher = pattern.matcher(splited_line);
		System.out.println("the names you got:");
		while (matcher.find()){
			int start = matcher.start();
			int end= matcher.end();
			String name = splited_line.substring(start,end);
			if ((!name.equals("true"))&&(!name.equals("false"))) {
				rawLine.addName(name);
				System.out.println(name);
			}
		}
		return rawLine;
	}

	private static varType checkType(String first) {
		varType varType = MainParser.varType.UNKNOWN;
		switch (first){
			case "int":
				varType =  varType.INT;
				break;
			case "String":
				varType = varType.STRING;
				break;
			case "char":
				varType = varType.CHAR;
				break;
			case "boolean":
				varType = varType.BOOLEAN;
				break;
			case "double":
				varType = varType.DOUBLE;
				break;
		}
		return varType;
	}
	public static boolean isCompatible(MainParser.varType arg1, MainParser.varType arg2) {
		if (arg1 == null || arg2 == null || arg1 == MainParser.varType.UNKNOWN || arg2 == MainParser.varType.UNKNOWN)
			return false;
		if (arg1 == MainParser.varType.BOOLEAN)
			return arg2 == MainParser.varType.BOOLEAN || arg2 == MainParser.varType.INT
					|| arg2 == MainParser.varType.DOUBLE;
		if (arg1 == MainParser.varType.DOUBLE)
			return arg2 == MainParser.varType.INT
					|| arg2 == MainParser.varType.DOUBLE;
		return arg1 == arg2;
	}

}
