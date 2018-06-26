package parser;

import validator.Checkable;
import validator.FuncCall;
import validator.VarInstance;

import java.util.ArrayList;

public class RawFuncCall implements RawLine {
    /**
     * this class is a representation of a function call line, as one, it holds the parameters passed and the functions
     * detatils.
     */
    private String funcname;
    private ArrayList<VarInstance> params;

    public RawFuncCall(String funcname, ArrayList<VarInstance> params) {
        this.funcname = funcname;
        this.params = params;
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public Checkable transfom() {
        return new FuncCall(funcname,params);

    }
}
