package parser;

import validator.CodeSegment;

public class CloseLine implements RawLine {
    @Override
    public boolean isClosed() {
        return true;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public CodeSegment transfom() {
        //todo
        return null;
    }
}
