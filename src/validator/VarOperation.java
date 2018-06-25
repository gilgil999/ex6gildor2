package validator;

import parser.MainParser;

public class VarOperation {
    /**
     * this fucntion represents an assignment or a decelaration of two variables, where destination is the variable \
     * being assigned and source contains the value
     */
    private VarInstance source;
    private VarInstance destination;

    public VarOperation(MainParser.varType sourcetype, MainParser.varType desttype, String sourcename, String destname) {
        this.source=new VarInstance(sourcename,sourcetype);
        this.destination=new VarInstance(destname,desttype);
    }

    public VarInstance getSource() {
        return source;
    }

    public VarInstance getDestination() {
        return destination;
    }
}
