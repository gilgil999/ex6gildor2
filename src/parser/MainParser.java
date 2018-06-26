package parser;

import validator.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainParser {
    /**
     * this class does all of the parsing of the files lines, it then converts it to a Checkable object
     */
    /*CONSTANTS-REGEX*/

    /*GENERAL_CONSTANTS*/
    private static final String INT = "-?\\s?\\d+";
    private static final String STRING = "\"[^,\'\"\\\\]*\"";
    private static final String CHAR = "\'[^,\'\"\\\\]?\'";
    private static final String DOUBLE = "-?\\s?\\d+\\.\\d+";
    private static final String space = "\\s*";
    private static final String FINAL = "(?:final\\s+)";
    private static final String BOOLEAN_CONTENT = "(?:true|false)";
    private static final String IF_WHILE = "(?:if|while)";
    private static final String OPEN_FINISH = ".*(?:\\{\\s*)$";
    private static final String CLOSE = "\\s*}\\s*";
    private static final String SINGLE_LINE = ".*(?:;\\s*)$";
    private static final String VALID_NUMBER = "(?:-?\\s?\\d+(?:\\.\\d+)?)";
    private static final String RETURN = space + "return\\s*;" + space;

    /*BASIC EXPRESSIONS*/
    public static final String NAME_VAR_VALDIATION = "(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)";
    public static final String CONTENT = "(?:" + VALID_NUMBER + "|" + CHAR + "|" + BOOLEAN_CONTENT + "|" + STRING + ")"; // (?:(?:\d+(?:.\d+)?)|(?:true|false)|"\S*"|(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*))
    private static final String CHECK_TYPE = "(?:int|String|char|double|boolean)";
    private static final String CONDITION = "(?:" + VALID_NUMBER + "|" + BOOLEAN_CONTENT + "|" + NAME_VAR_VALDIATION + ")"; // (?:(?:\d+(?:.\d+)?)|(?:true|false)|(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*))
    private static final String CONDITION_LINE = "(?:" + space + IF_WHILE + space + "\\((?:" + space + CONDITION + space + "(?:\\|\\||&&))*" + space + CONDITION + space + "\\)" + space + "\\{" + space + ")"; // (?:\s*(?:if|while)\s*\((?:\s*(?:(?:\d+(?:.\d+)?)|(?:true|false)|(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*))\s*(?:\|\||&&))*\s*(?:(?:\d+(?:.\d+)?)|(?:true|false)|(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*))\s*\)\s*{\s*)
    private static final String NAME_METHOD_VALDIATION = "[a-zA-Z]+[a-zA-Z0-9_]*";

    /*COMPLEX LINES*/
    private static final String PARAMS = "(?:" + space + FINAL + "?" + space + CHECK_TYPE + "\\s+" + NAME_VAR_VALDIATION + space + ")"; // (?:\s*(?:int|String|char|double|boolean)\s*(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*)
    private static final String CHECK_PARAM = "(?:[(](?:" + PARAMS + ",)*" + PARAMS + "?\\s*[)]" + space + "\\{\\s*)"; // (?:[(](?:(?:\s*(?:final\s+)?\s*(?:int|String|char|double|boolean)\s+(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*),)*(?:\s*(?:final\s+)?\s*(?:int|String|char|double|boolean)\s+(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*)[)]\s*\{\s*)
    private static final String CHECK_FUNC_LINE = "(?:" + space + "void" + "\\s+" + NAME_METHOD_VALDIATION + space + CHECK_PARAM + ")"; // (?:\s*(?:void|(?:int|String|char|double|boolean))\s+[a-zA-Z]+[a-zA-Z0-9_]*\s*(?:[(](?:(?:\s*(?:int|String|char|double|boolean)\s+(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*),)*(?:\s*(?:int|String|char|double|boolean)\s+(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*)[)]\s*\{\s*))
    private static final String ASSIGNMENT = "(?:" + space + "(" + NAME_VAR_VALDIATION + ")" + space + "=" + space + "(" + CONTENT + "|" + NAME_VAR_VALDIATION + ")" + space + ")"; //(?:\s*(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*=\s*(?:(?:\d+(?:.\d+)?)|(?:true|false)|"\S*"|(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*))\s*)
    private static final String CHECK_ASSIGNMENT = space + FINAL + "?" + space + CHECK_TYPE + "\\s+" + "(?:(?:(?:\\s*" + ASSIGNMENT + "\\s*|" + NAME_VAR_VALDIATION + "\\s*),\\s*)*\\s*" + "(?:\\s*" + ASSIGNMENT + "\\s*|\\s*" + NAME_VAR_VALDIATION + "\\s*);\\s*)"; // (?:final\s+)?\s*(?:int|String|char|double|boolean)\s+(?:(?:(?:\s*(?:\s*(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*=\s*(?:(?:\d+(?:.\d+)?)|(?:true|false)|"\S*"|(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*))\s*)\s*|(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*),)*\s*(?:\s*(?:\s*(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*=\s*(?:(?:\d+(?:.\d+)?)|(?:true|false)|"\S*"|(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*))\s*)\s*|\s*(?:_[a-zA-Z0-9_]+|[a-zA-Z]+[a-zA-Z0-9_]*)\s*);\s*)
    private static final String ASSIGN_WITHOUTH_DEC = space + ",?(" + NAME_VAR_VALDIATION + ")" + space + "(?:,|;)";
    private static final String FUNC_CALL = space + NAME_METHOD_VALDIATION + space + "\\((?:" + space + NAME_VAR_VALDIATION + space + "|" + space + CONTENT + space + ",)*" + space + "(?:" + NAME_VAR_VALDIATION + "|" + CONTENT + ")?" + space + "\\)" + space + ";" + space;

    /**
     * enum describing the possible types of objects in javas.
     */
    public enum varType {
        DOUBLE, INT, STRING, CHAR, BOOLEAN, UNKNOWN
    }

    /**
     * this function receive ArrayList of Strings and delete extra spaces, empty lines and comment lines
     *
     * @param text the text you want to parse
     * @return the new cleaner text
     */
    public static ArrayList<String> primaryParsing(ArrayList<String> text) {
        ArrayList<String> newText = new ArrayList<String>();
        Pattern pattern = Pattern.compile("[\\s]+");
        Matcher matcher;
        for (String row : text) {
            matcher = pattern.matcher(row);
            row = matcher.replaceAll(" ");
            if (row.equals(" ") || row.equals("") || (row.length() >= 2 && row.substring(0, 2).equals("//"))) {
                continue;
            }
            newText.add(row);

        }
        return newText;
    }

    /**
     * this function takes as input a list of string lines of the file and returns the globalsegment object, it contatins
     * all of the data in the file. if the file cannot be parsed, a TypeOneException will be thrown.
     *
     * @param lines
     * @return
     * @throws TypeOneException
     */
    public static GlobalSegment parse(ArrayList<String> lines) throws TypeOneException {
        RawLine[] parsedlines = readlines(lines);//converting the lines to rawline format

        GlobalSegment globalSegment = new GlobalSegment();//creating the globalsegment

        int len = parsedlines.length;
        CodeSegment currentParent = globalSegment;

        for (int i = 0; i < len; i++) {
            if (parsedlines[i].isOpen()) {
                //if a new codesegment is being created
                CodeSegment newparent = (CodeSegment) parsedlines[i].transfom();
                newparent.setParent(currentParent);
                currentParent.addLine(newparent);
                currentParent = newparent;
            } else if (parsedlines[i].isClosed()) {
                //a codesegment end and we now we go back to adding stuff to its parent
                if (currentParent == null)
                    //we are already in the globalsegment
                    //todo exception
                    throw new TypeOneException();
                    ////System.out.println("a closing without an opening");
                else
                    currentParent = currentParent.getParent();
            } else {
                //adding a singleline to the current codesegment
                currentParent.addLine(parsedlines[i].transfom());
            }
        }

        if (currentParent == null)
            throw new TypeOneException();
        //check if a segment is left open
        if (currentParent.getParent() != null)
            throw new TypeOneException();

        if (globalSegment == currentParent)
            //should apply, not a neccessary check
            return globalSegment;

        throw new TypeOneException();

    }

    /**
     * function that receives array list of (parsed) line Strings and return list of raw line - lines that describe what this line does.
     *
     * @param parsed_text
     * @return
     * @throws BadLineException
     */
    private static RawLine[] readlines(ArrayList<String> parsed_text) throws BadLineException {
        RawLine[] parsedlines = new RawLine[parsed_text.size()];
        int counter = 0;
        for (String row : parsed_text) {
            try {
                RawLine line = readline(row);
                parsedlines[counter] = line;
                counter++;
            } catch (BadLineException exception) {//todo make exception for this
                throw exception;
            }

        }
        return parsedlines;

    }

    /**
     * function that receives String line  and return new raw line - line that describe what this line does.
     *
     * @param line
     * @return
     * @throws BadLineException
     */

    public static RawLine readline(String line) throws BadLineException {
        Pattern pattern = Pattern.compile(OPEN_FINISH);
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            return treatOpen(line);

        }
        pattern = Pattern.compile(CLOSE);
        matcher = pattern.matcher(line);
        if (matcher.matches()) {
            return (new CloseLine());
        }
        pattern = Pattern.compile(SINGLE_LINE);
        matcher = pattern.matcher(line);
        if (matcher.matches()) {
            return treatSingle(line);
        }
        throw new BadLineException();
    }


    /*conceive line that ends with ; and return matching raw line*/
    private static RawLine treatSingle(String line) throws BadLineException {
        Pattern pattern = Pattern.compile(CHECK_ASSIGNMENT);
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) { // this is assignment line (with deceleration)
            ArrayList<VarOperation> vars = new ArrayList<>();
            boolean isFinal = false;
            varType type;
            pattern = Pattern.compile(NAME_METHOD_VALDIATION);
            matcher = pattern.matcher(line);
            matcher.find();
            if (matcher.group(0).equals("final")) {
                isFinal = true;
                matcher.find();
            }
            type = checkType(matcher.group(0));
            line = line.substring(matcher.end());
            pattern = Pattern.compile(ASSIGNMENT);
            matcher = pattern.matcher(line);
            Pattern pattern1 = Pattern.compile(ASSIGN_WITHOUTH_DEC);
            Matcher matcher1 = pattern1.matcher(line);

            String name, content;
            varType variableType;
            while (matcher.lookingAt() || matcher1.lookingAt()) {
                if (matcher.lookingAt()) {//this is assignment
                    matcher.reset();
                    matcher.find();
                    name = matcher.group(1);
                    content = matcher.group(2);
                    variableType = recognizeType(content);
                    if (variableType != varType.UNKNOWN) {
                        content = null;
                    }
                    VarOperation operation = new VarOperation(variableType, type, content, name);
                    vars.add(operation);
                    line = line.substring(matcher.end());
                } else if (matcher1.lookingAt()) {
                    matcher1.reset();
                    matcher1.find();
                    name = matcher1.group(1);
                    content = null;
                    variableType = varType.UNKNOWN;
                    VarOperation operation = new VarOperation(variableType, type, content, name);
                    vars.add(operation);
                    line = line.substring(matcher1.end());
                }
                matcher = pattern.matcher(line);
                matcher1 = pattern1.matcher(line);
            }
            return new VarDeceleration(isFinal, vars);
        }
        pattern = Pattern.compile(ASSIGNMENT + space + ";" + space);
        matcher = pattern.matcher(line);
        if (matcher.matches()) {
            ArrayList<VarOperation> vars = new ArrayList<>();
            boolean isFinal = false;
            String name = matcher.group(1);
            String content = matcher.group(2);
            varType variableType = recognizeType(content);
            if (variableType != varType.UNKNOWN) {
                content = null;
            }
            VarOperation operation = new VarOperation(variableType, varType.UNKNOWN, content, name);
            vars.add(operation);
            return new VarDeceleration(isFinal, vars);
        }
        pattern = Pattern.compile(RETURN);
        matcher = pattern.matcher(line);
        if (matcher.matches()) {
            return new RawReturn();
        }
        pattern = Pattern.compile(FUNC_CALL);
        matcher = pattern.matcher(line);
        if (matcher.matches()) { // this is function call
            ArrayList<VarInstance> vars = new ArrayList<>();
            pattern = Pattern.compile("(?:" + CONTENT + "|" + NAME_VAR_VALDIATION + ")");
            matcher = pattern.matcher(line);
            matcher.find();
            String methodName = matcher.group(0);

            while (matcher.find()) {
                String varName = matcher.group(0);
                varType type = recognizeType(varName);
                if (type == varType.UNKNOWN) {
                    vars.add(new VarInstance(varName, varType.UNKNOWN));
                    continue;
                }
                vars.add(new VarInstance(null, type));
            }
            return new RawFuncCall(methodName, vars);
        }
        throw new BadLineException();
    }

    /*this function receive a String and recognize the type of his content.
     (for example : for the input "4" the function return INT */
    private static varType recognizeType(String content) {
        Pattern pattern = Pattern.compile(INT);
        Matcher matcher = pattern.matcher(content);
        varType type = varType.UNKNOWN;
        if (matcher.matches()) {
            type = varType.INT;
        }
        pattern = Pattern.compile(STRING);
        matcher = pattern.matcher(content);
        if (matcher.matches()) {
            type = varType.STRING;
        }
        pattern = Pattern.compile(CHAR);
        matcher = pattern.matcher(content);
        if (matcher.matches()) {
            type = varType.CHAR;
        }
        pattern = Pattern.compile(BOOLEAN_CONTENT);
        matcher = pattern.matcher(content);
        if (matcher.matches()) {
            type = varType.BOOLEAN;
        }
        pattern = Pattern.compile(DOUBLE);
        matcher = pattern.matcher(content);
        if (matcher.matches()) {
            type = varType.DOUBLE;
        }
        return type;
    }

    /*conceive line that ends with ; and return matching raw line*/
    private static RawLine treatOpen(String line) throws BadLineException {
        Pattern pattern = Pattern.compile(CONDITION_LINE);
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) { // this is condition
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
        String name;
        Pattern pattern = Pattern.compile(NAME_METHOD_VALDIATION);
        Matcher matcher = pattern.matcher(line);
        matcher.find(); //find void
        matcher.find();// find the name of the function
        name = line.substring(matcher.start(), matcher.end());
        FunctionObj functionObj = new FunctionObj(name);
        String[] params = line.split("\\(")[1].split(",");
        for (String param : params) {
            boolean isFinal = false;
            String varName;
            MainParser.varType type;
            matcher = pattern.matcher(param);
            if (!matcher.find()) { // find the first name
                continue;
            }
            String first = param.substring(matcher.start(), matcher.end());
            if (first.equals("final")) {
                isFinal = true;
                matcher.find(); // if it's final the type is the next instancce
                first = param.substring(matcher.start(), matcher.end());
            }
            type = checkType(first);
            matcher.find(); // the name is the next match
            varName = param.substring(matcher.start(), matcher.end());
            functionObj.addVar(varName, type, isFinal);


        }
        return new OpenFunction(functionObj);
    }

    private static OpenCondition treatCondition(String line) {
        OpenCondition rawLine = new OpenCondition();
        String splited_line = line.split("\\(")[1];
        Pattern pattern = Pattern.compile(NAME_VAR_VALDIATION);
        Matcher matcher = pattern.matcher(splited_line);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String name = splited_line.substring(start, end);
            if ((!name.equals("true")) && (!name.equals("false"))) {
                rawLine.addName(name);

            }
        }
        return rawLine;
    }

    private static varType checkType(String first) {
        varType varType = MainParser.varType.UNKNOWN;
        switch (first) {
            case "int":
                varType = varType.INT;
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

    /**
     * this function returns true only if the type of arg2 can be assigned to arg1
     * @param arg1
     * @param arg2
     * @return
     */
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
