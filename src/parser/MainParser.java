package parser;

import validator.CodeSegment;
import validator.GlobalSegment;
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
	private static final String NAME_VAR_VALDIATION = "(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)";
	private static final String CONTENT =  "(?:" + VALID_NUMBER + "|" + BOOLEAN_CONTENT + "|" + STRING_CONTENT + "|" + NAME_VAR_VALDIATION + ")"; // (?:(?:\d+(?:.\d+)?)|(?:true|false)|"\S*")
	private static final String CHECK_TYPE ="(?:int|String|char|double|boolean)";
	private static final String CONDITION = "(?:" + VALID_NUMBER + "|" + BOOLEAN_CONTENT + "|" + NAME_VAR_VALDIATION + ")"; // (?:(?:\d+(?:.\d+)?)|(?:true|false)|(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*))
	private static final String CONDITION_LINE = "(?:" + space + IF_WHILE + space + "\\((?:"+ space + CONDITION + space + "(?:\\|\\||&&))*" + space + CONDITION + space + "\\)" + space + "\\{" + space + ")"; // (?:\s*(?:if|while)\s*\((?:\s*(?:(?:\d+(?:.\d+)?)|(?:true|false)|(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*))\s*(?:\|\||&&))*\s*(?:(?:\d+(?:.\d+)?)|(?:true|false)|(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*))\s*\)\s*{\s*)
	private static final String NAME_METHOD_VALDIATION = "[a-zA-Z]+[a-zA-Z0-9_]*";
	private static final String CHECK_IF_WHILE = "(?:if/while)";
	private static final String CHECK_CONDITION = "(?:(?:\\s)*(?:true|false|"+NAME_VAR_VALDIATION+"|" + VALID_NUMBER+"))";
	private static final String PARAMS = "(?:" + space+ CHECK_TYPE + space + NAME_VAR_VALDIATION + space + ")"; // (?:\s*(?:int|String|char|double|boolean)\s*(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*)
	private static final String CHECK_PARAM ="(?:[(]" + PARAMS + "*" + PARAMS + "[)]"+space+"{)"; // (?:[(](?:(?:\s*(?:int|String|char|double|boolean)\s*(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*),)*(?:\s*(?:int|String|char|double|boolean)\s*(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*)[)]\s*{)
	private static final String ASSIGNMENT = "(?:" + space + NAME_VAR_VALDIATION + space + "|" + space
										+ NAME_VAR_VALDIATION + space + "=" + space + CONTENT + space + ")"; //(?:(?:\s*_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*\s*=\s*(?:(?:\d+(?:.\d+)?)|(?:true|false)|"\S*")\s*)|(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*\s*))
	private final String CHECK_ASSIGNMENT = FINAL + "?" + "\\s+" + CHECK_TYPE + "\\s+" + "(?:(?:" + ASSIGNMENT + ",\\s*)*" + "(?:" + ASSIGNMENT + ";)";



	/**
	 * this function convert text file to String's array.
	 *
	 * @param path the absolute/relative path of the file.
	 * @return array of strings that consist of the text lines.
	 * @throws IOException - if the given path did not match an actual file to read
	 */
	public static ArrayList<String> fileToText(String path) throws IOException {
		FileReader reader = new FileReader(path); // File not found exception if not file - Type 2 error
		BufferedReader in = new BufferedReader(reader);
		String line;
		ArrayList<String> list = new ArrayList<>();
		while ((line = in.readLine()) != null) { // if there are new lines - add them.
			list.add(line);
		}
		return list;
	}

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
    


    public static GlobalSegment parse(ArrayList<String> lines) {
        RawLine[] parsedlines = Readlines(lines);


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

    private static RawLine[] Readlines(ArrayList<String> parsed_text) {
        RawLine[] parsedlines=new RawLine[parsed_text.size()];
        for (String row:parsed_text){
            try {
				RawLine line = readline(row);
			}catch (Exception exception){//todo make exception for this

			}



        }
        return parsedlines;

    }

    public static RawLine readline(String line) {
		Pattern pattern = Pattern.compile(OPEN_FINISH);
		Matcher matcher = pattern.matcher(line);
		if (matcher.matches()){
			RawLine rawLine = treatOpen(line);
		}
		pattern = Pattern.compile(CLOSE);
		matcher = pattern.matcher(line);
		if (matcher.matches()){
			return (new CloseLine());
		}
		pattern = Pattern.compile(SINGLE_LINE);
		matcher = pattern.matcher(line);
		if (matcher.matches()){
			RawLine rawLine = treatSingle(line);

		}
		//todo if reaches here needs to throw exception of bad lines




        return null;
    }

    private static RawLine treatSingle(String line) {
		return new OpenCondition(); //bullshit just for compilation
	}

	public static RawLine treatOpen(String line) {
		Pattern pattern = Pattern.compile(CONDITION_LINE);
		Matcher matcher = pattern.matcher(line);
		if (matcher.matches()){ // this is condition
			OpenCondition rawLine = new OpenCondition();
			String splited_line = line.split("\\(")[1];
			pattern = Pattern.compile(NAME_VAR_VALDIATION);
			matcher = pattern.matcher(splited_line);
			while (matcher.find()){
				int start = matcher.start();
				int end= matcher.end();
				rawLine.addName(splited_line.substring(start,end));
				System.out.println(splited_line.substring(start,end));
				return rawLine;

			}
		}

	return new OpenCondition(); //bullshit just for compilation
	}

}
