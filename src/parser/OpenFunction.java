package parser;

import validator.CodeSegment;

public class OpenFunction implements RawLine {
    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public CodeSegment transfom() {
        //todo
        return null;
    }
}
