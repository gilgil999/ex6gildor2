package parser;

import validator.*;

import java.util.ArrayList;

public class VarDecelaration implements RawLine {
    private boolean isFinal;
    private ArrayList<VarOperation> operations;

    public VarDecelaration(boolean isFinal, ArrayList<VarOperation> operations) {
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
