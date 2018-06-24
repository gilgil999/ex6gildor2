package validator;

import parser.MainParser;

import java.util.*;


public abstract class CodeSegment implements Checkable {

    protected ArrayList<Checkable> children;
    protected CodeSegment parent;


    @Override
    public boolean isValid(ScopeObj scopeObj) {
        //maybe we can check the validity before iterating over the
        System.out.println("i dont know what to do here");
        return true;
    }

    public CodeSegment(ArrayList<Checkable> children, CodeSegment parent) {
        this.children = children;
        this.parent = parent;
    }

    public CodeSegment(ArrayList<Checkable> children) {
        this.children = children;
    }

    public CodeSegment() {
        this.children = new ArrayList<Checkable>();

    }

    public CodeSegment getParent() {
        return parent;
    }

    public void addLine(Checkable line) {
        this.children.add(line);
    }


    public void setParent(CodeSegment parent) {
        this.parent = parent;
    }

    public static MainParser.varType getVarInstanceType(VarInstance var, ScopeObj scopeObj) {
        if (var == null) {
            System.out.println("var is null");
            return null;
        }
        MainParser.varType type;
        VarObj varObj = scopeObj.getVar(var.getName());
        if (varObj == null) {
            System.out.println("var is not in scope");
            type = var.getType();
        } else {
            System.out.println("var is in scope");
            type = varObj.getType();
        }
        return type;

    }

}
