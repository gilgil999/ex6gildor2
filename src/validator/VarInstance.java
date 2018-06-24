package validator;

import parser.MainParser;

public class VarInstance {
    protected String name;
    protected MainParser.varType type;

    public VarInstance(String name, MainParser.varType type) {
        this.name = name;
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public MainParser.varType getType() {
        return type;
    }

}
