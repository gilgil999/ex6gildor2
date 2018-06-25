package validator;
import java.util.ArrayList;

import parser.MainParser;

public class FunctionObj {
    /**
     * this class is a representation of a fucntion object, as one, it has a list of parameters and a name
     */
    private ArrayList<VarObj> parameters;
    private String name;


    public FunctionObj(ArrayList<VarObj> parameters, String name) {
        this.parameters = parameters;
        this.name = name;
    }
	public FunctionObj(String name) {
    	this.parameters = new ArrayList<VarObj>();
		this.name = name;
	}

    public FunctionObj(FunctionObj tocopy){
        this.name= tocopy.name;
        int len =tocopy.getParameters().size();
        this.parameters=new ArrayList<VarObj>();
        for(int i=0;i<len;i++){
            this.parameters.add(new VarObj(tocopy.parameters.get(i)));
        }

    }
	public void addVar(String name, MainParser.varType type, boolean isFinal){
		VarObj newVar = new VarObj(name,type,true,isFinal,false);
		parameters.add(newVar);
	}

    public ArrayList<VarObj> getParameters() {
        return parameters;
    }

    public String getName() {
        return name;
    }


}
