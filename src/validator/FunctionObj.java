package validator;

import parser.MainParser;

public class FunctionObj {
    private VarObj[] parameters;
    private String name;

    public FunctionObj(VarObj[] parameters) {
        this.parameters = parameters;
    }

    public VarObj[] getParameters() {
        return parameters;
    }

    public String getName() {
        return name;
    }
}
