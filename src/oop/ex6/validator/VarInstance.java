package oop.ex6.validator;

import oop.ex6.parser.MainParser;

public class VarInstance {
    /**
     * this class represents a variable instance, which is a representation of a variable outside the context of
     * a specific scope. its fields are a name and a type
     */
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
