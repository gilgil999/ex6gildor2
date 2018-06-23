package validator;

import java.util.ArrayList;

public class ScopeObj {
    //may be some other datastructure

    private ArrayList<VarObj> vars;
    private ArrayList<FunctionObj> funcs;
    private boolean isFunction;

    public ScopeObj(ArrayList<VarObj> vars, ArrayList<FunctionObj> funcs, boolean isFunction) {
        this.vars = vars;
        this.funcs = funcs;
        this.isFunction=isFunction;
    }

    /**
     * constructs a copy of tocopy
     * @param tocopy
     */
    public ScopeObj(ScopeObj tocopy) {
        ArrayList<VarObj> tocopyvars=tocopy.vars;
        ArrayList<FunctionObj> tocopyfuncs = tocopy.funcs;

        int varlen=tocopyvars.size();
        int funclen=tocopyfuncs.size();

        ArrayList<VarObj> newvars =new ArrayList<>();
        ArrayList<FunctionObj> newfuncs =new ArrayList<>();

        for(int i=0; i<varlen;i++){
            newvars.add(new VarObj(tocopyvars.get(i)));
        }
        for(int i=0;i<funclen;i++){
            newfuncs.add(new FunctionObj(tocopyfuncs.get(i)));
        }

        this.vars=newvars;
        this.funcs=newfuncs;
        this.isFunction=tocopy.isFunction;
    }

    public boolean isFunction() {
        return isFunction;
    }

    public void setFunction(boolean function) {
        isFunction = function;
    }

    /**
     * finds and returns the FunctionObj with the name matching the funcname.
     * @param funcname
     * @return
     */
    public FunctionObj getFunction(String funcname){
        for(FunctionObj func : funcs){
            if(func.getName().equals(funcname))
                return func;
        }
        return null;
    }


    /**
     * finds and returns the VarObj with the name matching to varname
     * @param varname
     * @return the matching varObj with the name given
     */
    public VarObj getVar(String varname){
        for(VarObj var : vars){
            if(var.getName().equals(varname))
                return var;
        }
        return null;
    }

    /**
     * updating the scope with an array of parameters, for each it will either override of add to the scope.
     * @param parameters
     */
    public void update(VarObj[] parameters) {
        for (int i=0;i<parameters.length;i++){
            update(parameters[i]);

        }
    }

    /**
     * updates the currnet scope with the variable parameter, if there is already a variable with that name in the scope
     * then it will be overridden. otherwise it will be added to the current scope.
     * @param parameter
     */
    private void update(VarObj parameter) {
        for (VarObj var : vars){
            if(var.getName().equals(parameter.getName())){
                vars.remove(var);
                vars.add(parameter);
                return;
            }
        }
        vars.add(parameter);
    }
}
