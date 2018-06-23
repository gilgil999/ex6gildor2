package parser;

import validator.CodeSegment;
import validator.VarOperation;

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
    public CodeSegment transfom() {
        return null;
    }
}
