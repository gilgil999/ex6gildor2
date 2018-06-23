package parser;

import validator.CodeSegment;
import validator.GlobalSegment;

public class MainParser {
//needs to be singelton

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
        }
        return parsedlines;

    }

    public static RawLine readline(String line) {
        //todo bom

        return null;
    }

}
