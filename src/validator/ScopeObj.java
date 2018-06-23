package validator;

public class ScopeObj {
    //may be some other datastructure
    private VarObj[] vars;
    private FunctionObj[] funcs;


    public ScopeObj(VarObj[] vars, FunctionObj[] funcs) {
        this.vars = vars;
        this.funcs = funcs;
    }

    public ScopeObj(ScopeObj tocopy) {
        VarObj[] tocopyvars=tocopy.vars;
        FunctionObj[] tocopyfuncs = tocopy.funcs;

        int varlen=tocopyvars.length;
        int funclen=tocopyfuncs.length;

        VarObj[] newvars =new VarObj[varlen];
        FunctionObj[] newfuncs =new FunctionObj[funclen];

        for(int i=0; i<varlen;i++){
            newvars[i]=new VarObj(tocopyvars[i]);
        }
        for(int i=0;i<funclen;i++){
            newfuncs[i]=new FunctionObj(tocopyfuncs[i]);
        }

        this.vars=newvars;
        this.funcs=newfuncs;

    }

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
