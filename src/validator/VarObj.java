package validator;

import parser.MainParser;

public class VarObj {
    private String name;
    private MainParser.varType type;
    private boolean isAssigned;
    private boolean isFinal;
    private boolean overridable;

    public VarObj(String name, MainParser.varType type, boolean isAssigned, boolean isFinal, boolean overridable) {
        this.name = name;
        this.type = type;
        this.isAssigned = isAssigned;
        this.isFinal = isFinal;
        this.overridable=overridable;
    }

    public VarObj(VarObj tocopy){
        this.name=tocopy.getName();
        this.type = tocopy.getType();
        this.isAssigned= tocopy.isAssigned();
        this.isFinal=tocopy.isFinal();
        this.overridable=true;//refers to the case where a new segment is created
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

    public boolean isOverridable() {
        return overridable;
    }
}
