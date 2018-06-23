package validator;

import parser.MainParser;

public class FuncCall extends Singleline {

    private String funcname;
    private String[] paramnames;


    @Override
    public boolean isValid(ScopeObj scopeObj) {
        //checking if the function exists in the scope
        FunctionObj thisfunc = scopeObj.getFunction(funcname);
        if(thisfunc==null){
            return false;
        }

        VarObj[] expectedParams = thisfunc.getParameters();

        //for every parameter, check if it is in the scope and whether it is in the
        //correct type
        for(int i=0; i<paramnames.length; i++){
            VarObj currvar = scopeObj.getVar(paramnames[i]);
            if(currvar==null){
                return false;
            }
            if(expectedParams[i].getType()!=currvar.getType())
                return false;
            if(!expectedParams[i].getName().equals(currvar.getName()))
                return false;

        }
        return true;
    }

    public FuncCall(String funcname, String[] paramnames) {
        this.funcname = funcname;
        this.paramnames = paramnames;
    }
}
