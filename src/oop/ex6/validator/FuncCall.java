package oop.ex6.validator;

import oop.ex6.parser.MainParser;
import oop.ex6.parser.TypeOneException;

import java.util.ArrayList;

public class FuncCall extends Singleline {
    /**
     * this class represents a function call, it contains the name of the function it intends to call and a list of the
     * given paraments to pass to the function
     */
    private String funcname;
    private ArrayList<VarInstance> params;


    @Override
    public void isValid(ScopeObj scopeObj) throws TypeOneException {
        if (!scopeObj.isFunction())//function calls can only take place inside functions
            throw new TypeOneException();
        //checking if the function exists in the scope
        FunctionObj thisfunc = scopeObj.getFunction(funcname);
        if (thisfunc == null) {
//            return false;
            throw new TypeOneException();

        }

        ArrayList<VarObj> expectedParams = thisfunc.getParameters();

        //for every parameter, check if it is in the scope and whether it is in the
        //correct type
        if (params.size() != expectedParams.size())
            throw new TypeOneException();
        for (int i = 0; i < params.size(); i++) {
            MainParser.varType type;
            VarObj scopevar = scopeObj.getVar(params.get(i).getName());
            if (scopevar == null) {
                type = params.get(i).getType();
            } else {
                if (scopevar.isAssigned() == false)
//                    return false;
                    throw new TypeOneException();


                type = scopevar.getType();
            }
            if (expectedParams.get(i) == null) {
//                return false;
                throw new TypeOneException();

            }

            if (!MainParser.isCompatible(expectedParams.get(i).getType(), type)) {
//                return false;
                throw new TypeOneException();

            }


        }
//        return true;
    }

    public FuncCall(String funcname, ArrayList<VarInstance> params) {
        this.funcname = funcname;
        this.params = params;
    }
}
