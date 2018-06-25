package validator;

import java.util.ArrayList;

public class ScopeObj {
    /**
     * this class represents a scope, as one, it has to contain a list of variables and a list of fundtions, in addition
     * to a boolean field stating whether the scope is inside a fucntion or not
     */
    //may be some other datastructure

    private ArrayList<VarObj> vars;
    private ArrayList<FunctionObj> funcs;
    private boolean isFunction;


    public ScopeObj() {
        this.vars=new ArrayList<>();
        this.funcs=new ArrayList<>();
        this.isFunction=false;
    }

    /**
     * constructs a copy of tocopy
     *
     * @param tocopy
     */
    public ScopeObj(ScopeObj tocopy) {
        ArrayList<VarObj> tocopyvars = tocopy.vars;
        ArrayList<FunctionObj> tocopyfuncs = tocopy.funcs;

        int varlen = tocopyvars.size();
        int funclen = tocopyfuncs.size();

        ArrayList<VarObj> newvars = new ArrayList<>();
        ArrayList<FunctionObj> newfuncs = new ArrayList<>();

        for (int i = 0; i < varlen; i++) {
            newvars.add(new VarObj(tocopyvars.get(i)));
        }
        for (int i = 0; i < funclen; i++) {
            newfuncs.add(new FunctionObj(tocopyfuncs.get(i)));
        }

        this.vars = newvars;
        this.funcs = newfuncs;
        this.isFunction = tocopy.isFunction;
    }

    public boolean isFunction() {
        return isFunction;
    }

    public void setFunction(boolean function) {
        isFunction = function;
    }

    /**
     * finds and returns the FunctionObj with the name matching the funcname.
     *
     * @param funcname
     * @return
     */
    public FunctionObj getFunction(String funcname) {
        for (FunctionObj func : funcs) {
            if (func.getName().equals(funcname))
                return func;
        }
        return null;
    }


    /**
     * finds and returns the VarObj with the name matching to varname
     *
     * @param varname
     * @return the matching varObj with the name given
     */
    public VarObj getVar(String varname) {
        //supports varname ==null
        for (VarObj var : vars) {
            if (var.getName().equals(varname))
                return var;
        }
        return null;
    }


    /**
     * updating the scope with an array of parameters, for each it will either override of add to the scope.
     *
     * @param parameters
     */
    public void update(VarObj[] parameters) {
        for (int i = 0; i < parameters.length; i++) {
            update(parameters[i]);

        }
    }
    public void update(ArrayList<VarObj> parameters){
        for (int i=0;i<parameters.size();i++){
            update(parameters.get(i));

        }
    }

    public ArrayList<FunctionObj> getFuncs() {
        return funcs;
    }

    /**
     * updates the currnet scope with the variable parameter, if there is already a variable with that name in the scope
     * then it will be overridden. otherwise it will be added to the current scope.
     *
     * @param parameter
     */
    public void update(VarObj parameter) {
        for (VarObj var : vars) {
            if (var.getName().equals(parameter.getName())) {
                vars.remove(var);
                vars.add(parameter);
                return;
            }

        }
        vars.add(parameter);
    }

    public void addFundtion(FunctionObj func){
        this.funcs.add(func);
    }
}
