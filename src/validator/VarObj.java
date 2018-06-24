package validator;

import parser.MainParser;

public class VarObj extends VarInstance{

    private boolean isAssigned;
    private boolean isFinal;
    private boolean overridable;

    public VarObj(String name, MainParser.varType type, boolean isAssigned, boolean isFinal, boolean overridable) {
        super(name,type);
        this.isAssigned = isAssigned;
        this.isFinal = isFinal;
        this.overridable=overridable;
    }

    public VarObj(VarObj tocopy){
        super(tocopy.getName(),tocopy.getType());
        this.isAssigned= tocopy.isAssigned();
        this.isFinal=tocopy.isFinal();
        this.overridable=true;//refers to the case where a new segment is created

    }

    public VarObj(String name, MainParser.varType type){
        super(name, type);
        this.isAssigned = false;
        this.isFinal = false;//arbitrary initial value
        this.overridable=false;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

    public void setOverridable(boolean overridable) {
        this.overridable = overridable;
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
