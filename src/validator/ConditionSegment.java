package validator;

import parser.MainParser;

import java.util.ArrayList;

import static parser.MainParser.isCompatible;

public class ConditionSegment extends CodeSegment {
    private ArrayList<VarInstance> condition;

    /**
     * is valid first check if all of the variables in the condition are valid and in the scope/are constants
     * compatible with boolean
     * it then proceeds the generic validity check of all of its lines
     * @param scopeObj
     * @return
     */
    @Override

    public boolean isValid(ScopeObj scopeObj) {

        for(VarInstance var : condition){
            if(!checkvar(var,scopeObj))
                return false;
        }
        ScopeObj myscope= new ScopeObj(scopeObj);
        for (Checkable line : this.children){
            if(!line.isValid(myscope))
                return false;
        }

        return true;

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
        MainParser.varType type = CodeSegment.getVarInstanceType(var, scopeObj);
        return isCompatible(MainParser.varType.BOOLEAN, type);
    }
}
