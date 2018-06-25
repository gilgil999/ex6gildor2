package validator;

import parser.MainParser;
import parser.TypeOneException;

import java.util.ArrayList;

public class FuncCall extends Singleline {

    private String funcname;
    private ArrayList<VarInstance> params;


    @Override
    public boolean isValid(ScopeObj scopeObj) throws TypeOneException {
        if(!scopeObj.isFunction())
            throw new TypeOneException();
        //checking if the function exists in the scope
        FunctionObj thisfunc = scopeObj.getFunction(funcname);
        if (thisfunc == null) {
            return false;
        }

        ArrayList<VarObj> expectedParams = thisfunc.getParameters();

        //for every parameter, check if it is in the scope and whether it is in the
        //correct type
        for (int i = 0; i < params.size(); i++) {
            MainParser.varType type = CodeSegment.getVarInstanceType(params.get(i), scopeObj);
            if (thisfunc.getParameters().get(i) == null) {
                /////System.out.println("thisfunc has less paramentes than given");
                return false;
            }
            if (MainParser.isCompatible(thisfunc.getParameters().get(i).getType(), type)) {
                /////System.out.println("this var is comopatible");
            }


        }
        return true;
    }

    public FuncCall(String funcname, ArrayList<VarInstance> params) {
        this.funcname = funcname;
        this.params = params;
    }
}
