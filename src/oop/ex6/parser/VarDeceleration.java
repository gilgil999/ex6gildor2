package oop.ex6.parser;

import oop.ex6.validator.*;

import java.util.ArrayList;

public class VarDeceleration implements RawLine {
    /**
     * this class is a rawline representation of a variable deceleration\assignment line
     */
    private boolean isFinal;
    private ArrayList<VarOperation> operations;

    public VarDeceleration(boolean isFinal, ArrayList<VarOperation> operations) {
        this.isFinal = isFinal;
        this.operations = operations;
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
        return new VariableLine(isFinal,operations);
    }
}
