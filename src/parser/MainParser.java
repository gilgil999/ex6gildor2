package parser;

import validator.CodeSegment;
import validator.GlobalSegment;

public class MainParser {
//needs to be singelton

    public enum varType {DOUBLE, INT, STRING, CHAR, BOOLEAN,UNKNOWN}

    public static GlobalSegment parse(String[] lines) {
        RawLine[] parsedlines = Readlines(lines);


        GlobalSegment globalSegment = new GlobalSegment();
        int len=parsedlines.length;
        CodeSegment currentParent=globalSegment;
        for (int i=0; i<len; i++){
            if(parsedlines[i].isOpen()){
                //if a new codesegment is being created
                CodeSegment newparent = parsedlines[i].transfom();
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
