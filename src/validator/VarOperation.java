package validator;

import parser.MainParser;

public class VarOperation {
    private MainParser.varType sourcetype;
    private MainParser.varType desttype;
    private String sourcename;
    private String destname;

    public VarOperation(MainParser.varType sourcetype, MainParser.varType desttype, String sourcename, String destname) {
        this.sourcetype = sourcetype;
        this.desttype = desttype;
        this.sourcename = sourcename;
        this.destname = destname;
    }

    public MainParser.varType getSourcetype() {
        return sourcetype;
    }

    public MainParser.varType getDesttype() {
        return desttype;
    }

    public String getSourcename() {
        return sourcename;
    }

    public String getDestname() {
        return destname;
    }
}
