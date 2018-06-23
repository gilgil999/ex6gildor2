package parser;

import org.omg.CORBA.PRIVATE_MEMBER;
import validator.CodeSegment;
import validator.GlobalSegment;

public class MainParser {
//needs to be singelton
	private final String space = "\\s*";
	private final String FINAL = "(?:final\\s+)";
	private final String STRING_CONTENT = "\"\\S*\"";
	private final String BOOLEAN_CONTENT = "(?:true|false)";
	private final String VALID_NUMBER = "(?:\\d+(?:.\\d+)?)";
	private final String NAME_VAR_VALDIATION = "(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)";
	private final String CONTENT =  "(?:" + VALID_NUMBER + "|" + BOOLEAN_CONTENT + "|" + STRING_CONTENT + "|" + NAME_VAR_VALDIATION + ")"; // (?:(?:\d+(?:.\d+)?)|(?:true|false)|"\S*")
	private final String CHECK_TYPE ="(?:int|String|char|double|boolean)";
	private final String NAME_METHOD_VALDIATION = "[a-zA-Z]+[a-zA-Z0-9_]*";
	private final String CHECK_IF_WHILE = "(?:if/while)";
	private final String CHECK_CONDITION = "(?:(?:\\s)*(?:true|false|"+NAME_VAR_VALDIATION+"|" + VALID_NUMBER+"))";
	private final String PARAMS = "(?:" + space+ CHECK_TYPE + space + NAME_VAR_VALDIATION + space + ")"; // (?:\s*(?:int|String|char|double|boolean)\s*(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*)
	private final String CHECK_PARAM ="(?:[(]" + PARAMS + "*" + PARAMS + "[)]"+space+"{)"; // (?:[(](?:(?:\s*(?:int|String|char|double|boolean)\s*(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*),)*(?:\s*(?:int|String|char|double|boolean)\s*(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*)[)]\s*{)
	private final String ASSIGNMENT = "(?:" + space + NAME_VAR_VALDIATION + space + "|" + space
										+ NAME_VAR_VALDIATION + space + "=" + space + CONTENT + space + ")"; //(?:(?:\s*_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*\s*=\s*(?:(?:\d+(?:.\d+)?)|(?:true|false)|"\S*")\s*)|(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*\s*))
	private final String CHECK_ASSIGNMENT = FINAL + "?" + "\\s+" + CHECK_TYPE + "\\s+" + "(?:(?:" + ASSIGNMENT + ",\\s*)*" + "(?:" + ASSIGNMENT + ";)";






    public static enum varType {DOUBLE, INT, STRING, CHAR, BOOLEAN}

    ;

    public static GlobalSegment parse(String[] lines) {
        RawLine[] parsedlines = Readlines(lines);


        GlobalSegment globalSegment = new GlobalSegment();
        int len=parsedlines.length;
        CodeSegment currentParent=globalSegment;
        for (int i=0; i<len; i++){

        }

        return null;
    }

    private static RawLine[] Readlines(String[] lines) {
        int len = lines.length;
        RawLine[] parsedlines=new RawLine[len];
        for (int i=0; i<lines.length; i++){
            parsedlines[i]=readline(lines[i]);
            int a;

        }
        return parsedlines;

    }

    public static RawLine readline(String line) {
        //todo bom

        return null;
    }

}
=