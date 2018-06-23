package parser;

import validator.*;

public class VarDecelaration implements RawLine {
    private boolean isFinal;
    private VarOperation[] operations;

    public VarDecelaration(boolean isFinal, VarOperation[] operations) {
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
