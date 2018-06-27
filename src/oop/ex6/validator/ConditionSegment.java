package oop.ex6.validator;

import oop.ex6.parser.MainParser;

import java.util.ArrayList;

import static oop.ex6.parser.MainParser.isCompatible;

public class ConditionSegment extends CodeSegment {
    /**
     * this class is a condition segment, a code that begins with a condition, if or while
     * its additional member is a list of VarInstance objects that compose the condition, they all sould match  a
     * boolean value
     */


    private ArrayList<VarInstance> condition;

    public ConditionSegment(ArrayList<VarInstance> params){
        this.condition=params;
    }

    /**
     * is valid first check if all of the variables in the condition are valid and in the scope/are constants
     * compatible with boolean
     * it then proceeds the generic validity check of all of its lines
     * @param scopeObj
     * @return
     */
    @Override

    public void isValid(ScopeObj scopeObj) throws TypeOneException {

        if(!scopeObj.isFunction())//first checks if the segment is in a function, if not it will raise error
            throw new SyntaxException("the condition segment is not inside a function");


        for(VarInstance var : condition){//then it will check if the variable matches with a boolean value
            if(!checkvar(var,scopeObj))
                throw new SyntaxException("variable is not defined or is not compatible with boolean");
        }
        ScopeObj myscope= new ScopeObj(scopeObj);

        for (Checkable line : this.children){//then proceed to checking the validity of the children
            line.isValid(myscope);
        }
    }

    /**
     * this function check whether the given varinstace is compatible as a boolean argument
     * if it is in the scope then the type of the variable in the scope should be compatible with boolean
     * if it isnt, then maybe it is a constant, if so then the type is specified in the type field of the varinstance
     * @param var
     * @param scopeObj
     * @return
     */
    private boolean checkvar(VarInstance var, ScopeObj scopeObj) {
        VarObj scopevar= scopeObj.getVar(var.getName());
        MainParser.varType type;
        if(scopevar==null){
            type=var.getType();
        }else {
            if (!scopevar.isAssigned())
                return false;
            type = scopevar.getType();
        }
//        MainParser.varType type = CodeSegment.getVarInstanceType(var, scopeObj);

        return isCompatible(MainParser.varType.BOOLEAN, type);
    }
}
