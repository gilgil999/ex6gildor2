package validator;

import parser.MainParser;

import java.util.ArrayList;

public class VariableLine extends Singleline {


    private boolean isFinal;
    private VarOperation[] operations;

    public VariableLine(boolean isFinal, VarOperation[] operations) {
        this.isFinal = isFinal;
        this.operations = operations;
    }

    @Override
    public boolean isValid(ScopeObj scopeObj) {
        ArrayList<String> tobeadded=new ArrayList<>();
        for (VarOperation operation : operations){

            if(!processOperation(operation,scopeObj,tobeadded))
                return false;
        }
        return true;
    }

    /**
     * this function gets a VarOperation object, a scope and a String arraylist and
     * @param operation
     * @param scopeObj
     * @param tobeadded
     */
    private boolean processOperation(VarOperation operation, ScopeObj scopeObj, ArrayList<String> tobeadded) {
        if(operation.getDesttype()==null){
            //this is an assignment
            //both of the variables needs to be already declared
            if(isFinal)//if there is a final deceleration than you must return false
                return false;
            VarObj source=scopeObj.getVar(operation.getSourcename());
            VarObj dest=scopeObj.getVar(operation.getDestname());
            MainParser.varType desttype;
            MainParser.varType sourcetype;
            if(dest==null)//assigned value not in scope
                return false;
            desttype=dest.getType();
            if(source==null)//
                sourcetype=operation.getSourcetype();
            else
                sourcetype=source.getType();
            if(!isCompatible(desttype,sourcetype))
                return false;
            //now dest is already defined and source is either defined as well or it is a constant, either way they are both compatible
            dest.setAssigned(true);
        }else if(operation.getDestname()==null){
            //this is a de
        }
        return true;
    }
    private static boolean isCompatible(MainParser.varType arg1,MainParser.varType arg2){
        if(arg1==null||arg2==null)
            return false;
        if(arg1==MainParser.varType.BOOLEAN)
            return arg2==MainParser.varType.BOOLEAN||arg2==MainParser.varType.INT
                    ||arg2==MainParser.varType.DOUBLE;
        if(arg1==MainParser.varType.DOUBLE)
            return arg2==MainParser.varType.INT
                    ||arg2==MainParser.varType.DOUBLE;
        return arg1==arg2;
    }
}
