package validator;

public class ScopeObj {
    //may be some other datastructure
    private VarObj[] vars;
    private FunctionObj[] funcs;

    public FunctionObj getFunction(String funcname){
        for(FunctionObj func : funcs){
            if(func.getName().equals(funcname))
                return func;
        }
        return null;
    }

    public VarObj getVar(String varname){
        for(VarObj var : vars){
            if(var.getName().equals(varname))
                return var;
        }
        return null;
    }
}
