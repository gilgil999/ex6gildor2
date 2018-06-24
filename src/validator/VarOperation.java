package validator;

import parser.MainParser;

public class VarOperation {
    private VarInstance source;
    private VarInstance destination;
    public VarOperation(MainParser.varType sourcetype, MainParser.varType desttype, String sourcename, String destname) {
        this.source=new VarInstance(sourcename,sourcetype);
        this.destination=new VarInstance(destname,desttype);
    }

    public VarOperation(VarInstance source, VarInstance destination) {
        this.source = source;
        this.destination = destination;
    }

    public VarInstance getSource() {
        return source;
    }

    public VarInstance getDestination() {
        return destination;
    }
}
