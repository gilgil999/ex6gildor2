package validator;

import parser.MainParser;

public class FunctionObj {
    private VarObj[] parameters;
    private String name;


    public FunctionObj(VarObj[] parameters, String name) {
        this.parameters = parameters;
        this.name = name;
    }

    public FunctionObj(FunctionObj tocopy){
        this.name= tocopy.name;
        int len =tocopy.getParameters().length;
        this.parameters=new VarObj[len];
        for(int i=0;i<len;i++){
            this.parameters[i]=new VarObj(tocopy.parameters[i]);
        }

    }

    public VarObj[] getParameters() {
        return parameters;
    }

    public String getName() {
        return name;
    }


}
