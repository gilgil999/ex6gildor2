package validator;

import parser.MainParser;

public class VarObj {
    private String name;
    private MainParser.varType type;
    private boolean isAssigned;
    private boolean isFinal;

    public VarObj(String name, MainParser.varType type, boolean isAssigned, boolean isFinal) {
        this.name = name;
        this.type = type;
        this.isAssigned = isAssigned;
        this.isFinal = isFinal;
    }

    public String getName() {
        return name;
    }

    public MainParser.varType getType() {
        return type;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public boolean isFinal() {
        return isFinal;
    }
}
